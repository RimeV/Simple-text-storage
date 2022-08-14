package com.rimev.simple_text_storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.rimev.simple_text_storage.model.UserText;
import com.rimev.simple_text_storage.service.Storable;
import com.rimev.simple_text_storage.service.TextStorageService;

/* Т.к. stringStorage, в котором хранятся пользовательские данные, является закрытым
 * полем, а метод для заполнения хранилища также тестируется, то оно будет заполнятся
 * отдельно в каждом тестовом методе, а не в отдельном методе */
public class TextStorageServiceTest {

	private final Storable textStorageService = new TextStorageService();
	
	private UserText testText1 = new UserText();
	private UserText testText2 = new UserText();
	private UserText testText3 = new UserText();
	private UserText testText4 = new UserText();
	private UserText testText5 = new UserText();
	private UserText testText6 = new UserText();
	private UserText testText7 = new UserText();

	{
		testText1.setHeader("1");
		testText1.setText("Меркурий");
		
		testText2.setHeader("2");
		testText2.setText("Венера");
		
		testText3.setHeader("3");
		testText3.setText("Земля");
		
		testText4.setHeader("   ");
		testText4.setText("Марс");
		
		testText5.setHeader("5");
		testText5.setText("   ");
		
		testText6.setHeader(null);
		testText6.setText("Сатурн");
		
		testText7.setHeader("7");
		testText7.setText(null);
	}
	
	@Test
	public void setDataTest() {
		assertTrue(textStorageService.putData(testText1));
		assertFalse(textStorageService.putData(testText4));
		assertFalse(textStorageService.putData(testText5));
		assertFalse(textStorageService.putData(testText6));
		assertFalse(textStorageService.putData(testText7));
	}
	
	@Test
	public void getDataTest() {
		textStorageService.putData(testText1);
		assertEquals("Меркурий", textStorageService.getData("1"));
		assertEquals("Заметок с таким заголовком не найдено", textStorageService.getData("3"));
		assertEquals("Заголовок не может быть пустым или содержать одни пробелы", textStorageService.getData("  "));
	}
	
	@Test
	public void delDataTest() {
		textStorageService.putData(testText1);
		assertEquals("Заметка удалена", textStorageService.removeData("1"));
		assertEquals("Заметок с таким заголовком не найдено", textStorageService.removeData("1"));
		assertEquals("Заметок с таким заголовком не найдено", textStorageService.removeData("2"));
		assertEquals("Заголовок не может быть пустым или содержать одни пробелы", textStorageService.removeData("   "));
	}
	
	@Test
	public void getKeysTest() {
		textStorageService.putData(testText1);
		textStorageService.putData(testText2);
		textStorageService.putData(testText3);
		assertEquals("Список ваших заметок: 1, 2, 3", textStorageService.getHeaders());
		textStorageService.removeData("1");
		textStorageService.removeData("2");
		textStorageService.removeData("3");
		assertEquals("Список ваших заметок: у вас нет сохраненных заметок, давайте добавим их прямо сейчас!", textStorageService.getHeaders());
	}
}
