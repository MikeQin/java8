package com.example;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

	public static List<Person> generate() {
		List<Person> persons = new ArrayList<>();
		Person p = null;
		p = new Person("p1", 21, "student");
		persons.add(p);
		p = new Person("p2", 25, "student");
		persons.add(p);
		p = new Person("p3", 31, "student");
		persons.add(p);
		p = new Person("p4", 35, "engineer");
		persons.add(p);
		p = new Person("p5", 49, "teacher");
		persons.add(p);
		p = new Person("p6", 44, "engineer");
		persons.add(p);
		p = new Person("p7", 34, "student");
		persons.add(p);

		return persons;
	}

}
