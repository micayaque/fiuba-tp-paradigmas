package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.carta.Carta;

public interface CartaSeleccionadaListener {
    Puntaje onCartaSeleccionada(Carta carta);
}