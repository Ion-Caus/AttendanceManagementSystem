package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import viewmodel.LessonViewModel;
import viewmodel.ScheduleViewModel;


public class ScheduleViewController extends ViewController {
    @FXML private TableView<LessonViewModel> scheduleTable;
    @FXML private TableColumn<LessonViewModel, String> subjectColumn ;
    @FXML private TableColumn<LessonViewModel, String> titleColumn;
    @FXML private TableColumn<LessonViewModel, String> teacherColumn;
    @FXML private TableColumn<LessonViewModel, String> timeColumn;
    @FXML private TableColumn<LessonViewModel, String> classroomColumn;

    @FXML private Label userLabel;
    @FXML private Label schoolClassLabel;
    @FXML private Label errorLabel;

    @FXML private DatePicker datePicker;

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
        titleColumn.setCellValueFactory(
                cellData -> cellData.getValue().titleProperty()
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
    }

    public void reset() {
        viewModel.clear();
    }


    @FXML
    private void manageAccount() {

    }

    @FXML
    private void infoLesson() {
    }


    @FXML
    private void previousDay() {

    }

    @FXML
    private void nextDay() {
    }

}
