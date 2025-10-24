package com.taller;

import java.sql.Connection;
import com.taller.modelo.ConexionDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Cargar la vista FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/MenuVista.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Configurar la ventana
        stage.setTitle("Menú Principal");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Intentando conectar...");
        Connection conexion = ConexionDatabase.getConnection();

        System.out.println("Inicio aplicación JavaFX...");
        launch(args);
    }
}

