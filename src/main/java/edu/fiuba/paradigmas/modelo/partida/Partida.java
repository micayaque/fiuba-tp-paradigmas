package edu.fiuba.paradigmas.modelo.partida;

import edu.fiuba.paradigmas.modelo.Fase;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private final List<Jugador> jugadores;
    private Fase faseActual;

    public Partida(List<Jugador> jugadores, Fase faseInicial) {
        this.jugadores = jugadores;
        this.faseActual = faseInicial;
    }

    public ResultadoPartida resolverFase() {
        AccionVotacion resultado = this.faseActual.ejecutarResultadoVotacion();
        resultado.ejecutar(this.faseActual);
        this.faseActual.cerrar(this.jugadores);
        return this.evaluarGanador();
    }

    public ResultadoPartida evaluarGanador() {
        List<Jugador> vivos = new ArrayList<>();
        for (Jugador jugador : this.jugadores) {
            jugador.estaVivo(vivos);
        }

        RecuentoDeBandos recuento = new RecuentoDeBandos();
        for (Jugador jugador : vivos) {
            jugador.contarBandoEn(recuento);
        }

        return recuento.determinarResultado();
    }
}
