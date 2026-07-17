package org.unisiga.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.unisiga.model.*;
import org.unisiga.view.FrmMatricula;

public class MatriculaController {
    private FrmMatricula vista;
    private Database db;
    private List<Asignatura> asignaturasActuales;
    private List<Grupo> gruposActuales;

    public MatriculaController(FrmMatricula vista, Database db) {
        this.vista = vista;
        this.db = db;
        this.asignaturasActuales = db.getAsignaturas();
        this.gruposActuales = new ArrayList<>();
        inicializarVista();
    }
    
    private void inicializarVista() {
        String[] asigItems = new String[asignaturasActuales.size()];
        for (int i = 0; i < asignaturasActuales.size(); i++) {
            asigItems[i] = asignaturasActuales.get(i).getCodigo() + " - " + asignaturasActuales.get(i).getNombre();
        }
        vista.setAsignaturasItems(asigItems);
        actualizarHistorial();
    }
    
    public void onAsignaturaSeleccionada() {
        int asigIdx = vista.getAsignaturaSeleccionadaIndex();
        if (asigIdx == -1) return;
        
        Asignatura a = asignaturasActuales.get(asigIdx);
        gruposActuales = new ArrayList<>();
        for (Grupo g : db.getGrupos()) {
            if (g.getAsignatura().getCodigo().equals(a.getCodigo())) {
                gruposActuales.add(g);
            }
        }
        
        String[] grupoItems = new String[gruposActuales.size()];
        for (int i = 0; i < gruposActuales.size(); i++) {
            Grupo g = gruposActuales.get(i);
            grupoItems[i] = "Grupo " + g.getIdGrupo() + " | Cupos: " + (g.getCupoMaximo() - g.getMatriculas().size());
        }
        vista.setGruposItems(grupoItems);
    }
    
    private void actualizarHistorial() {
        if (!(db.getUsuarioLogueado() instanceof Estudiante)) return;
        Estudiante estudiante = (Estudiante) db.getUsuarioLogueado();
        List<Matricula> historial = estudiante.getHistorialMatriculas();
        Object[][] data = new Object[historial.size()][4];
        
        for (int i = 0; i < historial.size(); i++) {
            Matricula m = historial.get(i);
            data[i][0] = m.getGrupo().getAsignatura().getNombre();
            data[i][1] = m.getGrupo().getIdGrupo();
            data[i][2] = m.getEstadoInscripcion();
            data[i][3] = m.getFechaInscripcion().toString();
        }
        vista.setHistorialTable(data);
    }
    
    public void onInscribirClicked() {
        try {
            int asigIdx = vista.getAsignaturaSeleccionadaIndex();
            int grpIdx = vista.getGrupoSeleccionadoIndex();
            
            if (asigIdx == -1 || grpIdx == -1) {
                throw new NullPointerException("Debe seleccionar una asignatura y un grupo.");
            }
            
            Asignatura a = asignaturasActuales.get(asigIdx);
            Grupo g = gruposActuales.get(grpIdx);
            
            if (!(db.getUsuarioLogueado() instanceof Estudiante)) {
                throw new IllegalStateException("Solo los estudiantes pueden inscribirse.");
            }
            Estudiante estudiante = (Estudiante) db.getUsuarioLogueado();
            
            Matricula m = new Matricula("Inscrito", new java.util.Date(), estudiante, g);
            
            // Reglas de negocio desde el modelo (arrojan excepciones)
            estudiante.agregarMatricula(m);
            g.agregarMatricula(m);
            
            JOptionPane.showMessageDialog(vista, "Inscripción exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            onAsignaturaSeleccionada(); // Actualizar cupos visuales
            actualizarHistorial();
            
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error de Selección", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Regla de Negocio", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
