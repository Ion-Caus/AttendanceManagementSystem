package mediator;

import model.Model;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.GeneralListener;
import utility.observer.listener.LocalListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteModelManager implements RemoteModel, LocalListener<Object, Object> {
    private PropertyChangeHandler<Object, Object> property;
    private Model model;

    public RemoteModelManager(Model model) throws RemoteException, MalformedURLException {
        this.model = model;
        this.property = new PropertyChangeHandler<>(this);

        startRegistry();
        startServer();
        model.addListener(this);
    }

    private void startRegistry() throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started...");
        } catch (java.rmi.server.ExportException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void startServer() throws RemoteException, MalformedURLException {
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind("AMS", this);
    }

    public void close() {
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            // do nothing
        }
    }

    @Override
    public void propertyChange(ObserverEvent<Object, Object> event) {

    }

    @Override
    public boolean addListener(GeneralListener<Object, Object> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<Object, Object> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }
}