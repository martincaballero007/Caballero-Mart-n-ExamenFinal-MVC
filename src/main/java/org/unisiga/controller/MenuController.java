package org.unisiga.controller;

import org.unisiga.model.Database;
import org.unisiga.view.FrmCalificaciones;
import org.unisiga.view.FrmMatricula;
import org.unisiga.view.FrmMenuPrincipal;

public class MenuController {
    private FrmMenuPrincipal vista;
    private Database db;

    public MenuController(FrmMenuPrincipal vista, Database db) {
        this.vista = vista;
        this.db = db;
    }

    public void abrirInscripcion() {
        FrmMatricula frm = new FrmMatricula();
        MatriculaController ctrl = new MatriculaController(frm, db);
        frm.setController(ctrl);
        vista.agregarVentanaInterna(frm);
    }

    public void abrirCalificaciones() {
        FrmCalificaciones frm = new FrmCalificaciones();
        CalificacionesController ctrl = new CalificacionesController(frm, db);
        frm.setController(ctrl);
        vista.agregarVentanaInterna(frm);
    }
}
