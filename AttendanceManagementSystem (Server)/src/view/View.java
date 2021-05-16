package view;

public enum View {
    LOGIN_VIEW("LoginView.fxml"),
    SCHOOL_VIEW("SchoolView.fxml"),
    SCHEDULE_VIEW("ScheduleView.fxml"),
    CLASS_VIEW("AddClassView.fxml"),
    STUDENT_VIEW("AddStudentView.fxml"),
    TEACHER_VIEW("AddTeacherView.fxml"),
    INFO_VIEW("InfoView.fxml"),
    STUDENTLIST_VIEW("StudentListView.fxml");


    private final String fxmlFile;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

}
