package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.urna.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.voto.Voto;
import edu.fiuba.paradigmas.modelo.voto.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Padrino extends Mafioso {
    @Override
    public void contarseEn(CreadorDeJugadores contador) {
        contador.sumarPadrino();
    }

    @Override
    public void identificarseEn(IdentificadorRol identificador) {
        identificador.esPadrino();
    }

    @Override
    public Bando revelarBando() {
        return new Ciudadanos();
    }

    @Override
    public void votarComoMafiosoA(Jugador victima, UrnaDeVotacion urnaVotacion) {
        Voto votoDelPadrino = new VotoDelPadrino(victima);
        victima.recibirVotoMafioso(votoDelPadrino, urnaVotacion);
    }
}