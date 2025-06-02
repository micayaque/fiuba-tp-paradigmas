package edu.fiuba.algo3.modelo.Ordenador;

import edu.fiuba.algo3.modelo.carta.Carta;

import java.util.Collections;
import java.util.List;

public class OrdenadorDeCartas {

    public OrdenadorDeCartas(){
    }
    public static void ordenarCartas(List<Carta> cartasRecibidas){
        Collections.sort(cartasRecibidas, (carta1, carta2) -> carta1.esMayor(carta2) ? 1: -1);
    }

}
