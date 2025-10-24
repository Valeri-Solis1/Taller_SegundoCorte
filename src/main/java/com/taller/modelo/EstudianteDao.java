package com.taller.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EstudianteDao {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    // === INSERTAR USANDO PROCEDIMIENTO ===

    public boolean insertarEstudiante(Estudiante estudiante) {
        boolean exito = false;
        String sql = "{CALL insertarEstudiante(?, ?)}";
        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setInt(2, estudiante.getEdad());
            preparedStatement.execute();
            exito = true;
        } catch (Exception e) {
            System.out.println("Error al insertar estudiante: " + e.getMessage());
        }
        return exito;
    }

    // === MOSTRAR TODOS LOS ESTUDIANTES ===
    public ArrayList<Estudiante> obtenerTodosLosEstudiantes(String filter, ArrayList<String> data) {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            String sql = "{CALL mostrarEstudiantes()}";
            preparedStatement = conn.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante(0, sql, 0);
                estudiante.setId(resultSet.getInt("id_estudiante"));
                estudiante.setNombre(resultSet.getString("nombre"));
                estudiante.setEdad(resultSet.getInt("edad"));
                estudiantes.add(estudiante);
            }

        } catch (Exception e) {
            System.out.println("Error al obtener estudiantes: " + e.getMessage());
        } finally {
            try {
                ConexionDatabase.getInstance().close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return estudiantes;
    }

    // === ACTUALIZAR USANDO PROCEDIMIENTO ===
    public boolean actualizarEstudiante(Estudiante estudiante) {
        boolean state = false;
        String sql = "{CALL actualizarEstudiante(?, ?, ?)}";

        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, estudiante.getId());
            preparedStatement.setString(2, estudiante.getNombre());
            preparedStatement.setInt(3, estudiante.getEdad());

            int res = preparedStatement.executeUpdate();
            state = res > 0;

        } catch (Exception e) {
            System.out.println("Error al actualizar estudiante: " + e.getMessage());
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
    public boolean eliminarEstudiante(int id_estudiante) {
        boolean state = false;
        String sql = "{CALL eliminarEstudiante(?)}";

        try {
            Connection conn = ConexionDatabase.getInstance().getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id_estudiante);
            int res = preparedStatement.executeUpdate();
            state = res > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar estudiante: " + e.getMessage());
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
