package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.excepciones.VictimaInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.fase.Urna;
import edu.fiuba.paradigmas.modelo.fase.Voto;

import java.util.ArrayList;
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
        List<Jugador> candidatosValidos = new ArrayList<>();
        jugador.postularseComoCandidatoParaMafia(candidatosValidos);
        if (candidatosValidos.isEmpty()) {
            throw new VictimaInvalidaExcepcion("La Mafia no puede elegir a otro mafioso");
        }
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

    @Override
    public void serProtegido(Jugador jugador) {
        jugador.cambiarEstado(new Protegido());
    }

}
