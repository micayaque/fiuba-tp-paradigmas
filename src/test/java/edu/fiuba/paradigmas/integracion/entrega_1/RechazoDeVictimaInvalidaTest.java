package edu.fiuba.paradigmas.integracion.entrega_1;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RechazoDeVictimaInvalidaTest {

    @Test
    public void laMafiaNoPuedeElegirAUnJugadorMuerto() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador muerto = new Jugador("muerto", new Ciudadano());
        muerto.morir();

        FaseNocturna fase = new FaseNocturna();

        assertThrows(JugadorMuertoExcepcion.class, () -> fase.recibirVoto(mafioso, muerto));
    }

    @Test
    public void laMafiaNoPuedeElegirAOtroMafioso() {
        Jugador mafioso1 = new Jugador("mafioso1", new Mafioso());
        Jugador mafioso2 = new Jugador("mafioso2", new Mafioso());

        FaseNocturna fase = new FaseNocturna();

        assertThrows(VotoInvalidoExcepcion.class, () -> fase.recibirVoto(mafioso1, mafioso2));
    }
}
