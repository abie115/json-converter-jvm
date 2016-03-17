package com.abiewska.jvm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class App {

	public static void writeJson(String json, String name) throws IOException {
		try {
			FileWriter fw = new FileWriter("jsonFile/" + name + ".json");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(json);
			bw.close();
		} catch (IOException e) {
			System.out.println("Blad zapisu do *.json");
		}
	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {

		PojoToJsonConverter converter = new PojoToJsonConverter();
		GsonConverter gsonConverter = new GsonConverter();
		Animal animal = new Animal();
		System.out.println("Moj konwerter:\n\n" + converter.pojoToJson(animal));
		System.out.println("Gson:\n" + gsonConverter.pojoToJsonWithGson(animal));

		writeJson(converter.pojoToJson(animal), "AnimalMyConverter");
		writeJson(gsonConverter.pojoToJsonWithGson(animal),"AnimalGsonConverter");

		TestTimeConverter test = new TestTimeConverter();
		for (int i = 0; i < 10; i++) {
			test.runTest();
		}
		System.out.println("\n");
		test.print();
	}
}
