package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Teacher;

public class TeacherViewModel {
    private StringProperty name;
    private StringProperty id;
    private StringProperty initials;


    public TeacherViewModel(Teacher teacher) {
        this.name = new SimpleStringProperty(teacher.getName());
        this.id = new SimpleStringProperty(teacher.getID());
        this.initials = new SimpleStringProperty(teacher.getInitials());
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty initialsProperty() {
        return initials;
    }


}
