package com.abiewska.jvm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Animal {

	public String name;
	public int age;
	private String[] food;
	private Integer[] number;
	private double weight;
	private boolean vegetarian;
	public List<String> listKeeper = new LinkedList<String>();
	private Integer[] empty={};
	public List<Integer> listArray = new ArrayList<Integer>();

	
	public Animal() {
		this.name = "Delfin";
		this.age = 5;
		this.weight = 50.45;
		this.food = new String[] { "gruszka", "glony", "marchewka",
				"pietruszka" };
		this.number = new Integer[] { 1, 2, 45 };
		this.vegetarian = false;
		this.listKeeper.add("Kowalski");
		this.listKeeper.add("Wiaderko");
		this.listArray.add(12);
		this.listArray.add(15);		
	}

	public String getName() {
		return name;
	}

	public double getWeigth() {
		return weight;
	}

	public int getAge() {
		return age;
	}

	public String[] getFood() {
		return food;
	}

	public Integer[] getNumber() {
		return number;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public Integer[] getEmpty() {
		return empty;
	}

}
