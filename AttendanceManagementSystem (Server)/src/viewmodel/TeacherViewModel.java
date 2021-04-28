package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Teacher;

public class TeacherViewModel {
    private StringProperty name;

    public TeacherViewModel(Teacher teacher) {
        this.name = new SimpleStringProperty(teacher.getName());
    }

    public StringProperty nameProperty() {
        return name;
    }
}
