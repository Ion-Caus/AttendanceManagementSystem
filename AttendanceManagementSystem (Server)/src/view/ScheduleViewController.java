package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import viewModel.LessonViewModel;
import viewModel.ScheduleViewModel;


public class ScheduleViewController extends ViewController {
    @FXML private TableView<LessonViewModel> scheduleTable;
    @FXML private TableColumn<LessonViewModel, String> subjectColumn ;
    @FXML private TableColumn<LessonViewModel, String> topicColumn;
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
    }

    @Override
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
