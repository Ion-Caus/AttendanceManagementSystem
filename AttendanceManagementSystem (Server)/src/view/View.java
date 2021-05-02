package view;

public enum View {
    SCHOOL_VIEW("SchoolView.fxml"),
    SCHEDULE_VIEW("ScheduleView.fxml"),
    LOGIN_VIEW("LoginView.fxml"),
    STUDENT_VIEW("StudentView.fxml"),
    CLASS_VIEW("ClassView.fxml");

    private String fxmlFile;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

}
