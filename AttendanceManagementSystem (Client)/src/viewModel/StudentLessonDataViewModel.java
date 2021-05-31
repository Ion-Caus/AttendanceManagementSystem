package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.LessonData;

public class StudentLessonDataViewModel {

    private StringProperty idStudentProperty;
    private StringProperty studentNameProperty;
    private StringProperty commentProperty;
    private StringProperty absenceProperty;
    private StringProperty motiveProperty;
    private StringProperty gradeProperty;

    public StudentLessonDataViewModel(LessonData lessonData) {
        this.idStudentProperty = new SimpleStringProperty(lessonData.getStudent().getID());
        this.studentNameProperty = new SimpleStringProperty(lessonData.getStudent().getName());
        this.gradeProperty = new SimpleStringProperty((lessonData.getGrade().getGrade() == -1)? " " : "" + lessonData.getGrade().getGrade());
        this.commentProperty = new SimpleStringProperty(lessonData.getGrade().getComment());
        this.absenceProperty = new SimpleStringProperty(lessonData.getAbsence().wasAbsent() ? "YES" : "---");
        this.motiveProperty = new SimpleStringProperty(lessonData.getAbsence().getMotive());
    }

    public StringProperty getIDStudentProperty() {
        return idStudentProperty;
    }

    public StringProperty getStudentNameProperty() {
        return studentNameProperty;
    }

    public StringProperty getCommentProperty() {
        return commentProperty;
    }

    public StringProperty getAbsenceProperty() {
        return absenceProperty;
    }

    public StringProperty getMotiveProperty() {
        return motiveProperty;
    }

    public StringProperty getGradeProperty() {
        return gradeProperty;
    }

    public void setAbsence(String absence) {
       absenceProperty.set(absence);
    }
}
