package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.fase.Urna;
import edu.fiuba.paradigmas.modelo.fase.Voto;

import java.util.List;

public class Vivo extends Estado {

    @Override
    public void postularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones) {
        jugador.continuarPostulacionConCarta(opciones);
    }

    @Override
    public void morir(Jugador jugador) {
        jugador.cambiarEstado(new Muerto());
    }

    @Override
    public void recibirVotoMafioso(Jugador jugador, Urna urna) {
        urna.agregarVoto(new Voto(jugador));
    }

    @Override
    public void estaVivo(Jugador jugador, List<Jugador> vivos) {
        vivos.add(jugador);
    }

    @Override
    public void intentarVotarComoMafiosoA(Jugador victimaElegida, Urna urnaDeMafia) {
        victimaElegida.recibirVotoMafioso(urnaDeMafia);
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        jugador.continuarVistoPorMafiaConCarta(complices);
    }

    @Override
    public void desempatarVotacionMafia(Jugador jugador, Jugador victima) {
        jugador.continuarDesempateConCarta(victima);
    }

}
