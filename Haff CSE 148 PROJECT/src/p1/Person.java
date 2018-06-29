package p1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Person implements Serializable {
	private String firstName;
	private String lastName;
	private String id;
	private static int ID;;
	private String phoneNumber;

	// getters and setters

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// constructors

	public Person() {
		super();
		ID++;
		this.id = String.valueOf(ID);
	}
	
	public static void load() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("PersonClass.dat");
			ois = new ObjectInputStream(fis);
			ID = (int) ois.readObject();
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
			FileOutputStream fos = new FileOutputStream("PersonClass.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ID);
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
		System.out.println("ID saved to PersonClass.dat");
		
	}
 
	public Person(String firstName, String lastName, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		ID++;
		this.id = String.valueOf(ID);
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", phoneNumber="
				+ phoneNumber + "]";
	}

}
