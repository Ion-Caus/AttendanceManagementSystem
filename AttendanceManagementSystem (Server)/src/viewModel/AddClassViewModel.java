package viewModel;

import dao.ClassesDAO;
import dao.ClassesDAOImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.sql.SQLException;

public class AddClassViewModel {
    private StringProperty className;
    private StringProperty errorProperty;

    private Model model;

    public AddClassViewModel(Model model){
        this.model = model;
        this.className = new SimpleStringProperty();
        this.errorProperty = new SimpleStringProperty();
    }

    public void clear() {
        className.set("");
        errorProperty.set("");
    }


    public boolean addclass() {
        try {
            model.addClass(className.get());
            return true;
        }
        catch (IllegalArgumentException | SQLException e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }

    public StringProperty classNameProperty() {
        return className;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }
}
