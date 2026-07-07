package edu.fiuba.paradigmas.modelo.partida;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.historial.HistorialDePartida;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Moderador {
    private final List<Jugador> jugadores;
    private final SistemaDeEmpate sistemaDeEmpateDiurno;
    private Fase faseActual;
    private int numeroDeRonda;

    private final HistorialDePartida historial;

    public Moderador(List<Jugador> jugadores, SistemaDeEmpate sistemaDeEmpateDiurno) {
        this.jugadores = jugadores;
        this.sistemaDeEmpateDiurno = sistemaDeEmpateDiurno;
        this.numeroDeRonda = 0;
        this.historial = new HistorialDePartida();
    }

    public List<Jugador> jugadoresVivos() {
        List<Jugador> vivos = new ArrayList<>();
        this.jugadores.forEach(j -> j.estaVivo(vivos));
        return vivos;
    }

    public List<Jugador> jugadoresEliminados() {
        List<Jugador> eliminados = new ArrayList<>();
        this.jugadores.forEach(j -> j.estaEliminado(eliminados));
        return eliminados;
    }

    public void anunciarVictoriaMafia() {
    }

    public void anunciarVictoriaCiudadanos() {
    }

    public void registrarVoto(Jugador votante, Jugador votado) {
        this.faseActual.recibirVoto(votante, votado);
    }

    public void registrarProteccion(Jugador medico, Jugador protegido) {
        Memento resultado = this.faseActual.recibirProteccion(medico, protegido);
        this.historial.registrar(resultado);
    }

    public Memento resolverVotacion() {
        AccionFase accion = this.faseActual.ejecutarResultadoVotacion();
        Memento resultado = this.faseActual.envolverResultado(accion.guardarEstado());
        this.historial.registrar(resultado);
        return resultado;
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
        return this.evaluarGanador();
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

    public Memento registrarInvestigacion(Jugador jugadorActivo, Jugador sospechoso) {
        Memento resultado = this.faseActual.recibirInvestigacion(jugadorActivo, sospechoso);
        this.historial.registrar(resultado);
        return resultado;

    }

    public Fase obtenerFaseActual() {
        return this.faseActual;
    }

    public void avanzarFase() {
        this.faseActual.avanzar(this);
    }

    public void registrarRevelacionDeSheriff(Jugador jugador) {
        Memento resultado = this.faseActual.recibirRevelacion(jugador);
        this.historial.registrar(resultado);
    }

    public HistorialDePartida historialDePartida() {
        return this.historial;
    }
}
