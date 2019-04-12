package main;

import java.lang.Runnable;

public class Philosopher implements Runnable {
	private int position;
	private Context context;

	public Philosopher(int i, Context c) {
		position = i;
		context = c;
	}

	@Override
	public void run() {
		
	}
	
}
