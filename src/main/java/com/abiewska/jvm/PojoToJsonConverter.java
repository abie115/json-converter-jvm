package com.abiewska.jvm;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public class PojoToJsonConverter {

	public String pojoToJson(Object object) throws IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			InvocationTargetException {
		StringBuilder json = new StringBuilder("{\n");
		Field[] fields = object.getClass().getDeclaredFields();
		String fldName;
		Object fldVal;
		Class<?> type;
		
		for (int i = 0; i < fields.length; i++) {

			fields[i].setAccessible(true);
			fldName = fields[i].getName();
			fldVal = fields[i].get(object);
			type = fields[i].getType();
			json.append("  \"" + fldName + "\": ");

			if (type.isArray()) {
				json.append(arrayToJson(fldVal, type));
			} else if (isCollection(type)) {
				ParameterizedType fieldGenericType = (ParameterizedType) fields[i].getGenericType();
				Class<?> fieldTypeParameterType = (Class<?>) fieldGenericType.getActualTypeArguments()[0];
				Method toArray = List.class.getDeclaredMethod("toArray");
				json.append(arrayToJson(toArray.invoke(fldVal), fieldTypeParameterType));
			} else if (isBoolean(type)) {
				json.append(fldVal);
			} else if (isNumber(type)) {
				json.append(fldVal);
			} else {
				json.append("\"" + fldVal + "\"");
			}
			if (i < fields.length - 1)
				json.append(",\n");
		}

		json.append("\n}");

		return json.toString();

	}

	private boolean isNumber(Class<?> type) {
		return type.equals(int.class) || type.equals(Integer.class)
				|| type.equals(double.class) || type.equals(Double.class)
				|| type.equals(float.class) || type.equals(Float.class)
				|| type.equals(Short.class) || type.equals(short.class)
				|| type.equals(Long.class) || type.equals(long.class)
				|| type.equals(int[].class) || type.equals(Integer[].class)
				|| type.equals(double[].class) || type.equals(Double[].class)
				|| type.equals(float[].class) || type.equals(Float[].class)
				|| type.equals(Short[].class) || type.equals(short[].class)
				|| type.equals(Long[].class) || type.equals(long[].class);
	}

	private boolean isBoolean(Class<?> type) {
		return type.equals(boolean.class) || type.equals(Boolean.class)
				|| type.equals(boolean[].class) || type.equals(Boolean[].class);
	}

	private boolean isCollection(Class<?> type) {
		return Collection.class.isAssignableFrom(type);
	}

	private String arrayToJson(Object array, Class<?> type) {
		StringBuilder arrayJson = new StringBuilder();
		Object arrayVal;
		int length = Array.getLength(array);
		arrayJson.append("[\n");
		for (int i = 0; i < length; i++) {
			arrayVal = Array.get(array, i);
			if (isBoolean(type)) {
				arrayJson.append("\t" + arrayVal);
			} else if (isNumber(type)) {
				arrayJson.append("\t" + arrayVal);
			} else {
				arrayJson.append("\t\"" + arrayVal + "\"");
			}
			if (i < length - 1)
				arrayJson.append(",\n");
		}
		arrayJson.append("\n  ]");
		return arrayJson.toString();
	}

}
