package com.example;

import java.util.UUID;

import lombok.Data;

@Data
public class Person {

	private UUID uuid;
	private String name;
	private int age;
	private String occupation;
	
	public Person(String name, int age, String occupation) {
		this.name = name;
		this.age = age;
		this.occupation = occupation;
	}
}
