package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private Character idGrupo;
    private Integer cupoMaximo;
    private String horario;
    
    private Asignatura asignatura;
    private Academico profesor; 
    private List<Matricula> matriculas; 

    public Grupo(Character idGrupo, Integer cupoMaximo, String horario, 
                 Asignatura asignatura, Academico profesor) {
        this.idGrupo = idGrupo;
        this.cupoMaximo = cupoMaximo;
        this.horario = horario;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.matriculas = new ArrayList<>();
    }

    public boolean hayCupo() {
        return matriculas.size() < cupoMaximo;
    }

    public Character getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Character idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Academico getProfesor() {
        return profesor;
    }

    public void setProfesor(Academico profesor) {
        this.profesor = profesor;
    }

    public void agregarMatricula(Matricula m) {
        if (!hayCupo()) {
            throw new IllegalStateException("El grupo " + idGrupo + " ya alcanzó su cupo máximo de " + cupoMaximo + " estudiantes.");
        }
        this.matriculas.add(m);
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}