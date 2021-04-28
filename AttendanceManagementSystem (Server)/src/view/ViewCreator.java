package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.util.HashMap;

public abstract class ViewCreator {

    private HashMap<View, ViewController> map;

    public ViewCreator() {
        this.map = new HashMap<>();
    }

    public ViewController getViewController(View view) {
        ViewController controller = map.get(view);
        if (controller == null) {
            controller = loadFromFXML(view.getFxmlFile());
            map.put(view, controller);
        }
        else {
            controller.reset();
        }
        return controller;
    }

    protected abstract void initViewController(ViewController controller, Region root);

    private ViewController loadFromFXML(String fxmlFile) {
        ViewController viewController = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlFile));
            Region root = loader.load();
            viewController = loader.getController();
            initViewController(viewController, root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return viewController;
    }

}
