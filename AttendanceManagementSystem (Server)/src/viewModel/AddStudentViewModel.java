package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AddStudentViewModel {
    private StringProperty errorProperty;
    private Model model;

    public AddStudentViewModel(Model model){
        this.model = model;
        this.errorProperty = new SimpleStringProperty();
    }


    public boolean addStudent(String studentName) {
        try {
            model.addStudent(studentName, "someTempId");
            return true;
        } catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    public StringProperty addStudentErrorProperty() {
        return errorProperty;
    }

}
