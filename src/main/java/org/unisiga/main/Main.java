package org.unisiga.main;

import org.unisiga.controller.LoginController;
import org.unisiga.model.*;
import org.unisiga.view.FrmLogin;

/**
 * Orquestador principal de pruebas. Configura el escenario inicial de la pauta.
 */
public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        
        // 1. Sembrar catálogo de asignaturas en el sistema (ej. Álgebra, Programación, POO)
        Asignatura algebra = new Asignatura("MAT101", "Álgebra Lineal", 5);
        Asignatura prograBasica = new Asignatura("INF101", "Programación Básica", 6);
        Asignatura poo = new Asignatura("INF102", "Programación Orientada a Objetos", 6);
        
        // 2. Definir reglas recursivas (POO requiere Programación Básica)
        poo.getPrerrequisitos().add(prograBasica);
        
        db.getAsignaturas().add(algebra);
        db.getAsignaturas().add(prograBasica);
        db.getAsignaturas().add(poo);
        
        // 3. Instanciar secciones y evaluaciones a través de Composición Fuerte en Asignatura
        poo.getEvaluaciones().add(new Evaluacion("Examen Parcial", 30f, poo));
        poo.getEvaluaciones().add(new Evaluacion("Examen Final", 40f, poo));
        poo.getEvaluaciones().add(new Evaluacion("Proyecto", 30f, poo));
        
        Departamento deptoInf = new Departamento("D01", "Computación");
        Academico profPedro = new Academico("12345678-9", "Pedro Perez", "profesor", "profesor", "EMP001", "Principal", deptoInf);
        db.getAcademicos().add(profPedro);
        
        Grupo grupoPOO = new Grupo('A', 30, "Lun-Mie 10:00", poo, profPedro);
        poo.getGrupos().add(grupoPOO);
        profPedro.getGruposDictados().add(grupoPOO);
        db.getGrupos().add(grupoPOO);
        
        // 4. Crear estudiantes de prueba
        Estudiante juan = new Estudiante("98765432-1", "Juan Soto", "alumno", "alumno", "20230001", 2023, 5.0f);
        Estudiante maria = new Estudiante("11223344-5", "Maria Gomez", "mgomez@alumnos.uni.edu", "password", "20230002", 2023, 6.0f);
        
        // Juan tiene prograBasica aprobada
        Grupo grupoProg = new Grupo('A', 30, "X", prograBasica, profPedro);
        Matricula mJuanProg = new Matricula("Aprobado", new java.util.Date(), juan, grupoProg);
        juan.getHistorialMatriculas().add(mJuanProg);
        
        db.getEstudiantes().add(juan);
        db.getEstudiantes().add(maria);
        
        // 5. Ejecutar la vista inyectando su propio controlador
        FrmLogin vista = new FrmLogin();
        LoginController loginCtrl = new LoginController(vista, db);
        vista.setController(loginCtrl);
        vista.setVisible(true);
        
        System.out.println("[SISTEMA] El sistema ha sido sembrado y arrancado con éxito.");
    }
}
