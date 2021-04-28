package viewmodel;

import model.Class;
import model.Student;

public class ViewModelState {
    private Class theClass;
    private Student student;

    public ViewModelState() {
        this.theClass = null;
        this.student = null;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setTheClass(Class theClass) {
        this.theClass = theClass;
    }

    public Class getTheClass() {
        return theClass;
    }
}
