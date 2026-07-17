package org.unisiga.model;

public class Calificacion {
    private Float nota;
    
    private Matricula matricula; 
    private Evaluacion evaluacion;

    public Calificacion(Float nota, Matricula matricula, Evaluacion evaluacion) {
        this.nota = nota;
        this.matricula = matricula;
        this.evaluacion = evaluacion;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }
}