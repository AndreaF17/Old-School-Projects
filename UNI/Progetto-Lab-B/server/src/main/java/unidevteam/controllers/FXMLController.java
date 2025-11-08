/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package unidevteam.controllers;

import unidevteam.util.DBManager;
import unidevteam.util.SceneManager;

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
import javafx.scene.paint.Color;

/**
 * Classe per gestire elementi grafici
 * Permette di:
 * <ul>
 * <li>Accedere</li>
 * </ul>
 */
public class FXMLController implements Initializable {

    DBManager dbManager;

    @FXML
    private TextField hostNameTextField;

    @FXML
    private TextField dbNameTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessageLabel;

    /**
     * Permette di autenticarsi a sistema
     * @param event
     */
    @FXML
    void onClickLoginButton(ActionEvent event) {
        String hostName = hostNameTextField.getText();
        String dbName = dbNameTextField.getText();
        String userName = userNameTextField.getText();
        String password = passwordTextField.getText();

        if(hostName != null && dbName != null && userName != null && password != null) {
            if(hostName.trim() != "" && dbName.trim() != "" && userName.trim() != "" && password.trim() != "") {

                Task<Void> dbConnectTask = new Task<Void>() {
                    protected Void call() throws Exception {
                        dbManager = DBManager.getInstance(hostName, dbName, userName, password);
                        return null;
                    }
                };

                dbConnectTask.setOnSucceeded(e -> { 
                    loginButton.setText("Accedi");
                    errorMessageLabel.setText("Connessione stabilita.");
                    errorMessageLabel.setTextFill(Color.GREEN);
                    errorMessageLabel.setVisible(true);
                    new SceneManager().switchToNewScene(event, "metrics");
                });
                dbConnectTask.setOnFailed(e -> {
                    loginButton.setText("Accedi");
                    System.out.println("Connection not estabilished!");
                    errorMessageLabel.setText("Connessione non stabilita, ritenta.");
                    errorMessageLabel.setVisible(true);
                    loginButton.setDisable(false);

                    hostNameTextField.setDisable(false);
                    dbNameTextField.setDisable(false);
                    userNameTextField.setDisable(false);
                    passwordTextField.setDisable(false);
                });

                Thread localThread = new Thread(dbConnectTask);
                localThread.start();
                loginButton.setText("Caricamento...");
                loginButton.setDisable(true);

                hostNameTextField.setDisable(true);
                dbNameTextField.setDisable(true);
                userNameTextField.setDisable(true);
                passwordTextField.setDisable(true);

                return;
            }
        }
        
        errorMessageLabel.setText("Compila tutti i campi.");
        errorMessageLabel.setVisible(true);
        System.out.println("Compila tutti i campi.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hostNameTextField.setText("tai.db.elephantsql.com");
        dbNameTextField.setText("igabhvra");
        userNameTextField.setText("igabhvra");
        passwordTextField.setText("pM8kEKh0soYm_ZRR_DcpIlelPAFb2UFv");
    }
}