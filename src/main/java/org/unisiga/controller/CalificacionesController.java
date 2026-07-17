package org.unisiga.controller;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.unisiga.model.Academico;
import org.unisiga.model.Calificacion;
import org.unisiga.model.Database;
import org.unisiga.model.Evaluacion;
import org.unisiga.model.Grupo;
import org.unisiga.model.Matricula;
import org.unisiga.view.FrmCalificaciones;

public class CalificacionesController {
    private FrmCalificaciones vista;
    private Database db;

    public CalificacionesController(FrmCalificaciones vista, Database db) {
        this.vista = vista;
        this.db = db;
    }

    public List<Grupo> getGruposDictados() {
        if (db.getUsuarioLogueado() instanceof Academico) {
            return ((Academico) db.getUsuarioLogueado()).getGruposDictados();
        }
        return new ArrayList<>();
    }

    public void guardarNotas(Grupo g, Evaluacion ev, List<String> notasStr) {
        if (g == null || ev == null) return;
        
        int guardadas = 0;
        for (int i = 0; i < notasStr.size() && i < g.getMatriculas().size(); i++) {
            String notaStr = notasStr.get(i);
            if (notaStr != null && !notaStr.trim().isEmpty()) {
                try {
                    float nota = Float.parseFloat(notaStr);
                    Matricula m = g.getMatriculas().get(i);
                    Calificacion c = new Calificacion(nota, m, ev);
                    m.getCalificaciones().add(c);
                    m.actualizarEstado();
                    guardadas++;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(vista, "Nota inválida en la fila " + (i + 1));
                }
            }
        }
        
        JOptionPane.showMessageDialog(vista, "Se guardaron " + guardadas + " calificaciones exitosamente.");
    }
}
