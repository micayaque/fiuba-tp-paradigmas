package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.votacion.Voto;
import edu.fiuba.paradigmas.modelo.votacion.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Padrino extends Mafioso {
    @Override
    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
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