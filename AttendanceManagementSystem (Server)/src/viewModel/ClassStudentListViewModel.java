package viewModel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import model.packages.Package;
import model.packages.PackageName;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.LocalListener;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClassStudentListViewModel implements LocalListener<String, Package> {

    private Model model;
    private ViewModelState viewModelState;

    private StringProperty className;
    private StringProperty searchField;
    private StringProperty errorProperty;

    private ObservableList<StudentViewModel> classStudentTable;
    private ObjectProperty<StudentViewModel> selectedStudentProperty;

    ClassStudentListViewModel(Model model, ViewModelState viewModelState) {
        this.model = model;
        this.model.addListener(this,  "ADD_TO_CLASS Student", "REMOVE_FROM_CLASS Student");
        this.viewModelState = viewModelState;

        className = new SimpleStringProperty(viewModelState.getClassName());
        searchField = new SimpleStringProperty();
        errorProperty = new SimpleStringProperty();

        classStudentTable = FXCollections.observableArrayList();
        selectedStudentProperty = new SimpleObjectProperty<>();

    }
    
    public void loadFromModel() {
        classStudentTable.clear();
        for(Student student: model.getStudentsByClass(viewModelState.getClassName())){
            classStudentTable.add(new StudentViewModel(student));
        }
    }

    public void clear() {
        className.setValue("Class " + viewModelState.getClassName());
        searchField.setValue("");
        errorProperty.setValue("");
    }

    public void addStudent() {
        try {
            //TODO 17/5 by Ion clean up
            String id = (searchField.get().contains("("))? searchField.get().split("[()]")[1] : "no id";
            model.addStudentToClass(id, viewModelState.getClassName() );
            clear();
        }
        catch (IllegalArgumentException | SQLException e) {
            errorProperty.set(e.getMessage());
        }
    }

    public void removeStudent(String ID) {
        try {
            model.removeStudentFromClass(ID, viewModelState.getClassName() );
            clear();
        }
        catch (IllegalArgumentException | SQLException e) {
            errorProperty.set(e.getMessage());
        }

    }



    public ArrayList<String> getUnassignedStudents() {
        return model.getUnassignedStudents();
    }


    public StudentViewModel getSelectedStudent() {
        return selectedStudentProperty.get();
    }
    
    public void setSelected(StudentViewModel selectedStudent) {
        selectedStudentProperty.set(selectedStudent);
    }

    public ObservableList<StudentViewModel> getClassStudentTable() {
        return classStudentTable;
    }

    public StringProperty classProperty() {
        return className;
    }

    public StringProperty searchFieldProperty() {
        return searchField;
    }

    public StringProperty errorProperty() {
        return errorProperty;
    }

    @Override
    public void propertyChange(ObserverEvent<String, Package> event) {
        Platform.runLater(() -> {
            PackageName packageName = (PackageName)event.getValue2();

            if (!viewModelState.getClassName().equals(packageName.getName())) {
                return;
            }

            switch (event.getPropertyName().split(" ")[0]) {
                case "ADD_TO_CLASS":
                    classStudentTable.add(new StudentViewModel(
                            model.getStudentBy(packageName.getID())
                            )
                    );
                    break;
                case "REMOVE_FROM_CLASS":
                    classStudentTable.removeIf(
                            student -> student.idProperty().get().equals(packageName.getID())
                    );
                    break;
            }
        });
    }

    public boolean hasSelection() {
        if (selectedStudentProperty.get() == null) {
            errorProperty.set("Please select a student.");
            return false;
        }
        return true;
    }

}
