package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.IdentificadorRol;

public class TraductorVisualRol implements IdentificadorRol {
    private String textoResultado = "";

    @Override public void esCiudadano() { this.textoResultado = "Ciudadano"; }
    @Override public void esDetective() { this.textoResultado = "Detective"; }
    @Override public void esMafioso()   { this.textoResultado = "Mafioso"; }
    @Override public void esMedico()    { this.textoResultado = "Medico"; }
    @Override public void esPadrino()   { this.textoResultado = "Padrino"; }
    @Override public void esSheriff()   { this.textoResultado = "Sheriff"; }

    public String traducirRolDe(Jugador jugador) {
        jugador.identificarRolEn(this);
        return this.textoResultado;
    }
}