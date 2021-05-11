package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewModel.LessonViewModel;
import viewModel.ScheduleViewModel;

import java.time.format.DateTimeFormatter;


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
    public void reset() {
        viewModel.clear();

        addLessonButton.setVisible(viewModel.canEditProperty().get());
        removeLessonButton.setVisible(viewModel.canEditProperty().get());

        backButton.setVisible(viewModel.canBackProperty().get());
    }

    @FXML
    private void addLesson() {
        //getViewHandler().openView();
    }

    @FXML
    private void removeLesson() {

    }

    @FXML
    private void backToSchoolView() {
        //TODO  viewModel.backToSchoolView(); is optional?
        viewModel.backToSchoolView();
        getViewHandler().openView(View.SCHOOL_VIEW);
    }


    @FXML
    private void manageAccount() {

    }

    @FXML
    private void infoLesson() {

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
