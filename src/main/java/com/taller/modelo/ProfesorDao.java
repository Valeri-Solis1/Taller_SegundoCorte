package com.taller.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProfesorDao {
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    // === INSERTAR USANDO PROCEDIMIENTO ===
    public boolean insertarProfesor(Profesor profesor) {
        boolean exito = false;
        String sql = "{CALL insertarProfesor(?, ?, ?)}";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, profesor.getNombre());
            preparedStatement.setString(2, profesor.getMateria());
            preparedStatement.setString(3, profesor.getCorreoElectronico());
            preparedStatement.execute();
            exito = true;
        } catch (Exception e) {
            System.out.println("Error al insertar profesor: " + e.getMessage());
        }
        return exito;
    }

    // === MOSTRAR TODOS LOS PROFESORES ===
    public ArrayList<Profesor> obtenerTodosLosProfesores(String filter, ArrayList<String> data) {
        ArrayList<Profesor> profesores = new ArrayList<>();
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            String sql = "{CALL mostrarProfesores()}";
            preparedStatement = conn.prepareStatement(sql);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Profesor profesor = new Profesor(0, "", "", "");
                profesor.setId(resultSet.getInt("id_profesor"));
                profesor.setNombre(resultSet.getString("nombre"));
                profesor.setMateria(resultSet.getString("materia"));
                profesor.setCorreoElectronico(resultSet.getString("correo_electronico"));
                profesores.add(profesor);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener profesores: " + e.getMessage());
        } 
           return profesores;

    }

    // === ACTUALIZAR USANDO PROCEDIMIENTO ===
    public boolean actualizarProfesor(Profesor profesor) {
        boolean state = false;
        String sql = " {CALL actualizarProfesor(?, ?, ?, ?)}";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, profesor.getNombre());
                preparedStatement.setString(2, profesor.getMateria());
                preparedStatement.setString(3, profesor.getCorreoElectronico());
                preparedStatement.setInt(4, profesor.getId());

                int res = preparedStatement.executeUpdate();
                    state = res > 0;
                   
        } catch (Exception e) {
            System.out.println("Error al actualizar profesor: " + e.getMessage());
        } finally {
            try {
                ConexionDatabase.getInstance().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return state;
    }

    // === ELIMINAR USANDO PROCEDIMIENTO ===
    public boolean eliminarProfesor(int id_profesor) {
        boolean state = false;
        String sql = "{CALL eliminarProfesor(?)}";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, id_profesor);
                int res = preparedStatement.executeUpdate();
                state = res > 0;
            
        } catch (Exception e) {
            System.out.println("Error al eliminar profesor: " + e.getMessage());
        } finally {
            try {
                ConexionDatabase.getInstance().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return state;
    }
}
