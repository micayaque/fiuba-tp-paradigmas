package edu.fiuba.paradigmas.modelo.accionVotacion;

import edu.fiuba.paradigmas.modelo.fasediurna.GestorDeBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.List;

public class IniciarBallotage implements AccionVotacion {
    private final GestorDeBallotage gestor;
    private final List<Jugador> empatados;

    public IniciarBallotage(GestorDeBallotage gestor, List<Jugador> empatados) {
        this.gestor = gestor;
        this.empatados = empatados;
    }

    @Override
    public void ejecutar() {
        this.gestor.iniciarBallotage(this.empatados);
    }
}