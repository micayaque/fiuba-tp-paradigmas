package edu.fiuba.paradigmas.modelo.excepciones;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.ArrayList;

public class EmpateMafiosoExcepcion extends RuntimeException {
    private ArrayList<Jugador> candidatosEmpatados;

    public EmpateMafiosoExcepcion(ArrayList<Jugador> candidatosEmpatados) {
        this.candidatosEmpatados = candidatosEmpatados;
    }

    public ArrayList<Jugador> candidatosEmpatados() {
        return this.candidatosEmpatados;
    }
}