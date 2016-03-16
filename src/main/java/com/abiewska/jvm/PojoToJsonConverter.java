package com.abiewska.jvm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class PojoToJsonConverter {

	public void pojoToJson(Object object) throws IllegalArgumentException,
			IllegalAccessException, IOException {
		StringBuilder json = new StringBuilder("{");
		Field[] fields = object.getClass().getDeclaredFields();
		String fldName;
		Object fldVal;
		String clsName = object.getClass().getSimpleName();

		json.append("\"" + clsName + "\": ");
		json.append("{\n");

		for (int i = 0; i < fields.length; i++) {
			fldName = fields[i].getName();
			fldVal = fields[i].get(object);
			json.append("\t\"" + fldName + "\": ");
			json.append("\"" + fldVal + "\",\n");
			// System.out.println(fldName);
			// System.out.println(fldVal);
		}
		json.setLength(json.length() - 2);
		json.append("\n}}");

		writeJson(json.toString(), clsName);

	}

	public void writeJson(String json, String clsName) throws IOException {
		FileWriter fw = new FileWriter(clsName + ".json");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(json);
		bw.close();
		System.out.println(json);
	}
}
