package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public abstract class Bando {

    public abstract void intentarVerA(Jugador otroJugador, List<Jugador> complices);

    public abstract void vistoPorMafia(Jugador jugador, List<Jugador> complices);

    public abstract void postularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones);

    public abstract ResultadoInvestigacion informarAlDetective();
}