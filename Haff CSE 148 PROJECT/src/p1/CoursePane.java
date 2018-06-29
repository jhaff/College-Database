package p1;
import java.util.Arrays;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CoursePane {
	private GridPane gridPane;
	private Button addBtn;
	private Button findBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button addTakenCourseBtn;
	private Button addTakingCourseBtn;
	private Button addToTakeCourseBtn;
	public static Student selectedStudent = null;
	ListView<String> coursesToTakeListView;
	ListView<String> coursesTakingListView;
	ListView<String> coursesTakenListView;


	
	public static Student getSelectedStudent() {
		return selectedStudent;
	}

	public static void setSelectedStudent(Student selectedStudent) {
		CoursePane.selectedStudent = selectedStudent;
	}

	private TextField courseTitleField;
	private TextField courseGradeField;

	private Text text;

	public CoursePane() {
		final int BUTTON_WIDTH = 115;
		final int TEXTFIELD_WIDTH = 245;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		courseTitleField = new TextField();
		courseTitleField.setPromptText("Course Title");
		courseTitleField.setPrefWidth(TEXTFIELD_WIDTH);
		courseGradeField = new TextField();
		courseGradeField.setPromptText("Letter Grade (if applicable)");
		courseGradeField.setPrefWidth(TEXTFIELD_WIDTH);
		
		addTakenCourseBtn = new Button("Add Past Course");
		addTakenCourseBtn.setPrefWidth(155);
		addTakingCourseBtn = new Button("Add Current Course");
		addTakingCourseBtn.setPrefWidth(155);
		addToTakeCourseBtn = new Button("Add Future Course");
		addToTakeCourseBtn.setPrefWidth(155);
		
		String[] currentCourses = selectedStudent.getCourseBag().getCurrentCourseArray();
		String[] pastCourses = selectedStudent.getCourseBag().getTakenCourseArray();
		String[] futureCourses = selectedStudent.getCourseBag().getFutureCourseArray();

		
		ObservableList<String> coursesToTakeList = FXCollections.observableArrayList(Arrays.asList(futureCourses));
		ListView<String> coursesToTakeListView = new ListView<>(coursesToTakeList);
		
		ObservableList<String> coursesTakingList = FXCollections.observableArrayList(Arrays.asList(currentCourses));
		ListView<String> coursesTakingListView = new ListView<>(coursesTakingList);
		
		ObservableList<String> coursesTakenList = FXCollections.observableArrayList(Arrays.asList(pastCourses));
		ListView<String> coursesTakenListView = new ListView<>(coursesTakenList);
		
		
		addTakenCourseBtn.setOnAction(e -> {
			if(courseGradeField.getText().equals("A") 
					|| courseGradeField.getText().equals("A") 
					|| courseGradeField.getText().equals("B+")
					|| courseGradeField.getText().equals("B")
					|| courseGradeField.getText().equals("C+")
					|| courseGradeField.getText().equals("D+")
					|| courseGradeField.getText().equals("D")
					|| courseGradeField.getText().equals("F")) {
				System.out.println("valid grade entered");
				selectedStudent.getCourseBag().addToTakenCourseBag(courseTitleField.getText());
				coursesTakingList.add(courseTitleField.getText());

				Grade grade = new Grade(courseTitleField.getText(), courseGradeField.getText());
				selectedStudent.getGrades().insert(grade);
				courseTitleField.clear();
				System.out.println(selectedStudent.getCourseBag());
			}
			else {
				Alert alert3 = new Alert(AlertType.ERROR);
				alert3.setTitle("Invalid grade entered");
				alert3.setHeaderText("Error!");
				alert3.setContentText("Invalid grade entered. You must enter a letter grade (example: B+) if you want to add a previously taken course");
				alert3.showAndWait();

			}
		
		});
		
		addTakingCourseBtn.setOnAction(e -> {
			
		
			selectedStudent.getCourseBag().addToCurrentCourseBag(courseTitleField.getText());
			courseTitleField.clear();

		});
		
		addToTakeCourseBtn.setOnAction(e -> {
	
			selectedStudent.getCourseBag().addToFutureCourseBag(courseTitleField.getText());
			courseTitleField.clear();

			System.out.println(selectedStudent.getCourseBag());
		});
		
		TextInputDialog dialog = new TextInputDialog("ID");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Information Needed");
		dialog.setContentText("Please enter the Student's ID number:");
		
		Alert alert2 = new Alert(AlertType.WARNING);
		alert2.setTitle("Warning Dialog");
		alert2.setHeaderText("Warning!");
		
		findBtn = new Button("FIND");
		findBtn.setPrefWidth(BUTTON_WIDTH);
		
		removeBtn = new Button("Remove");
		removeBtn.setPrefWidth(BUTTON_WIDTH);
		
		removeBtn.setOnAction(e -> {  // lambda expression
			TextInputDialog dialog2 = new TextInputDialog("ID");
			dialog2.setTitle("Text Input Dialog");
			dialog2.setHeaderText("Information Needed");
			dialog2.setContentText("Please enter the Course Title:");
			Optional<String> result = dialog2.showAndWait();
			
			if (result.isPresent()){
				
				String resultString = String.valueOf(result);
				
				String resultString2 = resultString.substring(9, resultString.length()-1);
				
			    	selectedStudent.getCourseBag().delete(resultString);
				alert2.setContentText("Deleting Course: " + resultString2);
				alert2.showAndWait();
			    	
			    }
			    else {
			    	Alert alert3 = new Alert(AlertType.ERROR);
					alert3.setTitle("Error Dialog");
					alert3.setHeaderText("Warning!");
					alert3.setContentText("No course currently selected");
					alert3.showAndWait();
			    }
			});
			
		
		updateBtn = new Button("Update");
		updateBtn.setPrefWidth(BUTTON_WIDTH);
		
		updateBtn.setOnAction(e -> {
			if (selectedStudent instanceof Student) {
			refreshLists();
		
			System.out.println(selectedStudent.getFirstName() + selectedStudent.getCourseBag());
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setHeaderText("Warning!");
				alert.setContentText("You have not selected a Student!");
				alert.showAndWait();
			}
		});

		HBox btnBox = new HBox(20);

		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(addTakenCourseBtn, addTakingCourseBtn, addToTakeCourseBtn,  findBtn, removeBtn, updateBtn);
		

		gridPane.add(btnBox, 0, 4, 4, 1);
		ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(50);
		ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().addAll(column1, column2, column3);
		
		Label coursesToTakeLbl = new Label("Courses to Take");
		GridPane.setHalignment(coursesToTakeLbl, HPos.CENTER);
		gridPane.add(coursesToTakeLbl, 4, 1);
		
		Label coursesTakingLbl = new Label("Courses Taking");
		GridPane.setHalignment(coursesTakingLbl, HPos.CENTER);
		gridPane.add(coursesTakingLbl, 2, 1);
		
		Label coursesTakenLbl = new Label("Courses Taken");
		GridPane.setHalignment(coursesTakenLbl, HPos.CENTER);
		gridPane.add(coursesTakenLbl, 0, 1);
		
		
		Button sendRightButton = new Button("->");
		Button sendLeftButton = new Button("<-");
		
		Button sendRightButton2 = new Button("->");
		Button sendLeftButton2 = new Button("<-");
		
		sendRightButton.setOnAction(e -> {
			String potential = coursesTakenListView.getSelectionModel().getSelectedItem();
			if(potential != null) {
				clearSelections();
				coursesTakenList.remove(potential);
				coursesTakingList.add(potential);
				selectedStudent.getCourseBag().delete(potential);
				selectedStudent.getCourseBag().addToCurrentCourseBag(potential);
				System.out.println(selectedStudent.getCourseBag());
			}
		});
		
		sendLeftButton.setOnAction(e -> {
			String potential = coursesTakingListView.getSelectionModel().getSelectedItem();
			System.out.println(potential);
			if(potential != null) {
				clearSelections();
				coursesTakingList.remove(potential);
				coursesToTakeList.add(potential);
				selectedStudent.getCourseBag().delete(potential);
				selectedStudent.getCourseBag().addToTakenCourseBag(potential);
				System.out.println(selectedStudent.getCourseBag());
			}
		});
		
		sendRightButton2.setOnAction(e -> {
			refreshLists();
			String potential = coursesTakingListView.getSelectionModel().getSelectedItem();
			if(potential != null) {
				coursesTakingListView.getSelectionModel().clearSelection();
				coursesTakingList.remove(potential);
				coursesToTakeList.add(potential);
				selectedStudent.getCourseBag().delete(potential);
				selectedStudent.getCourseBag().addToFutureCourseBag(potential);
				System.out.println(selectedStudent.getCourseBag());
				refreshLists();
			}
				else {
					coursesToTakeListView.getSelectionModel().clearSelection();
					System.out.println("empty field");

				}
				
			
		});
		
		sendLeftButton2.setOnAction(e -> {
			String potential = coursesToTakeListView.getSelectionModel().getSelectedItem();
			if(potential != null) {
				coursesToTakeListView.getSelectionModel().clearSelection();
				coursesToTakeList.remove(potential);
				coursesTakingList.add(potential);
				selectedStudent.getCourseBag().delete(potential);
				selectedStudent.getCourseBag().addToCurrentCourseBag(potential);
				System.out.println(selectedStudent.getCourseBag());
				refreshLists();
			}
		});
		
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(sendRightButton, sendLeftButton);
		
		VBox vbox2 = new VBox(5);
		vbox2.getChildren().addAll(sendRightButton2, sendLeftButton2);
		
		gridPane.add(coursesTakenListView, 0, 2, 1, 1);
		gridPane.add(vbox, 1, 2, 1, 1);
		gridPane.add(coursesTakingListView, 2, 2, 1, 1);
		gridPane.add(vbox2, 3, 2, 1, 1);
		gridPane.add(coursesToTakeListView, 4, 2, 1, 1);
		gridPane.add(courseTitleField, 0, 0, 2, 1);
		gridPane.add(courseGradeField, 2, 0, 2, 1);

		System.out.println("coursePane created");
	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	
	
	
	public void refreshLists() {
		ObservableList<String> coursesToTakeList = FXCollections.observableArrayList(Arrays.asList(selectedStudent.getCourseBag().getFutureCourseArray()));
		ListView<String> coursesToTakeListView = new ListView<>(coursesToTakeList);
		gridPane.add(coursesToTakeListView, 4, 2, 1, 1);
		
		ObservableList<String> coursesTakingList = FXCollections.observableArrayList(Arrays.asList(selectedStudent.getCourseBag().getCurrentCourseArray()));
		ListView<String> coursesTakingListView = new ListView<>(coursesTakingList);
		gridPane.add(coursesTakingListView, 2, 2, 1, 1);
		
		ObservableList<String> coursesTakenList = FXCollections.observableArrayList(Arrays.asList(selectedStudent.getCourseBag().getTakenCourseArray()));
		ListView<String> coursesTakenListView = new ListView<>(coursesTakenList);
		gridPane.add(coursesTakenListView, 0, 2, 1, 1);
		
	}
	
	public void clearSelections() {
//		coursesToTakeListView.getSelectionModel().clearSelection();
//		coursesTakenListView.getSelectionModel().clearSelection();
//		coursesTakingListView.getSelectionModel().clearSelection()

	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}
}
