package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewModel.LessonViewModel;
import viewModel.ScheduleViewModel;

import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class ScheduleViewController extends ViewController {
    @FXML private TableView<LessonViewModel> scheduleTable;
    @FXML private TableColumn<LessonViewModel, String> subjectColumn;
    @FXML private TableColumn<LessonViewModel, String> topicColumn;
    @FXML private TableColumn<LessonViewModel, String> teacherColumn;
    @FXML private TableColumn<LessonViewModel, String> timeColumn;
    @FXML private TableColumn<LessonViewModel, String> classroomColumn;

    @FXML private Label userLabel;
    @FXML private Label schoolClassLabel;
    @FXML private Label errorLabel;

    @FXML private DatePicker datePicker;

    @FXML private Button addLessonButton;
    @FXML private Button removeLessonButton;
    @FXML private Button backButton;
    @FXML private Button manageAccountButton;


    private ScheduleViewModel viewModel;

    public ScheduleViewController() {
        // Called by FXMLLoader
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getScheduleViewModel();

        subjectColumn.setCellValueFactory(
                cellData -> cellData.getValue().subjectProperty()
        );
        topicColumn.setCellValueFactory(
                cellData -> cellData.getValue().topicProperty()
        );
        teacherColumn.setCellValueFactory(
                cellData -> cellData.getValue().teacherProperty()
        );
        timeColumn.setCellValueFactory(
                cellData -> cellData.getValue().timeProperty()
        );
        classroomColumn.setCellValueFactory(
                cellData -> cellData.getValue().classroomProperty()
        );
        scheduleTable.setItems(viewModel.getSchedule());
        scheduleTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> viewModel.setSelected(newVal)
        );


        userLabel.textProperty().bind(viewModel.userProperty());
        schoolClassLabel.textProperty().bind(viewModel.schoolClassProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
        datePicker.valueProperty().bindBidirectional(viewModel.dateProperty());
        datePicker.getEditor().setText(
                DateTimeFormatter.ofPattern("EEEE dd/MM").format(datePicker.getValue())
        );

    }

    @Override
    public void reset() { // this method is called automatically
        viewModel.clear();
        viewModel.loadScheduleForDay();

        addLessonButton.setVisible(viewModel.forAdminProperty().get());
        removeLessonButton.setVisible(viewModel.forAdminProperty().get());
        backButton.setVisible(viewModel.forAdminProperty().get());
        manageAccountButton.setVisible(!viewModel.forAdminProperty().get());

        adjustViewButtonsForAdmin();
        scheduleTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void addLesson() {
        getViewHandler().openView(View.ADD_LESSON_VIEW);
    }

    @FXML
    private void removeLesson() {

        if (viewModel.hasSelectionProperty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result;

            alert.setTitle("Delete Lesson");
            alert.setHeaderText("Delete " +viewModel.getSelected().get().subjectProperty().get()+ " lesson "  +viewModel.getSelected().get().topicProperty().get()+ " ?");
            result = alert.showAndWait();


            if (result.isPresent() && result.get() == ButtonType.OK) {
                viewModel.deleteLesson();
            }
            scheduleTable.getSelectionModel().clearSelection();
        }
    }


    private void adjustViewButtonsForAdmin() {
        if(!viewModel.forAdminProperty().get())
            return;

        viewModel.clear();
        addLessonButton.setVisible(false);
        removeLessonButton.setVisible(false);
        manageAccountButton.setVisible(false);

        switch (viewModel.getSection()) {
            case "StudentClass":
                addLessonButton.setVisible(true);
                removeLessonButton.setVisible(true);
                manageAccountButton.setVisible(false);
                break;
            case "Teacher":
            case "Student":
                manageAccountButton.setVisible(true);
                break;
        }
    }


    @FXML
    private void backToSchoolView() {
        viewModel.backToSchoolView();
        getViewHandler().openView(View.SCHOOL_VIEW);
    }


    @FXML
    private void manageAccount() {
        getViewHandler().openView(View.MANAGE_ACCOUNT_VIEW);
    }

    @FXML
    private void infoLesson() {
       if(viewModel.loadInfoLesson())
           getViewHandler().openView(View.INFO_VIEW);
    }

    @FXML
    private void changeDateSchedule() {
        viewModel.changeDate();
        datePicker.getEditor().setText(
                DateTimeFormatter.ofPattern("EEEE dd/MM").format(datePicker.getValue())
        );
    }


    @FXML
    private void previousDay() {
        viewModel.previousDay();
    }

    @FXML
    private void nextDay() {
        viewModel.nextDay();
    }

}
