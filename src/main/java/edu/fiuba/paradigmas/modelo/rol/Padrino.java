package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.fase.urna.Voto;
import edu.fiuba.paradigmas.modelo.fase.urna.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.investigacion.ResultadoCiudadano;
import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Padrino extends Mafioso {
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarPadrino();
    }

    @Override
    public ResultadoInvestigacion serInvestigado() {
        return new ResultadoCiudadano();
    }

    @Override
    public void votarComoMafiosoA(Jugador victima, Urna urna) {
        Voto votoDelPadrino = new VotoDelPadrino(victima);
        victima.recibirVotoMafioso(votoDelPadrino, urna);
    }
}