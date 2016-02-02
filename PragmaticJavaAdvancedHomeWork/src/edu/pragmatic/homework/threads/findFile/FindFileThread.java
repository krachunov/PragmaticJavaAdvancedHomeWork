package edu.pragmatic.homework.threads.findFile;

import java.io.File;
import java.util.Stack;

public class FindFileThread extends Thread {
	private FileStorage storage;

	private String fileName;
	private String path;

	public FindFileThread(FileStorage storage, String fileName, String path) {
		super("File_Finder_Thread");
		this.storage = storage;
		this.fileName = fileName;
		this.path = path;
	}

	@Override
	public void run() {
		System.out.printf("Thread: [%s-%s]: start\n", this.getName(),
				this.getId());

		Stack<File> fileList = new Stack<>();
		findingFiles(fileName, path, fileList);

		storage.setFileList(fileList);

	}

	/**
	 * Use the form on the file name and path. For example: test.txt
	 * c:\testFolder
	 * 
	 * @param fileName
	 *            - the file's name who we looking with extension
	 * @param path
	 *            - path where we star looking
	 * @param fileList
	 *            - list with found files
	 */
	private void findingFiles(String fileName, String path, Stack<File> fileList) {
		File directory = new File(path);
		boolean isRegularDirectory = directory.isDirectory()
				&& directory.canRead();

		// it is directory
		if (isRegularDirectory) {
			File[] subDirectory = directory.listFiles();
			if (subDirectory != null) {
				for (File file : subDirectory) {
					// recursive
					findingFiles(fileName, file.getAbsolutePath(), fileList);
				}
			}
			// it is file
		} else {
			boolean isDesiredFiles = directory.getName().equals(fileName);
			if (isDesiredFiles) {
				fileList.add(directory);
			}
		}
	}
}