package org.unisiga.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.unisiga.model.Asignatura;
import org.unisiga.model.Database;
import org.unisiga.model.Estudiante;
import org.unisiga.model.Grupo;
import org.unisiga.model.Matricula;
import org.unisiga.view.FrmMatricula;

public class MatriculaController {
    private FrmMatricula vista;
    private Database db;

    public MatriculaController(FrmMatricula vista, Database db) {
        this.vista = vista;
        this.db = db;
    }

    public List<Asignatura> getAsignaturas() {
        return db.getAsignaturas();
    }

    public List<Grupo> getGruposPorAsignatura(Asignatura a) {
        List<Grupo> resultado = new ArrayList<>();
        for (Grupo g : db.getGrupos()) {
            if (g.getAsignatura().getCodigo().equals(a.getCodigo())) {
                resultado.add(g);
            }
        }
        return resultado;
    }

    public List<Matricula> getHistorialUsuario() {
        if (db.getUsuarioLogueado() instanceof Estudiante) {
            return ((Estudiante) db.getUsuarioLogueado()).getHistorialMatriculas();
        }
        return new ArrayList<>();
    }

    public void inscribirDesdeLista(Asignatura a, Grupo g) {
        if (!(db.getUsuarioLogueado() instanceof Estudiante)) {
            JOptionPane.showMessageDialog(vista, "Solo los estudiantes pueden inscribirse.");
            return;
        }
        Estudiante est = (Estudiante) db.getUsuarioLogueado();
        String res = procesarInscripcion(est, a, g);
        JOptionPane.showMessageDialog(vista, res);
    }

    // --- OPCIÓN DEL POPUP MOSTRADO EN LA IMAGEN ---
    public void mostrarFormularioInscripcionManual() {
        JTextField matField = new JTextField();
        if (db.getUsuarioLogueado() instanceof Estudiante) {
            matField.setText(((Estudiante) db.getUsuarioLogueado()).getMatricula());
            matField.setEditable(false);
        }
        JTextField asigField = new JTextField();
        JTextField grupoField = new JTextField();

        Object[] message = {
            "Matrícula del Estudiante:", matField,
            "Código Asignatura:", asigField,
            "Grupo/Sección (Ej. A):", grupoField
        };

        int option = JOptionPane.showConfirmDialog(vista, message, "Formulario de Inscripción", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String matriculaStr = matField.getText();
            String codAsig = asigField.getText();
            String idGrupoStr = grupoField.getText();

            if (matriculaStr.isEmpty() || codAsig.isEmpty() || idGrupoStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Llene todos los campos.");
                return;
            }

            Estudiante est = buscarEstudiante(matriculaStr);
            Asignatura asig = buscarAsignatura(codAsig);
            if (est == null || asig == null) {
                JOptionPane.showMessageDialog(vista, "Estudiante o Asignatura no encontrada.");
                return;
            }

            Grupo grupo = buscarGrupo(asig, idGrupoStr.charAt(0));
            if (grupo == null) {
                JOptionPane.showMessageDialog(vista, "Grupo no encontrado para esa asignatura.");
                return;
            }

            String resultado = procesarInscripcion(est, asig, grupo);
            JOptionPane.showMessageDialog(vista, resultado);
            
            // Actualizar vista pasiva si está ligada
            vista.refrescarDatos();
        }
    }

    private String procesarInscripcion(Estudiante estudiante, Asignatura asignatura, Grupo grupo) {
        if (!estudiante.tienePrerrequisitosAprobados(asignatura)) {
            return "El estudiante no cumple con los prerrequisitos para: " + asignatura.getNombre();
        }
        if (!grupo.hayCupo()) {
            return "El grupo " + grupo.getIdGrupo() + " no tiene cupos disponibles.";
        }
        for (Matricula m : estudiante.getHistorialMatriculas()) {
            if (m.getGrupo().getAsignatura().getCodigo().equals(asignatura.getCodigo()) && 
                !m.getEstadoInscripcion().equalsIgnoreCase("Reprobado")) {
                return "El estudiante ya cursó o está cursando esta asignatura.";
            }
        }

        Matricula nuevaMatricula = new Matricula("Cursando", new Date(), estudiante, grupo);
        estudiante.getHistorialMatriculas().add(nuevaMatricula);
        grupo.getMatriculas().add(nuevaMatricula);
        return "Inscripción exitosa en " + asignatura.getNombre() + " - Grupo " + grupo.getIdGrupo();
    }

    private Estudiante buscarEstudiante(String matricula) {
        for (Estudiante e : db.getEstudiantes()) {
            if (e.getMatricula().equalsIgnoreCase(matricula)) return e;
        }
        return null;
    }

    private Asignatura buscarAsignatura(String codigo) {
        for (Asignatura a : db.getAsignaturas()) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) return a;
        }
        return null;
    }

    private Grupo buscarGrupo(Asignatura a, char id) {
        for (Grupo g : db.getGrupos()) {
            if (g.getAsignatura().getCodigo().equals(a.getCodigo()) && g.getIdGrupo() == id) {
                return g;
            }
        }
        return null;
    }
}
