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

public class PersonBag implements Serializable {

	private Person[] personArray;
//	int counter;
	
	

	public PersonBag(int maxSize) {
		personArray = new Person[maxSize];
		this.personArray = personArray;
		nElems = 0;
		int counter = 0;
//		personArray[0] = new Faculty("Ben","Chen", "631 555 9000" , 100000.0, "Chair");
	}

	public Person[] getPersonArray() {
		return personArray;
	}

	public void setPersonArray(Person[] personArray) {
		this.personArray = personArray;
	}

	private int nElems;
	
	

	public void insert(Person person) {
		System.out.println(Util.getLength(personArray));
		if (personArray[Util.getLength(personArray) + 1] == null) {
			personArray[Util.getLength(personArray) + 1] = person;
//			counter++;
//			System.out.println(counter);
			System.out.println("Person Inserted: " + person);
			System.out.println("Person Bag: " + this);
		}
			else {
				System.out.println("person insertion failure");
			}
		}
	
	
	
	public Person findPersonById(String id) {
		for (int i = 0; i < Util.getLength(personArray); i++) {
			if (personArray[i] instanceof Person && id.equals(personArray[i].getId())) {
				System.out.println(personArray[i].getId());
				return personArray[i];
			}
		}
		return null;
	}

	public Person deletePersonById(String id) {
		for (int i = 0; i < Integer.parseInt(id); i++) {
			if (id.equals(personArray[i].getId())) {
				personArray[i] = null;
				removeElt(personArray, i);
				System.out.println("person deleted");
			}
		}
		return null;
	}

	public void importStudentData(String fileName) { // NOTE: This method is only for student data containing TAKEN
														// courses
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				String[] tokens = data.split("[;]");
				Student s = new Student(tokens[0], tokens[1], tokens[2], tokens[3]);
				for (int i = 4; i < tokens.length; i++) {
					s.insertCourseToTakenCourseBag(tokens[i]);
					s.gradesCounter++;
				}
				insert(s);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void importFacultyData(String fileName) {
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				String[] tokens = data.split("[,]");
				Faculty f = new Faculty(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4]);
				addFaculty(f);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addStudent(Student s) {
		System.out.println(Util.getLength(personArray));
		if (personArray[Util.getLength(personArray) + 1] == null) {
			personArray[Util.getLength(personArray) + 1] = s;
//			counter++;
//			System.out.println(counter);
			System.out.println("Student Inserted: " + s);
			System.out.println("Person Bag: " + this);
		}
			else {
				System.out.println("person insertion failure");
			}
		
		}
	

	public void addFaculty(Faculty f) {
		System.out.println(Util.getLength(personArray));
		if (personArray[Util.getLength(personArray) + 1] == null) {
			personArray[Util.getLength(personArray) + 1] = f;
//			counter++;
//			System.out.println(counter);
			System.out.println("Faculty Inserted: " + f);
			System.out.println("Person Bag: " + this);
		}
			else {
				System.out.println("person insertion failure");
			}
		
		}

	public void exportData(String fileName) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(fileName);
			for (int i = 0; i < Util.getLength(personArray); i++) {
				file.println(personArray[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
	}

	public static void removeElt(Person[] arr, int remIndex) {
		for (int i = remIndex; i < arr.length - 1; i++) {
			arr[i] = arr[i + 1];
		}
	}

	public void load() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("PersonBag.dat");
			ois = new ObjectInputStream(fis);
			personArray = (Person[]) ois.readObject();
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

	public void save() {

		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream("PersonBag.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(personArray);
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
		
		System.out.println("personBag saved to PersonBag.dat");
		
	}

	@Override
	public String toString() {
		return "PersonBag [personArray=" + Arrays.toString(personArray) + "]";
	}

	public void display() {
		System.out.println(this);
	}

}
