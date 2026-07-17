package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String codigoDepto;
    private String nombre;
    private List<Academico> staffAcademicos;

    public Departamento(String codigoDepto, String nombre) {
        this.codigoDepto = codigoDepto;
        this.nombre = nombre;
        this.staffAcademicos = new ArrayList<>();
    }

    public String getCodigoDepto() {
        return codigoDepto;
    }

    public void setCodigoDepto(String codigoDepto) {
        this.codigoDepto = codigoDepto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Academico> getStaffAcademicos() {
        return staffAcademicos;
    }

    public void setStaffAcademicos(List<Academico> staffAcademicos) {
        this.staffAcademicos = staffAcademicos;
    }
}