package com.school.ui;
import java.util.Scanner;
import com.school.student.StudentSchedular;;

public class Admin {

	private static Scanner sc=new Scanner(System.in);
	private static StudentSchedular studSch = new StudentSchedular();
	public static void main(String[] args) {
		showMenu();
	}

	private static void showMenu() {
		
		int choice;
		
		while(true)
		{
			System.out.println("1.add student");
			System.out.println("2.show all students");
			System.out.println("3.show all students in a course");
			System.out.println("4.exit");
			
			System.out.println("Enter your choice");
			choice=sc.nextInt();
			
			
			switch(choice)
			{
			case 1:addStudentDetails();
			       break;
			       
			case 2:showAllStudents();
			        break;
			case 3:reportCourse();
					break;
			case 4: System.out.println("Exiting...");
					return;
			
			default:System.out.println("Sorry entered wrong choice");
				   
			  
				   
			   
			}
			
		}
		
	}
	
	private static void reportCourse() {
		System.out.println("Enter course name");
		String courseName = sc.next();
		studSch.reportCourse(courseName);
	}

	private static void showAllStudents() {
		studSch.showAllStudents();
		
	}

	private static void addStudentDetails() {
		int rollNumber;
		boolean loop;
		do {
			System.out.println("Enter roll number");
			rollNumber=sc.nextInt();
			loop = !studSch.validateRollNumber(rollNumber);
			if (loop) {
				System.out.println("Roll number already in use. Try again");
			}
		}while (loop);
		
		
		System.out.println("Enter name");
		String name = sc.next();
		
		int numCourses;
		do {
			System.out.println("Enter number of courses");
			numCourses = sc.nextInt();
			if (numCourses < 1) {
				System.out.println("Must have at least one cuorse");
			}
		}while (numCourses > 0);
		
		System.out.println(studSch.addStudent(rollNumber, name, numCourses));
		
		for (int i = 0; i < numCourses; i++) {
			System.out.println("Enter course name");
			System.out.println((i + 1) + " out of " + numCourses);
			String courseName = sc.next();
			studSch.addCourse(courseName, rollNumber);
		}
		
	}

}
