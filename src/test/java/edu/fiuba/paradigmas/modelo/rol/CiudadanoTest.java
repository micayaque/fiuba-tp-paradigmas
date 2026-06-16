package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CiudadanoTest {

    @Test
    public void unCiudadanoVistoPorLaMafiaNoSeAgregaALaListaDeComplices() {
        Rol rolCiudadano = new Ciudadano();
        Jugador duenio = new Jugador("ciudadano", rolCiudadano);
        List<Jugador> complices = new ArrayList<>();

        rolCiudadano.vistoPorMafia(duenio, complices);

        assertTrue(complices.isEmpty(), "Un ciudadano no debe agregarse a la votación de la mafia");
    }

    @Test
    public void unCiudadanoSePostulaComoCandidatoParaSerAsesinado() {
        Rol rolCiudadano = new Ciudadano();
        Jugador duenio = new Jugador("ciudadano", rolCiudadano);

        List<Jugador> opciones = new ArrayList<>();
        rolCiudadano.validarBandoYPostularseComoCandidatoParaMafia(duenio, opciones);

        assertTrue(opciones.contains(duenio), "El ciudadano debe ser una opción válida para asesinar");
        assertEquals(1, opciones.size());
    }

    @Test
    public void unCiudadanoSeCuentaComoCiudadanoEnElContador() {
        Rol rolCiudadano = new Ciudadano();

        ContadorDeRoles contador = new ContadorDeRoles();
        rolCiudadano.contarseEn(contador);

        assertEquals(0, contador.cantidadDeMafiosos());
        assertEquals(1, contador.cantidadDeCiudadanos());
    }
}