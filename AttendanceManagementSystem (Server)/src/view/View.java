package view;

public enum View {
    
    LOGIN_VIEW("LoginView.fxml"),
    SCHOOL_VIEW("SchoolView.fxml"),
    SCHEDULE_VIEW("ScheduleView.fxml"),
    ADD_CLASS_VIEW("AddClassView.fxml"),
    ADD_STUDENT_VIEW("AddStudentView.fxml"),
    ADD_TEACHER_VIEW("AddTeacherView.fxml"),
    ADD_GRADE_VIEW("AddGradeView.fxml"),
    INFO_VIEW("InfoView.fxml"),
    CLASS_STUDENT_LIST_VIEW("ClassStudentListView.fxml"),
    ADD_CLASS("AddLessonView.fxml"),
    STUDENT_LIST_VIEW("StudentListView.fxml");

    private final String fxmlFile;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

}
