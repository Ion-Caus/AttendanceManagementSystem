import javafx.application.Application;
import javafx.stage.Stage;
import mediator.RemoteModel;
import mediator.RemoteModelManager;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewModel.ViewModelFactory;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyApplication extends Application {
    private Model model;
    private RemoteModel server;

    @Override
    public void start(Stage primaryStage) {
        try {
            model = new ModelManager();
            ViewModelFactory viewModelFactory = new ViewModelFactory(model);
            ViewHandler viewHandler = new ViewHandler(viewModelFactory);

            viewHandler.start(primaryStage);

            // starting server...

            server = new RemoteModelManager(model);

        } catch (RemoteException | MalformedURLException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        ( (RemoteModelManager)server ).close();
    }
}
