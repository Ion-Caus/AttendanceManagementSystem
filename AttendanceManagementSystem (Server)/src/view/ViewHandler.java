package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewModel.ViewModelFactory;


public class ViewHandler extends ViewCreator {
    private Scene currentScene;
    private Stage primaryStage;
    private ViewModelFactory viewModelFactory;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // TODO change to LOGIN_VIEW
        openView(View.SCHOOL_VIEW);
    }

    public void openView(View view) {
        Region root = getViewController(view).getRoot();

        currentScene.setRoot(root);

        String title = "";
        assert root != null;
        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }
        Image image = new Image("/view/study.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    protected void initViewController(ViewController controller, Region root) {
        controller.init(this, viewModelFactory, root);
    }
}

