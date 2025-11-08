/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import unidevteam.communication.Client;
import unidevteam.util.SceneManager;

/**
 * Classe per gestire elementi grafici
 * Permette di:
 * <ul>
 * <li>Portare all'accesso del cittadino</li>
 * <li>Portare all'accesso ospite</li>
 * <li>Portare alla registrazione un cittadino</li>
 * </ul>
 */
public class FXMLController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane accediButton;

    @FXML
    private AnchorPane registratiButton;

    @FXML
    private Label ospiteButton;

    /**
     * Portare all'accesso del cittadino
     * @param event
     */
    @FXML
    void onClickAccessoCittadino(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "accesso");
    }

    /**
     * Portare all'accesso ospite
     * @param event
     */
    @FXML
    void onClickAccessoOspite(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "home");
    }

    /**
     * Portare alla registrazione un cittadino
     * @param event
     */
    @FXML
    void onClickRegistraCittadino(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "registrazione1");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        new Client();
    }
}