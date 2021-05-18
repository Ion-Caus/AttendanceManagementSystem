package view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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

        this.subject.textProperty().bind(viewModel.getSubjectProperty());
        this.topicField.textProperty().bind(viewModel.getTopicProperty());
        this.contentsField.textProperty().bind(viewModel.getContentsProperty());
        this.homeworkField.textProperty().bind(viewModel.getHomeworkProperty());
        this.teacherField.textProperty().bind(viewModel.getTeacherProperty()); // TODO: 13/5/2021   can we change a teacher from here or not? if yes, change to bidirectional.
        this.datePicker.valueProperty().bind(viewModel.getDateProperty()); // TODO: 13/5/2021  can we change the date from here? if yes, change to bidirectional.

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
    }

    public void adjustView(){
        switch (viewModel.getViewStateAccessLevel()) {
            case "Student":
                studentView.setVisible(true);
                teacherView.setPrefHeight(10);
                teacherView.setVisible(false);
                break;
            case "Teacher":
            case "Administrator":
                teacherView.setVisible(true);
                studentView.setPrefHeight(10);
                studentView.setVisible(false);
                break;
        }
    }

    @FXML
    private void openStudentList() {
        getViewHandler().openView(View.CLASS_STUDENT_VIEW);
    }

    @FXML
    private void backToScheduleView() {
        getViewHandler().openView(View.SCHEDULE_VIEW);

    }
}
