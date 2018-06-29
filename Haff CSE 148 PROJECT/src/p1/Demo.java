package p1;

public class Demo {

	public static void main(String[] args) {

		PersonBag personBag = new PersonBag(4);

		TextbookBag textbookBag = new TextbookBag(7);

		MasterCourseBag actualMasterCourseBag = new MasterCourseBag(20);
		
//		textbookBag.importData("TextbookList.txt");
		
		System.out.println("Displaying Textbook Bag");
		textbookBag.display();
		
		System.out.println("Displaying Textbook with ISBN 41134");
		System.out.println(textbookBag.find("41134"));
		
		System.out.println("Deleting Textbook with ISBN 41134");
		textbookBag.delete("41134");
		
		System.out.println("Displaying Textbook Bag");
		textbookBag.display();
	
//		actualMasterCourseBag.importData("MasterCourseList.txt")

		System.out.println("Displaying Master Course Bag");
		actualMasterCourseBag.display();

		System.out.println("Displaying course with course num 1");

		System.out.println(actualMasterCourseBag.find(1));

		System.out.println("Deleting course with course num 1");
		actualMasterCourseBag.delete(1);
		
		System.out.println("Displaying Master Course Bag");
		actualMasterCourseBag.display();

		personBag.importStudentData("data.txt");

		System.out.println("Displaying Person Bag");
		personBag.display();

		System.out.println("Deleting student with ID#4");
		personBag.deletePersonById("4");

		System.out.println("Displaying Person Bag");
		personBag.display();

		Student student2 = (Student) personBag.findPersonById("1");

		System.out.println(student2);

		Student student1 = (Student) personBag.findPersonById("2");

		System.out.println(student1);

		student2.getGrades().importData2((Integer.parseInt(student2.getId())));

		student2.printGPA();

		student1.getGrades().importData2(Integer.parseInt(student1.getId()) + 1);

		student1.printGPA();

		System.out.println("NOTE: This GPA calculator uses a B+=3.3 etc. system.");

		System.out.println(student1.getCourseBag().getTakenCourseArray().length);

		personBag.save();
		textbookBag.save();
		actualMasterCourseBag.save();
		
		System.out.println("Exporting bags...");
		
		personBag.exportData("personBagExport.txt");
		textbookBag.exportData("textbookBagExport.txt");
		actualMasterCourseBag.exportData("masterCourseBagExport.txt");
		
		System.out.println("Done!");

	}
}
