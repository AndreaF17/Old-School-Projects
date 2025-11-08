/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe per la gestione del centro vaccinale
 * Possiede getter e setter per impostare i valori chiave della classe
 * Serializzabile in quanto utilizzata assieme all'RMI
 */
public class Server extends Application
{
    public static void main( String[] args ){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            System.out.println(getClass().getClassLoader().getResource("primula.png").toString());
            stage.getIcons().add(new Image(getClass().getClassLoader().getResource("primula.png").toString()));
        } catch (FileNotFoundException e) {
            root = FXMLLoader.load(new File("../login.fxml").toURI().toURL());
            stage.getIcons().add(new Image(new File("../primula.png").toURI().toURL().toString()));
        } finally {
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.setTitle("Server Dashboard");
            stage.show();
            root.requestFocus();
        }
    }
}
