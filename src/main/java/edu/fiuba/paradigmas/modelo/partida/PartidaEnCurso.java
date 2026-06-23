package edu.fiuba.paradigmas.modelo.partida;

public class PartidaEnCurso implements ResultadoPartida {

    @Override
    public boolean partidaTerminada() {
        return false;
    }

    @Override
    public String anuncio() {
        return "La partida continúa";
    }
}
