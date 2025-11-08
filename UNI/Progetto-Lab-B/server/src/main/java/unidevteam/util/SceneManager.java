/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Classe che ci permette di cambiare scena
 */
public class SceneManager {
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Permette di cambiare scena
     * @param event di tipo ActionEvent
     * @param fileName scena su cui vuoi andare
     */
    public void switchToNewScene(ActionEvent event, String fileName) {
        try {
            try{
                root = FXMLLoader.load(getClass().getClassLoader().getResource(fileName+".fxml"));
            } catch (FileNotFoundException e) {
                root = FXMLLoader.load(new File("../"+ fileName + ".fxml").toURI().toURL());
            } finally {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                if(fileName.equals("metrics")) {
                    stage.setOnCloseRequest(onCLickEvent -> {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attenzione");
                        alert.setHeaderText("Attenzione");
                        alert.setContentText("Impossibile chiudere la pagina. \n Se vuoi terminare il programma termina il server e poi esci.");
                        alert.showAndWait();
                        onCLickEvent.consume();
                });
                } else {
                    stage.setOnCloseRequest(onCLickEvent -> {
                        stage.close();
                    });
                }
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                root.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
