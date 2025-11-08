/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import unidevteam.classes.CentroVaccinale;
import unidevteam.communication.Client;
import unidevteam.enumerators.*;
import unidevteam.util.JsonReader;
import unidevteam.util.Regex;
import unidevteam.util.SceneManager;

/**
 * Classe per gestire elementi grafici
 * Permette:
 * <ul>
 * <li>Registrazione centro</li>
 * <li>Muoversi alla schermata precedente</li>
 * <li>Ottenere comuni</li>
 * </ul>
 */
public class FXMLRegistraCentroController implements Initializable {

    JsonReader jsonReader;
    List<String> province;
    List<String> comuni;
    String resl;
    Client client;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nomeCentroTextField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField nomeStradaTextField;

    @FXML
    private ComboBox<String> qualificatoreIndirizzoComboBox;

    @FXML
    private TextField numeroAbitazioneTextField;

    @FXML
    private ComboBox<String> provinciaComboBox;

    @FXML
    private ComboBox<String> comuneComboBox;

    @FXML
    private ComboBox<String> tipologiaCentroComboBox;

    @FXML
    private ImageView goBackButton;

    @FXML
    private Label errorMessage;

    /**
     * Permette la registrazione di un centro
     * @param event
     */
    @FXML
    void onClickRegistraCentro(ActionEvent event) {
        registerButton.setText("Caricamento...");
        registerButton.setDisable(true);

        nomeCentroTextField.setDisable(true);
        qualificatoreIndirizzoComboBox.setDisable(true);
        nomeStradaTextField.setDisable(true);
        numeroAbitazioneTextField.setDisable(true);
        comuneComboBox.setDisable(true);
        provinciaComboBox.setDisable(true);
        tipologiaCentroComboBox.setDisable(true);

        String nomeCentro = nomeCentroTextField.getText();
        QualificatoreIndirizzo qualificatoreIndirizzo = QualificatoreIndirizzo.valueOf(qualificatoreIndirizzoComboBox.getSelectionModel().getSelectedItem().toUpperCase());
        String nomeStrada = nomeStradaTextField.getText();
        String numeroAbitazione = numeroAbitazioneTextField.getText();
        String comune = comuneComboBox.getSelectionModel().getSelectedItem();
        String provincia = provinciaComboBox.getSelectionModel().getSelectedItem();
        provincia = provincia.substring(provincia.length()-3, provincia.length()-1);
        String CAP = jsonReader.getCAPfromComune(comune);
        TipologiaCentroVaccinale tipologiaCentro = TipologiaCentroVaccinale.valueOf(tipologiaCentroComboBox.getSelectionModel().getSelectedItem().toUpperCase());

        // Validazione campi
        // Controllo se sono vuoti
        if(nomeCentro != null && nomeStrada != null && numeroAbitazione != null && comune != null && provincia != null) {
            if(nomeCentro.trim() != "" && nomeStrada.trim() != "" && numeroAbitazione.trim() != "" && comune.trim() != "" && provincia.trim() != "") {

                // Controllo se sono validi
                if(Regex.check(nomeCentro, "^[a-zA-Z0-9 ]*$")) {
                    if(Regex.check(nomeStrada, "^[a-zA-Z0-9 ]*$")) {
                        if(Regex.check(numeroAbitazione, "^[A-Za-z0-9\\/]*$")) {
                            CentroVaccinale centroVaccinale = new CentroVaccinale(
                                nomeCentro, 
                                qualificatoreIndirizzo, 
                                nomeStrada,
                                numeroAbitazione,
                                comune, 
                                provincia, 
                                CAP, 
                                tipologiaCentro
                            );

                            try {
                                Task<String> addCentroTask = new Task<String>() {
                                    protected String call() throws Exception {
                                        client = new Client();
                                        return client.addCentroVaccinale(centroVaccinale);
                                    }
                                };
                
                                addCentroTask.setOnSucceeded(e -> {
                                    resl = addCentroTask.getValue();

                                    nomeCentroTextField.setText(null);
                                    qualificatoreIndirizzoComboBox.getSelectionModel().select(0);
                                    nomeStradaTextField.setText(null);
                                    numeroAbitazioneTextField.setText(null);
                                    comuneComboBox.getSelectionModel().select(0);
                                    provinciaComboBox.getSelectionModel().select(0);
                                    tipologiaCentroComboBox.getSelectionModel().select(0);

                                    nomeCentroTextField.setDisable(false);
                                    qualificatoreIndirizzoComboBox.setDisable(false);
                                    nomeStradaTextField.setDisable(false);
                                    numeroAbitazioneTextField.setDisable(false);
                                    comuneComboBox.setDisable(false);
                                    provinciaComboBox.setDisable(false);
                                    tipologiaCentroComboBox.setDisable(false);

                                    registerButton.setDisable(false);
                                    registerButton.setText("Registra");

                                    errorMessage.setVisible(true);
                                    errorMessage.setText("Registrazione andata a buon fine!");
                                    errorMessage.setTextFill(Color.GREEN);
                                });

                                addCentroTask.setOnFailed(e -> {
                                    errorMessage.setVisible(true);
                                    errorMessage.setText("Connessione al server non riuscita.");

                                    registerButton.setDisable(false);
                                    registerButton.setText("Registra");

                                    nomeCentroTextField.setDisable(false);
                                    qualificatoreIndirizzoComboBox.setDisable(false);
                                    nomeStradaTextField.setDisable(false);
                                    numeroAbitazioneTextField.setDisable(false);
                                    comuneComboBox.setDisable(false);
                                    provinciaComboBox.setDisable(false);
                                    tipologiaCentroComboBox.setDisable(false);
                                });

                                new Thread(addCentroTask).start();

                            } catch(Exception e) {
                                errorMessage.setVisible(true);
                                errorMessage.setText("Connessione al server non riuscita.");
                            }

                            // Gestisci codice centro vaccinale (ID)
                            // ...
                        } else {
                            errorMessage.setVisible(true);
                            errorMessage.setText("Il numero civico non è valido.");
                        }
                    } else {
                        errorMessage.setVisible(true);
                        errorMessage.setText("Il nome della strada non è valido.");    
                    }
                } else {
                    errorMessage.setVisible(true);
                    errorMessage.setText("Il nome del centro non è valido.");
                }
            } else {
                errorMessage.setVisible(true);
                errorMessage.setText("Compila tutti i campi.");
            }
        } else {
            errorMessage.setVisible(true);
            errorMessage.setText("Compila tutti i campi.");
        }
    }

    /**
     * Permette di tornare alla scena precedente
     * @param event
     */
    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    /**
     * Ottiene i comuni e li inserisce nella combobox
     * @param event
     */
    @FXML
    void onActionProvincia(ActionEvent event) {
        String fullProvincia = provinciaComboBox.getValue();
        String siglaProvincia = fullProvincia.substring(fullProvincia.length()-3, fullProvincia.length()-1);
        
        Task<Void> getComuniTask = new Task<Void>() {
            protected Void call() throws Exception {
                comuni = jsonReader.getComuniByProvincia(siglaProvincia);
                return null;
            }
        };

        getComuniTask.setOnSucceeded(e -> {
            comuneComboBox.getItems().setAll(comuni);
            comuneComboBox.setDisable(false);
            if(errorMessage.getText().equals("Errore inaspettato, chiudi e riapri l'applicazione"))
                errorMessage.setVisible(false);
        });

        getComuniTask.setOnFailed(e -> {
            // Errore
            errorMessage.setVisible(true);
            errorMessage.setText("Errore inaspettato, chiudi e riapri l'applicazione");
            registerButton.setDisable(true);
        });

        new Thread(getComuniTask).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jsonReader = new JsonReader();

        errorMessage.setVisible(false);

        provinciaComboBox.setDisable(true);
        comuneComboBox.setDisable(true);
        Task<Void> getProvinceTask = new Task<Void>() {
            protected Void call() throws Exception {
                province = jsonReader.getProvince();
                return null;
            }
        };

        getProvinceTask.setOnSucceeded(e -> {
            provinciaComboBox.getItems().addAll(province);
            provinciaComboBox.setDisable(false);
            if(errorMessage.getText().equals("Errore inaspettato, chiudi e riapri l'applicazione"))
                errorMessage.setVisible(false);
        });

        getProvinceTask.setOnFailed(e -> {
            // Errore
            errorMessage.setVisible(true);
            errorMessage.setText("Errore inaspettato, chiudi e riapri l'applicazione");
            registerButton.setDisable(true);
        });

        new Thread(getProvinceTask).start();

        qualificatoreIndirizzoComboBox.getItems().addAll(
            QualificatoreIndirizzo.VIA.getValue(),
            QualificatoreIndirizzo.VIALE.getValue(),
            QualificatoreIndirizzo.PIAZZA.getValue()
        );
        qualificatoreIndirizzoComboBox.getSelectionModel().select(0);

        tipologiaCentroComboBox.getItems().addAll(
            TipologiaCentroVaccinale.OSPEDALIERO.getValue(),
            TipologiaCentroVaccinale.AZIENDALE.getValue(),
            TipologiaCentroVaccinale.HUB.getValue()
        );
        tipologiaCentroComboBox.getSelectionModel().select(0);
    }
}
