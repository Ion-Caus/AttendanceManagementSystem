package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import view.View;

public class LoginViewModel {
    private StringProperty userIDProperty;
    private StringProperty passwordProperty;
    private StringProperty errorProperty;

    private Model model;
    private ViewModelState viewModelState;

    public LoginViewModel(Model model, ViewModelState viewModelState){
        this.model = model;
        this.viewModelState = viewModelState;

        this.userIDProperty = new SimpleStringProperty();
        this.passwordProperty = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void clear() {
        userIDProperty.set("");
        passwordProperty.set("");
        errorProperty.set("");
    }

    public View login() {
        try {
            String access = model.login(userIDProperty.get(), passwordProperty.get());
            switch (access) {
                case "admin":
                    viewModelState.setAccessLevel("Administrator");
                    return View.SCHOOL_VIEW;
                case "teacher":
                    viewModelState.setAccessLevel("Teacher");
                    viewModelState.setSection("Teacher");
                    viewModelState.setTeacherID(userIDProperty.get());
                    return View.SCHEDULE_VIEW;
                case "student":
                    viewModelState.setAccessLevel("Student");
                    viewModelState.setSection("Student");
                    viewModelState.setStudentID(userIDProperty.get());
                    return View.SCHEDULE_VIEW;
            }
        } catch (IllegalAccessException e) {
            errorProperty.set(e.getLocalizedMessage());
        }
        return null;
    }

    public StringProperty userIDProperty() {
        return userIDProperty;
    }

    public StringProperty passwordProperty() {
        return passwordProperty;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }
}
