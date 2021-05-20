package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AddGradeViewModel {
    private StringProperty gradeProperty;
    private StringProperty commentProperty;
    private StringProperty errorProperty;

    private Model model;

    public AddGradeViewModel(Model model){
        this.model = model;

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
        //model.changeGradeComment();
        return false;
        //TODO 20/5 by Ion observer in model
    }
}
