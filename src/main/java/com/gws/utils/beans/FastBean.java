/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.utils.beans;

import java.beans.PropertyDescriptor;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

/**
 * 动态创建一个bean对象
 *
 * @version
 * @author liuyi 2017年4月12日 上午10:57:40
 * 
 */
public class FastBean {
	private Object objBean = null;
	private BeanMap beanPropMap = null;
	
	/**
	 * 
	 *动态bean对象创建方法
	 * 
	 * @author liuyi 2017年4月12日
	 * @param propertyMap
	 * @return
	 */
	public static FastBean builder(Map<String, Class<?>> propertyMap){
		FastBean fb= new FastBean();
		fb.toBean(propertyMap);
		return fb;
	}
	

	/**
	 * 
	 * 动态创建属性
	 * 
	 * @author liuyi 2017年4月12日
	 * @param propertyMap
	 * @return
	 */
	private void toBean(Map<String, Class<?>> propertyMap) {
		BeanGenerator generator = new BeanGenerator();
		propertyMap.forEach((k, v) -> {
			generator.addProperty(k, v);
		});
		this.objBean= generator.create();
		this.beanPropMap = BeanMap.create(objBean);
	}

	/**
	 * 
	 * 属性赋值
	 * 
	 * @author liuyi 2017年4月12日
	 * @param property
	 * @param value
	 */
	public FastBean setValue(String property, Object value) {
		beanPropMap.put(property, value);  
		return this;
	}
	
	
	/**
	 * 
	 * 获取属性值
	 * 
	 * @author liuyi 2017年4月12日
	 * @param property
	 * @return
	 */
	public Object getValue(String property){
		 return beanPropMap.get(property);
	}
	
	/**
	 * 
	 * 获取创建成功的bean对象
	 * 
	 * @author liuyi 2017年4月12日
	 * @return
	 */
	public Object getBeanObj(){
		return objBean;
	}

}