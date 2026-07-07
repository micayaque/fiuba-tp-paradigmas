package edu.fiuba.paradigmas.modelo.historial;

import java.util.ArrayList;
import java.util.List;

public class HistorialDePartida {
    private final List<Memento> mementos = new ArrayList<>();

    public void registrar(Memento memento) {
        this.mementos.add(memento);
    }

    public List<Memento> mementos() {
        return List.copyOf(this.mementos);
    }
}