package p1;

import java.io.Serializable;

public class Textbook implements Serializable {
	private String title;
	private String author;
	private String publisher;
	private double price;
	private String ibsn;

	// getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIbsn() {
		return ibsn;
	}

	public void setIbsn(String ibsn) {
		this.ibsn = ibsn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	// constructors
	public Textbook(String title, String author, String ibsn, double price, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.ibsn = ibsn;
		this.price = price;
		this.publisher = publisher;
	}
 
	public Textbook() {
		super();
	}

	// shallow and deep copy methods
	public Textbook shallowCopy(Textbook textbook) {
		return textbook;
	}

	// public Textbook deepCopy(Textbook textbook) {
	// return new
	// Textbook(textbook.title,textbook.author,textbook.ibsn,textbook.price);
	// }
	// toString method
	
	@Override
	public String toString() {
		return "Textbook [title=" + title + ", author=" + author + ", ibsn=" + ibsn + ", price=" + price + "]";
	}

}
