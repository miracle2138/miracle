package com.bamboocloud.oauth.sso.service;

import com.bamboocloud.oauth.sso.core.ServiceException;

import java.util.Map;

public interface IdpAuthService {

	/**
	 * 检查授权是否有效
	 * @param accessToken 授权令牌
	 * @return 令牌有效性
	 */
	boolean checkTokenValid(String accessToken);

	/**
	 * 根据授权码获取授权令牌
	 * @param code 授权码
	 * @return 包含授权信息的键值对
	 * 			access_token用于获取用户信息，
	 * 			expires_in是access_token有效时长，时长在console应用注册时配置。
	 * 			refresh_token可在access_token到期后进行刷新续期，
	 * 			uid为登录用户uid
	 */
	Map<String, Object> getToken(String code);

	/**
	 *	刷新授权
	 * @param refreshToken 续期token
	 * @return 包含授权信息的键值对
	 * 			access_token用于获取用户信息，
	 * 			expires_in是access_token有效时长，时长在console应用注册时配置。
	 * 			refresh_token可在access_token到期后进行刷新续期，
	 * 			uid为登录用户uid
	 */
	Map<String, Object> refreshToken(String refreshToken) throws ServiceException;

	/**
	 * 回收授权
	 * @param accessToken 授权令牌
	 * @return 是否回收成功
	 */
	boolean revokeToken(String accessToken);

    /**
     * 获取用户信息
     * @param accessToken
     * @return
     */
	Map<String, Object> getUserInfo(String accessToken);
}
