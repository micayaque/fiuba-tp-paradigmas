package edu.fiuba.algo3.modelo.Cartas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.ConstruccionExistenteException;
import edu.fiuba.algo3.modelo.Tablero.Factory.Coordenada;
import edu.fiuba.algo3.modelo.Tablero.Factory.ReglaConstruccionException;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.constructoresDeCarreteras.EstrategiaPagoEstandar;
import edu.fiuba.algo3.modelo.constructoresDeCarreteras.EstrategiaPagoGratuito;

import java.util.List;

public class CartaConstruccionCarreteras extends CartaDesarrollo {
    private Coordenada coordenada1;
    private Coordenada coordenada2;
    public CartaConstruccionCarreteras() {
        super();
    }
    public CartaConstruccionCarreteras(IEstadoCarta estado) {
        super(estado);
    }

    @Override
    public void ejecutarEfecto(Jugador jugadorActivo, Tablero tablero, List<Jugador> oponentes) {
        this.usar();

        if (coordenada1 == null || coordenada2 == null) {
            throw new IllegalStateException("Faltan coordenadas para construir carreteras.");
        }

        jugadorActivo.setEstrategiaDePago(new EstrategiaPagoGratuito());

        try {
            jugadorActivo.construirCarretera(tablero, coordenada1);
            jugadorActivo.construirCarretera(tablero, coordenada2);


        } catch (ConstruccionExistenteException e) {
            throw new RuntimeException(e);
        } catch (ReglaConstruccionException e) {
            throw new RuntimeException(e);
        } finally {
            jugadorActivo.setEstrategiaDePago(new EstrategiaPagoEstandar());
        }
    }

    public void setCoordenadas(Coordenada coord1, Coordenada coord2) {
        this.coordenada1 = coord1;
        this.coordenada2 = coord2;
    }
}
