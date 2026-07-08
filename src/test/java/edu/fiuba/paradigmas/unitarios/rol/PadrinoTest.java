package edu.fiuba.paradigmas.unitarios.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorVivoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Padrino;
import edu.fiuba.paradigmas.modelo.rol.Rol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


public class PadrinoTest {

    @Test
    public void alRevelarSuIdentidadElPadrinoMienteYSePresentaComoCiudadano() {
        Padrino rolPadrino = new Padrino();
        Jugador jugadorPadrino = new Jugador("Padrino", rolPadrino);

        Bando bandoRevelado = rolPadrino.revelarBando();

        List<Jugador> vistosPorMafia = new ArrayList<>();

        bandoRevelado.vistoPorMafia(jugadorPadrino, vistosPorMafia);
        assertFalse(vistosPorMafia.contains(jugadorPadrino),
                "El bando devuelto no debió revelarse como Mafia");
    }

    @Test
    public void elPadrinoSumaCorrectamenteEnElContadorDeRoles() {
        Random  random = new Random();
        CreadorDeJugadores contador = new CreadorDeJugadores(random);
        Padrino padrino = new Padrino();

        padrino.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 0;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 0;
        int cantidadMedicosEsperadosEnElContador = 0;
        int cantidadPadrinosEsperadosEnElContador = 1;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadDeMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadDeCiudadanos());
        assertEquals(cantidadMedicosEsperadosEnElContador, contador.cantidadDeMedicos());
        assertEquals(cantidadPadrinosEsperadosEnElContador, contador.cantidadDePadrinos());
    }
    @Test
    public void unPadrinoEliminadoDebeMostrarSuCarta(){
        Rol rolPadrino = new Padrino();
        Jugador padrino = new Jugador("padrino", rolPadrino);

        padrino.morir();

        assertEquals( rolPadrino, padrino.revelarCarta());
    }

    @Test
    public void unPadrinoVivoNoDebeMostrarSuCarta(){
        Rol rolPadrino = new Padrino();
        Jugador padrino = new Jugador("padrino", rolPadrino);

        assertThrows(JugadorVivoExcepcion.class, padrino::revelarCarta,
                "Un padrino no debería mostrar su carta si está vivo");
    }
}
