package com.abiewska.jvm;

public class Animal {
	
	public String name;
	public int age;
	public double weight;
	
	
	public Animal(){
		this.name="Delfin";
		this.age=5;
		this.weight=50.45;
	}
	
	public String getName(){
		return name;
	}
	
	public double getWeigth(){
		return weight;
	}
	
	public int getAge(){
		return age;
	}
}
