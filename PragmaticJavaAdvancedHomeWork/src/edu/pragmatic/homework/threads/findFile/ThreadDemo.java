package edu.pragmatic.homework.threads.findFile;

public class ThreadDemo {
	public static void main(String[] args) {
		FileStorage storage = new FileStorage();
		MainThread main = new MainThread(storage);
		main.start();
	}
}
