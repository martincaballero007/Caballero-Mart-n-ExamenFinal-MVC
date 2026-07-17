package org.unisiga.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Matricula {
    private String estadoInscripcion;
    private Date fechaInscripcion;
    
    private Estudiante estudiante;
    private Grupo grupo;
    private List<Calificacion> calificaciones;

    public Matricula(String estadoInscripcion, Date fechaInscripcion, 
                     Estudiante estudiante, Grupo grupo) {
        this.estadoInscripcion = estadoInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.estudiante = estudiante;
        this.grupo = grupo;
        this.calificaciones = new ArrayList<>();
    }

    public void actualizarEstado() {
        if (calificaciones == null || calificaciones.isEmpty()) {
            return;
        }
        float suma = 0;
        float ponderacionTotal = 0;
        for (Calificacion c : calificaciones) {
            suma += (c.getNota() * (c.getEvaluacion().getPonderacion() / 100f));
            ponderacionTotal += c.getEvaluacion().getPonderacion();
        }
        
        if (ponderacionTotal >= 100) {
            if (suma >= 4.0) { // Asumiendo escala donde 4.0 aprueba
                this.estadoInscripcion = "Aprobado";
            } else {
                this.estadoInscripcion = "Reprobado";
            }
        } else {
            this.estadoInscripcion = "Cursando";
        }
    }

    public String getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(String estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
}