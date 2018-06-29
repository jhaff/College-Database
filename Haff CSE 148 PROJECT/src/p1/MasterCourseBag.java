package p1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class MasterCourseBag implements Serializable {
	public static Course masterCourseArray[];
	private static int nElems;
	int counter = 0;
	// int counter2 = 0;

	public MasterCourseBag(int maxSize) {
		masterCourseArray = new Course[maxSize];
		this.masterCourseArray = masterCourseArray;
		nElems = 0;
//		if (masterCourseArray[1] == null) {
//			masterCourseArray[1] = new Course(); //example course
//		}
		masterCourseArray[0] = new Course("SEMINAR1", 0, "none", 1);
		masterCourseArray[1] = new Course("SEMINAR2", 1, "none", 1);

//				courseTitle, int cNum, String textbookISBNAssigned, int numberOfCredits) {
	}

	public Course[] getMasterCourseArray() {
		return masterCourseArray;
	}

	public void setMasterCourseArray(Course[] masterCourseArray) {
		this.masterCourseArray = masterCourseArray;
	}

	public static void importData(File file) {
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				String[] tokens = data.split("[,]");
				Course c = new Course(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
				add(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void add(Course c) {
		Util.insertToArrayProper(MasterCourseBag.masterCourseArray, c);
		System.out.println("course inserted to masterCourseArray.");
	}

	public static Course find(int cnum) {
		for (int i = 0; i < Util.getLength(masterCourseArray); i++) {
			if (masterCourseArray[i] instanceof Course && cnum == (masterCourseArray[i].getCourseNumber())) {
				return masterCourseArray[i];
			}
		}
		return null;
	}
	

	public static void delete(int cnum) {
		for (int i = 0; i < Util.getLength(masterCourseArray); i++) {
			if (masterCourseArray[i] instanceof Course && cnum == (masterCourseArray[i].getCourseNumber())) {
				System.out.println("Removing " + masterCourseArray[i].getCourseTitle());
				masterCourseArray[i] = null;
				removeElt(masterCourseArray, i);
			}
		}
		// return null;
	}

	public static void exportData(String fileName) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(fileName);
			for (int i = 0; i < Util.getLength(masterCourseArray); i++) {
				file.println(masterCourseArray[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
	}

	public static void removeElt(Course[] arr, int remIndex) {
		for (int i = remIndex; i < arr.length - 1; i++) {
			arr[i] = arr[i + 1];
		}
	}

	public static void load() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("MasterCourseArray.dat");
			ois = new ObjectInputStream(fis);
			masterCourseArray = (Course[]) ois.readObject();
			nElems = (Integer) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void save() {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("MasterCourseArray.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(masterCourseArray);
			oos.writeObject(nElems);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("masterCourseArray saved to MasterCourseArray.dat");
	}

	@Override
	public String toString() {
		return "MasterCourseBag [masterCourseArray=" + Arrays.toString(masterCourseArray) + "]";
	}

	public void display() {
		System.out.println(this);
	}

}
