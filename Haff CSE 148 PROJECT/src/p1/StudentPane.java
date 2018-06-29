package p1;
import java.util.Arrays;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class StudentPane {
	private GridPane gridPane;
	private Button addBtn;
	private Button findBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button gpaBtn;


	private TextField firstNameField;
	private TextField lastNameField;
	private TextField phoneField;
	private TextField majorField;
	private Student selectedStudent = null;

	public StudentPane(College college) {
		final int BUTTON_WIDTH = 115;
		final int TEXTFIELD_WIDTH = 245;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);
		firstNameField = new TextField();
		firstNameField.setPromptText("First Name");
		firstNameField.setPrefWidth(TEXTFIELD_WIDTH);
		lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		lastNameField.setPrefWidth(TEXTFIELD_WIDTH);
		majorField = new TextField();
		majorField.setPromptText("Major");
		majorField.setPrefWidth(TEXTFIELD_WIDTH);
		phoneField = new TextField();
		phoneField.setPromptText("Phone");
		phoneField.setPrefWidth(TEXTFIELD_WIDTH);
		Alert alert;
		addBtn = new Button("ADD");
		addBtn.setPrefWidth(BUTTON_WIDTH);
		
		
		addBtn.setOnAction(e -> {
			Student student = new Student(firstNameField.getText(), lastNameField.getText(), phoneField.getText(), majorField.getText());
			college.getPersonBag().addStudent(student);
			App.updatePersonList(college.getPersonBag().getPersonArray());
			firstNameField.clear();
			lastNameField.clear();
			phoneField.clear();
			majorField.clear();
		});
		
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		
		TextInputDialog dialog = new TextInputDialog("ID");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Information Needed");
		dialog.setContentText("Please enter the Student's ID number:");
		
		Alert alert2 = new Alert(AlertType.WARNING);
		alert2.setTitle("Warning Dialog");
		alert2.setHeaderText("Warning!");
		
		findBtn = new Button("FIND");
		findBtn.setPrefWidth(BUTTON_WIDTH);
		
		findBtn.setOnAction(e -> {  // lambda expression
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Your ID: " + result.get());
			    String currentId = new String();
			    currentId = result.get();
				college.getPersonBag().findPersonById(currentId);
				selectedStudent = (Student) college.getPersonBag().findPersonById(currentId);
				System.out.println(selectedStudent);
//				App.showCousePane(selectedStudent);
				firstNameField.setText(selectedStudent.getFirstName());
				lastNameField.setText(selectedStudent.getLastName());
				phoneField.setText(selectedStudent.getPhoneNumber());
				majorField.setText(selectedStudent.getMajor());

				
			}
		});
		
		
		gpaBtn = new Button("GPA Lookup");
		gpaBtn.setPrefWidth(150);
		gpaBtn.setPrefHeight(70);
		
		gpaBtn.setOnAction(e ->{
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Your ID: " + result.get());
			    String currentId = new String();
			    currentId = result.get();
				college.getPersonBag().findPersonById(currentId);
				selectedStudent = (Student) college.getPersonBag().findPersonById(currentId);
				
				System.out.println(selectedStudent);
//				App.showCousePane(selectedStudent);
				
				alert.setHeaderText("GPA Calculated.");
				alert.setContentText(selectedStudent.getFirstName() + " " + 
						selectedStudent.getLastName() + "'s GPA is" + 
						selectedStudent.calculateGPA());
				alert.showAndWait();
				
			}
		});

		
		removeBtn = new Button("Remove");
		removeBtn.setPrefWidth(BUTTON_WIDTH);
		
		removeBtn.setOnAction(e -> {  // lambda expression
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				String currentId = new String();
			    currentId = result.get();
				college.getPersonBag().findPersonById(currentId);
				selectedStudent = (Student) college.getPersonBag().findPersonById(currentId);
			    System.out.println("Your ID: " + result.get());
			    System.out.println(college.getPersonBag());
			    if (selectedStudent != null) {
			    		alert2.setContentText("Are you sure you want to remove " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " ?");
			    		alert2.showAndWait();
			    		
			    }
			    else {
			    	Alert alert3 = new Alert(AlertType.ERROR);
					alert3.setTitle("Error Dialog");
					alert3.setHeaderText("Warning!");
					alert3.setContentText("No student currently selected");
					alert3.showAndWait();
			    }
			}
			
			
			
		});
		
		updateBtn = new Button("Update");
		updateBtn.setPrefWidth(BUTTON_WIDTH);
		
		updateBtn.setOnAction(e -> {  // lambda expression
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Your ID: " + result.get());
			    String currentId = new String();
			    currentId = result.get();
				college.getPersonBag().findPersonById(currentId);
				selectedStudent = (Student) college.getPersonBag().findPersonById(currentId);
//				System.out.println(selectedStudent);
//				System.out.println(college.getPersonBag());
				selectedStudent.setFirstName(firstNameField.getText());
				selectedStudent.setLastName(lastNameField.getText());
				selectedStudent.setPhoneNumber(phoneField.getText());
				selectedStudent.setMajor(majorField.getText());
				
				
				alert.setHeaderText("Student updated.");
				alert.setContentText(selectedStudent.getFirstName() + "'s file has been updated.");
				alert.showAndWait();
				
				App.updatePersonList(college.getPersonBag().getPersonArray());
				

			}
		});
		

		HBox btnBox = new HBox(20);

		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(addBtn, findBtn, removeBtn, updateBtn, gpaBtn);
		
		gridPane.add(firstNameField, 0, 0, 2, 1);
		gridPane.add(lastNameField, 2, 0, 2, 1);
		gridPane.add(phoneField, 4, 0, 2, 1);
		gridPane.add(majorField, 6, 0, 2, 1);
		gridPane.add(btnBox, 0, 2, 4, 1);
		
	
}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}}
