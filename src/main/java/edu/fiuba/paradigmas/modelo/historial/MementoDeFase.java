package edu.fiuba.paradigmas.modelo.historial;

public abstract class MementoDeFase implements Memento {
    private final Memento contenido;

    protected MementoDeFase(Memento contenido) {
        this.contenido = contenido;
    }

    public Memento contenido() { return this.contenido; }
}