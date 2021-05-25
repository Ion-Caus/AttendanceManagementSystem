package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.sql.SQLException;

public class AddGradeViewModel {
    private StringProperty gradeProperty;
    private StringProperty commentProperty;
    private StringProperty errorProperty;

    private Model model;
    private ViewModelState viewModelState;

    public AddGradeViewModel(Model model, ViewModelState viewModelState){
        this.model = model;
        this.viewModelState = viewModelState;

        this.gradeProperty = new SimpleStringProperty();
        this.commentProperty = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void clear() {
        gradeProperty.set("");
        commentProperty.set("");
        errorProperty.set("");
    }

    public StringProperty getGradeProperty() {
        return gradeProperty;
    }

    public StringProperty getCommentProperty() {
        return commentProperty;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public boolean submitGradeComment() {
        try {
            if (gradeProperty.get().isEmpty()) {
                errorProperty.set("Please enter the grade.");
                return false;
            }
            model.changeGradeComment(viewModelState.getStudentID(), viewModelState.getLessonID(), Integer.parseInt(gradeProperty.get()),commentProperty.get());
            return true;
        } catch (IllegalArgumentException | SQLException e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }
}
