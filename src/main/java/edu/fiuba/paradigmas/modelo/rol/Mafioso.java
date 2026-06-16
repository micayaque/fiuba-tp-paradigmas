package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Mafia;
import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.fase.urna.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Mafioso extends Rol {

    public Mafioso(){
        super(new Mafia());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarMafioso();
    }

    @Override
    public void votarComoMafiosoA(Jugador victima, Urna urna) {
        Voto miVoto = new Voto(victima);
        victima.recibirVotoMafioso(miVoto, urna);
    }

}