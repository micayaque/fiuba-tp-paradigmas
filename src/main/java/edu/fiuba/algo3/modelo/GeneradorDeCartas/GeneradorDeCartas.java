package edu.fiuba.algo3.modelo.GeneradorDeCartas;

import edu.fiuba.algo3.modelo.Palo.*;
import edu.fiuba.algo3.modelo.Prooveedor.ProveedorDeCartas;
import edu.fiuba.algo3.modelo.carta.Carta;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeCartas implements ProveedorDeCartas {


    public GeneradorDeCartas(){}

    public List<Carta> generarCartasPorLimite(Palo palo, int valorMin, int valorMax) {
        List<Carta> cartasGeneradas = new ArrayList<>();

        for (int i = 0; i < valorMax - 1 ; i++) {
            cartasGeneradas.add(new Carta(palo, valorMin + i, valorMin + i, 1));
        }

        return cartasGeneradas;
    }


    public List<Carta> generarCartas() {
        List<Carta> cartasGeneradas = this.generarCartasPorLimite(new Corazon(),2,14);
        cartasGeneradas.addAll(this.generarCartasPorLimite(new Diamante(), 2, 14));
        cartasGeneradas.addAll(this.generarCartasPorLimite(new Pica(), 2, 14));
        cartasGeneradas.addAll(this.generarCartasPorLimite(new Trebol(), 2, 14));
        return cartasGeneradas;
    }
}
