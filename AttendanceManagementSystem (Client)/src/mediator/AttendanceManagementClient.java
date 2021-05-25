package mediator;

import javafx.application.Platform;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AttendanceManagementClient implements ClientModel, RemoteListener<Object, Object> {
    private RemoteModel remoteModel;
    private PropertyChangeHandler<Object, Object> property;

    public static final String HOST = "localhost";

    public AttendanceManagementClient()
            throws RemoteException, NotBoundException, MalformedURLException {
        this.remoteModel = (RemoteModel) Naming.lookup("rmi://" + HOST
                + ":1099/AMS");
        UnicastRemoteObject.exportObject(this, 0);
        remoteModel.addListener(this);
        this.property = new PropertyChangeHandler<>(this);
    }


    @Override
    public void propertyChange(ObserverEvent event) throws RemoteException {

    }

    @Override
    public boolean addListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return false;
    }

    @Override
    public boolean removeListener(GeneralListener<String, Package> listener, String... propertyNames) {
        return false;
    }
}
}
