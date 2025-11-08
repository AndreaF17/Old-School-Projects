/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.controllers;

import unidevteam.communication.*;
import unidevteam.util.DBManager;
import unidevteam.util.SceneManager;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Classe per gestire elementi grafici
 * Permette di:
 * <ul>
 * <li>Uccidere il server</li>
 * <li>Attuare un update delle metrics</li>
 * </ul>
 */
public class FXMLMetricsController {

    ServerCentroVaccinale rmiServerCentroVaccinale;
    ServerCittadino rmiServerCittadini;

    String numOfVaccineCenters;
    String numOfCitizens;
    String numOfAdverseEvents;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label serverStatusLabel;

    @FXML
    private Label numOfVaccineCentersLabel;

    @FXML
    private Label numOfCitizensLabel;

    @FXML
    private Label numOfAdverseEventsLabel;

    @FXML
    private ImageView updateButton;

    @FXML
    private Button killServerButton;

    /**
     * Permette di uccidere il server
     * @param event
     */
    @FXML
    void onClickKillServer(ActionEvent event) {
        try {
            rmiServerCentroVaccinale.exit();
            rmiServerCittadini.exit();
            new SceneManager().switchToNewScene(event, "login");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permette di aggiornare i dati mostrati nella finestra
     * @param event
     */
    @FXML
    void onClickUpdateMetrics(MouseEvent event) {

        Task<Void> updateMetricsTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                numOfVaccineCenters = Long.toString(DBManager.getInstance().getCountCentriVaccinali());
                numOfCitizens = Long.toString(DBManager.getInstance().getCountCittadini());
                numOfAdverseEvents = Long.toString(DBManager.getInstance().getCountEventiAvversi());
                return null;
            }
        };

        updateMetricsTask.setOnSucceeded(e -> {
            numOfVaccineCentersLabel.setText(numOfVaccineCenters);
            numOfCitizensLabel.setText(numOfCitizens);
            numOfAdverseEventsLabel.setText(numOfAdverseEvents);
            killServerButton.setStyle("-fx-background-color: #E3381E; -fx-text-fill: #FFF");
            killServerButton.setText("Termina server");
        });

        updateMetricsTask.setOnFailed(e -> {
            killServerButton.setStyle("-fx-background-color: #E3381E; -fx-text-fill: #FFF");
            killServerButton.setText("Termina server");
        });

        new Thread(updateMetricsTask).start();
        killServerButton.setStyle("-fx-background-color: #C9CCD5");
        killServerButton.setText("Caricamento...");
    }

    @FXML
    void initialize() {
        DBManager dbManager;
        try {
            dbManager = DBManager.getInstance();
            numOfVaccineCentersLabel.setText(Long.toString(dbManager.getCountCentriVaccinali()));
            numOfCitizensLabel.setText(Long.toString(dbManager.getCountCittadini()));
            numOfAdverseEventsLabel.setText(Long.toString(dbManager.getCountEventiAvversi()));

            rmiServerCentroVaccinale = new ServerCentroVaccinale();
            rmiServerCittadini = new ServerCittadino();
        
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
