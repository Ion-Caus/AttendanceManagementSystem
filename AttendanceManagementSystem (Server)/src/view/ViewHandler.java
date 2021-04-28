package view;

import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;


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
        Region root = null;

        switch (view) {
            case SCHOOL_VIEW:
                root = getViewController(View.SCHOOL_VIEW).getRoot();
                break;
            case LOGIN_VIEW:
                root = getViewController(View.LOGIN_VIEW).getRoot();
                break;
            case SCHEDULE_VIEW:
                root = getViewController(View.SCHEDULE_VIEW).getRoot();
                break;
        }

        currentScene.setRoot(root);

        String title = "";
        assert root != null;
        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }
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
