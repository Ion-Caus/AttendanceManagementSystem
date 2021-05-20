package mediator;

import model.packages.Package;
import utility.observer.subject.RemoteSubject;

public interface RemoteModel extends RemoteSubject<String, Package> {
}
