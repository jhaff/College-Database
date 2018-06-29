package p1;
import java.util.Arrays;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
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

public class MasterCoursePane {
	private GridPane gridPane;
	private Button findBtn;
	private Button removeBtn;
	private Button updateBtn;
	private Button addCourseBtn;
	private Button removeCourseBtn;
	private Button selBtn;
	private TextField courseNameField;
	private TextField cNumField;
	private TextField creditsNumField;
	private TextField isbnField;
	public static Course selectedCourse = null;
	ListView<String> masterCoursesListView;
	
	
	public static Course getSelectedCourse() {
		return selectedCourse;
	}

	public static void setSelectedCourse(Course selectedCourse) {
		MasterCoursePane.selectedCourse = selectedCourse;
	}

	private TextField courseTitleField;
	private TextField courseGradeField;
	private Alert alert3;


	private Text text;

	public MasterCoursePane() {
		final int BUTTON_WIDTH = 115;
		final int TEXTFIELD_WIDTH = 245;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(30));
		gridPane.setHgap(20);
		gridPane.setVgap(30);

		courseNameField = new TextField();
		courseNameField.setPromptText("Course Title");
		courseNameField.setPrefWidth(TEXTFIELD_WIDTH);
		cNumField = new TextField();
		cNumField.setPromptText("Course Number");
		cNumField.setPrefWidth(TEXTFIELD_WIDTH);
		creditsNumField = new TextField();
		creditsNumField.setPromptText("Number of Credits");
		creditsNumField.setPrefWidth(TEXTFIELD_WIDTH);
		isbnField = new TextField();
		isbnField.setPromptText("ISBN Assigned");
		isbnField.setPrefWidth(TEXTFIELD_WIDTH);
		
		addCourseBtn = new Button("Add Course");
		addCourseBtn.setPrefWidth(155);
		removeCourseBtn = new Button("Remove Course");
		removeCourseBtn.setPrefWidth(155);
		selBtn = new Button("SELECT");
		selBtn.setPrefWidth(100);
		selBtn.setPrefHeight(150);
		
		
		addCourseBtn.setOnAction(e -> {
			if (courseNameField.getText() != null && cNumField.getText() != null && isbnField.getText() != null && creditsNumField.getText() != null) {
				Course course = null;
				try {
					course = new Course(courseNameField.getText(), Integer.parseInt(cNumField.getText()), isbnField.getText(), Integer.parseInt(creditsNumField.getText()));
					
					try {
						for(int i = 0; i < Util.getLength(MasterCourseBag.masterCourseArray); i++) {
						    if(MasterCourseBag.masterCourseArray[Util.getLength(MasterCourseBag.masterCourseArray)] == null)
						    	MasterCourseBag.masterCourseArray[Util.getLength(MasterCourseBag.masterCourseArray)] = course;
						    System.out.println("courseInserted");
							refreshList();
						        break;
						       }
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} catch (NumberFormatException e1) {
					alert3.setContentText("Something was wrong with your entry. Please check your fields and try again!");
					alert3.showAndWait();

					e1.printStackTrace();
				}
				
		
			}
			else {
				alert3.setContentText("You have not filled all required fields!");
				alert3.showAndWait();

			}
			
			courseNameField.clear();
			cNumField.clear();
			isbnField.clear();
			creditsNumField.clear();
		});
		
		
		
		Alert alert3 = new Alert(AlertType.ERROR);
		alert3.setTitle("Error Dialog");
		alert3.setHeaderText("Warning!");
		
		TextInputDialog dialog = new TextInputDialog("ID");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Information Needed");
		dialog.setContentText("Please enter the ID number:");
		
		Alert alert2 = new Alert(AlertType.WARNING);
		alert2.setTitle("Warning Dialog");
		alert2.setHeaderText("Warning!");
		
		findBtn = new Button("FIND");
		findBtn.setPrefWidth(BUTTON_WIDTH);
		
		
		removeCourseBtn.setOnAction(e -> {  // lambda expression
//			String potential = masterCoursesListView.getSelectionModel().getSelectedItem();				
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    System.out.println("Your ID: " + result.get());
			    String currentId = new String();
			    currentId = result.get();
				
				selectedCourse = MasterCourseBag.find(Integer.parseInt(result.get()));
				
				System.out.println(selectedCourse);
//				App.showCousePane(selectedStudent);
				
				
			    if (selectedCourse != null) {
			    		alert2.setContentText("Are you sure you want to remove " + selectedCourse.getCourseTitle() + "?");
			    		alert2.showAndWait();
//			    		MasterCourseBag.delete(potential);
			    		MasterCourseBag.delete(selectedCourse.getCourseNumber());			refreshList();

			    }
			    else {
			    	
					alert3.setContentText("No course currently selected");
					alert3.showAndWait();
			    }			
			
		}
		});
		
		updateBtn = new Button("Update List");
		updateBtn.setPrefWidth(BUTTON_WIDTH);
		
		updateBtn.setOnAction(e -> {
			refreshList();
		
		});

		HBox btnBox = new HBox(20);

		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(selBtn, addCourseBtn, removeCourseBtn, updateBtn);
		

		gridPane.add(btnBox, 0, 4, 4, 1);
		ColumnConstraints column1 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(50);
		ColumnConstraints column3 = new ColumnConstraints(150, 150, Double.MAX_VALUE);
		
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().addAll(column1, column2, column3);
		
		
		
		Label label = new Label("Master Course Bag");
		GridPane.setHalignment(label, HPos.CENTER);
		gridPane.add(label, 0, 1);
		
		Course[] masterCourses = MasterCourseBag.masterCourseArray;

		
		ObservableList<Course> masterCoursesList = FXCollections.observableArrayList(Arrays.asList(masterCourses));
		ListView<Course> masterCoursesListView = new ListView<>(masterCoursesList);
		
		
		MultipleSelectionModel<Course> lvSelModel = masterCoursesListView.getSelectionModel();
		lvSelModel.setSelectionMode(SelectionMode.MULTIPLE);
		lvSelModel.selectedItemProperty().addListener(new ChangeListener<Course>() {


			
			@Override
			public void changed(ObservableValue<? extends Course> observable, Course oldValue, Course newValue) {
				System.out.println("You selected " + newValue);
//				System.out.println(newValue  + " is selected");
				if (newValue instanceof Course) {
				MasterCoursePane.setSelectedCourse(newValue);
				
				}
				else {
					System.out.println(newValue.getCourseTitle()  + " is not a student. Setting selectedStudent to null");
					CoursePane.setSelectedStudent(null);

				}
			}
		});
		
		gridPane.add(courseNameField, 0, 0, 2, 1);
		gridPane.add(cNumField, 2, 0, 2, 1);
		gridPane.add(creditsNumField, 4, 0, 2, 1);
		gridPane.add(isbnField, 6, 0, 2, 1);
		gridPane.add(masterCoursesListView, 0, 2, 4, 1);
		

		System.out.println("masterCoursePane created");
	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	
	
	
	public void refreshList() {
		ObservableList<Course> masterCoursesList = FXCollections.observableArrayList(Arrays.asList(MasterCourseBag.masterCourseArray));
		ListView<Course> masterCoursesListView = new ListView<>(masterCoursesList);
		gridPane.add(masterCoursesListView, 0, 2, 4, 1);
		
	}
	
	public void clearSelections() {
		masterCoursesListView.getSelectionModel().clearSelection();
		

	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}
}