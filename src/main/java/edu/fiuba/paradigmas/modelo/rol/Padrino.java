package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Voto;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Padrino extends Mafioso {
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarPadrino();
    }

    @Override
    public Bando revelarBando() {
        return new Ciudadanos();
    }

    @Override
    public void votarComoMafiosoA(Jugador victima, Urna urna) {
        Voto votoDelPadrino = new VotoDelPadrino(victima);
        victima.recibirVotoMafioso(votoDelPadrino, urna);
    }
}