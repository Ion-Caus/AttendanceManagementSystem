package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Student;

public class StudentViewModel {
    private StringProperty name;
    private StringProperty id;

    //TODO change to 2 strings
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
