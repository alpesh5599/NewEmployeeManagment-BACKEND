package com.nexscend.employee.management.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.nexscend.employee.management.entity.Candidate;

@Component
public class ResponseBean {

	private int statusCode;
	private HttpStatus status;
	private String message;
	private Object body;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public static ResponseBean generateResponse(HttpStatus status, String message, Object body) {
		ResponseBean bean = new ResponseBean();

		bean.setMessage(message);
		bean.setStatus(status);
		bean.setBody(body);

		return bean;
	}

	public static ResponseBean generateResponse(HttpStatus status, String message) {
		ResponseBean bean = new ResponseBean();

		bean.setMessage(message);
		bean.setStatus(status);

		return bean;
	}

	public static ResponseBean generateResponse(HttpStatus status, Object body) {
		ResponseBean bean = new ResponseBean();

		bean.setStatus(status);
		bean.setBody(body);

		return bean;
	}

}
