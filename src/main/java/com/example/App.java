package com.example;

public class App {

	public static void main(String[] args) throws Exception {
		ReduceFunction func = new ReduceFunction();
		func.runAll();
		
		WordCount count = new WordCount();
		count.wordCount("Hello World Hello World Hello World Java Spring Nice Spring");
	}

}
