package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Academico extends MiembroUniversitario {
    private String idEmpleado;
    private String tipoContrato;
    private Departamento departamento; 
    private List<Grupo> gruposDictados; 

    public Academico(String rut, String nombre, String correoInstitucional, 
                     String idEmpleado, String tipoContrato, Departamento departamento) {
        super(rut, nombre, correoInstitucional);
        this.idEmpleado = idEmpleado;
        this.tipoContrato = tipoContrato;
        this.departamento = departamento;
        this.gruposDictados = new ArrayList<>();
    }

    public void registrarNota() {
        // Lógica para registrar calificación
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Grupo> getGruposDictados() {
        return gruposDictados;
    }

    public void setGruposDictados(List<Grupo> gruposDictados) {
        this.gruposDictados = gruposDictados;
    }
}