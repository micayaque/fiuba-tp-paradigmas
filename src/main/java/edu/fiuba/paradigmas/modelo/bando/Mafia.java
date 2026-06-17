package edu.fiuba.paradigmas.modelo.bando;

// import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
// import edu.fiuba.paradigmas.modelo.investigacion.ResultadoMafia;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class Mafia extends Bando {

    @Override
    public void intentarVerA(Jugador otroJugador, List<Jugador> complices) {
        otroJugador.vistoPorMafia(complices);
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        complices.add(jugador);
    }

    @Override
    public void postularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones) {
        // Null Object
    }

    // @Override
    // public ResultadoInvestigacion informarAlDetective() {
    //     return new ResultadoMafia();
    // }

}