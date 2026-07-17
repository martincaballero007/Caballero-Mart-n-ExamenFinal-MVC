package org.unisiga.model;

public class Evaluacion {
    private String titulo;
    private Float ponderacion;
    private Asignatura asignatura; 

    public Evaluacion(String titulo, Float ponderacion, Asignatura asignatura) {
        this.titulo = titulo;
        this.ponderacion = ponderacion;
        this.asignatura = asignatura;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Float getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(Float ponderacion) {
        this.ponderacion = ponderacion;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}