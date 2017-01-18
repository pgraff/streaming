package com.paypal.example.reactiveness;

import io.reactivex.Single;

public class ExampleUsingLibrary {
	private static class SomeService {
		public Single<Integer> getValue() {
			return Single.create(emitter -> {
				Thread thread = new Thread(
						() -> {
							try {
								Thread.sleep(1000);
								emitter.onSuccess( (int) (Math.random() * 100));
							} catch (InterruptedException e) {
								emitter.onError(e);
							}
						});
				thread.start();
			});
		}
	}
	public static void main(String[] args) {
		SomeService s = new SomeService();
		Single<Integer> o1 = s.getValue();
		Single<Integer> o2 = s.getValue();
		o1.subscribe(System.out::println);
		o2.subscribe(System.out::println);
	}

}
