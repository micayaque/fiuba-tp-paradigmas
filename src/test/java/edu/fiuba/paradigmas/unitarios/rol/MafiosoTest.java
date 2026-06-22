package edu.fiuba.paradigmas.unitarios.rol;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorVivoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Rol;
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
    public void unMafiosoSeCuentaComoMafiosoEnElContador() {
        Rol rolMafioso = new Mafioso();

        ValidadorDeComposicionDelMazo contador = new ValidadorDeComposicionDelMazo();
        rolMafioso.contarseEn(contador);

        assertEquals(1, contador.cantidadDeMafiosos());
        assertEquals(0, contador.cantidadDeCiudadanos());
    }

    @Test
    public void unMafiosoEliminadoDebeMostrarSuCarta(){
        Rol rolMafioso = new Mafioso();
        Jugador mafioso = new Jugador("mafioso", rolMafioso);

        mafioso.morir();

        assertEquals( rolMafioso, mafioso.revelarCarta());
    }

    @Test
    public void unMafiosoVivoNoDebeMostrarSuCarta(){
        Rol rolMafioso = new Mafioso();
        Jugador mafioso = new Jugador("mafioso", rolMafioso);

        assertThrows(JugadorVivoExcepcion.class, mafioso::revelarCarta,
                "Un mafioso no debería mostrar su carta si está vivo");
    }
}
