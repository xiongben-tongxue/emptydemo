/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.interceptor;

import com.gws.configuration.EnvironmentConfig;
import com.gws.dto.AccessLog;
import com.gws.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


/**
 * http 拦截器 demo
 *
 * @version 
 * @author liuyi  2016年4月12日 下午1:35:11
 * 
 */
public class HttpInterceptor extends HandlerInterceptorAdapter {

	private Long startTime = 0L;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		AccessLog accessLog = new AccessLog();

		String ip = IPUtil.getIpAddr(request);
		accessLog.setIp(ip);

		String url=	request.getRequestURI();
		accessLog.setUrl(url);


		/**访问时间**/
		Long durationTime = System.currentTimeMillis()-startTime;
		accessLog.setAccessTime(Long.toString(TimeUnit.MILLISECONDS.toMillis(durationTime))+"ms");
		
		GlobalConstant.accessLog.set(accessLog);
		
		GwsLogger.info(GwsLoggerTypeEnum.ACCESSTRACE,accessLog.toString());
		super.afterCompletion(request, response, handler, ex);
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}