package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.rol.ContadorDeRoles;
import edu.fiuba.paradigmas.modelo.rol.Rol;

public class Jugador {

    private final String nombre;
    private final Rol carta;

    public Jugador(String nombre, Rol carta) {
        this.nombre = nombre;
        this.carta = carta;
    }

    public String nombre() {
        return nombre;
    }

    public Rol miRol() {
        return carta;
    }

    public void contarseEn(ContadorDeRoles contador) {
        carta.contarseEn(contador);
    }
}
