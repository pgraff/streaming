package com.paypal.example.streaming;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Java8StreamExample {
	
	public static void main(String[] args) {
		int [] samples = new int [] { 1, 2, 3, 4, 5, 6, 7, 8 ,9, 10 };
		
		IntStream stream = Arrays.stream(samples);
		stream
			.map( i -> i * i)
			.forEach(System.out::println);
	}

}
