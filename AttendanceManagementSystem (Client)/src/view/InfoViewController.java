package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;
import viewModel.InfoViewModel;

public class InfoViewController extends ViewController {
    @FXML private Label subject;
    @FXML private TextField topicField;
    @FXML private TextArea contentsField;
    @FXML private TextArea homeworkField;
    @FXML private TextField teacherField;
    @FXML private DatePicker datePicker;

    @FXML private Label errorLabel;
    @FXML private Label className;

    //Student view
    @FXML private VBox studentView;
    @FXML private TextField absenceTextField;
    @FXML private TextArea motiveTextArea;
    @FXML private TextField gradeTextField;
    @FXML private TextArea commentTextArea;

    //Teacher view
    @FXML private VBox teacherView;

    private InfoViewModel viewModel;

    public InfoViewController() {
        // called by the FXML loader
    }

    @Override
    protected void init() {
        this.viewModel = getViewModelFactory().getInfoViewModel();

        this.topicField.textProperty().bindBidirectional(viewModel.getTopicProperty());
        this.contentsField.textProperty().bindBidirectional(viewModel.getContentsProperty());
        this.homeworkField.textProperty().bindBidirectional(viewModel.getHomeworkProperty());

        this.subject.textProperty().bind(viewModel.getSubjectProperty());

        TextFields.bindAutoCompletion(teacherField,viewModel.getTeacherList());
        this.teacherField.textProperty().bindBidirectional(viewModel.getTeacherProperty());

        this.datePicker.valueProperty().bind(viewModel.getDateProperty());
        this.datePicker.setDisable(true);
        this.datePicker.setStyle("-fx-opacity: 1");
        this.datePicker.getEditor().setStyle("-fx-opacity: 1");

        this.className.textProperty().bind(viewModel.getClassNameProperty());
        this.errorLabel.textProperty().bind(viewModel.getErrorProperty());

        this.absenceTextField.textProperty().bind(viewModel.getAbsentProperty());
        this.motiveTextArea.textProperty().bindBidirectional(viewModel.getMotiveProperty());
        this.gradeTextField.textProperty().bind(viewModel.getGradeProperty());
        this.commentTextArea.textProperty().bind(viewModel.getCommentProperty());

        adjustView();
    }

    @Override
    public void reset() {
        viewModel.clear();
        viewModel.loadLessonFromModel();
    }

    public void adjustView(){
        teacherField.editableProperty().set(false);
        switch (viewModel.getViewStateAccessLevel()) {
            case "Student":
                studentView.setVisible(true);
                teacherView.setVisible(false);
                teacherView.setPrefHeight(10);
                break;
            case "Administrator":
                teacherField.editableProperty().set(true);
            case "Teacher":
                teacherView.setVisible(true);
                studentView.setVisible(false);
                studentView.setPrefHeight(10);

                topicField.editableProperty().set(true);
                contentsField.editableProperty().set(true);
                homeworkField.editableProperty().set(true);
                break;
        }
    }

    @FXML
    private void openStudentList() {

        getViewHandler().openView(View.STUDENT_LIST_VIEW);
    }

    @FXML
    private void backToScheduleView() {
        getViewHandler().openView(View.SCHEDULE_VIEW);

    }

    @FXML
    private void submitChangeLesson() {
        if (viewModel.submitChangeLesson()) {
            getViewHandler().openView(View.SCHEDULE_VIEW);
        }
    }



}
