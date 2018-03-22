/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * sprint servlet工具类
 *
 * @version 
 * @author liuyi  2016年4月19日 下午6:37:30
 * 
 */
public final class ServletUtils {
	
	
	/**
	 * 
	 * 获取用户SID
	 * 
	 * @author liuyi 2016年4月19日
	 * @return
	 */
	public static String getSid(){	
		return UUID.randomUUID().toString()+"_"+System.currentTimeMillis();
	}
	
    public static Cookie getCookieByName (HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    
    public static Cookie setCookie(String name,String value,String domain){
    	Cookie cookie  = new Cookie(name, value);
    	cookie.setDomain(domain);
    	cookie.setPath("/");
    	cookie.setHttpOnly(true);
    	//cookie.setSecure(true);
    	cookie.setMaxAge(GlobalConstant.SID_COOKIE_MAXAGE);
    	return cookie;
    }
	
	  /**
	   * 
	   * 获取当前线程的请求
	   * 
	   * @author liuyi 2016年4月19日
	   * @return
	   */
	  public static HttpServletRequest getRequest() {
	        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
	        return ((ServletRequestAttributes) ra).getRequest();
	    }
}