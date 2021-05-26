import javafx.application.Application;

public class MainClient {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "rmi.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Application.launch(MyApplication.class);
    }
}