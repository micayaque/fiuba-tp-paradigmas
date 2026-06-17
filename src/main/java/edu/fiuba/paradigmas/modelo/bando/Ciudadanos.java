package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.investigacion.ResultadoCiudadano;
import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class Ciudadanos extends Bando {

    @Override
    public void intentarVerA(Jugador otroJugador, List<Jugador> complices) {
        // NULL OBJECT
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        // NULL OBJECT
    }

    @Override
    public void postularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones) {
        opciones.add(jugador);
    }

    @Override
    public ResultadoInvestigacion informarAlDetective() {
        return new ResultadoCiudadano();
    }

}