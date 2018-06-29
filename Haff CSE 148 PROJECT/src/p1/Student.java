package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Student extends Person {

	private double gpa;
	private String major;
	private CourseBag courseBag;
	int counter = 0;
	int gradesCounter;
	Grades grades = new Grades(6);
	Grade[] gradeArray = grades.getGrades();

	public Student(String firstName, String lastName, String phoneNumber, String major) {
		super();
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setPhoneNumber(phoneNumber);
		setMajor(major);
		this.courseBag = new CourseBag(6);
	}

	public double convertToPoints(String letterGrade) {
		if (letterGrade.equals("A")) {
			return 4.0;
		} else if (letterGrade.equals("B+")) {
			return 3.33;
		} else if (letterGrade.equals("B")) {
			return 3.0;
		} else if (letterGrade.equals("C+")) {
			return 2.33;
		} else if (letterGrade.equals("C")) {
			return 2.0;
		} else if (letterGrade.equals("D+")) {
			return 1.33;
		} else if (letterGrade.equals("D")) {
			return 1.0;
		} else {
			return 0.0;
		}
	}

	public static void importStudentData(File file) { // NOTE: This method is only for student data containing TAKEN
														// courses
		
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				String[] tokens = data.split("[;]");
				Student s = new Student(tokens[0], tokens[1], tokens[2], tokens[3]);
				for (int i = 4; i < tokens.length; i++) {
					s.getCourseBag().addToTakenCourseBag(tokens[i]);
				}
				// addStudent(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insertCourseToTakenCourseBag(String course) {
		if (courseBag.getTakenCourseArray()[counter] == null) {
			courseBag.addToTakenCourseBag(course);
			counter++;
		}
	}
	// public double calculateGPA(String[] courses, Course[] courseBag) {
	//// Course[] courseArray = courseBag;
	// double gradePoints = 0;
	// int totalCredits = 0;
	// for(int i = 0; i < courses.length; i++) {
	// for(int j = 0; j < courseBag.length; j++) {
	// String[] tokens = courses[i].split("[;]");
	// String[] tokens2 = new String[1];
	// System.out.println(tokens.length);
	//
	// for (int k = 0; k < tokens.length; k++) {
	// tokens2 = tokens[k].split("[ ]");
	// }
	//
	// System.out.println(tokens2[0]);
	// System.out.println(courseBag[0].getCourseTitle());
	//
	// if(tokens2[0].equals(courseBag[j].getCourseTitle())) {
	//
	// gradePoints += convertToPoints(tokens2[1]) *
	// courseBag[j].getNumberOfCredits();
	// totalCredits += courseBag[j].getNumberOfCredits();
	// break;
	// }
	// }
	// }
	// return gradePoints / totalCredits;
	// }

	public void printGPA() {
		System.out.println(
				this.getFirstName() + "'s GPA is:" + calculateGPA());
	}
	

	public double calculateGPA() {
		Course[] courseBag2 = MasterCourseBag.masterCourseArray;
		System.out.println(courseBag2);
		double gradePoints = 0;
		int totalCredits = 0;
		for (int i = 0; i < Util.getLength(courseBag.getTakenCourseArray()); i++) {
			for (int j = 0; j < Util.getLength(courseBag2); j++) {
				if (gradeArray[i] != null) {
					if(courseBag2[i] != null) {
					// System.out.println(courseArray[j].getCourseTitle());
					// System.out.println(courseBag.getTakenCourseArray().length);

					if (gradeArray[i].getCourseNumber().equals(courseBag2[j].getCourseTitle())) {
						gradePoints += convertToPoints(gradeArray[i].getLetterGrade())
								* courseBag2[j].getNumberOfCredits();
						totalCredits += courseBag2[j].getNumberOfCredits();
						// System.out.println(gradePoints);
						// System.out.println(totalCredits);
						// System.out.println(grades[i]);
						break;
					}
					}
				}
			}
		}
		System.out.println("GPA Calculated for " + this.getFirstName() + " " + this.getLastName() + "!");
		System.out.println("It's " + gradePoints/totalCredits);
		System.out.println("Total Credits:  " + totalCredits);
		System.out.println("Grade Pts:  " + gradePoints);
		return (gradePoints) / (totalCredits);
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public CourseBag getCourseBag() {
		return courseBag;
	}

	public void setCourseBag(CourseBag courseBag) {
		this.courseBag = courseBag;
	}

	public Grades getGrades() {
		return grades;
	}

	public void setGrades(Grades grades) {
		this.grades = grades;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student: firstName=" + super.getFirstName() + ", lastName=" + super.getLastName() + ", id="
				+ super.getId() + ", phoneNumber=" + super.getPhoneNumber() + " major=" + major +"]";
	}

}
