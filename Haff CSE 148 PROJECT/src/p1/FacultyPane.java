package p1;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FacultyPane {
	private GridPane gridPane;
	private Button addBtn;
	private Button findBtn;
	private Button removeBtn;
	private Button updateBtn;
	
	private Faculty selectedFaculty = null;
	
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField phoneField;
	private TextField salaryField;
	private TextField titleField;

	Alert alert;

	public FacultyPane(College college) {
		final int BUTTON_WIDTH = 110;
		final int TEXTFIELD_WIDTH = 250;
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
		phoneField = new TextField();
		phoneField.setPromptText("Phone");
		phoneField.setPrefWidth(TEXTFIELD_WIDTH);
		salaryField = new TextField();
		salaryField.setPromptText("Salary");
		salaryField.setPrefWidth(TEXTFIELD_WIDTH);
		titleField = new TextField();
		titleField.setPromptText("Title");
		titleField.setPrefWidth(TEXTFIELD_WIDTH);
		addBtn = new Button("ADD");
		addBtn.setPrefWidth(BUTTON_WIDTH);
		
		removeBtn = new Button("Remove");
		removeBtn.setPrefWidth(BUTTON_WIDTH);
		
		updateBtn = new Button("Update");
		updateBtn.setPrefWidth(BUTTON_WIDTH);
		
		findBtn = new Button("FIND");
		findBtn.setPrefWidth(BUTTON_WIDTH);
		
		Alert alert3 = new Alert(AlertType.ERROR);
		alert3.setTitle("Error Dialog");
		alert3.setHeaderText("Warning!");
		
		addBtn.setOnAction(e -> {
			try {
				Faculty faculty = new Faculty(firstNameField.getText(), lastNameField.getText(),phoneField.getText(),Double.parseDouble(salaryField.getText()), titleField.getText());
				college.getPersonBag().insert(faculty);
				System.out.println(faculty);
				App.updatePersonList(college.getPersonBag().getPersonArray());
				firstNameField.clear();
				lastNameField.clear();			
				phoneField.clear();
				salaryField.clear();
				titleField.clear();
			} catch (IllegalArgumentException ex) {
				System.out.println("You have entered invalid text" + ex.getMessage());
				alert3.setContentText("You have entered invalid text " + ex.getMessage());
				alert3.showAndWait();
			}
			
		});
		
		
		
		Alert alert2 = new Alert(AlertType.WARNING);
		alert2.setTitle("Warning Dialog");
		alert2.setHeaderText("Warning!");
	
	
		TextInputDialog dialog = new TextInputDialog("ID");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Information Needed");
		dialog.setContentText("Please enter the Faculty's ID number:");
		
		removeBtn.setOnAction(e -> {  // lambda expression
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				String currentId = new String();
			    currentId = result.get();
				college.getPersonBag().findPersonById(currentId);
				selectedFaculty = (Faculty) college.getPersonBag().findPersonById(currentId);
			    System.out.println("Your ID: " + result.get());
			    System.out.println(college.getPersonBag());
			    if (selectedFaculty != null) {
			    		alert2.setContentText("Are you sure you want to remove " + selectedFaculty.getFirstName() + " " + selectedFaculty.getLastName() + " ?");
			    		alert2.showAndWait();
			    		
			    }
			    else {
//			    	Alert alert3 = new Alert(AlertType.ERROR);
					alert3.setTitle("Error Dialog");
					alert3.setHeaderText("Warning!");
					alert3.setContentText("No student currently selected");
					alert3.showAndWait();
			    }
			}
			
		});
		
		findBtn.setOnAction(e -> {  // lambda expression
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Your ID: " + result.get());
			    String currentId = new String();
			    currentId = result.get();
				college.getPersonBag().findPersonById(currentId);
				selectedFaculty = (Faculty) college.getPersonBag().findPersonById(currentId);
				System.out.println(selectedFaculty);
				firstNameField.setText(selectedFaculty.getFirstName());
				lastNameField.setText(selectedFaculty.getLastName());
				phoneField.setText(selectedFaculty.getPhoneNumber());
				salaryField.setText(String.valueOf(selectedFaculty.getSalary()));
				titleField.setText(selectedFaculty.getTitle());

				
			}
		});
		
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		
		updateBtn.setOnAction(e -> {  // lambda expression
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Your ID: " + result.get());
			    String currentId = new String();
			    currentId = result.get();
				college.getPersonBag().findPersonById(currentId);
				selectedFaculty = (Faculty) college.getPersonBag().findPersonById(currentId);
//				System.out.println(selectedStudent)
//				System.out.println(college.getPersonBag());
				selectedFaculty.setFirstName(firstNameField.getText());
				selectedFaculty.setLastName(lastNameField.getText());
				selectedFaculty.setPhoneNumber(phoneField.getText());
				selectedFaculty.setSalary(Double.parseDouble(salaryField.getText()));
				selectedFaculty.setTitle(titleField.getText());

				
				alert.setHeaderText("Faculty updated.");
				alert.setContentText(selectedFaculty.getFirstName() + "'s file has been updated.");
				alert.showAndWait();
				
				App.updatePersonList(college.getPersonBag().getPersonArray());


			}
		});
		
		
		HBox btnBox = new HBox(20);

		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(addBtn, findBtn, removeBtn, updateBtn);
		
		gridPane.add(firstNameField, 0, 0, 2, 1);
		gridPane.add(lastNameField, 2, 0, 2, 1);
		gridPane.add(phoneField, 4, 0, 2, 1);
		gridPane.add(salaryField, 6, 0, 2, 1);
		gridPane.add(titleField, 8, 0, 2, 1);

//		gridPane.add(addBtn, 0, 1);
//		gridPane.add(findBtn, 1, 1);
//		gridPane.add(removeBtn, 2, 1);
//		gridPane.add(updateBtn, 3, 1);
		
		gridPane.add(btnBox, 0, 2, 4, 1);
	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
}
