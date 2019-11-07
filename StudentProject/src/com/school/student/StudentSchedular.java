package com.school.student;
public class StudentSchedular {
	
	private Student[] students = new Student[10];
	private int counterStudent;
	
	public String addStudent(int rollNumber, String name, int numCourses)
	{
		
		students[counterStudent++]=new Student(rollNumber,name, numCourses);
		return "Student added successfully";
		
	}
	
	public void showAllStudents()
	{
		for (Student student : students) {
			if (student == null)
				return;
			System.out.println(student.getRollNumber());
			System.out.println(student.getName() + "\n");
		}
	}

	public boolean validateRollNumber(int rollNumber) {
		Student student = getStudent(rollNumber);
		if (student != null) {
			return false;
		}
		
		return true;
	}
	
	public void addCourse(String courseName, int rollNumber) {
		Student student = getStudent(rollNumber);
		student.addCourse(courseName);
	}
	
	public void reportCourse(String courseName) {
		for (Student student : students) {
			if (student == null)
				break;
			for (String course : student.getCourses()) {
				if (course.equals(courseName)) {
					System.out.println(student.getName());
				}
			}
		}
	}
	
	private Student getStudent(int rollNumber) {
		for (Student student : students) {
			if (student == null)
				return null;
			if (rollNumber == student.getRollNumber()) {
				return student;
			}
		}
		
		return null;
	}
}
