package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.urna.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Padrino extends Mafioso {
    @Override
    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
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