/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmultiusuario;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ankel
 */
public class MainAdminVistaController implements Initializable {

    @FXML
    private AnchorPane rootPanelCreate;
    @FXML
    private Button btnSalir;
    @FXML
    private Label lblBienvenido;
    @FXML
    private Label lblMensaje;
    @FXML
    private Button btnAgregar;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtEdad;
    @FXML
    private TableView<Persona> tblPersonas;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colEdad;
    
    private ObservableList<Persona> personas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // Creo el observablelist
        personas = FXCollections.observableArrayList();

        // Asigno las columnas con los atributos del modelo
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));
        
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

    @FXML
    private void agregarPersona(ActionEvent event) {
         try {

            // Obtengo los datos del formulario
            String nombre = this.txtNombre.getText();
            String apellidos = this.txtApellidos.getText();
            int edad = Integer.parseInt(this.txtEdad.getText());

            // Creo una persona
            Persona p = new Persona(nombre, apellidos, edad);

            // Compruebo si la persona esta en el lista
            if (!this.personas.contains(p)) {
                // Lo añado a la lista
                this.personas.add(p);
                // Seteo los items
                this.tblPersonas.setItems(personas);
            } else {
               
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona existe");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
        
    }
}
