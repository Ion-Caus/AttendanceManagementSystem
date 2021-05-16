package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AddTeacherViewModel {
    private StringProperty teacherName;
    private StringProperty teacherID;
    private StringProperty errorProperty;

    private Model model;

    public AddTeacherViewModel(Model model) {
        this.model = model;
        this.teacherName = new SimpleStringProperty();
        this.teacherID = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void clear() {
        teacherName.set("");
        teacherID.set("");
        errorProperty.set("");
    }


    public boolean addTeacher() {
        try {
            model.addTeacher(teacherName.get(), teacherID.get());
            return true;
        } catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    public StringProperty teacherIDProperty() {
        return teacherID;
    }

    public StringProperty teacherNameProperty() {
        return teacherName;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }
}
