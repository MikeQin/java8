package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {
	
	private Utils() {}

	public static final String toJson(Object o) {

		ObjectMapper om = new ObjectMapper();
		String json = null;
		try {
			json = om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}

}
