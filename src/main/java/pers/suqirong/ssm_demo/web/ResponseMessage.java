package pers.suqirong.ssm_demo.web;

public class ResponseMessage<T> {

	private int code;
	
	private String msg;
	
	private T data;
	
	public ResponseMessage() {
	}
	
	public ResponseMessage(T data) {
		this(200,"操作成功", data);
	}

	public ResponseMessage(int code) {
		super();
		this.code = code;
	}

	public ResponseMessage(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseMessage(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
