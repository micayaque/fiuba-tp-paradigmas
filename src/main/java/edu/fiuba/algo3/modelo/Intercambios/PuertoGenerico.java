package edu.fiuba.algo3.modelo.Intercambios;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.TipoDeRecurso;

public class PuertoGenerico implements PoliticaDeIntercambio {

    private final int tasa;  // ej: 3 para 3:1

    public PuertoGenerico(int tasa) {
        this.tasa = tasa;
    }

    @Override
    public boolean aplicaA(Jugador jugador, TipoDeRecurso recursoEntregado) {
        return recursoEntregado != null;
    }

    @Override
    public int tasa() {
        return tasa;
    }
}