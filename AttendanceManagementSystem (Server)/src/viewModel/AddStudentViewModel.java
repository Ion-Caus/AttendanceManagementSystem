package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AddStudentViewModel {
    private StringProperty studentName;
    private StringProperty studentID;
    private StringProperty errorProperty;

    private Model model;

    public AddStudentViewModel(Model model){
        this.model = model;
        this.studentName = new SimpleStringProperty();
        this.studentID = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void clear() {
        studentName.set("");
        studentID.set("");
        errorProperty.set("");
    }


    public boolean addStudent() {
        try {
            model.addStudent(studentName.get(), studentID.get());
            return true;
        }
        catch (IllegalArgumentException e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    public StringProperty studentIDProperty() {
        return studentID;
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }

}
