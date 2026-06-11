package edu.fiuba.algo3.modelo.jugadores;

import edu.fiuba.algo3.modelo.roles.RolNulo;
import edu.fiuba.algo3.modelo.roles.Rol;

public class JugadorNulo extends Jugador {
    
    private static final JugadorNulo INSTANCIA = new JugadorNulo();

    private JugadorNulo() {
        super("Jugador Nulo", new RolNulo()); 
    }

    public static JugadorNulo obtenerInstancia() {
        return INSTANCIA;
    }
    
    @Override
    public void eliminar() {

    }
    
    @Override
    public boolean conoceElRolDe(Jugador otro, Rol rol) {
        return false;
    }
}