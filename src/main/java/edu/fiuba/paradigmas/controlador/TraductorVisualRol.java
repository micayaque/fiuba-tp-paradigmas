package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.IdentificadorRol;

public class TraductorVisualRol implements IdentificadorRol {
    private String textoResultado = "";

    @Override public void esCiudadano() { this.textoResultado = "Ciudadano"; }
    @Override public void esDetective() { this.textoResultado = "Detective"; }
    @Override public void esMafioso()   { this.textoResultado = "Miembro de la Mafia"; }
    @Override public void esMedico()    { this.textoResultado = "Médico"; }
    @Override public void esPadrino()   { this.textoResultado = "El Padrino"; }
    @Override public void esSheriff()   { this.textoResultado = "Sheriff"; }

    public String traducirRolDe(Jugador jugador) {
        jugador.identificarRolEn(this);
        return this.textoResultado;
    }
}
