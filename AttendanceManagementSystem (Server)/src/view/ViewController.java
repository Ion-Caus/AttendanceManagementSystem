package view;

import javafx.scene.layout.Region;
import viewModel.ViewModelFactory;


public abstract class ViewController {

    private Region root;
    private ViewHandler viewHandler;
    private ViewModelFactory viewModelFactory;

    public ViewController() {
        // Called by FXML loader
    }

    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.root = root;

        init();
        reset();
    }

    protected abstract void init();

    public abstract void reset();

    public Region getRoot() {
        return root;
    }

    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }

    public ViewHandler getViewHandler() {
        return viewHandler;
    }
}
