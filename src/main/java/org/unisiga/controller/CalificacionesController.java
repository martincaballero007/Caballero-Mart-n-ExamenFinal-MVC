package org.unisiga.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.unisiga.model.*;
import org.unisiga.view.FrmCalificaciones;

public class CalificacionesController {
    private FrmCalificaciones vista;
    private Database db;
    private List<Grupo> gruposActuales;
    private List<Evaluacion> evaluacionesActuales;

    public CalificacionesController(FrmCalificaciones vista, Database db) {
        this.vista = vista;
        this.db = db;
        this.gruposActuales = new ArrayList<>();
        this.evaluacionesActuales = new ArrayList<>();
        inicializarVista();
    }
    
    private void inicializarVista() {
        if (!(db.getUsuarioLogueado() instanceof Academico)) return;
        Academico profesor = (Academico) db.getUsuarioLogueado();
        
        for (Grupo g : db.getGrupos()) {
            if (g.getProfesor().getRut().equals(profesor.getRut())) {
                gruposActuales.add(g);
            }
        }
        
        String[] grupoItems = new String[gruposActuales.size()];
        for (int i = 0; i < gruposActuales.size(); i++) {
            Grupo g = gruposActuales.get(i);
            grupoItems[i] = g.getAsignatura().getNombre() + " - Grupo " + g.getIdGrupo();
        }
        vista.setGruposItems(grupoItems);
    }
    
    public void onGrupoSeleccionado() {
        int grpIdx = vista.getGrupoSeleccionadoIndex();
        if (grpIdx == -1) return;
        
        Grupo g = gruposActuales.get(grpIdx);
        evaluacionesActuales = g.getAsignatura().getEvaluaciones();
        
        String[] evItems = new String[evaluacionesActuales.size()];
        for (int i = 0; i < evaluacionesActuales.size(); i++) {
            Evaluacion ev = evaluacionesActuales.get(i);
            evItems[i] = ev.getTitulo() + " (" + ev.getPonderacion() + "%)";
        }
        vista.setEvaluacionesItems(evItems);
        
        // Cargar alumnos al seleccionar el grupo
        cargarAlumnosDeGrupo(g);
    }
    
    public void onEvaluacionSeleccionada() {
        // En este diseño básico, no cambia la lista de alumnos, pero se podría cargar
        // notas previas si ya existen para esta evaluación.
    }
    
    private void cargarAlumnosDeGrupo(Grupo g) {
        List<Matricula> matriculas = g.getMatriculas();
        Object[][] data = new Object[matriculas.size()][3];
        
        for (int i = 0; i < matriculas.size(); i++) {
            Matricula m = matriculas.get(i);
            data[i][0] = m.getEstudiante().getMatricula();
            data[i][1] = m.getEstudiante().getNombre();
            data[i][2] = ""; // Nota inicial vacía
        }
        vista.setAlumnosTable(data);
    }
    
    public void procesarGuardado() {
        try {
            int grpIdx = vista.getGrupoSeleccionadoIndex();
            int evIdx = vista.getEvaluacionSeleccionadaIndex();
            
            if (grpIdx == -1 || evIdx == -1) {
                throw new NullPointerException("Debe seleccionar un grupo y una evaluación.");
            }
            
            Grupo g = gruposActuales.get(grpIdx);
            Evaluacion ev = evaluacionesActuales.get(evIdx);
            List<String> notasIngresadas = vista.getNotasIngresadas();
            List<Matricula> matriculas = g.getMatriculas();
            
            int guardadas = 0;
            for (int i = 0; i < matriculas.size(); i++) {
                String notaStr = notasIngresadas.get(i);
                if (notaStr != null && !notaStr.trim().isEmpty()) {
                    float valorNota = Float.parseFloat(notaStr.trim());
                    // Lanzará NotaInvalidaException si no está entre 0 y 20
                    Calificacion calificacion = new Calificacion(valorNota, matriculas.get(i), ev);
                    matriculas.get(i).getCalificaciones().add(calificacion);
                    guardadas++;
                }
            }
            
            if (guardadas > 0) {
                JOptionPane.showMessageDialog(vista, "Se guardaron " + guardadas + " calificaciones exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "No se ingresó ninguna nota nueva.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error de Selección", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Asegúrese de ingresar solo valores numéricos válidos (ej. 15.5).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Nota Inválida", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error inesperado al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
