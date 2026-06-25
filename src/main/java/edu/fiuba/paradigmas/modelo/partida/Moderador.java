package edu.fiuba.paradigmas.modelo.partida;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fasediurna.FaseDiurna;
import edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public class Moderador {
    private final List<Jugador> jugadores;
    private final SistemaDeEmpate sistemaDeEmpateDiurno;
    private Fase faseActual;
    private int numeroDeRonda;

    public Moderador(List<Jugador> jugadores, SistemaDeEmpate sistemaDeEmpateDiurno) {
        this.jugadores = jugadores;
        this.sistemaDeEmpateDiurno = sistemaDeEmpateDiurno;
        this.faseActual = new FaseNocturna();
        this.numeroDeRonda = 1;
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

    public ResultadoPartida avanzarFase() {
        ResultadoPartida resultado = this.resolverFase();
        if (!resultado.partidaTerminada()) {
            this.faseActual.avanzar(this);
        }
        return resultado;
    }

    public ResultadoPartida resolverFase() {
        AccionVotacion resultado = this.faseActual.ejecutarResultadoVotacion();
        resultado.ejecutar(this.faseActual);
        this.faseActual.cerrar(this.jugadores);
        return this.evaluarGanador();
    }

    public void comenzarFaseDiurna() {
        this.faseActual = new FaseDiurna(this.sistemaDeEmpateDiurno);
    }

    public void comenzarFaseNocturna() {
        this.faseActual = new FaseNocturna();
        this.numeroDeRonda++;
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

    public int numeroDeRonda() {
        return this.numeroDeRonda;
    }

    public Fase faseActual() {
        return this.faseActual;
    }
}
