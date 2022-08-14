package com.rimev.simple_text_storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rimev.simple_text_storage.model.UserText;
import com.rimev.simple_text_storage.service.TextStorageService;

@RestController
public class StorageController {

	private final TextStorageService textStorageServise;
	
	@Autowired
	public StorageController(TextStorageService textStorageService) {
		this.textStorageServise = textStorageService;
	}
	
	@PostMapping(value = "/texts")
	public ResponseEntity<String> addText(@RequestBody UserText text) {
			return textStorageServise.putData(text) ? new ResponseEntity<>("Заметка добавлена", HttpStatus.OK) : new ResponseEntity<>("Заголовок и текст заметки не может быть пустым или содержать одни пробелы", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/texts/{header}")
	public ResponseEntity<String> readText(@PathVariable(name = "header") String header) {
		final String text = textStorageServise.getData(header);
		return new ResponseEntity<>(text, HttpStatus.OK);
	}
	
	@PostMapping(value = "/texts/{header}")
	public ResponseEntity<String> deleteText(@PathVariable(name = "header") String header) {
		return new ResponseEntity<>(textStorageServise.removeData(header), HttpStatus.OK);
	}
	
	@GetMapping(value = "/texts")
	public ResponseEntity<String> readHeaders() {
		final String headers = textStorageServise.getHeaders();
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}
}
