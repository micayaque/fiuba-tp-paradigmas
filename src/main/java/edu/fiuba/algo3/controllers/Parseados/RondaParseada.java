package edu.fiuba.algo3.controllers.Parseados;

public class RondaParseada {
    private int nro;
    private int manos;
    private int descartes;
    private int puntajeASuperar;
    private TiendaParseado tienda;

    // Getters y setters
    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getManos() {
        return manos;
    }

    public void setManos(int manos) {
        this.manos = manos;
    }

    public int getDescarte() {
        return descartes;
    }

    public void setDescarte(int descartes) {
        this.descartes = descartes;
    }

    public int getPuntajeASuperar() {
        return puntajeASuperar;
    }

    public void setPuntajeASuperar(int puntajeASuperar) {
        this.puntajeASuperar = puntajeASuperar;
    }

    public TiendaParseado getTienda() {
        return tienda;
    }

    public void setTienda(TiendaParseado tienda) {
        this.tienda = tienda;
    }
}

