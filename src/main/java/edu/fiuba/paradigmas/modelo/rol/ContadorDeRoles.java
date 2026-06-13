package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.excepciones.*;

public class ContadorDeRoles {

    private static final int MINIMO = 5;
    private static final int MAXIMO = 12;

    private int mafiosos;
    private int padrinos;
    private int detectives;
    private int medicos;
    private int sheriffs;
    private int ciudadanos;

    public void sumarMafioso()   { mafiosos++; }
    public void sumarPadrino()   { padrinos++; }
    public void sumarDetective() { detectives++; }
    public void sumarMedico()    { medicos++; }
    public void sumarSheriff()   { sheriffs++; }
    public void sumarCiudadano() { ciudadanos++; }

    public int cantidadDeMafiosos()   { return mafiosos; }
    public int cantidadDePadrinos()   { return padrinos; }
    public int cantidadDeDetectives() { return detectives; }
    public int cantidadDeMedicos()    { return medicos; }
    public int cantidadDeSheriffs()   { return sheriffs; }
    public int cantidadDeCiudadanos() { return ciudadanos; }
    public int total() {
        return mafiosos + padrinos + detectives + medicos + sheriffs + ciudadanos;
    }

    public boolean mafiaEsMinoria() {
        return integrantesDeLaMafia() < integrantesDelPueblo();
    }

    public void validar() {
        if (total() < MINIMO || total() > MAXIMO) {
            throw new CantidadDeJugadoresInvalidaExcepcion(
                "Los jugadores deben estar entre " + MINIMO + " y " + MAXIMO);
        }
        if (integrantesDeLaMafia() < 1) {
            throw new ComposicionInvalidaExcepcion("Debe haber al menos un integrante de la Mafia");
        }
        if (!mafiaEsMinoria()) {
            throw new ComposicionInvalidaExcepcion("La Mafia debe ser minoría frente a los Ciudadanos");
        }
        if (padrinos > 1 || detectives > 1 || medicos > 1 || sheriffs > 1) {
            throw new ComposicionInvalidaExcepcion("No puede haber más de un rol especial de cada tipo");
        }
        if (total() < 7 && rolesEspeciales() > 1) {
            throw new ComposicionInvalidaExcepcion("Hasta 1 rol especial para menos de 7 jugadores");
        }
        if (total() < 10 && rolesEspeciales() > 2) {
            throw new ComposicionInvalidaExcepcion("Hasta 2 roles especiales para menos de 10 jugadores");
        }
    }

    private int integrantesDeLaMafia() { return mafiosos + padrinos; }
    private int integrantesDelPueblo() { return detectives + medicos + sheriffs + ciudadanos; }
    private int rolesEspeciales()      { return padrinos + detectives + medicos + sheriffs; }
}