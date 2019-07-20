package com.example;

import java.util.Arrays;

public class StreamOps {
	
	public void simpleStream() {
		
		int [] numbers = {10, 14, 15, 20};
		int result = 0;
		
		for(int e : numbers) {
			if (e % 2 == 0) {
				result += e * 2;
			}
		}
		System.out.println("result=" + result);
		
		System.out.println(
		 Arrays.stream(numbers)
		       .filter(e -> e % 2 == 0)
		       .mapToLong(e -> e * 2)
		       .sum());
	}

}
