/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmultiusuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Ankel
 */
public class DBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String userName, String rol) {
        Parent root = null;

        if (userName != null && rol != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                MainAdminVistaController mainAdminVistaController = loader.getController();
                mainAdminVistaController.setUserInformation(userName, rol);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 940, 644));
        stage.show();
    }
    
    public static void changeSceneCoord(ActionEvent event, String fxmlFile, String title, String userName, String rol) {
        Parent root = null;

        if (userName != null && rol != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                MainCoordVistaController mainAdminVistaController = loader.getController();
                mainAdminVistaController.setUserInformation(userName, rol);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 940, 644));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String userName, String password, String rol) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bdpruebaces", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE user_name= ?");
            psCheckUserExists.setString(1, userName);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("El usuario ya existe");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("no puedes usar este nombre de usuario");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (user_name, password, rol) VALUES(?,?,?)");
                psInsert.setString(1, userName);
                psInsert.setString(2, password);
                psInsert.setString(3, rol);
                psInsert.executeUpdate();

                changeScene(event, "MainAdminVista.fxml", "Bienvenido!!", userName, rol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    
    public static void logInUser(ActionEvent event, String userName, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;        
        ResultSet resultSet = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bdpruebaces", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password, rol FROM users WHERE user_name= ?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Usuario no encontrado en la BD");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("las credenciales proporcionadas son incorrectas");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedRol = resultSet.getString("rol");
                    
                    if (retrievedPassword.equals(password)) {
                        changeScene(event,  "MainAdminVista.fxml", "Bienvenido", userName, retrievedRol);
                        
                    } else {
                        System.out.println("la contraseña no coincidió");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("las credenciales proporcionadas son incorrectas");
                        alert.show();
                    }
                    
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }           
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            
        }
        
        
    }
    public static void logInUserCoord(ActionEvent event, String userName, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;        
        ResultSet resultSet = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/bdpruebaces", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password, rol FROM users WHERE user_name= ?");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("Usuario no encontrado en la BD");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("las credenciales proporcionadas son incorrectas");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedRol = resultSet.getString("rol");
                    
                    if (retrievedPassword.equals(password)) {
                        changeSceneCoord(event,  "MainCoordVista.fxml", "Bienvenido", userName, retrievedRol);
                        
                    } else {
                        System.out.println("la contraseña no coincidió");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("las credenciales proporcionadas son incorrectas");
                        alert.show();
                    }
                    
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }           
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            
        }
        
        
    }
}
