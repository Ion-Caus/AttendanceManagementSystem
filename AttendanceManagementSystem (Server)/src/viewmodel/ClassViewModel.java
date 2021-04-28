package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Class;

public class ClassViewModel {
    private StringProperty className;

    public ClassViewModel(Class theClass) {
        this.className = new SimpleStringProperty(theClass.getClassName());
    }

    public StringProperty classNameProperty() {
        return className;
    }
}
