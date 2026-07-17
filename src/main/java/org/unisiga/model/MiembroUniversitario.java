package org.unisiga.model;

public abstract class MiembroUniversitario {
    protected String rut;
    protected String nombre;
    protected String correoInstitucional;

    public MiembroUniversitario(String rut, String nombre, String correoInstitucional) {
        this.rut = rut;
        this.nombre = nombre;
        this.correoInstitucional = correoInstitucional;
    }

    public Boolean login() {
        // Lógica de autenticación
        return true; 
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }
}