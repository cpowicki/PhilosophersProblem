package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Context {
	public ArrayList<Integer> forks;
	public int alive;
	public Context() {
		ExecutorService es = Executors.newCachedThreadPool();		

		forks = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			forks.add(1);
		}
		alive = 5;
		for (int i = 0; i < 5; i++) {
			es.execute(new Philosopher(i, this));
		}
		
		
	}
	
}
