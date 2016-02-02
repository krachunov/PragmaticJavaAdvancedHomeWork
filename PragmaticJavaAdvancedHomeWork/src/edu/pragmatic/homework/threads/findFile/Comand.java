package edu.pragmatic.homework.threads.findFile;

public enum Comand {
	FIND("find"), LIST("list"), STOP("stop"), PAUSE("pause"), RESUME("resume"), HELP(
			"help");

	String comand;

	private Comand(String comand) {
		this.comand = comand;
	}

	/**
	 * 
	 * @param string
	 *            - command
	 * @return - returns true if the command exists
	 */
	public static boolean content(String comand) {
		for (Comand enumComand : Comand.values()) {
			if (enumComand.comand.equals(comand)) {
				return true;
			}
		}
		return false;
	}
}
