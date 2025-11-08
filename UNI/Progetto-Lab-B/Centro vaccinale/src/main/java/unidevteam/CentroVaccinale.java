/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point applicazione client centro vaccinale 
 */
public class CentroVaccinale extends Application{
    public static void main( String[] args ) throws RemoteException, NotBoundException {
        launch(args);
        // new Client();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("scene.fxml"));
        } catch (Exception e) {
            root = FXMLLoader.load(new File("src/main/resources/scene.fxml").toURI().toURL());
        } finally {
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.setTitle("Client: Centro vaccinale");
            stage.show();
            root.requestFocus();
        }
    }
}
