package com.rimev.simple_text_storage.model;

public class UserText implements UserData {

	private String header;
	private String text;
	
	@Override
	public void setHeader(String heder) {
		this.header = heder;
	}
	
	@Override
	public String getHeader() {
		return header;
	}
	
	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String getText() {
		return text;
	}
}
