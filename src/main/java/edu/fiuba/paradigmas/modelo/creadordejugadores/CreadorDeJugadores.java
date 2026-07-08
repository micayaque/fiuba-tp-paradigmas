package edu.fiuba.paradigmas.modelo.creadordejugadores;

import edu.fiuba.paradigmas.modelo.excepciones.mazo.CantidadDeJugadoresInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.ComposicionInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.RepartoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CreadorDeJugadores {

    private static final int MINIMO = 5;
    private static final int MAXIMO = 12;

    private final Random generadorAleatorio;

    private int mafiosos;
    private int padrinos;
    private int detectives;
    private int medicos;
    private int sheriffs;
    private int ciudadanos;

    public CreadorDeJugadores(Random generadorAleatorio) {
        this.generadorAleatorio = generadorAleatorio;
    }

    public List<Jugador> crearPartida(List<String> nombres, List<Rol> rolesAsignados) {

        if (nombres.size() != rolesAsignados.size()) {
            throw new RepartoInvalidoExcepcion("Debe haber exactamente un rol por cada jugador.");
        }

        validarComposicionDeRoles(rolesAsignados);

        List<Rol> rolesMezclados = new ArrayList<>(rolesAsignados);
        Collections.shuffle(rolesMezclados, this.generadorAleatorio);

        return asignarRolesAJugadores(nombres, rolesMezclados);
    }

    private void validarComposicionDeRoles(List<Rol> roles) {
        reiniciarContadores();
        roles.forEach(rol -> rol.contarseEn(this));
        validar();
    }

    private void reiniciarContadores() {
        this.mafiosos = 0;
        this.padrinos = 0;
        this.detectives = 0;
        this.medicos = 0;
        this.sheriffs = 0;
        this.ciudadanos = 0;
    }

    private List<Jugador> asignarRolesAJugadores(List<String> nombres, List<Rol> rolesMezclados) {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < nombres.size(); i++) {
            jugadores.add(new Jugador(nombres.get(i), rolesMezclados.get(i)));
        }
        return jugadores;
    }

    public void sumarMafioso()   { this.mafiosos++; }
    public void sumarPadrino()   { this.padrinos++; }
    public void sumarDetective() { this.detectives++; }
    public void sumarMedico()    { this.medicos++; }
    public void sumarSheriff()   { this.sheriffs++; }
    public void sumarCiudadano() { this.ciudadanos++; }

    public int cantidadDeMafiosos()   { return mafiosos; }
    public int cantidadDePadrinos()   { return padrinos; }
    public int cantidadDeDetectives() { return detectives; }
    public int cantidadDeMedicos()    { return medicos; }
    public int cantidadDeSheriffs()   { return sheriffs; }
    public int cantidadDeCiudadanos() { return ciudadanos; }

    private int total() {
        return mafiosos + padrinos + detectives + medicos + sheriffs + ciudadanos;
    }

    private int integrantesDeLaMafia()  { return mafiosos + padrinos; }
    private int integrantesCiudadanos() { return detectives + medicos + sheriffs + ciudadanos; }
    private int rolesEspeciales()       { return padrinos + detectives + medicos + sheriffs; }

    private boolean mafiaEsMinoria() {
        return integrantesDeLaMafia() < integrantesCiudadanos();
    }

    private void validar() {
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
}