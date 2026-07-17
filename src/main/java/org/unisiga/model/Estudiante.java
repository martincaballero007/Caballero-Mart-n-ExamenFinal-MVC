package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends MiembroUniversitario {
    private String matricula;
    private Integer anioIngreso;
    private Float promedio;
    private List<Matricula> historialMatriculas;

    public Estudiante(String rut, String nombre, String correoInstitucional, String password,
                      String matricula, Integer anioIngreso, Float promedio) {
        super(rut, nombre, correoInstitucional, password);
        this.matricula = matricula;
        this.anioIngreso = anioIngreso;
        this.promedio = promedio;
        this.historialMatriculas = new ArrayList<>();
    }

    public void inscribirSeccion() {
        // Lógica para solicitar inscripción a un grupo
    }

    public boolean tienePrerrequisitosAprobados(Asignatura asignatura) {
        if (asignatura.getPrerrequisitos() == null || asignatura.getPrerrequisitos().isEmpty()) {
            return true;
        }
        for (Asignatura req : asignatura.getPrerrequisitos()) {
            boolean aprobado = false;
            for (Matricula m : historialMatriculas) {
                if (m.getGrupo().getAsignatura().getCodigo().equals(req.getCodigo()) && 
                    "Aprobado".equalsIgnoreCase(m.getEstadoInscripcion())) {
                    aprobado = true;
                    break;
                }
            }
            if (!aprobado) {
                return false; 
            }
        }
        return true;
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