package com.edu.reading.service.impl;

import org.springframework.stereotype.Service;

import com.edu.reading.service.WxService;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author zht
 * @Description: 微信相关业务逻辑实现类
 * @date 2020/2/13 3:58 下午
 */
//@RequiredArgsConstructor
@Service
public class WxServiceImpl implements WxService {
//    private final WxMpService wxMpService;

    @Override
    public String getUserOpenId(String code) {
    	return "";
//        String openId = null;
//
//        try {
//            WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);
//            WxMpUser user = wxMpService.oauth2getUserInfo(accessToken, null);
//            openId = user.getOpenId();
//        } catch(WxErrorException e) {
//            e.printStackTrace();
//        }
//
//        return openId;
    }

    @Override
    public String buildAuthorizationUrl(String redirectUrl) {
    	return "";
//        return wxMpService.oauth2buildAuthorizationUrl(redirectUrl, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
    }


}
