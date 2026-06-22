package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Mafia;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.votacion.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Mafioso extends Rol {

    public Mafioso(){
        super(new Mafia());
    }

    @Override
    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
        contador.sumarMafioso();
    }

    @Override
    public void votarComoMafiosoA(Jugador victima, UrnaDeVotacion urnaVotacion) {
        Voto miVoto = new Voto(victima);
        victima.recibirVotoMafioso(miVoto, urnaVotacion);
    }

}