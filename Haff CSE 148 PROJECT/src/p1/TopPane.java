package p1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TopPane {
	private MenuBar menuBar;

	private Menu fileMenu;
	private Menu operationMenu;
	private Menu importMenu;
	private Menu exportMenu;

	private MenuItem saveMenuItem;
	private MenuItem loadMenuItem;
	private MenuItem studentMenuItemImport;
	private MenuItem facultyMenuItemImport;
	private MenuItem classroomMenuItemImport;
	private MenuItem courseMenuItemImport;
	private MenuItem textbookMenuItemExport;
	private MenuItem studentMenuItemExport;
	private MenuItem facultyMenuItemExport;
	private MenuItem classroomMenuItemExport;
	private MenuItem courseMenuItemExport;
	private MenuItem textbookMenuItemImport;
	private MenuItem exitMenuItem;
	private MenuItem studentMenuItem;
	private MenuItem facultyMenuItem;
	private MenuItem textbookMenuItem;
	private MenuItem courseMenuItem;
	private MenuItem masterCourseMenuItem;
	private MenuItem mCourseMenuItemImport;
	private MenuItem mCourseMenuItemExport;


	private PersonBag personBag;
	private TextbookBag textbookBag;
	private College college1;

	// private Desktop desktop = Desktop.getDesktop();

	public TopPane(College college) {
		buildFileMenu();
		buildOperationMenu();
		buildMenuBar();
		personBag = college.getPersonBag();
		textbookBag = college.getTextbookBag();
		college1 = college;
		
	}

	private void buildMenuBar() {
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, operationMenu);
	}

	private void buildFileMenu() {
		final FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();

		fileMenu = new Menu("File");
		saveMenuItem = new MenuItem("Save");
		saveMenuItem.setOnAction(e -> {
			saveAll();
		});
		loadMenuItem = new MenuItem("Load");
		loadMenuItem.setOnAction(e -> {
			loadAll();
		});

		importMenu = new Menu("Import...");
		studentMenuItemImport = new MenuItem("Student");
		studentMenuItemImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					

						Student.importStudentData(file);
							
						

				}
			}
		});
		facultyMenuItemImport = new MenuItem("Faculty");
		facultyMenuItemImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					try {
						Scanner scanner = new Scanner(file);
						// System.out.println(scanner.nextLine());
					} catch (FileNotFoundException e1) {
					}

				}
			}
		});
		
//		courseMenuItemImport = new MenuItem("Course");
//		courseMenuItemImport.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(final ActionEvent e) {
//				File file = fileChooser.showOpenDialog(stage);
//				if (file != null) {
//					try {
//						Scanner scanner = new Scanner(file);
//						// System.out.println(scanner.nextLine());
//					} catch (FileNotFoundException e1) {
//					}
//
//				}
//			}
//		});
		mCourseMenuItemImport = new MenuItem("Master Course Bag");
		
		mCourseMenuItemImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					
						MasterCourseBag.importData(file);
						// System.out.println(scanner.nextLine());
				}
				else{
						Util.failureAlert("Failure importing MasterCourseBag file");
				}	
				}
			});
		mCourseMenuItemExport = new MenuItem("Master Course Bag");
		
		mCourseMenuItemExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {

			MasterCourseBag.exportData("MasterCourseList.txt");
			
			}
			});
		
		textbookMenuItemImport = new MenuItem("Textbook");
		textbookMenuItemImport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					
						TextbookBag.importData(file);
						// System.out.println(scanner.nextLine());
				}
				else{
						Util.failureAlert("Failure importing textbooks.txt file");
				}	
				}
			});
		
		importMenu.getItems().addAll(studentMenuItemImport, facultyMenuItemImport,
				textbookMenuItemImport, mCourseMenuItemImport);
		exportMenu = new Menu("Export...");
		studentMenuItemExport = new MenuItem("Student");
		studentMenuItemExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();

				// Set extension filter
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
				fileChooser.getExtensionFilters().add(extFilter);

				// Show save file dialog
				File file = fileChooser.showSaveDialog(stage);

				if (file != null) {
					try {
						PrintWriter pw = new PrintWriter(file);
						for (int i = 0; i < personBag.getPersonArray().length; i++) {
							pw.println(personBag.getPersonArray()[i].getFirstName() + ": " 
									+ personBag.getPersonArray()[i].getLastName() + 
									personBag.getPersonArray()[i].getId() +
									personBag.getPersonArray()[i].getPhoneNumber());
							
						}
						pw.close();
					} catch (FileNotFoundException e) {
						Util.failureAlert("Failure exporting persons.txt file");
					}
				}
			}
		});
		facultyMenuItemExport = new MenuItem("Faculty");
		classroomMenuItemExport = new MenuItem("Classroom");
		courseMenuItemExport = new MenuItem("Course");
		textbookMenuItemExport = new MenuItem("Textbook");
		exportMenu.getItems().addAll(studentMenuItemExport, facultyMenuItemExport,
				mCourseMenuItemExport, textbookMenuItemExport);
		exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setOnAction(e -> {
			saveAll();
			Platform.exit();
		});

		fileMenu.getItems().addAll(saveMenuItem, loadMenuItem, new SeparatorMenuItem(), importMenu, exportMenu,
				new SeparatorMenuItem(), exitMenuItem);
	}

	private void buildOperationMenu() {
		operationMenu = new Menu("Opeartion");
		studentMenuItem = new MenuItem("Student");
		facultyMenuItem = new MenuItem("Faculty");
		masterCourseMenuItem = new MenuItem("Master Course Bag");
		courseMenuItem = new MenuItem("Course");
		textbookMenuItem = new MenuItem("Textbook");

		operationMenu.getItems().addAll(studentMenuItem, facultyMenuItem, new SeparatorMenuItem(), masterCourseMenuItem,
				courseMenuItem);
	}

	public void saveAll() {
		personBag.save();
		textbookBag.save();
	}	

	public void loadAll() {
		personBag.load();
		textbookBag.load();
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public MenuItem getSaveMenuItem() {
		return saveMenuItem;
	}

	public void setSaveMenuItem(MenuItem saveMenuItem) {
		this.saveMenuItem = saveMenuItem;
	}

	public MenuItem getLoadMenuItem() {
		return loadMenuItem;
	}

	public void setLoadMenuItem(MenuItem loadMenuItem) {
		this.loadMenuItem = loadMenuItem;
	}

	public MenuItem getImportMenu() {
		return importMenu;
	}

	public void setImportMenuItem(Menu importMenu) {
		this.importMenu = importMenu;
	}

	public MenuItem getExportMenu() {
		return exportMenu;
	}

	public void setExportMenuItem(Menu exportMenu) {
		this.exportMenu = exportMenu;
	}

	public MenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	public void setExitMenuItem(MenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}

	public MenuItem getStudentMenuItem() {
		return studentMenuItem;
	}

	public void setStudentMenuItem(MenuItem studentMenuItem) {
		this.studentMenuItem = studentMenuItem;
	}

	public MenuItem getFacultyMenuItem() {
		return facultyMenuItem;
	}

	public void setFacultyMenuItem(MenuItem facultyMenuItem) {
		this.facultyMenuItem = facultyMenuItem;
	}

	public MenuItem getTextbookMenuItem() {
		return textbookMenuItem;
	}

	public void setTextbookMenuItem(MenuItem textbookMenuItem) {
		this.textbookMenuItem = textbookMenuItem;
	}

	public MenuItem getCourseMenuItem() {
		return courseMenuItem;
	}

	public void setCourseMenuItem(MenuItem courseMenuItem) {
		this.courseMenuItem = courseMenuItem;
	}

	public MenuItem getMasterCourseMenuItem() {
		return masterCourseMenuItem;
	}

	public void setMasterCourseMenuItem(MenuItem classroomMenuItem) {
		this.masterCourseMenuItem = classroomMenuItem;
	}
	//
	// private void openFile(File file) {
	// try {
	// desktop.open(file);
	// } catch (IOException ex) {
	// Logger.getLogger(TopPane.class.getName()).log(Level.SEVERE, null, ex);
	// }
	// }
	
}
