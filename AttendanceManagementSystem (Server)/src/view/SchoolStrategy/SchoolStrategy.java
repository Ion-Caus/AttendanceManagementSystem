package view.SchoolStrategy;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.View;
import viewModel.SchoolViewModel;

import java.util.Optional;


public abstract class SchoolStrategy {
    protected SchoolViewModel viewModel;
    protected Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    protected Optional<ButtonType> result;

    public SchoolStrategy(SchoolViewModel viewModel) {
        this.viewModel = viewModel;
    }

    abstract View add();

    abstract void remove();
}