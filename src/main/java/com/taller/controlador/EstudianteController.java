package com.taller.controlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class EstudianteController {


    @FXML
    private TextField txtEdad;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TextField txtNombre;

    @FXML
    void actualizarEstudiante(ActionEvent event) {

    }

    @FXML
    void eliminarEstudiante(ActionEvent event) {

    }

    @FXML
    void insertarEstudiante(ActionEvent event) {
        String nombre = txtNombre.getText();
        String edad = txtEdad.getText();
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);

    }

    @FXML
    void ListarEstudiante(ActionEvent event) {

    }
}
