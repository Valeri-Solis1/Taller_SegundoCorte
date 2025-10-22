package com.taller.controlador;

import java.util.ArrayList;

import com.taller.modelo.Estudiante;
import com.taller.modelo.EstudianteDao;

public class ControladorEstudiante {
    
    private EstudianteDao estudianteDao;

    public ControladorEstudiante() {
        estudianteDao = new EstudianteDao();
    }

    public boolean agregarEstudiante(String nombre, int edad) {
        Estudiante estudiante = new Estudiante(0, nombre, edad);
        return estudianteDao.insertarEstudiante(estudiante);
    }

    public ArrayList<Estudiante> listarEstudiantes() {
        return estudianteDao.obtenerTodosLosEstudiantes("", new ArrayList<>());
    }

    public boolean actualizarEstudiante(int id, String nombre, int edad) {
        Estudiante estudiante = new Estudiante(id, nombre, edad);
        return estudianteDao.actualizarEstudiante(estudiante);
    }

    public boolean eliminarEstudiante(int id) {
        return estudianteDao.eliminarEstudiante(id);
    }
}
