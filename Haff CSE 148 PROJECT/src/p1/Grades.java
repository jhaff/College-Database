package p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class Grades implements Serializable {
	private Grade[] grades;
	private int nElems;

	public Grades(int maxSize) {
		grades = new Grade[maxSize + 2];
		nElems = 0;
	}

	public void importData2(int skipLines) {
		File file = new File("data.txt");
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				// if (in.nextLine() != null) {
				for (int i = 0; i < skipLines - 2; i++) {
					in.nextLine();
					// System.out.println("data line skipped");
				}
				// System.out.println(in.nextLine());
				String[] tokens = in.nextLine().split(";");
				String[] tokens2 = new String[1];
				for (int i = 4; i < tokens.length; i++) {
					tokens2 = tokens[i].split(" ");
					Grade grade = new Grade(tokens2[0], tokens2[1]);
					insert(grade);
					// System.out.println("grade inserted");
				}
				// 
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void insert(Grade grade) {
		grades[Util.getLength(grades)] = grade;
		System.out.println("grade inserted");
		System.out.println(Arrays.toString(grades));

	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(grades[i]);
		}
		System.out.println();

		// for(Grade grade : grades) {
		// System.out.println(grade);
		// }
	}

	public Grade[] getGrades() {
		return grades;
	}

	public void setGrades(Grade[] grades) {
		this.grades = grades;
	}

}
