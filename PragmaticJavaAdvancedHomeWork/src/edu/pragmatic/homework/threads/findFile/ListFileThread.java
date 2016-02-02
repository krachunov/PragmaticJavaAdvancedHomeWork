package edu.pragmatic.homework.threads.findFile;

import java.io.File;
import java.util.Stack;

public class ListFileThread extends Thread {
	private FileStorage storage;
	private boolean isWait;

	public boolean isWait() {
		return isWait;
	}

	public void setWait(boolean isWait) {
		this.isWait = isWait;
	}

	public ListFileThread(FileStorage storage) {
		super("Print_File_Thread");
		this.storage = storage;
	}

	@Override
	public void run() {
		System.out.printf("Thread: [%s-%s]: start\n", this.getName(),
				this.getId());

		Stack<File> fileList = storage.getFileList(this);

		for (File file : fileList) {
			System.out.printf("%s: %s\n", file.getName(),
					file.getAbsolutePath());
		}
	}

}