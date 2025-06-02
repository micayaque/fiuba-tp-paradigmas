package edu.fiuba.algo3.modelo.Modificable;

import edu.fiuba.algo3.modelo.carta.Carta;

public class Modificador {
    private String tipoDeModificable;


    public Modificador(String modificicable) {
        this.tipoDeModificable = modificicable;
    }

    public boolean modificar(Modificable modificable) {
        return modificable.validarModificable(this);
    }

    public Boolean validarTipo(String tipo) {
        return tipo.equals(tipoDeModificable);
    }
}