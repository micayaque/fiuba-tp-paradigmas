package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.carta.Carta;

import java.util.ArrayList;
import java.util.List;

public class GenerarCartasVista {

    public GenerarCartasVista() {}

    public static List<CartaVista> generarCarta(List<Carta> cartas) {
        List<CartaVista> cartasVista = new ArrayList<>();
        for (Carta carta : cartas) {
            cartasVista.add(new CartaVista(carta));
        }
        return cartasVista;
    }
}
