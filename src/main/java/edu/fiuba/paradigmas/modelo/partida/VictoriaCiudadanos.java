package edu.fiuba.paradigmas.modelo.partida;

public class VictoriaCiudadanos implements ResultadoPartida {

    @Override
    public boolean partidaTerminada() {
        return true;
    }

    @Override
    public String anuncio() {
        return "Ganan los Ciudadanos";
    }
}
