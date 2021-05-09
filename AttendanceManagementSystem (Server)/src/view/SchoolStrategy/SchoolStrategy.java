package view.SchoolStrategy;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.SchoolViewController;
import view.ViewController;
import view.ViewHandler;
import viewModel.SchoolViewModel;

import java.util.Optional;

public abstract class SchoolStrategy
{
  protected SchoolViewController controller;
  protected SchoolViewModel viewModel;
  protected ViewController viewController;



 protected Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
 protected Optional<ButtonType> result;

  public SchoolStrategy(SchoolViewController controller, SchoolViewModel viewModel) {
    this.controller = controller;
    this.viewModel = viewModel;
  }

  abstract void add();
  abstract void remove();
}
