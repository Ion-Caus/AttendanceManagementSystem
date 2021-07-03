package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.StudentClass;

public class ClassViewModel {
    private StringProperty className;

    public ClassViewModel(StudentClass theClass) {
        this.className = new SimpleStringProperty(theClass.getClassName());
    }

    public StringProperty classNameProperty() {
        return className;
    }
}