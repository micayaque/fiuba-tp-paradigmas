package edu.fiuba.paradigmas.modelo.partida;

public class VictoriaMafia implements ResultadoPartida {

    @Override
    public boolean partidaTerminada() {
        return true;
    }

    @Override
    public String anuncio() {
        return "Gana la Mafia";
    }
}
