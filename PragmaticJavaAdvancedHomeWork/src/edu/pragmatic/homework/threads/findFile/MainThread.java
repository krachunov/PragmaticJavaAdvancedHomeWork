package edu.pragmatic.homework.threads.findFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainThread extends Thread {
	private boolean isRun;

	private FileStorage storage;
	private List<Thread> threads;

	private boolean isRun() {
		return isRun;
	}

	private void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public MainThread(FileStorage storage) {
		super("Main_thread_Thread");
		this.storage = storage;
		threads = new ArrayList<Thread>();
	}

	@Override
	public void run() {

		String option = null;
		setRun(true);
		while (isRun()) {
			option = addCorrectCommand();
			if (option.equals("find")) {
				startFindThread();
			} else if (option.equals("list")) {
				startListThread();
			} else if (option.equals("pause")) {
				pauseAllThreads();
			} else if (option.equals("resume")) {
				synchronized (this) {
					this.notifyAll();
				}
			} else if (option.equals("help")) {
				askUser();
			} else if (option.equals("stop")) {
				stopAllThreads(this);
			}
		}
	}

	private void startListThread() {
		ListFileThread threadListFiles = new ListFileThread(storage);
		threads.add(threadListFiles);
		threadListFiles.start();
	}

	private void startFindThread() {
		FindFileThread findFile = crateFindFileThread();
		threads.add(findFile);
		findFile.start();
	}

	/**
	 * Method pause all running threads for searching and printing
	 */
	private void pauseAllThreads() {
		if (threads.size() != 0) {
			for (Thread thread : threads) {
				try {
					synchronized (thread) {
						printThreadStatus(thread);
						thread.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("All stoped");
		}
	}

	/**
	 * Method stops all running threads for searching and printing. After that
	 * stop current thread
	 * 
	 * @param mainThread
	 */
	private void stopAllThreads(Thread mainThread) {
		if (threads.size() != 0) {
			for (Thread thread : threads) {
				try {
					thread.join();
					printThreadStatus(thread);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		setRun(false);
	}

	/**
	 * Method check the input command. If command does not exist, ask again
	 * 
	 * @return - String with command
	 */
	private String addCorrectCommand() {
		Scanner sc = new Scanner(System.in);
		String option = null;
		boolean correctCommand = false;
		do {
			option = sc.nextLine();
			option = option.trim();
			correctCommand = Comand.content(option);
		} while (!correctCommand);
		return option;
	}

	/**
	 * 
	 Asks the user to select a command
	 * 
	 * @return - returns the command that the user has entered
	 */
	private void askUser() {
		System.out
				.printf("\nSelect option:\n1.find\n2.list\n3.stop\n4.pause\n5.resume\n6.help\n");
	}

	/**
	 * 
	 * Asks for the file name to be found and where to start looking. And create
	 * searching thread
	 * 
	 * @return Object thread by type FindFile
	 */
	private FindFileThread crateFindFileThread() {
		Scanner sc = new Scanner(System.in);
		System.out
				.println("Enter the search file and the path, separated with space");
		String[] line = sc.nextLine().split(" ");
		// TODO - add Validate data
		String name = line[0];
		String path = line[1];
		FindFileThread findFileThread = new FindFileThread(storage, name, path);
		return findFileThread;
	}

	private void printThreadStatus(Thread mainThread) {
		System.out.printf("[%s-%s] Status: %s\n", mainThread.getName(),
				mainThread.getId(), mainThread.getState());
	}

}