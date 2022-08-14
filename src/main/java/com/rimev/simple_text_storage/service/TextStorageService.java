package com.rimev.simple_text_storage.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rimev.simple_text_storage.model.UserText;


@Service
public class TextStorageService implements Storable {
 
	private final Map<String, UserText> textStorage = new HashMap<>();
	
	/* Метод не позволяет сохранять и использовать в качестве заголовка и тела заметки пустые строки
	 * и строки из одних пробелов, а также использовать в качестве заголовка или тела заметки
	 * null */
	@Override
	public boolean putData(UserText text) {
		if (headerVerification(text.getHeader()) && dataVerification(text)) {
			textStorage.put(text.getHeader(), text);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getData(String header) {
		if (headerVerification(header)) {
			return textStorage.containsKey(header) ? textStorage.get(header).getText() : "Заметок с таким заголовком не найдено";
		} else {
			return "Заголовок не может быть пустым или содержать одни пробелы";
		}
	}

	@Override
	public String removeData(String header) {
		if (headerVerification(header)) {
			return (textStorage.remove(header) != null) ?  "Заметка удалена" : "Заметок с таким заголовком не найдено";
		} else {
			return "Заголовок не может быть пустым или содержать одни пробелы";
		}
	}

	@Override
	public String getHeaders() {
		StringBuilder headers = new StringBuilder().append("Список ваших заметок: ");
		if (!textStorage.isEmpty()) {
			for (String header : textStorage.keySet()) {
				headers.append(header);
				headers.append(", ");
			}
			// Удаляем лишние запятую и пробел в конце списка заголовков
			headers.delete(headers.length()-2, headers.length());
		} else {
			headers.append("у вас нет сохраненных заметок, давайте добавим их прямо сейчас!");
		}
		return headers.toString();
	}
	
	private boolean headerVerification(String header) {
		return header != null && !header.isBlank();
	}
	
	private boolean dataVerification(UserText data) {
		return data.getText() != null && !data.getText().isBlank();
	}
}
