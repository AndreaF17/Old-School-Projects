/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */
package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import unidevteam.util.Regex;
import unidevteam.util.RegistrationHandler;
import unidevteam.util.SceneManager;

/**
 * Classe per gestire elementi grafici
 * Permette di:
 * <ul>
 * <li>Passare alla seconda parte della registrazione</li>
 * <li>Muoversi alla schermata precedente</li>
 * </ul>
 */
public class FXMLRegistrazioneController1 implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView goBackButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button avantiButton;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField codiceFiscaleTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cognomeTextField;

    /**
     * Permette di andare alla seconda parte della registrazione
     * @param event
     */
    @FXML
    void onClickAvanti(ActionEvent event) {
        String nome = nomeTextField.getText();
        String cognome = cognomeTextField.getText();
        String codiceFiscale = codiceFiscaleTextField.getText();
        String email = emailTextField.getText();

        String codiceFiscaleRegex = "^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$";

        if(nome != null && cognome != null && codiceFiscale != null && email != null) {
            if(nome.trim() != "" && cognome.trim() != "" && codiceFiscale.trim() != "" && email.trim() != "") {

                // Controlli formato regex
                if(Regex.check(nome, "^[a-zA-Z]*$")) {
                    if(Regex.check(cognome, "^[a-zA-Z]*$")) {
                        if(Regex.check(codiceFiscale, codiceFiscaleRegex)) {

                            if(Regex.check(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                                // Setta oggetto registrazione e spostati alla pagina nuova
                                RegistrationHandler.setData1(nome, cognome, codiceFiscale, email);
                                new SceneManager().switchToNewScene(event, "registrazione2");
                            } else {
                                errorMessage.setText("Email non valida.");
                                errorMessage.setVisible(true);    
                            }

                        } else {
                            errorMessage.setText("Codice fiscale non valido.");
                            errorMessage.setVisible(true);
                        }
                    } else {
                        errorMessage.setText("Cognome non valido.");
                        errorMessage.setVisible(true);
                    }
                } else {
                    errorMessage.setText("Nome non valido.");
                    errorMessage.setVisible(true);
                }

            } else {
                errorMessage.setText("Compila tutti i campi.");
                errorMessage.setVisible(true);
            }
        } else {
            errorMessage.setText("Compila tutti i campi.");
            errorMessage.setVisible(true);
        }
    }

    /**
     * Permette di tornare alla pagina precedente
     * @param event
     */
    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}