package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.vista.bienvenida.BienvenidaVista;

public class BienvenidaControlador {

    public BienvenidaControlador(BienvenidaVista vista, JuegoControlador orquestador) {
        vista.configurarBotonContinuar(orquestador::irAConfiguracion);
    }
}