package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Estudiante> estudiantes;
    private List<Academico> academicos;
    private List<Asignatura> asignaturas;
    private List<Grupo> grupos;
    
    private MiembroUniversitario usuarioLogueado;

    public Database() {
        this.estudiantes = new ArrayList<>();
        this.academicos = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        this.grupos = new ArrayList<>();
    }

    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Academico> getAcademicos() { return academicos; }
    public List<Asignatura> getAsignaturas() { return asignaturas; }
    public List<Grupo> getGrupos() { return grupos; }

    public MiembroUniversitario getUsuarioLogueado() { return usuarioLogueado; }
    public void setUsuarioLogueado(MiembroUniversitario u) { this.usuarioLogueado = u; }
}
