package edu.fiuba.algo3.modelo.Balatro;

import edu.fiuba.algo3.controllers.Factory.FactoryComodines;
import edu.fiuba.algo3.controllers.Factory.FactoryDeMazo;
import edu.fiuba.algo3.controllers.Factory.FactoryDeTarot;
import edu.fiuba.algo3.controllers.Factory.FactoryRondas;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.ronda.Tienda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Balatro {

    private FactoryRondas factoryRondas;
    private FactoryDeMazo factoryDeMazo;
    private FactoryDeTarot factoryDeTarot;
    private FactoryComodines factoryComodines;
    private Jugador jugador;
    private List<Ronda> rondas;
    private Mazo mazo;
    private Ronda rondaActual;
    private static Balatro balatro;

    private Balatro(){
    }

    public static Balatro juego(){
        if(balatro == null){
            balatro = new Balatro();
        }return balatro;
    }


    public void inicializadorDeBalatro(String rutaDeBalatro, String rutaDeMazo, String rutaDeTarots, String rutaDeComodines, String nombreDeJugador) throws IOException {
        factoryDeMazo = new FactoryDeMazo(rutaDeMazo);
        factoryDeTarot = new FactoryDeTarot(rutaDeTarots);
        factoryComodines = new FactoryComodines(rutaDeComodines);
        factoryRondas = new FactoryRondas(rutaDeBalatro, factoryDeTarot, factoryDeMazo, factoryComodines);
        jugador = Jugador.CrearJugador(nombreDeJugador);
        mazo = new Mazo(factoryDeMazo);
        rondas = new ArrayList<>();
        rondas = factoryRondas.generarRondas();
        rondaActual = rondas.get(0);
    }


    public List<Carta> mostrarCartasDeLaMano() {
        return jugador.verCartasEnMano();
    }

    public Tienda mostrarTienda() {
        return rondaActual.verTienda();
    }

    public Puntaje seleccionar(Carta carta) {
       return rondaActual.seleccionar(carta);
    }

    public Puntaje deseleccionarCarta(Carta carta) {
        return rondaActual.deSeleccionarUnaCarta(carta);
    }

    public void descartar() {
        rondaActual.descartar();
    }

    public int jugar() {
        Puntaje puntaje = rondaActual.jugar();
        if(rondaActual.seGanoLaRonda()){
            rondaActual = rondas.iterator().next();
        }else if(rondaActual.sePerdioLaRonda()){
            throw new Derrota();
        }
        return puntaje.calcularPuntaje();
    }

    public Ronda jugarRonda() {
        this.rondaActual.empezarRonda(jugador, mazo);
        return this.rondaActual;
    }

}
