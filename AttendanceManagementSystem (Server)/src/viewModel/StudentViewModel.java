package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Student;

public class StudentViewModel {
    private StringProperty name;
    private StringProperty id;

    //TODO 13/5 by Ion Change this to Student, and add StringProperty class to display on the table the class.
    public StudentViewModel(Student student) {
        this.name = new SimpleStringProperty(student.getName());
        this.id = new SimpleStringProperty(student.getID());
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty idProperty() {
        return id;
    }
}