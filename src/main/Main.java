package main;

import java.util.concurrent.*;

public class Main {
	public static void main(String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.isShutdown();
	}
}
