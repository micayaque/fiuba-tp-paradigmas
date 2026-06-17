package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MafiosoTest {
    @Test
    public void unMafiosoVistoPorLaMafiaSeAgregaALaListaDeComplices() {
        Rol rolMafioso = new Mafioso();
        Jugador duenio = new Jugador("mafioso", rolMafioso);

        List<Jugador> complices = new ArrayList<>();
        rolMafioso.vistoPorMafia(duenio, complices);

        assertTrue(complices.contains(duenio), "El mafioso debe agregarse a la lista de cómplices");
        assertEquals(1, complices.size());
    }

    @Test
    public void unMafiosoNoSePostulaComoCandidatoParaSerAsesinado() {
        Rol rolMafioso = new Mafioso();
        Jugador duenio = new Jugador("mafioso", rolMafioso);

        List<Jugador> opciones = new ArrayList<>();
        rolMafioso.validarBandoYPostularseComoCandidatoParaMafia(duenio, opciones);

        assertTrue(opciones.isEmpty(), "Un mafioso no puede ser candidato para que la mafia lo asesine");
    }

    @Test
    public void unMafiosoSeCuentaComoMafiosoEnElContador() {
        Rol rolMafioso = new Mafioso();

        ContadorDeRoles contador = new ContadorDeRoles();
        rolMafioso.contarseEn(contador);

        assertEquals(1, contador.cantidadDeMafiosos());
        assertEquals(0, contador.cantidadDeCiudadanos());
    }

    @Test
    public void unMafiosoInvestigadoApareceComoMafia() {
        Rol rolMafioso = new Mafioso();

        assertEquals("Mafia", rolMafioso.serInvestigado().informe(),
                "Un Mafioso debe delegar en su bando y aparecer como 'Mafia'");
    }
}
