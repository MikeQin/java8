package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Here is the list of all Stream terminal operations:
	toArray()
	collect()
	count()
	reduce()
	forEach()
	forEachOrdered()
	min()
	max()
	anyMatch()
	allMatch()
	noneMatch()
	findAny()
	findFirst()
 */
public class ReduceFunction {

	List<Person> persons;
	
	public ReduceFunction() {
		persons = DataGenerator.generate();
		System.out.println("---- for each loop in constructor ----");
		persons.stream()
		.map(person -> {
			person.setUuid(UUID.randomUUID());
			return person;
		})		
		.forEach(p -> { 
			System.out.println(p);
		});
		
		System.out.println("---- simple for each loop ----");
		persons.forEach(p -> {
			System.out.println(p);
		});
	}
	
	public void runAll() {
		parallelProcessing();
		countOccupation();
		CategorizedByOccupation();
		createAgeGroups();
		filterByAge(40);
		countByOccupation("student");		
	}
	
	public void parallelProcessing() {
		// Pattern: U reduce(I, (U, T) -> U, (U, U) -> U)
		System.out.println("---- parallel processing ----");
		Set<String> occupations = persons.stream()		
				.parallel()
				.reduce(new HashSet<String>(), (sub, p) -> {
					sub.add(p.getOccupation());
					return sub;
				}, 
				(sum, thread) -> {
					sum.addAll(thread);
					return sum;
				});
		System.out.println("Occupations: " + Utils.toJson(occupations));
		
		int totalAges = persons.parallelStream().reduce(0, (sub, p) -> (sub + p.getAge()), Integer::sum);
		System.out.println("Total ages in parallel: " + totalAges);
		
		totalAges = persons.stream().parallel().reduce(0, (sub, p) -> (sub + p.getAge()), 
				(sum, thread) -> (sum + thread));
		System.out.println("Total ages in parallel (init, accumulator, combiner): " + totalAges);		
	}

	public Map<String, List<Person>> CategorizedByOccupation() {
		System.out.println("---- categorized by occupation ----");
		
		Map<String, List<Person>> initMap = new HashMap<>();
		Map<String, List<Person>> result = persons
			.stream()
			.map(person -> {
				person.setUuid(UUID.randomUUID());
				return person;
			})
			.reduce(initMap, (resultMap, person) -> {
				if (resultMap.get(person.getOccupation()) == null) {
					List<Person> list = new ArrayList<>();
					list.add(person);
					resultMap.put(person.getOccupation(), list);
				} else {
					resultMap.get(person.getOccupation()).add(person);
				}
				return resultMap;
			}, (x, y) -> {
				// Combiner, nothing to do here... simple pass through
				return y;
			});
		
		System.out.println("\nCategories by Occupation:\n" + Utils.toJson(result));
		
		return result;
	}

	public Map<String, Integer> countOccupation() {
		System.out.println("---- count by occupation ----");
		// U reduce(I, (U, T) -> U, (U, U) -> U)
		Map<String, Integer> initMap = new HashMap<>();
		Map<String, Integer> result = persons
			.stream()
			.map(person -> {
				person.setUuid(UUID.randomUUID());
				return person;
			})
			.reduce(initMap, (resultMap, person) -> {
				if (resultMap.get(person.getOccupation()) == null) {
					resultMap.put(person.getOccupation(), 1);
				}
				else {
					int count = resultMap.get(person.getOccupation()) + 1;
					resultMap.put(person.getOccupation(), count);
				}
				
				return resultMap;
			}, (x, y) -> {
				// Combiner, nothing to do here... simple pass through
				return y;
			});
		
		System.out.println("\nCount Occupation:\n" + Utils.toJson(result));
		
		return result;
	}	
	
	public Map<String, List<Person>> createAgeGroups() {
		
		System.out.println("---- age groups ----");
		
		String age20to30 = "Age 20-30";
		String age30to40 = "Age 30-40";
		String age40to50 = "Age 40-50";
		
		Map<String, List<Person>> initMap = new HashMap<>();
		initMap.put(age20to30, new ArrayList<Person>());
		initMap.put(age30to40, new ArrayList<Person>());
		initMap.put(age40to50, new ArrayList<Person>());
		
		Map<String, List<Person>> result = persons
			.stream()
			.map(person -> {
				person.setUuid(UUID.randomUUID());
				return person;
			})				
			.reduce(initMap, (accumMap, person) -> {
		
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
	
		System.out.println("\nAge Groups:\n" + Utils.toJson(result));
		
		return result;
	}
	
	public List<Person> filterByAge(int age) {
		System.out.println("---- filter by age ---- age: " + age);
		
		List<Person> result = persons
			.stream()
			.map(person -> {
				person.setUuid(UUID.randomUUID());
				return person;
			})
			.filter(p -> (p.getAge() > age))
			.collect(Collectors.toList()); 
		// Terminal operations: reduce(), collect(), sum(), average(), count()
		
		System.out.println("\nFilter By Age "+ age +":\n" + Utils.toJson(result));
		
		return result;
	}
	
	public long countByOccupation(String occupation) {
		System.out.println("---- count by occupation ---- occupation: " + occupation);
		long result = persons
			.stream()
			.map(person -> {
				person.setUuid(UUID.randomUUID());
				return person;
			})
			.parallel()
			.filter(p -> (p.getOccupation() == occupation))
			.count();
		
		String formatted = String.format("\n%s Count: %d", occupation, result);
		System.out.println(formatted);
		
		return result;
	}
}
