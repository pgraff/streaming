package com.paypal.example.concurrency;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RXConcurrency {
	
	static private class SomeService {
		final static Random r = new Random();
		int produceANumber(int value) {
			RXConcurrency.printTreadAndMessage("Service invoked with value " + value);
			try {
				Thread.sleep(r.nextInt(2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return value;
		}
	}
	
	private static SomeService service = new SomeService();
	
	private static Integer[] someIntegers = new Integer [] {
		new Integer(1), 
		new Integer(2),
		new Integer(3),
		new Integer(4),
		new Integer(5),
		new Integer(6)
	};
	
	public static void printTreadAndMessage(String msg) {
		System.out.println("Thread " + Thread.currentThread().getName() + ": " + msg);
	}

	public static void main(String[] args) {
//		onlyMain();
//		usingSubscribeOn();
//		usingSubscribeOnLate();
//		usingObserveOn();
		usingFlatMapAndParallelObservers();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void onlyMain() {
		System.out.println("Start of onlyMain");
		Observable.fromArray(RXConcurrency.someIntegers)
			.map(service::produceANumber)
			.subscribe((i) -> RXConcurrency.printTreadAndMessage("Subscription recieved " + i));
	}
	public static void usingSubscribeOn() {
		System.out.println("Start of usingSubscribeOn");
		Observable.fromArray(RXConcurrency.someIntegers)
			.subscribeOn(Schedulers.computation())
			.map(service::produceANumber)
			.subscribe((i) -> RXConcurrency.printTreadAndMessage("Subscription recieved " + i));
	}
	public static void usingSubscribeOnLate() {
		System.out.println("Start of usingSubscribeOnLate");
		Observable.fromArray(RXConcurrency.someIntegers)
			.map(service::produceANumber)
			.subscribeOn(Schedulers.computation())
			.subscribe((i) -> RXConcurrency.printTreadAndMessage("Subscription recieved " + i));
	}
	public static void usingObserveOn() {
		System.out.println("Start of usingObserveOn");
		Observable.fromArray(RXConcurrency.someIntegers)
			.map(service::produceANumber)
			.observeOn(Schedulers.computation())
			.subscribe((i) -> RXConcurrency.printTreadAndMessage("Subscription recieved " + i));
	}
	public static void usingFlatMapAndParallelObservers() {
		System.out.println("Start of usingFlatMapAndParallelObservers");
		Observable
			.fromArray(RXConcurrency.someIntegers)
			.flatMap(i -> Observable.just(i)
					.subscribeOn(Schedulers.computation())
					.map(service::produceANumber)
			)
			.doOnNext((i) -> RXConcurrency.printTreadAndMessage("Received " + i))
			.toList()
			.subscribe((i) -> RXConcurrency.printTreadAndMessage("Subscription recieved " + i));
			
	}
}
