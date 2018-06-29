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

public class TextbookBag implements Serializable {
	private static Textbook textbookArray[];
	private int nElems;
	int counter = 0;
	int counter2 = 0;

	public TextbookBag(int maxSize) {
		textbookArray = new Textbook[maxSize];
		this.textbookArray = textbookArray;
		nElems = 0;
	}

	public static void importData(File file) {
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				String[] tokens = data.split("[,]");
				Textbook b = new Textbook(tokens[0], tokens[1], tokens[2], Double.parseDouble(tokens[3]), tokens[4]);
				add(b);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void add(Textbook b) {
		textbookArray[Util.getLength(textbookArray) + 1] = b;
//		counter++;
	}

	public Textbook find(String ibsn) {
		for (int i = 0; i < counter; i++) {
			if (textbookArray[i] instanceof Textbook && ibsn.equals(textbookArray[i].getIbsn())) {
				return textbookArray[i];
			}
		}
		return null;
	}
	

	public void delete(String ibsn) {
		for (int i = 0; i < counter; i++) {
			if (textbookArray[i] instanceof Textbook && ibsn.equals(textbookArray[i].getIbsn())) {
				textbookArray[i] = null;
				removeElt(textbookArray, i);
			}
		}
		// return null;
	}

	public void exportData(String fileName) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(fileName);
			for (int i = 0; i < counter; i++) {
				file.println(textbookArray[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
	}

	public static void removeElt(Textbook[] arr, int remIndex) {
		for (int i = remIndex; i < arr.length - 1; i++) {
			arr[i] = arr[i + 1];
		}
	}

	public void load() {
		FileInputStream fis;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("TextbookArray.dat");
			ois = new ObjectInputStream(fis);
			textbookArray = (Textbook[]) ois.readObject();
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
			FileOutputStream fos = new FileOutputStream("TextbookArray.dat");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(textbookArray);
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
		System.out.println("textBookArray saved to TextbookArray.dat");
	}

	@Override
	public String toString() {
		return "TextbookBag [textbookArray=" + Arrays.toString(textbookArray) + "]";
	}

	public void display() {
		System.out.println(this);
	}

}
