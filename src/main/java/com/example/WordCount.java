package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordCount {
	
	public Map<String, Integer> wordCount(String paragraph) {
		Map<String, Integer> initMap = new HashMap<>();
		
		String[] strArr = paragraph.split(" ");
		
		Map<String, Integer> result = Arrays.stream(strArr)
			.reduce(initMap, (resultMap, word) -> {			
				if (resultMap.get(word) == null) {
					resultMap.put(word, 1);
				}
				else {
					int count = resultMap.get(word) + 1;
					resultMap.put(word, count);
				}

				return resultMap;
			}, (x, y) -> y);
		
		System.out.println("Word Count Result:\n" + Utils.toJson(result));
		
		return result;
	}

}
