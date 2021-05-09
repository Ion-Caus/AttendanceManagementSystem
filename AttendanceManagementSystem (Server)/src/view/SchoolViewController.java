package view;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewModel.ClassViewModel;
import viewModel.SchoolViewModel;
import viewModel.StudentViewModel;
import viewModel.TeacherViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class SchoolViewController extends ViewController {
    @FXML private TabPane tabPane;

    @FXML private Label schoolName;
    @FXML private Label errorLabel;

    @FXML private Button scheduleButton;
    @FXML private Button studentListButton;

    @FXML private TableView<ClassViewModel> classesTable;
    @FXML private TableColumn<ClassViewModel, String> classNameColumn;

    @FXML private TableView<StudentViewModel> allStudentsTable;
    @FXML private TableColumn<StudentViewModel, String> studentColumn;

    @FXML private TableView<TeacherViewModel> allTeachersTable;
    @FXML private TableColumn<TeacherViewModel, String> teacherColumn;

    private SchoolViewModel viewModel;



    public SchoolViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {


        viewModel = getViewModelFactory().getSchoolViewModel();

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    viewModel.setTabSelectedProperty(newVal.getText());
                    adjustViewButtons();
                }
        );
        tabPane.getSelectionModel().selectFirst();

        schoolName.textProperty().bindBidirectional(viewModel.schoolNameProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());


        // Classes Table
        classNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().classNameProperty()
        );
        classesTable.setItems(viewModel.getAllClasses());
        classesTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

        // Students Table
        studentColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty()
        );
        allStudentsTable.setItems(viewModel.getAllStudents());
        allStudentsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );

        // Teachers Table
        teacherColumn.setCellValueFactory(
                cellData -> cellData.getValue().nameProperty()
        );
        allTeachersTable.setItems(viewModel.getAllTeachers());
        allTeachersTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );



    }

    @Override
    public void reset() {
        viewModel.clear();
    }

    @FXML
    private void adjustViewButtons() {
        switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
            case "Classes" :
                scheduleButton.setVisible(true);
                studentListButton.setVisible(true);
                break;


            case "Students" :
            case "Teachers" :
                scheduleButton.setVisible(true);
                studentListButton.setVisible(false);

                break;
            case "Admins" :
            case "Log" :
                scheduleButton.setVisible(false);
                studentListButton.setVisible(false);
                break;
        }


    }

    @FXML
    private void editSchoolName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("School Name");
        dialog.setHeaderText("School Name");
        dialog.setContentText("Please enter your school name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && !result.get().isBlank()) {
            schoolName.setText(result.get());
            viewModel.setSchoolName(result.get());
        }
    }

    @FXML
    private void add() {

        switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
            case "Classes" :
                getViewHandler().openView(View.CLASS_VIEW);


                break;

            case "Students" :
                getViewHandler().openView(View.STUDENT_VIEW);

                break;

            case "Teachers" :

                break;
            case "Admins" :
            case "Log" :

                break;
        }



    }

    @FXML
    private void remove() {

        // TODO: 02/5/2021 by tomas this might need to be refactored, this shit ugly as hell, strategy pattern might help here

        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result;


        switch (tabPane.getSelectionModel().getSelectedItem().getText()) {
            case "Classes" :
              ClassViewModel  classViewModel =classesTable.getSelectionModel().getSelectedItem();
                alert.setTitle("Delete class");
                alert.setHeaderText("Delete class "+classViewModel.classNameProperty()+" ?");

                result= alert.showAndWait();

                if (result.isPresent() && result.get()==ButtonType.OK){
                classesTable.getSelectionModel().clearSelection();
                viewModel.removeClass(classViewModel);

                //placeholder until we have a way to really delete it
                classesTable.getItems().remove(classViewModel);

            }
                break;

            case "Students" :
                StudentViewModel studentViewModel =allStudentsTable.getSelectionModel().getSelectedItem();
                alert.setTitle("Delete class");
                alert.setHeaderText("Delete class "+studentViewModel.nameProperty()+" ?");
               result = alert.showAndWait();

                if (result.isPresent() && result.get()==ButtonType.OK) {

                    allStudentsTable.getSelectionModel().clearSelection();
                    allStudentsTable.getItems().remove(studentViewModel);
                }
                break;

            case "Teachers" :

                TeacherViewModel teacherViewModel =allTeachersTable.getSelectionModel().getSelectedItem();
                alert.setTitle("Delete teacher");
                alert.setHeaderText("Delete teacher "+teacherViewModel.nameProperty()+" ?");
                result = alert.showAndWait();

                if (result.isPresent() && result.get()==ButtonType.OK) {

                    allTeachersTable.getSelectionModel().clearSelection();
                    allTeachersTable.getItems().remove(teacherViewModel);
                }

                break;
            case "Admins" :
            case "Log" :

                break;
        }




    }

    @FXML
    private void viewSchedule() {
        viewModel.viewSchedule();
        getViewHandler().openView(View.SCHEDULE_VIEW);
    }

    @FXML
    private void viewStudentList() {
    }
}
