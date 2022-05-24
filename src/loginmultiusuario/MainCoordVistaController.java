/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmultiusuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ankel
 */
public class MainCoordVistaController implements Initializable {

    @FXML
    private AnchorPane rootPanelCreate;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblBienvenido;
    @FXML
    private Label lblMensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                DBUtils.changeScene(event, "VistaLogin.fxml", "Login", null, null);
            }
        }
                
        );
    } 
    
    public void setUserInformation(String username, String rol){
        lblBienvenido.setText("Bienvenido " + username + "!");
        lblMensaje.setText("Su Rol es: " + rol);
        
    }
    
}
