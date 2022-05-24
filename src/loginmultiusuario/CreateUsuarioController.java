/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmultiusuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ankel
 */
public class CreateUsuarioController implements Initializable {

    @FXML
    private Label lblLogin;
    @FXML
    private AnchorPane rootPanelCreate;
    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnRegistrar;
    @FXML
    private RadioButton rbAdmin;
    @FXML
    private RadioButton rbCoord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup toggleGroup = new ToggleGroup();
        rbAdmin.setToggleGroup(toggleGroup);
        rbCoord.setToggleGroup(toggleGroup);
        
        rbAdmin.setSelected(true);
        
        btnRegistrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
                
                
                
                if (!txtNombreUsuario.getText().trim().isEmpty() && !txtPassword.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, txtNombreUsuario.getText(), txtPassword.getText(), toggleName);
                    
                } else {
                    System.out.println("Ingrese la información Completa, Porfavor!!!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Ingrese la Información Completa para registrarse.");
                }
            }
        });
        
        
    }    

    @FXML
    private void openLognScene(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VistaLogin.fxml"));
        Scene acountScene = lblLogin.getScene();
        root.translateYProperty().set(acountScene.getHeight());
        
        StackPane rootPanel = (StackPane) acountScene.getRoot();
        
        rootPanel.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        
        timeline.setOnFinished((ActionEvent event2) -> {
            rootPanel.getChildren().remove(rootPanelCreate);
        }
        );
    }
    
}
