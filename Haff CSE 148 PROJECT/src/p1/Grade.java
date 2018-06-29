package p1;

import java.io.Serializable;

public class Grade implements Serializable {
	private String courseNumber;
	private String letterGrade;

	public Grade(String courseNumber, String letterGrade) {
		super();
		this.courseNumber = courseNumber;
		this.letterGrade = letterGrade;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	} 

	@Override
	public String toString() {
		return "Grade [courseNumber=" + courseNumber + ", letterGrade=" + letterGrade + "]";
	}

}
//
