package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.vistas.bienvenida.BienvenidaVista;

public class BienvenidaControlador {

    public BienvenidaControlador(BienvenidaVista vista, ControladorDeJuego orquestador) {
        vista.configurarBotonContinuar(orquestador::irAConfiguracion);
    }
}