package edu.fiuba.paradigmas.controlador;

import java.util.List;

public interface AccionIniciarJuego {
    void iniciar(List<String> nombres, int cantidadMafiosos, boolean usaPadrino, boolean usaDetective, boolean usaMedico, boolean usaSheriff);
}
