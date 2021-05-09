package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.ClassList;
import model.School;
import viewModel.ClassViewModel;
import viewModel.SchoolViewModel;

public class StudentViewController extends ViewController{



    @FXML  private TextField studentName;



    SchoolViewModel viewModel;



    @Override
    protected void init() {



        viewModel = getViewModelFactory().getSchoolViewModel();



    }


    @FXML public void okayButtonClicked(){
        viewModel.addStudent(studentName.getText());
        getViewHandler().openView(View.SCHOOL_VIEW);
        reset();

    }

    @FXML public void cancelButtonClicked(){
        getViewHandler().openView(View.SCHOOL_VIEW);
        reset();
    }

    @Override
    public void reset() {
        studentName.clear();
    }
}
