package edu.fiuba.paradigmas.modelo.partida;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.bando.Bando;
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
    private Fase faseActual;
    private int numeroDeRonda;

//    private final List<EventoFase> historialDePartida = new ArrayList<>();

    public Moderador(List<Jugador> jugadores, SistemaDeEmpate sistemaDeEmpateDiurno) {
        this.jugadores = jugadores;
        this.sistemaDeEmpateDiurno = sistemaDeEmpateDiurno;
        this.numeroDeRonda = 0;
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
        this.faseActual.recibirProteccion(medico, protegido);
    }

    public AccionFase resolverVotacion() {
        AccionFase accion = this.faseActual.ejecutarResultadoVotacion();
//        this.historialDePartida.add(accion);
        return accion;
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

    public Bando registrarInvestigacion(Jugador jugadorActivo, Jugador sospechoso) {
        return jugadorActivo.investigarA(sospechoso);
    }

    public Fase obtenerFaseActual() {
        return this.faseActual;
    }

    public void avanzarFase() {
        this.faseActual.avanzar(this);
    }
}
