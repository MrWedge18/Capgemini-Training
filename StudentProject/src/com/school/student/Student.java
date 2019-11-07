package com.school.student;

public class Student {
	
	private int rollNumber;
	private String name;
	private String[] courses;
	private int counterCourse;
	
	public Student(int rollNumber, String name, int numCourses) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.courses = new String[numCourses];
		this.counterCourse = 0;
	}
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addCourse(String courseName) {
		if (counterCourse < courses.length) {
			this.courses[counterCourse++] = courseName;
		}
	}
	
	public String[] getCourses() {
		return this.courses;
	}

}
