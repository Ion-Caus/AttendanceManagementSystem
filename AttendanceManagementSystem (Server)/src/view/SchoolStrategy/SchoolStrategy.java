package view.SchoolStrategy;
import view.View;
import viewModel.SchoolViewModel;


public abstract class SchoolStrategy {
    protected SchoolViewModel viewModel;

    public SchoolStrategy(SchoolViewModel viewModel) {
        this.viewModel = viewModel;
    }

    abstract View add();

    abstract void remove();
}