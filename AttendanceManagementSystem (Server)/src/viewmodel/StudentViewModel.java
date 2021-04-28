package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Student;

public class StudentViewModel {
    private StringProperty name;

    public StudentViewModel(Student student) {
        this.name = new SimpleStringProperty(student.getName());
    }

    public StringProperty nameProperty() {
        return name;
    }
}
