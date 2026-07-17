package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends MiembroUniversitario {
    private String matricula;
    private Integer anioIngreso;
    private Float promedio;
    private List<Matricula> historialMatriculas;

    public Estudiante(String rut, String nombre, String correoInstitucional, 
                      String matricula, Integer anioIngreso, Float promedio) {
        super(rut, nombre, correoInstitucional);
        this.matricula = matricula;
        this.anioIngreso = anioIngreso;
        this.promedio = promedio;
        this.historialMatriculas = new ArrayList<>();
    }

    public void inscribirSeccion() {
        // Lógica para solicitar inscripción a un grupo
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(Integer anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public Float getPromedio() {
        return promedio;
    }

    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }

    public List<Matricula> getHistorialMatriculas() {
        return historialMatriculas;
    }

    public void setHistorialMatriculas(List<Matricula> historialMatriculas) {
        this.historialMatriculas = historialMatriculas;
    }
}