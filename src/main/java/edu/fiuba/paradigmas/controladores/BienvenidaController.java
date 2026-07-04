package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.BienvenidaVista;

public class BienvenidaController {
    private final BienvenidaVista vista;
    private final App app;

    public BienvenidaController(BienvenidaVista vista, App app) {
        this.vista = vista;
        this.app = app;

        this.vista.alPresionarContinuar(this::iniciarConfiguracion);
    }

    private void iniciarConfiguracion() {
        this.app.irAConfiguracion();
    }
}