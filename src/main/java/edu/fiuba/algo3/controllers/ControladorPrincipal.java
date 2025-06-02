package edu.fiuba.algo3.controllers;
import edu.fiuba.algo3.modelo.carta.Carta;

import edu.fiuba.algo3.modelo.Balatro.Balatro;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControladorPrincipal implements CartaSeleccionadaListener {
    private Stage stage;
    private Ronda ronda;

    public ControladorPrincipal(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage(){
        return stage;
    }

    public void empezarPartida(){
        Balatro balatro = Balatro.juego();
        this.ronda = balatro.jugarRonda();
        CambiadorDeVistas.cambiarVistaANuevaRonda(stage, this.ronda);
    }

    public void cambiarVistaARonda(List<Object> elementos, Ronda ronda){
        this.ronda = ronda;
        ControladorJugar controladorJugar = new ControladorJugar(stage);
        controladorJugar.cambiarAVistaRonda(elementos, ronda); // Asigna el controlador al evento.
    }

    @Override
    public Puntaje onCartaSeleccionada(Carta carta) {
        Balatro balatro = Balatro.juego();

        return balatro.seleccionar(carta);
    }

    public int jugarMano(List<Carta> cartasSeleccionadas) {
            Balatro balatro = Balatro.juego();
            System.out.println(balatro.jugar());
            return  balatro.jugar();
    }

    public void deselecionarCarta(Carta cartaSeleccionada) {
        Balatro balatro = Balatro.juego();
        balatro.deseleccionarCarta( cartaSeleccionada );
    }
}