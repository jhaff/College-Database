package p1;

	import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

	public class App extends Application {
		
		public static College college;
		public static ListView<Person> peopleList;
		
		public static BorderPane root = new BorderPane();
		static ObservableList<Person> people;
		
		public static void main(String[] args) {
			launch(args);
		}
		
		public static void updatePersonList(Person[] personArray) {
		ObservableList<Person> people = FXCollections.observableArrayList(personArray);
		peopleList = new ListView<Person>(people);
		peopleList.setPrefSize(300, 150);
		ListPane listPane = new ListPane(college, peopleList);
		root.setBottom(listPane.getGridPane());
		}

//		public static void showCousePane(Student student) {
//			CoursePane coursePane = new CoursePane(student);
//			root.setCenter(coursePane.getGridPane());
////			Scene scene = new Scene(root, 800, 500);
////			primaryStage.setScene(scene);
////			primaryStage.show() 
//		}
		
		@Override
		public void start(Stage primaryStage) throws Exception {
			College college = new College();
			TopPane topPane = new TopPane(college);
			root.setTop(topPane.getMenuBar());

			CoursePane.setSelectedStudent((Student) college.getPersonBag().findPersonById("1"));
			
			Label response = new Label("Select: ");
			
			
			
//			FlowPane root = new FlowPane(10, 10);
//			root.setAlignment(Pos.CENTER);
//			
			ObservableList<Person> people = FXCollections.observableArrayList(college.getPersonBag().getPersonArray());
			peopleList = new ListView<Person>(people);
			peopleList.setPrefSize(300, 150);
			
			MultipleSelectionModel<Person> lvSelModel = peopleList.getSelectionModel();
			lvSelModel.setSelectionMode(SelectionMode.MULTIPLE);
			lvSelModel.selectedItemProperty().addListener(new ChangeListener<Person>() {


				@Override
				public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
					response.setText("You selected " + newValue);
					System.out.println(newValue  + " is selected");
					if (newValue instanceof Student) {
					CoursePane.setSelectedStudent((Student) newValue);
					
					}
					else {
						System.out.println(newValue.getFirstName()  + " is not a student. Setting selectedStudent to null");
						CoursePane.setSelectedStudent(null);

					}
				}
				
			});
			
			ListPane listPane = new ListPane(college,peopleList);
			
			root.setBottom(listPane.getGridPane());
//			root.getBottom()
//			root.getChildren().add(title);
//			root.getChildren().add(lvColleges);
//			root.getChildren().add(response);
//			
//			Scene scene = new Scene(root, 350, 300);
			
//			primaryStage.setTitle("List View");
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			MenuItem studentMenuItem = topPane.getStudentMenuItem();
			studentMenuItem.setOnAction(e -> {
				StudentPane centerPane = new StudentPane(college);
				root.setCenter(centerPane.getGridPane());
			});

			MenuItem facultyMenuItem = topPane.getFacultyMenuItem();
			facultyMenuItem.setOnAction(e -> {
				FacultyPane centerPane = new FacultyPane(college);
				root.setCenter(centerPane.getGridPane());
			});

			MenuItem masterCourseMenuItem = topPane.getMasterCourseMenuItem();
			masterCourseMenuItem.setOnAction(e -> {
				MasterCoursePane centerPane = new MasterCoursePane();
				root.setCenter(centerPane.getGridPane());
			});

			TextInputDialog dialog = new TextInputDialog("ID");
			dialog.setTitle("Text Input Dialog");
			dialog.setHeaderText("Information Needed");
			dialog.setContentText("Please enter the ID number:");
			
			MenuItem courseMenuItem = topPane.getCourseMenuItem();
			courseMenuItem.setOnAction(e -> {
				if (CoursePane.selectedStudent == null) {
					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
					    System.out.println("Your ID: " + result.get());
					    String currentId = new String();
					    currentId = result.get();
					    
		CoursePane.setSelectedStudent((Student)college.getPersonBag().findPersonById(currentId));
		CoursePane centerPane = new CoursePane();
		root.setCenter(centerPane.getGridPane());
				}
				}
				else {
				CoursePane centerPane = new CoursePane();
				root.setCenter(centerPane.getGridPane());
				}
				
			});

				
//			CoursePane centerPane = new CoursePane();
//			root.setCenter(centerPane.getGridPane());

			Scene scene = new Scene(root, 1200, 700);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					college.save();
					Platform.exit();
				}
			});
			
			
			
		}

	}

