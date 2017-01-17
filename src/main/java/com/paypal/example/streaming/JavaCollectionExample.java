package com.paypal.example.streaming;

public class JavaCollectionExample {
	
	public static void main(String[] args) {
		int [] samples = new int [] {1,2,3,4,5, 6, 7, 8 ,9, 10};
		
		for (int i : samples ) {
			int square = i*i;
			System.out.println(square);
		}
	}

}
