package com.example.model;

import com.fasterxml.jackson.annotation.JsonView;

public class Retorno {
	@JsonView()
	String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
