package main;

import java.lang.Runnable;
import java.time.Instant;
import java.util.ArrayList;

public class Philosopher implements Runnable {
	private Context context;
	private int position;
	private int leftFork;
	private int rightFork;
	private int forks;
	private boolean living;

	public Philosopher(int i, Context c) {
		position = i;
		context = c;
		leftFork = i;
		rightFork =  (i == c.forks.size() - 1) ? 0 : i + 1;
		forks = 0;
		living = true;
	}

	@Override
	public void run() {
		long lastEat = System.currentTimeMillis();
		boolean hasLeft;
		boolean hasRight;
		while(living) {
			if (System.currentTimeMillis() - lastEat > 1000) {
				living = false;
				context.alive -= 1;
				System.out.println("Philosopher " + position + " has starved!");
			}else if (context.alive < 5) {
				living = false;
				context.alive -= 1;
			} else {
				hasLeft = grabFork(leftFork);
				hasRight = grabFork(rightFork);
				long sleepTime = 0;
				if(hasLeft && hasRight) {
					System.out.println("Philosopher " + position + " is eating!");
					lastEat = System.currentTimeMillis();
					sleepTime = 100;
				}
				if (hasLeft) {
					releaseFork(leftFork);
				}
				if (hasRight) {
					releaseFork(rightFork);
				}
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized boolean grabFork(int ind) {
		if(this.context.forks.get(ind) == 1) {
			this.context.forks.set(ind, 0);
			return true;
		} else {
			return false;
		}
	}
	
	public synchronized void releaseFork(int ind) {
		this.context.forks.set(ind, 1);
	}
	
	
	public void debug() {
		System.out.println("Position: " + position + ", Left: " + leftFork + ", Right: " + rightFork);
	}
	
}
