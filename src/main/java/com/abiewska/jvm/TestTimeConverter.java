package com.abiewska.jvm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

public class TestTimeConverter {
	public final static int M = 1000;
	public static ArrayList<Double> timeMyConvert = new ArrayList<Double>();
	public static ArrayList<Double> timeConvertGson = new ArrayList<Double>();

	public double testPojoToJson() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {
		double start, end;
		PojoToJsonConverter converter = new PojoToJsonConverter();
		Animal animal = new Animal();
		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			converter.pojoToJson(animal);
		}
		end = System.nanoTime();
		return (end - start);
	}

	public double testPojoToJsonWithGson() {
		double start, end;
		GsonConverter gsonConverter = new GsonConverter();
		Animal animal = new Animal();
		start = System.nanoTime();
		for (int i = 0; i < M; i++) {
			gsonConverter.pojoToJsonWithGson(animal);
		}
		end = System.nanoTime();
		return (end - start);
	}

	public void runTest() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, IOException {
		timeMyConvert.add(testPojoToJson());
		timeConvertGson.add(testPojoToJsonWithGson());
	}

	public void removeOutsiders(ArrayList<Double> times) {

		times.remove(times.indexOf(Collections.min(times)));
		times.remove(times.indexOf(Collections.max(times)));
	}

	public void prepareTimes() {
		removeOutsiders(timeMyConvert);
		removeOutsiders(timeConvertGson);
	}

	public double averageTimes(ArrayList<Double> times) {
		double avTime = 0;
		for (int i = 0; i < times.size(); i++) {
			avTime += times.get(i);
		}
		return Math.round((avTime / times.size()) * 100.0) / 100.0;
	}

	public void print() {
		prepareTimes();
		System.out.println("Sredni czas mojego konwertera: "+ averageTimes(timeMyConvert) + " ns");
		System.out.println("Sredni czas Gson konwerter: "+ averageTimes(timeConvertGson) + " ns");
	}
}
