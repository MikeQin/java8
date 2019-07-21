package com.example;

public class App {

	public static void main(String[] args) throws Exception {
		ReduceFunction func = new ReduceFunction();
		func.runAll();

		String paragraph = "Hello World Hello World Hello World Java Spring Nice Spring";
		WordCount count = new WordCount();
		count.wordCount(paragraph);
		count.wordCountUsingCollect(paragraph);

		StreamFunction streamFunc = new StreamFunction();
		streamFunc.runAll();
	}
}
