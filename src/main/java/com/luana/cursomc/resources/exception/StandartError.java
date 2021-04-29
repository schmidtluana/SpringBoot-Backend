package com.luana.cursomc.resources.exception;

import java.io.Serializable;

public class StandartError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private Long time;
	
	public StandartError(Integer status, String msg, Long time) {
		super();
		this.status = status;
		this.msg = msg;
		this.time = time;
	
	
}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	
	
	
}
