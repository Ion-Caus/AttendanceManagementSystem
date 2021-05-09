package view.SchoolStrategy;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.SchoolViewController;
import viewModel.ClassViewModel;
import viewModel.SchoolViewModel;

import java.util.Optional;

public class SchoolClassesStrategy extends SchoolStrategy
{

  public SchoolClassesStrategy(SchoolViewController controller, SchoolViewModel viewModel)
  {
    super(controller, viewModel);
  }

  @Override public void add() {

  }

  @Override public void remove() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    Optional<ButtonType> result;

    ClassViewModel classViewModel = controller.getClassesTable().getSelectionModel().getSelectedItem();
    alert.setTitle("Delete class");
    alert.setHeaderText("Delete class " + classViewModel.classNameProperty() + " ?");
    result = alert.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
      controller.getClassesTable().getSelectionModel().clearSelection();
      viewModel.removeClass(classViewModel);


      //placeholder until we have a way to really delete it
//      controller.getClassesTable().getItems().remove(classViewModel);
    }
  }
}
