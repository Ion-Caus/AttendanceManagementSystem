package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Student;

public class StudentViewModel {
    private StringProperty name;
    private StringProperty id;

    public StudentViewModel(String name, String id) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty idProperty() {
        return id;
    }
}