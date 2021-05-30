package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.Student;
import model.Teacher;

import java.sql.SQLException;
import java.util.Objects;

public class ManageViewModel {

    private Model model;
    private StringProperty nameProperty;
    private StringProperty idProperty;
    private StringProperty passwordProperty1;
    private StringProperty passwordProperty2;
    private StringProperty errorProperty;
    private ViewModelState viewState;

    public ManageViewModel(Model model, ViewModelState viewState) {
        this.model = model;
        this.viewState = viewState;
        nameProperty = new SimpleStringProperty();
        idProperty = new SimpleStringProperty();
        passwordProperty1 = new SimpleStringProperty();
        passwordProperty2 = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public StringProperty getIdProperty() {
        return idProperty;
    }

    public StringProperty getPasswordProperty1() {
        return passwordProperty1;
    }

    public StringProperty getPasswordProperty2() {
        return passwordProperty2;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }


    public void clear() {
        errorProperty.set("");
        passwordProperty1.set("");
        passwordProperty2.set("");
    }

    public void loadPersonDetails() {
        switch (viewState.getSection()){
            case "Student":
                Student student = model.getStudentBy(viewState.getStudentID());
                nameProperty.set(student.getName());
                idProperty.set(student.getID());
                break;
            case "Teacher":
                Teacher teacher = model.getTeacherBy(viewState.getTeacherID());
                nameProperty.set(teacher.getName());
                idProperty.set(teacher.getID());
                break;
        }
    }

    public boolean saveDetails() {
        if (!Objects.equals(passwordProperty1.get(), passwordProperty2.get())) {
            errorProperty.set("Please check your password spelling.");
            return false;
        }

        try {
            switch (viewState.getSection()) {
                case "Teacher":
                    model.changePassword(viewState.getTeacherID(), passwordProperty1.get());
                    break;
                case "Student":
                    model.changePassword(viewState.getStudentID(), passwordProperty1.get());
                    break;
            }
            return true;
        } catch (IllegalArgumentException | SQLException e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }
}
