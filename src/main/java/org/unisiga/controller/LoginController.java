package org.unisiga.controller;

import javax.swing.JOptionPane;
import org.unisiga.model.Database;
import org.unisiga.model.Estudiante;
import org.unisiga.model.Academico;
import org.unisiga.model.MiembroUniversitario;
import org.unisiga.view.FrmLogin;
import org.unisiga.view.FrmMenuPrincipal;

public class LoginController {
    private FrmLogin vista;
    private Database db;

    public LoginController(FrmLogin vista, Database db) {
        this.vista = vista;
        this.db = db;
    }

    public void iniciarSesion(String correo, String password) {
        MiembroUniversitario usuarioLogueado = null;
        for (Estudiante e : db.getEstudiantes()) {
            if (e.getCorreoInstitucional().equalsIgnoreCase(correo) && e.getPassword().equals(password)) {
                usuarioLogueado = e;
                break;
            }
        }
        if (usuarioLogueado == null) {
            for (Academico a : db.getAcademicos()) {
                if (a.getCorreoInstitucional().equalsIgnoreCase(correo) && a.getPassword().equals(password)) {
                    usuarioLogueado = a;
                    break;
                }
            }
        }

        if (usuarioLogueado != null) {
            db.setUsuarioLogueado(usuarioLogueado);
            JOptionPane.showMessageDialog(vista, "Bienvenido " + usuarioLogueado.getNombre());
            
            FrmMenuPrincipal menuView = new FrmMenuPrincipal();
            MenuController menuCtrl = new MenuController(menuView, db);
            menuView.setController(menuCtrl);
            menuView.setVisible(true);
            
            vista.dispose();
        } else {
            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
