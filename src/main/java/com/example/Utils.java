package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class Utils {

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private Utils() {}

	public static final String toJson(Object o) {

		String json = null;
		try {
			json = OBJECT_MAPPER.writerWithDefaultPrettyPrinter()
			    .writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return json;
	}

}
