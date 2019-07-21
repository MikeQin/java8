package com.example;

import java.util.Arrays;

import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

public class StreamFunction {

	List<Person> persons;
	
	public StreamFunction() {
		persons = DataGenerator.generate();
	}

	public void runAll() {
		simpleStream();
		groupByOccupationAges();
		groupByOccupationPersons();
	}

	public void simpleStream() {

		List<Integer> numbers = Arrays.asList(10, 14, 15, 20);
		int result = 0;

		for (int e : numbers) {
			if (e % 2 == 0) {
				result += e * 2;
			}
		}
		System.out.println("result=" + result);

		System.out.println("result=" + numbers.stream()
		    .filter(e -> e % 2 == 0)
		    .mapToLong(e -> e * 2)
		    .sum());
	}

	public void groupByOccupationAges() {
		System.out.println("---- group by {occupation: {age}} ----");
		Map<String, List<Integer>> result =	persons.stream()
		    .collect(groupingBy(
		    		Person::getOccupation, mapping(Person::getAge, toList())));
		
		System.out.println(Utils.toJson(result));
	}

	public void groupByOccupationPersons() {
		System.out.println("---- group by {occupation: {person}} ----");
		Map<String, List<Person>> result =	this.persons.stream()
		    .collect(groupingBy(
		    		p -> p.getOccupation(), mapping(p -> p, toList())));
			// OR: (p -> p.getOccupation(), mapping(p -> p, toList())));
		
		System.out.println(Utils.toJson(result));
	}	
}
