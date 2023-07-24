package com.bamboocloud.oauth.sso.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bamboocloud.oauth.sso.core.ServiceException;
import com.bamboocloud.oauth.sso.service.IdpAuthService;
import com.bamboocloud.oauth.sso.utils.Constants;
import com.bamboocloud.oauth.sso.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IdpAuthServiceImpl implements IdpAuthService {

    @Value("${app.bjam.oauth2.idp.head.url}")
    private String headUrl;

    @Value("${app.bjam.oauth2.client.id}")
    private String clientId;

    @Value("${app.bjam.oauth2.client.secret}")
    private String clientSecret;

    private String checkTokenValidUrl = "/checkTokenValid";
    private String getTokenUrl = "/getToken";
    private String refreshTokenUrl = "/refreshToken";
    private String revokeTokenUrl = "/revokeToken";
    private String getUserInfoUrl = "/getUserInfo";


	private Logger logger = LoggerFactory.getLogger(IdpAuthService.class);

	@Override
	public boolean checkTokenValid(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String response = HttpClientUtils.get(headUrl + checkTokenValidUrl, params, null);
        logger.info("checkTokenValid response:" + response);

        JSONObject responseObject = JSONObject.parseObject(response);
        Boolean resultObject = responseObject.getBoolean("result");
        if (resultObject != null) {
            return resultObject.booleanValue();
        }
        String errorCode = responseObject.getString("errcode");
        String errorMsg = responseObject.getString("msg");
        if("2002".equals(errorCode)) {
            return false;
        }
        throw new ServiceException("fail to check token valid,error code = ["+errorCode+"], error message=["+errorMsg+"]");
	}

    @Override
    public Map<String, Object> getToken(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("grant_type", Constants.GrantType.AUTHORIZATION_CODE);
        params.put("code", code);
        params.put("client_secret", clientSecret);
        String response = HttpClientUtils.post(headUrl + getTokenUrl, params, null, null);
        logger.info("getToken response:" + response);

        JSONObject responseObject = JSONObject.parseObject(response);
        String accessToken = responseObject.getString("access_token");
        if (accessToken == null || "".equals(accessToken)) {
            String errorCode = responseObject.getString("errcode");
            String errorMsg = responseObject.getString("msg");
            throw new ServiceException("fail to check token valid,error code = ["+errorCode+"], error message=["+errorMsg+"]");
        }
        Integer expiresIn = responseObject.getInteger("expires_in");
        String refreshToken = responseObject.getString("refresh_token");
        String uid = responseObject.getString("uid");
        Map<String, Object> authorizationInfoMap = new HashMap<String, Object>();
        authorizationInfoMap.put("access_token",accessToken);
        authorizationInfoMap.put("expires_in",expiresIn);
        authorizationInfoMap.put("refresh_token",refreshToken);
        authorizationInfoMap.put("uid",uid);
        return authorizationInfoMap;
    }

    @Override
    public Map<String, Object> refreshToken(String refreshToken) throws ServiceException {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("grant_type", Constants.GrantType.REFRESH_TOKEN);
        params.put("client_secret", clientSecret);
        params.put("refresh_token", refreshToken);
        String response = HttpClientUtils.post(headUrl + refreshTokenUrl, params, null, null);
        logger.info("refreshToken response:" + response);

        JSONObject responseObject = JSONObject.parseObject(response);
        String accessToken = responseObject.getString("access_token");
        if (accessToken == null || "".equals(accessToken)) {
            String errorCode = responseObject.getString("errcode");
            String errorMsg = responseObject.getString("msg");
            if("1025".equals(errorCode)) {
                throw new ServiceException(errorCode);
            }
            throw new ServiceException("fail to check token valid,error code = ["+errorCode+"], error message=["+errorMsg+"]");
        }

        Integer expiresIn = responseObject.getInteger("expires_in");
        refreshToken = responseObject.getString("refresh_token");
        String uid = responseObject.getString("uid");
        Map<String, Object> authorizationInfoMap = new HashMap<String, Object>();
        authorizationInfoMap.put("access_token",accessToken);
        authorizationInfoMap.put("expires_in",expiresIn);
        authorizationInfoMap.put("refresh_token",refreshToken);
        authorizationInfoMap.put("uid",uid);
        return authorizationInfoMap;
    }

    @Override
    public boolean revokeToken(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        String response = HttpClientUtils.get(headUrl + revokeTokenUrl, params, null);
        logger.info("revokeToken response:" + response);

        JSONObject responseObject = JSONObject.parseObject(response);
        Boolean resultObject = responseObject.getBoolean("result");
        if (resultObject != null) {
            return resultObject.booleanValue();
        }
        String errorCode = responseObject.getString("errcode");
        String errorMsg = responseObject.getString("msg");
        throw new ServiceException("fail to check token valid,error code = ["+errorCode+"], error message=["+errorMsg+"]");
    }

    @Override
    public Map<String, Object> getUserInfo(String accessToken) {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("client_id", clientId);
        String response = HttpClientUtils.get(headUrl + getUserInfoUrl, params, null);
        logger.info("getUserInfo response:" + response);

        JSONObject responseObject = JSONObject.parseObject(response);
        Map<String, Object> userInfoMap = new HashMap<String, Object>();
        if (responseObject != null) {
            return responseObject.getInnerMap();
        }
        String errorCode = responseObject.getString("errcode");
        String errorMsg = responseObject.getString("msg");
        throw new ServiceException("fail to check token valid,error code = ["+errorCode+"], error message=["+errorMsg+"]");
    }
}
