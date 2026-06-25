package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.fase.ReconocedorFase;
import edu.fiuba.paradigmas.modelo.partida.Moderador;

public class TraductorVisualFase implements ReconocedorFase {
    private String textoResultado = "";

    @Override
    public void esDiurna() {
        this.textoResultado = "Fase Diurna (Discusiones y Votación)";
    }

    @Override
    public void esNocturna() {
        this.textoResultado = "Fase Nocturna (La Mafia Ataca...)";
    }

    public String traducirDesde(Moderador moderador) {
        moderador.reconocerFaseEn(this);
        return this.textoResultado;
    }
}
