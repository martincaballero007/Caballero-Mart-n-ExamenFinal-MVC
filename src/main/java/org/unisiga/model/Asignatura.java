package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private String codigo;
    private String nombre;
    private Integer creditosSCT;
    
    private List<Asignatura> prerrequisitos; 
    private List<Grupo> grupos; 
    private List<Evaluacion> evaluaciones; 

    public Asignatura(String codigo, String nombre, Integer creditosSCT) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditosSCT = creditosSCT;
        this.prerrequisitos = new ArrayList<>();
        this.grupos = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditosSCT() {
        return creditosSCT;
    }

    public void setCreditosSCT(Integer creditosSCT) {
        this.creditosSCT = creditosSCT;
    }

    public List<Asignatura> getPrerrequisitos() {
        return prerrequisitos;
    }

    public void setPrerrequisitos(List<Asignatura> prerrequisitos) {
        this.prerrequisitos = prerrequisitos;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}