package edu.fiuba.paradigmas.modelo.partida;

public class RecuentoDeBandos {
    private int mafiosos;
    private int ciudadanos;

    public void sumarMafioso() {
        this.mafiosos++;
    }

    public void sumarCiudadano() {
        this.ciudadanos++;
    }

    public ResultadoPartida determinarResultado() {
        if (this.mafiosos == 0) {
            return new VictoriaCiudadanos();
        }
        if (this.mafiosos >= this.ciudadanos) {
            return new VictoriaMafia();
        }
        return new PartidaEnCurso();
    }
}
