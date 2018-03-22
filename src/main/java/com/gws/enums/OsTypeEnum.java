/*
 * Copyright (C) 2016  HangZhou YuShi Technology Co.Ltd  Holdings Ltd. All rights reserved
 *
 * 本代码版权归杭州宇石科技所有，且受到相关的法律保护。
 * 没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 */
package com.gws.enums;

/**
 * 【操作系统枚举】
 *
 * @author yangjh
 *
 */
public enum OsTypeEnum {

	iOS(1, "iOS"),
	Android(2, "安卓"),
	ALLSYSTEM(3,"全平台"),
    ;

	private Integer code;
	private String message;

	private OsTypeEnum(Integer code, String message){
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	public static OsTypeEnum getEnum(Integer vCodeType) {
		for (OsTypeEnum osTypeEnum: OsTypeEnum.values()) {
			if (osTypeEnum.getCode().equals(vCodeType)) {
				return osTypeEnum;
			}
		}
		return null;
	}
	
	
}