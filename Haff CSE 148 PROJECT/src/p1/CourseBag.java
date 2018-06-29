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

public class CourseBag implements Serializable {

	private String currentCourseArray[];
	private String takenCourseArray[];
	private String futureCourseArray[];

	private String masterCourseArray[];
	private int nElems = 0; // currentCourseArray
	private int nElems2 = 0; // takenCourseArray
	private int nElems3 = 0; // futureCourseArray

	int counter = 0; // currentCourseArray
	int counter2 = 0; // takenCourseArray
	int counter3 = 0; // futureCourseArray
	
	int maximumArraySize;
	// public void Grades(int maxSize) {
	//
	// grades = new Grade[maxSize];
	// gradeElems = 0;
	// }

	public CourseBag(int maxSize) {
		currentCourseArray = new String[maxSize];
		takenCourseArray = new String[maxSize];
		futureCourseArray = new String[maxSize];

		this.maximumArraySize = maxSize;
		
		// this.currentCourseArray = currentCourseArray;
		// this.takenCourseArray = takenCourseArray;
		// this.futureCourseArray = futureCourseArray

	}

	public String[] getCurrentCourseArray() {
		return currentCourseArray;
	}

	public void setCurrentCourseArray(String[] currentCourseArray) {
		this.currentCourseArray = currentCourseArray;
	}

	public String[] getTakenCourseArray() {
		return takenCourseArray;
	}

	public void setTakenCourseArray(String[] takenCourseArray) {
		this.takenCourseArray = takenCourseArray;
	}

	public String[] getFutureCourseArray() {
		return futureCourseArray;
	}

	public void setFutureCourseArray(String[] futureCourseArray) {
		this.futureCourseArray = futureCourseArray;
	}

	// for importing a list of already taken courses; a transcript if you will
	public void importData(String fileName) {
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				String[] tokens = data.split("[,]");
				String c = new String(tokens[1]);
				addToTakenCourseBag(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addToCurrentCourseBag(String c) {
		System.out.println(Util.getLength(currentCourseArray));
		if (currentCourseArray[Util.getLength(currentCourseArray)] == null) {
			currentCourseArray[Util.getLength(currentCourseArray)] = c;
			
			System.out.println("Course Inserted: " + c);
			System.out.println("Person Bag: " + this);
		}
	
	}

	public void addToTakenCourseBag(String c) {
		if (currentCourseArray[Util.getLength(currentCourseArray)] == null) {
			currentCourseArray[Util.getLength(currentCourseArray)] = c;
		
			System.out.println("Course Inserted: " + c);
			System.out.println("Person Bag: " + this);
		}

	}

	public void addToFutureCourseBag(String c) {
		if (currentCourseArray[Util.getLength(currentCourseArray)] == null) {
			currentCourseArray[Util.getLength(currentCourseArray)] = c;
			
			System.out.println("Course Inserted: " + c);
			System.out.println("Person Bag: " + this);
		}

	}

	public String find(String cnum) {
		for (int i = 0; i < counter; i++) {
			if (currentCourseArray[i] instanceof String && cnum.equals(currentCourseArray[i])) {
				return currentCourseArray[i];
			} else if (takenCourseArray[i] instanceof String && cnum.equals(takenCourseArray[i])) {
				return takenCourseArray[i];
			} else if (futureCourseArray[i] instanceof String && cnum.equals(futureCourseArray[i])) {
				return futureCourseArray[i];
			}
		}
		return null;
	}

	public void delete(String cnum) {
		for (int i = 0; i < this.maximumArraySize; i++) {
			if (currentCourseArray[i] instanceof String && cnum.equals(currentCourseArray[i])) {
				currentCourseArray[i] = null;
				removeElt(currentCourseArray, i);
				System.out.println("course removed from currentCourseArray");
			} else if (takenCourseArray[i] instanceof String && cnum.equals(takenCourseArray[i])) {
				takenCourseArray[i] = null;
				removeElt(takenCourseArray, i);
				System.out.println("course removed from takenCourseArray");

			} else if (futureCourseArray[i] instanceof String && cnum.equals(futureCourseArray[i])) {
				futureCourseArray[i] = null;
				removeElt(futureCourseArray, i);
				System.out.println("course removed from futureCourseArray");

			}
		}
	}
	// return null;

	public void exportCurrentCourseData(String fileName) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(fileName);
			for (int i = 0; i < counter; i++) {
				file.println(currentCourseArray[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
	}

	public void exportTakenCourseData(String fileName) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(fileName);
			for (int i = 0; i < counter2; i++) {
				file.println(takenCourseArray[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
	}

	public void exportFutureCourseData(String fileName) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(fileName);
			for (int i = 0; i < counter3; i++) {
				file.println(futureCourseArray[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
	}

	public static void removeElt(String[] arr, int remIndex) {
		for (int i = remIndex; i < arr.length - 1; i++) {
			arr[i] = arr[i + 1];
		}
		arr[arr.length - 1] = null;

	}

	public void loadCurrentCourseData() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("CurrentCourseArray.dat");
			ois = new ObjectInputStream(fis);
			currentCourseArray = (String[]) ois.readObject();
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

	public void loadTakenCourseData() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("TakenCourseArray.dat");
			ois = new ObjectInputStream(fis);
			takenCourseArray = (String[]) ois.readObject();
			nElems2 = (Integer) ois.readObject();
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

	public void loadFutureCourseData() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("FutureCourseArray.dat");
			ois = new ObjectInputStream(fis);
			futureCourseArray = (String[]) ois.readObject();
			nElems3 = (Integer) ois.readObject();
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

	public void saveCurrentCourseData() {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("CurrentCourseArray.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(currentCourseArray);
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
	}

	public void saveTakenCourseData() {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("TakenCourseArray.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(takenCourseArray);
			oos.writeObject(nElems2);
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
	}

	public void saveFutureCourseData() {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("FutureCourseArray.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(futureCourseArray);
			oos.writeObject(nElems3);
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
	}

	public void display() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		return "CourseBag [currentCourseArray=" + Arrays.toString(currentCourseArray) + ", takenCourseArray="
				+ Arrays.toString(takenCourseArray) + ", futureCourseArray=" + Arrays.toString(futureCourseArray) + "]";
	}

}
