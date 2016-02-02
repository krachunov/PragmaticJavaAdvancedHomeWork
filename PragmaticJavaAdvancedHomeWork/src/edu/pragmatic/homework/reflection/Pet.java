package edu.pragmatic.homework.reflection;

import java.util.ArrayList;
import java.util.List;

public class Pet {
	private int age;
	private String name;
	private Pet[] children;

	public Pet() {
	}

	public Pet(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return 0;
	}

	public void setAge(int age) throws IllegalArgumentException {
		if (age < 0)
			throw new IllegalArgumentException("Age can't be negative");
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pet[] getChildren() {
		return children;
	}

	public void setChildren(Pet[] pets) {
		this.children = pets;
	}

	@Override
	public String toString() {
		return String.format("Name = %s\nAge = %s ", name, age);
	}

}
