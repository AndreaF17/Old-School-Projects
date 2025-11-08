/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.controllers;

import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import unidevteam.classes.CentroVaccinale;
import unidevteam.communication.Client;
import unidevteam.enumerators.TipoVaccino;
import unidevteam.util.*;

/**
 * Classe per gestire elementi grafici
 * Permette:
 * <ul>
 * <li>Registrazione vaccinato</li>
 * <li>Muoversi alla schermata precedente</li>
 * </ul>
 */
public class FXMLRegistraVaccinatoController implements Initializable {

    List<CentroVaccinale> centriVaccinali;
    HashMap<String,String> idNomiCentri;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView goBackButton;

    @FXML
    private ComboBox<CentroVaccinale> nomeCentroComboBox;

    @FXML
    private TextField nomeCittadinoTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField cognomeCittadinoTextField;

    @FXML
    private TextField codiceFiscaleTextField;

    @FXML
    private ComboBox<String> tipoVaccinoComboBox;

    @FXML
    private DatePicker dataSomministrazioneDatePicker;

    /**
     * Permette di tornare alla scena precedente 
     * @param event
     */
    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    /**
     * Permette di registrare un vaccinato ottenendo i dati dalla GUI
     * @param event
     */
    @FXML
    void onClickRegistraVaccinato(ActionEvent event) {
        String nomeCentro = nomeCentroComboBox.getSelectionModel().getSelectedItem().getNome();
        String nomeCittadino = nomeCittadinoTextField.getText();
        String cognomeCittadino = cognomeCittadinoTextField.getText();
        String codiceFiscale = codiceFiscaleTextField.getText();
        Date dataSomministrazione = Date.valueOf(dataSomministrazioneDatePicker.getValue());
        String tipoVaccino = tipoVaccinoComboBox.getSelectionModel().getSelectedItem();
        
        // Validazione campi
        String codiceFiscaleRegex = "^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$";
        if(nomeCentro != null && nomeCittadino != null && cognomeCittadino != null && codiceFiscale != null && dataSomministrazione != null && tipoVaccino != null) {
            if(nomeCentro.trim() != "" && nomeCittadino.trim() != "" && cognomeCittadino.trim() != "" && codiceFiscale.trim() != "" && tipoVaccino.trim() != "") {
                
                // Validazione valori
                if(Regex.check(nomeCittadino, "^[a-zA-Z]*$")) {
                    if(Regex.check(cognomeCittadino, "^[a-zA-Z]*$")) {
                        if(Regex.check(codiceFiscale, codiceFiscaleRegex)) {
                            if(dataSomministrazione.before(new Date(Calendar.getInstance().getTime().getTime()))) {
                                try {
                                    // Task aggiunta vaccinato
                                    Task<String> addVaccinatoTask = new Task<String>(){
                                        protected String call() throws Exception {
                                            String resl = null;

                                            try {
                                                Client c = new Client();
                                                resl = c.addVaccinato(nomeCittadino, cognomeCittadino, codiceFiscale, dataSomministrazione, TipoVaccino.valueFromString(tipoVaccino.toUpperCase()), nomeCentroComboBox.getSelectionModel().getSelectedItem().getId());
                                            } catch(Exception e) {
                                                e.printStackTrace();
                                            }

                                            return resl;
                                        }
                                    };

                                    addVaccinatoTask.setOnSucceeded(e -> {
                                        registerButton.setText("Registra");
                                        registerButton.setDisable(false);

                                        nomeCentroComboBox.setDisable(false);
                                        nomeCittadinoTextField.setDisable(false);
                                        cognomeCittadinoTextField.setDisable(false);
                                        codiceFiscaleTextField.setDisable(false);
                                        dataSomministrazioneDatePicker.setDisable(false);
                                        tipoVaccinoComboBox.setDisable(false);

                                        errorMessage.setVisible(true);
                                        errorMessage.setText("Registrazione andata a buon fine!");
                                        errorMessage.setTextFill(Color.GREEN);

                                        nomeCentroComboBox.getSelectionModel().select(0);
                                        nomeCittadinoTextField.setText(null);
                                        cognomeCittadinoTextField.setText(null);
                                        codiceFiscaleTextField.setText(null);
                                        tipoVaccinoComboBox.getSelectionModel().select(0);

                                        String res = addVaccinatoTask.getValue();
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Registrazione andata a buon fine!");
                                        alert.setHeaderText(res);
                                        alert.setAlertType(AlertType.INFORMATION);
                                        alert.setContentText("Fornisci questo codice al cittadino vaccinato per permettergli di registrarsi a sistema.");
                                        alert.showAndWait();
                                    });

                                    addVaccinatoTask.setOnFailed(e -> {
                                        registerButton.setText("Registra");
                                        registerButton.setDisable(false);

                                        nomeCentroComboBox.setDisable(false);
                                        nomeCittadinoTextField.setDisable(false);
                                        cognomeCittadinoTextField.setDisable(false);
                                        codiceFiscaleTextField.setDisable(false);
                                        dataSomministrazioneDatePicker.setDisable(false);
                                        tipoVaccinoComboBox.setDisable(false);

                                        nomeCentroComboBox.getSelectionModel().select(0);
                                        nomeCittadinoTextField.setText(null);
                                        cognomeCittadinoTextField.setText(null);
                                        codiceFiscaleTextField.setText(null);
                                        tipoVaccinoComboBox.getSelectionModel().select(0);

                                        errorMessage.setVisible(true);
                                        errorMessage.setText("Errore nella registrazione.");
                                        errorMessage.setTextFill(Color.RED);
                                    });

                                    new Thread(addVaccinatoTask).start();
                                    registerButton.setText("Caricamento...");
                                    registerButton.setDisable(true);

                                    nomeCentroComboBox.setDisable(true);
                                    nomeCittadinoTextField.setDisable(true);
                                    cognomeCittadinoTextField.setDisable(true);
                                    codiceFiscaleTextField.setDisable(true);
                                    dataSomministrazioneDatePicker.setDisable(true);
                                    tipoVaccinoComboBox.setDisable(true);

                                } catch (Exception e) {
                                    errorMessage.setVisible(true);
                                    errorMessage.setText("Errore nella comunicazione con il server.");
                                }
                            } else {
                                errorMessage.setVisible(true);
                                errorMessage.setText("La data dev'essere precedente a quella attuale.");
                            }
                        } else {
                            errorMessage.setVisible(true);
                            errorMessage.setText("Codice fiscale non valido.");
                        }
                    } else {
                        errorMessage.setVisible(true);
                        errorMessage.setText("Cognome non valido.");
                    }
                } else {
                    errorMessage.setVisible(true);
                    errorMessage.setText("Nome non valido.");
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipoVaccinoComboBox.getItems().setAll(
            TipoVaccino.PFIZER.getValue(),
            TipoVaccino.ASTRAZENECA.getValue(),
            TipoVaccino.MODERNA.getValue(),
            TipoVaccino.J_AND_J.getValue()
        );

        tipoVaccinoComboBox.getSelectionModel().select(0);

        errorMessage.setVisible(false);

        try {
            
            Task<Void> getCentriVaccinaliTask = new Task<Void>() {
               protected Void call() throws Exception {
                   centriVaccinali = new Client().getAllCentriVaccinali();
                   return null;
               }
            };
            getCentriVaccinaliTask.setOnSucceeded(e-> {
                nomeCentroComboBox.getItems().setAll(centriVaccinali);
            });

            getCentriVaccinaliTask.setOnFailed(e-> {
                errorMessage.setVisible(true);
                errorMessage.setText("Connessione al server non riuscita.");
            });

            new Thread(getCentriVaccinaliTask).start();
        } catch (Exception e) {
            errorMessage.setVisible(true);
            errorMessage.setText("Connesione al server non riuscita.");;
        }
    }
}
