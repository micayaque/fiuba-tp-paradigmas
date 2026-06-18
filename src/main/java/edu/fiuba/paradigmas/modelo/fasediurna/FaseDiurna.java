package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class FaseDiurna {
    private final Urna urnaDeNominacion;

    public FaseDiurna() {
        this.urnaDeNominacion = new Urna();
    }

    public void recibirNominacion(Jugador nominante, Jugador nominado) {
        nominante.nominarA(nominado, this.urnaDeNominacion);
    }

    public List<Jugador> nominados() {
        return urnaDeNominacion.nominados();
    }


}
