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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Ankel
 */
public class VistaLoginController implements Initializable {

    @FXML
    private StackPane rootPanel;
    @FXML
    private TabPane tabPanelLogin;
    @FXML
    private Tab tabAdmin;
    @FXML
    private Tab tabCoord;
    @FXML
    private Label lblCrearCuenta;
    @FXML
    private Pane slidingPane;
    @FXML
    private Label lblAdmin;
    @FXML
    private Label lblCoord;
    @FXML
    private Label lblStatus;
    @FXML
    private AnchorPane subPanel;
    @FXML
    private TextField txtUserAdmin;
    @FXML
    private PasswordField txtPasswordAdmin;
    @FXML
    private Button btnIniciarSesionAdmin;
    @FXML
    private TextField txtUserCoord;
    @FXML
    private PasswordField txtPasswordCoord;
    @FXML
    private Button btnIniciarSesionCoord;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnIniciarSesionAdmin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                DBUtils.logInUser(event, txtUserAdmin.getText(), txtPasswordAdmin.getText());
            }
            
        }
        );
        
        btnIniciarSesionCoord.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                DBUtils.logInUserCoord(event, txtUserCoord.getText(), txtPasswordCoord.getText());
            }
            
        }
        );
    }    

    @FXML
    private void openAdminTab(MouseEvent event) {
        TranslateTransition transitionIzquierda = new TranslateTransition(new Duration(500), lblStatus);
        transitionIzquierda.setToX(slidingPane.getTranslateX());
        transitionIzquierda.play();
        transitionIzquierda.setOnFinished((ActionEvent event2) -> {
            lblStatus.setText("ADMINISTRADOR");
        }
        );
        //seleccionar la tabla de tipo de usuario
         tabPanelLogin.getSelectionModel().select(tabAdmin);
    }

    @FXML
    private void openCoordTab(MouseEvent event) {
        TranslateTransition transitionDerecha = new TranslateTransition(new Duration(500), lblStatus);
        
        transitionDerecha.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
        transitionDerecha.play();
        transitionDerecha.setOnFinished((ActionEvent event1) -> {
            lblStatus.setText("COORDINACIÓN");
        }
        );
        //seleccionar la tabla de tipo de usuario
         tabPanelLogin.getSelectionModel().select(tabCoord);
        
    } 

    private void openCreateAccountScene(MouseEvent event) throws IOException {
        
        
        
    }

    @FXML
    private void openCreateAccountScene2(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateUsuario.fxml"));
        Scene loginScene = lblAdmin.getScene();
        root.translateYProperty().set(loginScene.getHeight());
        rootPanel.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        
        timeline.setOnFinished((ActionEvent event2) -> {
            rootPanel.getChildren().remove(subPanel);
        }
        );
    }

    
}
