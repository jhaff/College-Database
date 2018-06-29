package p1;

public class Faculty extends Person {
	private double salary;
	private String title;

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// constructors.

	public Faculty() {
		super();
	}

	public Faculty(String firstName, String lastName, String phoneNumber, Double salary, String title) {
		super();
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setPhoneNumber(phoneNumber);
		setSalary(salary);
		setTitle(title);
		System.out.println("faculty created");
		System.out.println(this);
	}
	@Override
	public String toString() {
		return "Faculty: firstName=" + super.getFirstName() + ", lastName=" + super.getLastName() + ", id="
				+ super.getId() + ", phoneNumber=" + super.getPhoneNumber() + " salary=" + salary + "title=" + title + "]";
	}
}
