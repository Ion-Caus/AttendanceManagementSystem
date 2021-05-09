package view.SchoolStrategy;

import view.SchoolViewController;
import viewModel.SchoolViewModel;

public abstract class SchoolStrategy
{
  protected SchoolViewController controller;
  protected SchoolViewModel viewModel;

  public SchoolStrategy(SchoolViewController controller, SchoolViewModel viewModel) {
    this.controller = controller;
    this.viewModel = viewModel;
  }

  abstract void add();
  abstract void remove();
}
