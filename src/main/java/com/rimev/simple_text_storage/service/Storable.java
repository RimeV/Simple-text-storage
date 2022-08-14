package com.rimev.simple_text_storage.service;

import com.rimev.simple_text_storage.model.UserText;

public interface Storable {
	public boolean putData(UserText text);
	public String getData(String header);
	public String removeData(String header);
	public String getHeaders();
}
