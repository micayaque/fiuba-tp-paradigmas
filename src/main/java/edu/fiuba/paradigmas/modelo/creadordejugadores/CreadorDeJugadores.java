package edu.fiuba.paradigmas.modelo.creadordejugadores;

import edu.fiuba.paradigmas.modelo.excepciones.mazo.RepartoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreadorDeJugadores {

    public List<Jugador> crearPartida(List<String> nombres, List<Rol> rolesAsignados) {

        if (nombres.size() != rolesAsignados.size()) {
            throw new RepartoInvalidoExcepcion("Debe haber exactamente un rol por cada jugador.");
        }

        this.validarComposicionDeRoles(rolesAsignados);

        List<Rol> rolesMezclados = new ArrayList<>(rolesAsignados);
        Collections.shuffle(rolesMezclados);

        return this.asignarRolesAJugadores(nombres, rolesMezclados);
    }

    private void validarComposicionDeRoles(List<Rol> roles) {
        ValidadorDeComposicionDelMazo contador = new ValidadorDeComposicionDelMazo();
        roles.forEach(rol -> rol.contarseEn(contador));
        contador.validar();
    }

    private List<Jugador> asignarRolesAJugadores(List<String> nombres, List<Rol> rolesMezclados) {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < nombres.size(); i++) {
            jugadores.add(new Jugador(nombres.get(i), rolesMezclados.get(i)));
        }
        return jugadores;
    }
}