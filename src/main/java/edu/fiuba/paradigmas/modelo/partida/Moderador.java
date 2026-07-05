package edu.fiuba.paradigmas.modelo.partida;

import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Moderador {
    private final List<Jugador> jugadores;
    private final SistemaDeEmpate sistemaDeEmpateDiurno;
    private final ObservadorResultadoPartida observadorResultado;
    private Fase faseActual;
    private int numeroDeRonda;

    public Moderador(List<Jugador> jugadores, SistemaDeEmpate sistemaDeEmpateDiurno, ObservadorResultadoPartida observador) {
        this.jugadores = jugadores;
        this.sistemaDeEmpateDiurno = sistemaDeEmpateDiurno;
        this.observadorResultado = observador;
        this.faseActual = new FaseNocturna();
        this.numeroDeRonda = 1;
    }

    public List<Jugador> jugadoresVivos() {
        List<Jugador> jugadoresVivos = new ArrayList<>();
        for(Jugador jugador : this.jugadores){
            jugador.estaVivo(jugadoresVivos);
        }
        return jugadoresVivos;
    }

    public List<Jugador> jugadoresEliminados() {
        List<Jugador> jugadoresVivos = this.jugadoresVivos();
        List<Jugador> jugadoresEliminados = new ArrayList<>(this.jugadores);
        jugadoresEliminados.removeAll(jugadoresVivos);
        return jugadoresEliminados;
    }

    public void anunciarVictoriaMafia() {
        this.observadorResultado.anunciarVictoriaMafia();
    }

    public void anunciarVictoriaCiudadanos() {
        this.observadorResultado.anunciarVictoriaCiudadanos();
    }

    public void registrarVoto(Jugador votante, Jugador votado) {
        this.faseActual.recibirVoto(votante, votado);
    }

    public void registrarProteccion(Jugador medico, Jugador protegido) {
        this.faseActual.recibirProteccion(medico, protegido);
    }

    public void registrarNominacion(Jugador nominante, Jugador nominado) {
        this.faseActual.recibirNominacion(nominante, nominado);
    }

    public List<Jugador> iniciarVotacion() {
        return this.faseActual.iniciarVotacion();
    }

    public void comenzarFaseDiurna() {
        jugadores.forEach(Jugador::eliminarProteccion);
        this.faseActual = new FaseDiurna(this.sistemaDeEmpateDiurno);
    }

    public void comenzarFaseNocturna() {
        this.faseActual = new FaseNocturna();
        this.numeroDeRonda++;
    }

    public ResultadoPartida resolverFase() {
        this.faseActual.ejecutarResultadoVotacion();
        ResultadoPartida resultadoPartida = this.evaluarGanador();
        resultadoPartida.ejecutar(this.faseActual, this);
        return resultadoPartida;
    }

    public ResultadoPartida evaluarGanador() {
        List<Jugador> vivos = new ArrayList<>();
        this.jugadores.forEach(j -> j.estaVivo(vivos));

        VerificacionVictoria recuento = new VerificacionVictoria();
        vivos.forEach(j -> j.contarBandoEn(recuento));

        return recuento.determinarResultado();
    }

    public int numeroDeRonda() {
        return this.numeroDeRonda;
    }

    // ¿por qué el controlador tiene que conocer a la fase?
    public Fase obtenerFaseActual(){
        return this.faseActual;
    }
}
