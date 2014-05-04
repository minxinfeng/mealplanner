package com.threeone.mealplanner.common;

/**
 * @author mei
 */
public class JsonResult<T> {
	private boolean success;
	private String message;
	private T data;
	
	public JsonResult() {
		super();
	}

	public JsonResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public JsonResult(boolean success, String message, T data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
