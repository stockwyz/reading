package com.edu.reading.service;

/**
 * @author cq.lang (langchunqing@reignwood.com)
 * @Description: 微信相关业务逻辑
 * @date 2020/2/13 1:54 下午
 */
public interface WxService {
    String getUserOpenId(String code);

    String buildAuthorizationUrl(String redirectUrl);
}
