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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import unidevteam.util.SceneManager;

/**
 * Classe per gestire elementi grafici
 * Permette di spostarsi nelle scene in base al pulasante premuto
 */
public class FXMLController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane centroVaccinaleButton;

    @FXML
    private AnchorPane vaccinatoButton;

    /**
     * Alla pressione del tasto registra centro
     * @param event
     */
    @FXML
    void onClickRegistraCentro(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "registercentro");
    }

    /**
     * Alla pressione del tasto registra vaccinato
     * @param event
     */
    @FXML
    void onClickRegistraVaccinato(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "registervaccinato");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}
}