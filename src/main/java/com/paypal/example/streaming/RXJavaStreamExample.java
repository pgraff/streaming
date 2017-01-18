package com.paypal.example.streaming;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RXJavaStreamExample {
	
	public static void main(String[] args) {
		Integer [] samples = new Integer [] { 1, 2, 3, 4, 5, 6, 7, 8 ,9, 10 };
		
		Observable<Integer> obs = Observable
			.fromArray(samples)
			.map( i -> i * i)
			.doOnNext(System.out::println)
			.doOnComplete(() -> System.out.println("Thread id is " + Thread.currentThread().getId()));
		obs.subscribe();
		obs.subscribeOn(Schedulers.io()).subscribe();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
