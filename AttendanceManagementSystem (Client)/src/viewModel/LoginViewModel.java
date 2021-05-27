package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import view.View;

public class LoginViewModel {
    private StringProperty usernameProperty;
    private StringProperty passwordProperty;
    private StringProperty errorProperty;

    private Model model;
    private ViewModelState viewModelState;

    public LoginViewModel(Model model, ViewModelState viewModelState){
        this.model = model;
        this.viewModelState = viewModelState;

        this.usernameProperty = new SimpleStringProperty();
        this.passwordProperty = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void clear() {
        usernameProperty.set("");
        passwordProperty.set("");
        errorProperty.set("");
    }

    public View login() {
        try {
            String access = model.login(usernameProperty().get(), passwordProperty.get());
            switch (access) {
                case "admin":
                    viewModelState.setAccessLevel("Administrator");
                    return View.SCHOOL_VIEW;
                case "teacher":
                    viewModelState.setAccessLevel("Teacher");
                    viewModelState.setSection("Teacher");
                    viewModelState.setTeacherID(usernameProperty.get());
                    return View.SCHEDULE_VIEW;
                case "student":
                    viewModelState.setAccessLevel("Student");
                    viewModelState.setSection("Student");
                    viewModelState.setStudentID(usernameProperty().get());
                    return View.SCHEDULE_VIEW;
            }
        } catch (IllegalAccessException e) {
            errorProperty.set(e.getLocalizedMessage());
        }
        return null;
    }

    public StringProperty usernameProperty() {
        return usernameProperty;
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }
}
