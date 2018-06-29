package p1;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListPane {
	private GridPane gridPane;
	
	private Label title = new Label("Stored Persons");

	public ListPane(College college,ListView<Person> list) {
		
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(05);
		gridPane.setVgap(30);
		
		VBox theBox = new VBox(10);
		
		theBox.setPrefWidth(900);
		title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
		theBox.getChildren().add(title);
		theBox.getChildren().add(list);
		
//		gridPane.add(title, 1,0,2,1);
		
		gridPane.add(theBox, 0, 1, 2, 1);

		
	}
	
//	public static void updatePersonListPane(College college, ListView<Person> list){
//		ListPane(college,list);
//	}
	

	public GridPane getGridPane() {
		return gridPane;
	}
	
	public Label getTitle() {
		return title;
	}
	
	
	
}
