package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Medico;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProteccionExpiraAlCerrarLaNocheTest {

    @Test
    public void laVictimaProtegidaSobreviveLaNochePeroPierdeLaProteccionAlCerrarse() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador medico = new Jugador("medico", new Medico());
        Jugador victima = new Jugador("victima", new Ciudadano());
        List<Jugador> jugadores = List.of(mafioso, medico, victima);

        Moderador moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion());
        moderador.registrarVoto(mafioso, victima);
        moderador.registrarProteccion(medico, victima);
        moderador.resolverFase();

        List<Jugador> vivosTrasLaNoche = new ArrayList<>();
        victima.estaVivo(vivosTrasLaNoche);
        assertTrue(vivosTrasLaNoche.contains(victima),
                "El médico la protegió: debe sobrevivir la noche");

        victima.morir();

        List<Jugador> vivosTrasNuevoAtaque = new ArrayList<>();
        victima.estaVivo(vivosTrasNuevoAtaque);
        assertFalse(vivosTrasNuevoAtaque.contains(victima),
                "La protección expiró al cerrar la noche: un nuevo ataque debe poder eliminarla");
    }
}
