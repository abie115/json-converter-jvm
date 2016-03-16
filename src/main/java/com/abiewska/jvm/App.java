package com.abiewska.jvm;

import java.io.IOException;

public class App {
	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, IOException {
		PojoToJsonConverter converter = new PojoToJsonConverter();
		Animal animal = new Animal();
		converter.pojoToJson(animal);
	}
}
