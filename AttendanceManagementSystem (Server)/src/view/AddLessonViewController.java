package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import viewModel.AddLessonViewModel;

import java.time.format.DateTimeFormatter;

public class AddLessonViewController extends ViewController{


    @FXML private TextField titleField;
    @FXML private TextField topicField;
    @FXML private TextArea contentsField;
    @FXML private TextArea homeworkField;
    @FXML private TextField teacherField;
    @FXML private Label errorLabel;
    @FXML private TextField subjectField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;
    @FXML private DatePicker datePicker;
    @FXML private TextField classroom;



    private AddLessonViewModel viewModel;

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getAddLessonViewModel();

        titleField.textProperty().bindBidirectional(viewModel.titleProperty());
        subjectField.textProperty().bindBidirectional(viewModel.subjectProperty());
        topicField.textProperty().bindBidirectional(viewModel.topicProperty());
        contentsField.textProperty().bindBidirectional(viewModel.contentsProperty());
        homeworkField.textProperty().bindBidirectional(viewModel.homeworkProperty());
        teacherField.textProperty().bindBidirectional(viewModel.teacherProperty());
        startTimeField.textProperty().bindBidirectional(viewModel.startTimeProperty());
        endTimeField.textProperty().bindBidirectional(viewModel.endTimeProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());

        TextFields.bindAutoCompletion(teacherField,viewModel.getTeacherList());

         datePicker.valueProperty().bindBidirectional(viewModel.dateProperty());
        datePicker.getEditor().setText(
                DateTimeFormatter.ofPattern("EEEE dd/MM").format(datePicker.getValue())
        );
        classroom.textProperty().bindBidirectional(viewModel.classroomProperty());







    }

    @Override
    public void reset() {

        viewModel.clear();
    }




    public void createLessonButtonPressed(){
        if (viewModel.createLesson()) {
            reset();
            getViewHandler().openView(View.SCHEDULE_VIEW);

        }

    }

    public void backButtonClicked(){
        getViewHandler().openView(View.SCHEDULE_VIEW);
    }
}
