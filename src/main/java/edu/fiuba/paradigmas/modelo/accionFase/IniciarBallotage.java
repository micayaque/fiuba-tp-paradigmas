package edu.fiuba.paradigmas.modelo.accionFase;

import edu.fiuba.paradigmas.modelo.fase.*;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class IniciarBallotage implements AccionFase {
    private final List<Jugador> empatados;

    public IniciarBallotage(List<Jugador> empatados) {
        this.empatados = empatados;
    }

    @Override
    public void ejecutar(Fase fase) {
        fase.iniciarBallotage(this.empatados);
    }

    public List<Jugador> empatados() {
        return  this.empatados;
    }
}