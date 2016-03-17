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
		StringBuilder json = new StringBuilder("{");
		Field[] fields = object.getClass().getDeclaredFields();
		String fldName;
		Object fldVal;
		Class<?> type;
		String clsName = object.getClass().getSimpleName();

		json.append("\"" + clsName + "\": ");
		json.append("{\n");

		for (int i = 0; i < fields.length; i++) {

			fields[i].setAccessible(true);
			fldName = fields[i].getName();
			fldVal = fields[i].get(object);
			type = fields[i].getType();
			json.append("\t\"" + fldName + "\": ");

			if (type.isArray()) {
				json.append("[\n");
				json.append(arrayToJson(fldVal, type));
				json.setLength(json.length() - 2);
				json.append("\n\t],\n");
			} else if (isCollection(type)) {
				json.append("[\n");
				ParameterizedType fieldGenericType = (ParameterizedType) fields[i]
						.getGenericType();
				Class<?> fieldTypeParameterType = (Class<?>) fieldGenericType
						.getActualTypeArguments()[0];
				Method toArray = List.class.getDeclaredMethod("toArray");
				json.append(arrayToJson(toArray.invoke(fldVal),
						fieldTypeParameterType));
				json.setLength(json.length() - 2);
				json.append("\n\t],\n");
			} else if (isBoolean(type)) {
				json.append("" + fldVal + ",\n");
			} else if (isNumber(type)) {
				json.append("" + fldVal + ",\n");
			} else {
				json.append("\"" + fldVal + "\",\n");
			}
		}

		json.setLength(json.length() - 2);
		json.append("\n}}");

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
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				arrayVal = Array.get(array, i);
				if (isBoolean(type)) {
					arrayJson.append("\t\t" + arrayVal + ",\n");
				} else if (isNumber(type)) {
					arrayJson.append("\t\t" + arrayVal + ",\n");
				} else {
					arrayJson.append("\t\t\"" + arrayVal + "\",\n");
				}
			}
		} else {
			arrayJson.append(",\n");
		}
		return arrayJson.toString();
	}

}
