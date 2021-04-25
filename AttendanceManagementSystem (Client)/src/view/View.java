package view;

public enum View {
    SCHEDULE_VIEW("ScheduleView.fxml"),
    LOGIN_VIEW("LoginView.fxml");

    private String fxmlFile;

    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

}
