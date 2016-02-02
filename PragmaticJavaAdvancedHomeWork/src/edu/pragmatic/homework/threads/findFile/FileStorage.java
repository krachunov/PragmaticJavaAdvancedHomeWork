package edu.pragmatic.homework.threads.findFile;

import java.io.File;
import java.util.Stack;

public class FileStorage {
	private Stack<File> fileList;

	public FileStorage() {
		fileList = new Stack<File>();
	}

	public Stack<File> getFileList(ListFileThread listFileThread) {
		if (fileList.isEmpty()) {
			try {
				synchronized (this) {
					String nameThread = listFileThread.getName();
					long threadID = listFileThread.getId();
					System.out.printf("Thread who is waiting [%s-%s] ",
							nameThread, threadID);
					wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return fileList;
	}

	public void setFileList(Stack<File> fileList) {
		this.fileList.addAll(fileList);

		synchronized (this) {
			notifyAll();
		}
	}
}
