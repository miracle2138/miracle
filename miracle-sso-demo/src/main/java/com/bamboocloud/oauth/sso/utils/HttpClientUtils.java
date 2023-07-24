package com.bamboocloud.oauth.sso.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final Integer CONNECTION_TIMEOUT = 5 * 1000; //设置连接超时时间，单位毫秒
    private static final Integer SO_TIMEOUT = 20 * 1000; //请求获取数据的超时时间，单位毫秒
    private static final Integer CONN_MANAGER_TIMEOUT = 500;//设置从connect Manager获取Connection 超时时间，单位毫秒
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;

    private static String remoteIp = "";

    public static String getRemoteIp() {
        return remoteIp;
    }

    public static void setRemoteIp(String remoteIp) {
        HttpClientUtils.remoteIp = remoteIp;
    }

    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
            cm.setDefaultMaxPerRoute(20);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * httpClient post请求
     * @param url 请求url
     * @param params 请求参数 form提交适用
     * @param header 头部信息
     * @param entity 请求实体 json/xml提交适用
     * @return 可能为空 需要处理
     * @throws Exception
     *
     */
    public static String post(String  url, Map<String, String> params, Map<String, String> header, HttpEntity entity) {
        String result = "";
        CloseableHttpClient httpClient = null;
        try {
            httpClient = getHttpClient();
            HttpPost httpPost = new HttpPost(url);
            // 设置头信息
            if (MapUtils.isNotEmpty(header)) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            List<NameValuePair> formParams = new ArrayList<>();
            // 设置请求参数
            if (MapUtils.isNotEmpty(params)) {
                //params.put("remoteIp", remoteIp);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    //给参数赋值
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
                httpPost.setEntity(urlEncodedFormEntity);
            }
            // 设置实体 优先级高
            if (entity != null) {
                httpPost.setEntity(entity);
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            LOGGER.info("创建请求httpPost-URL={},params={}", url, formParams);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                readHttpResponse(httpResponse);
            }
        } catch (Exception e) {
            LOGGER.error("请求发送失败：{}", e.getMessage());
            throw new RuntimeException("请求发送失败，URL:"+url+",params:"+ params);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("关闭HttpClient出错:{}", e.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * httpClient get请求
     * @param url 请求url
     * @param params 请求参数 form提交适用
     * @param header 头部信息
     * @param entity 请求实体 json/xml提交适用
     * @return 可能为空 需要处理
     * @throws Exception
     *
     */
    public static String get(String  url, Map<String, String> params, Map<String, String> header) {
        String result = "";
        CloseableHttpClient httpClient = null;
        try {
            HttpGet httpGet = null;
            List<NameValuePair> formParams = new ArrayList<>();
            // 设置请求参数
            if (MapUtils.isNotEmpty(params)) {
                //params.put("remoteIp", remoteIp);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    //给参数赋值
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
                url += "?"+EntityUtils.toString(urlEncodedFormEntity);
                httpGet = new HttpGet(url);
            }
            // 设置头信息
            if (MapUtils.isNotEmpty(header)) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            httpClient = getHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            LOGGER.info("创建请求http get请求，URL={}", url);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = httpResponse.getEntity();
                result = EntityUtils.toString(resEntity);
            } else {
                readHttpResponse(httpResponse);
            }
        } catch (Exception e) {
            LOGGER.error("请求发送失败：{}", e.getMessage());
            throw new RuntimeException("请求发送失败，URL:"+url+",params:"+ params);
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("关闭HttpClient出错:{}", e.getMessage());
                }
            }
        }
        return result;
    }

    public static CloseableHttpClient getHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONN_MANAGER_TIMEOUT)
                .setSocketTimeout(SO_TIMEOUT).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .setDefaultRequestConfig(config)
                .build();
        return httpClient;
    }
    public static String readHttpResponse(HttpResponse httpResponse)
            throws ParseException, IOException {
        StringBuilder builder = new StringBuilder();
        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        builder.append("status:" + httpResponse.getStatusLine());
        builder.append("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            builder.append("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            builder.append("response length:" + responseString.length());
            builder.append("response content:" + responseString.replace("\r\n", ""));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String url = "http://epass.bamboocloud.com:18086/epass-api/outUserAndApp/saveChildUser";
        Map<String, String> params = new HashMap<>();
        params.put("description", "cgs child user 2");
        params.put("loginName", "cgs");
        params.put("name", "cgsChild2");
        params.put("sortName", "AppHub");
        String result = null;
        try {
            result = HttpClientUtils.post(url, params, null, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);



    }
}
