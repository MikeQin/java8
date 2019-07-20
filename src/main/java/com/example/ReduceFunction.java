package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReduceFunction {

	List<Person> persons = DataGenerator.generate();
	Map<String, Person> occupations = new HashMap<>();
	
	public void runAll() {
		createOccupationCategory();
		createAgeGroups();
	}

	public HashMap<String, List<Person>> createOccupationCategory() {
		// U reduce(I, (U, T) -> U, (U, U) -> U)
		HashMap<String, List<Person>> result = persons.stream()
				.map(p -> {
					p.setUuid(UUID.randomUUID());
					return p;
				})
				.reduce(new HashMap<>(), (accumMap, person) -> {
			if (accumMap.get(person.getOccupation()) == null) {
				List<Person> list = new ArrayList<>();
				list.add(person);
				accumMap.put(person.getOccupation(), list);
			} else {
				accumMap.get(person.getOccupation()).add(person);
			}
			return accumMap;
		}, (x, y) -> (y));
		
		System.out.println("\nPersons categorized by [Occupation]:\n" + Utils.toJson(result));
		
		return result;
	}
	
	public Map<String, List<Person>> createAgeGroups() {
		
		String age20to30 = "Age 20-30";
		String age30to40 = "Age 30-40";
		String age40to50 = "Age 40-50";
		
		Map<String, List<Person>> initMap = new HashMap<>();
		initMap.put(age20to30, new ArrayList<Person>());
		initMap.put(age30to40, new ArrayList<Person>());
		initMap.put(age40to50, new ArrayList<Person>());
		
		Map<String, List<Person>> result = persons.stream().reduce(initMap, (accumMap, person) -> {
			
			if (person.getAge()>= 20 && person.getAge() < 30) {
				accumMap.get(age20to30).add(person);
			}
			else if (person.getAge()>= 30 && person.getAge() < 40) {
				accumMap.get(age30to40).add(person);
			}
			else if (person.getAge()>= 40 && person.getAge() < 50) {
				accumMap.get(age40to50).add(person);
			}
			else {
				throw new RuntimeException("Age is beyond the range ...");
			}
			
			return accumMap;
			
		}, (x, y) -> (y));
		
		System.out.println("\nAge Group:\n" + Utils.toJson(result));
		
		return result;
	}

}
