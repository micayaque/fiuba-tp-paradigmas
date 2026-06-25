package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.ReconocedorFase;

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

    public String traducir(Fase fase) {
        if (fase == null) return "Sin Fase";
        
        fase.reconocerseEn(this);
        return this.textoResultado;
    }
}