package p1;

import java.io.Serializable;

public class Course implements Serializable {
	private String courseTitle;
	private int courseNumber;
	private String textbookISBNAssigned;
	private int numberOfCredits;
	int courseCounter = 1;

	private final int MIN_CREDITS = 0;
	private final int MAX_CREDITS = 4;

	// getters and setters

	public Course() {
		super();
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	// public Textbook getTextbookAssigned() {
	// return textbookAssigned;
	// }
	// public void setTextbookAssigned(Textbook textbookAssigned) {
	// this.textbookAssigned = textbookAssigned;
	// }
	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	// constructors. 

	public Course(String courseTitle, String textbookISBNAssigned, int numberOfCredits) {
		super();
		this.courseTitle = courseTitle;
		this.courseNumber = Helper.courseCounter++;
		this.textbookISBNAssigned = textbookISBNAssigned;
		this.numberOfCredits = numberOfCredits;
		checkCredits(numberOfCredits);
	}
	public Course(String courseTitle, int cNum, String textbookISBNAssigned, int numberOfCredits) {
		super();
		this.courseTitle = courseTitle;
		this.courseNumber = cNum;
		this.textbookISBNAssigned = textbookISBNAssigned;
		this.numberOfCredits = numberOfCredits;
		checkCredits(numberOfCredits);
	}

	private void checkCredits(int credits) {
		if (credits < MIN_CREDITS) {
			try {
				throw new InvalidCreditsException(
						"A course has less than 1 credit, which is invalid. Check your data!");
			} catch (InvalidCreditsException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
				// checkGPA(getNewGPA());
			}
			// this.gpa = MIN_GPA;
		} else if (credits > MAX_CREDITS) {
			try {
				throw new InvalidCreditsException("\n WARNING: The course titled " + (this.courseTitle)
						+ " has more than 4 credits, which is invalid. Check your data!\n");
			} catch (InvalidCreditsException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
				// checkGPA(getNewGPA());
			}
			// this.gpa = MIN_GPA;
		} else {
			this.numberOfCredits = credits;
		}

	}

	@Override
	public String toString() {
		return courseTitle + " [ courseNumber=" + courseNumber + ", textbookISBNAssigned="
				+ textbookISBNAssigned + ", numberOfCredits=" + numberOfCredits + "]";
	}

}
