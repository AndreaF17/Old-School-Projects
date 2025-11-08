/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import unidevteam.util.*;
import unidevteam.classes.*;
import unidevteam.communication.*;

/**
 * Classe per gestire elementi grafici
 * Permette:
 * <ul>
 * <li>Registrazione del cittadino</li>
 * <li>Muoversi alla schermata precedente</li>
 * </ul>
 */
public class FXMLRegistrazioneController2 implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView goBackButton;

    @FXML
    private Button registraCittadinoButton;

    @FXML
    private Label errorMessage1;

    @FXML
    private TextField idVaccinazioneTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confermaPasswordTextField;

    @FXML
    private Label errorMessage11;

    /**
     * Permette di tornare alla pagina precedente
     * @param event
     */
    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    /**
     * Permette di registrare il cittadino ottenendo i dati dalla GUI
     * @param event
     */
    @FXML
    void onClickRegistraCittadino(ActionEvent event) {
        String idVaccinazione = idVaccinazioneTextField.getText();
        String password1 = passwordTextField.getText();
        String password2 = confermaPasswordTextField.getText();

        if(idVaccinazione != null && password1 != null && password2 != null) {
            if(idVaccinazione.trim() != "" && password1.trim() != "" && password2.trim() != "") {

                // Controlli formato regex
                if(Regex.check(idVaccinazione, "^[a-zA-Z0-9]{16}$")) {
                    if(Regex.checkPsw(password1)) {
                        if(Regex.checkPsw(password2)) {

                            if(password1.equals(password2)) {
                                
                                RegistrationHandler.setData2(idVaccinazione, password1);
                                Cittadino c = RegistrationHandler.getCittadino();

                                Task<Void> registraCittadinoTask = new Task<Void>() {
                                    @Override
                                    protected Void call() throws Exception {
                                        Client client = new Client();
                                        client.registraCittadino(c);
                                        return null;
                                    }
                                };

                                registraCittadinoTask.setOnSucceeded(e -> {
                                    registraCittadinoButton.setText("Registra");
                                    registraCittadinoButton.setDisable(false);
    
                                    idVaccinazioneTextField.setDisable(false);
                                    passwordTextField.setDisable(false);
                                    confermaPasswordTextField.setDisable(false);

                                    errorMessage1.setVisible(true);
                                    errorMessage1.setTextFill(Color.GREEN);
                                    errorMessage1.setText("Registrazione andata a buon fine, ora puoi accedere.");
                                });

                                registraCittadinoTask.setOnFailed(e -> {
                                    registraCittadinoButton.setText("Registra");
                                    registraCittadinoButton.setDisable(false);
    
                                    idVaccinazioneTextField.setDisable(false);
                                    passwordTextField.setDisable(false);
                                    confermaPasswordTextField.setDisable(false);

                                    errorMessage1.setVisible(true);
                                    errorMessage1.setTextFill(Color.RED);
                                    errorMessage1.setText("Errore nella registrazione, riprova.");
                                });

                                new Thread(registraCittadinoTask).start();

                                registraCittadinoButton.setText("Caricamento...");
                                registraCittadinoButton.setDisable(true);

                                idVaccinazioneTextField.setDisable(true);
                                passwordTextField.setDisable(true);
                                confermaPasswordTextField.setDisable(true);

                            } else {
                                errorMessage1.setText("Le due password non combaciano.");
                                errorMessage1.setVisible(true);
                            }

                        } else {
                            errorMessage1.setText("Formato password non valido: da 8 a 40 caratteri, almeno una lettera maiuscola, almeno una lettera minuscola, almeno una cifra e almeno un carattere speciale: !%*?&$@.");
                            errorMessage1.setVisible(true);
                        }
                    } else {
                        errorMessage1.setText("Formato password non valido: da 8 a 40 caratteri, almeno una lettera maiuscola, almeno una lettera minuscola, almeno una cifra e almeno un carattere speciale: !%*?&$@.");
                        errorMessage1.setVisible(true);
                    }
                } else {
                    errorMessage1.setText("ID Vaccinazione non valido.");
                    errorMessage1.setVisible(true);
                }

            } else {
                errorMessage1.setText("Compila tutti i campi.");
                errorMessage1.setVisible(true);
            }
        } else {
            errorMessage1.setText("Compila tutti i campi.");
            errorMessage1.setVisible(true);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}