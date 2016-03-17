package com.abiewska.jvm;

import com.google.gson.Gson;


public class GsonConverter {
	private Gson gson = new Gson();;
	
	public String pojoToJsonWithGson(Object object) {
		return gson.toJson(object);		
	}
}
