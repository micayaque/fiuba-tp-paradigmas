package edu.fiuba.paradigmas.unitarios.rol;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorVivoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.rol.ProteccionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Medico;
import edu.fiuba.paradigmas.modelo.rol.Rol;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedicoTest {
    @Test
    public void elMedicoSumaCorrectamenteEnElContadorDeRoles() {
        ValidadorDeComposicionDelMazo contador = new ValidadorDeComposicionDelMazo();
        Medico medico = new Medico();

        medico.contarseEn(contador);
        medico.contarseEn(contador);
        medico.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 0;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 0;
        int cantidadMedicosEsperadosEnElContador = 3;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadDeMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadDeCiudadanos());
        assertEquals(cantidadMedicosEsperadosEnElContador, contador.cantidadDeMedicos());
    }

    @Test
    public void elMedicoNoPuedeProtegerAlMismoJugadorDosVecesSeguidas() {
        Medico medico = new Medico();
        Jugador protegido = new Jugador("protegido", new Ciudadano());

        medico.protegerComoMedico(protegido);

        assertThrows(ProteccionRepetidaExcepcion.class,
                () -> medico.protegerComoMedico(protegido),
                "Proteger al mismo jugador dos noches seguidas debe ser rechazado");
    }

    @Test
    public void elMedicoPuedeRepetirProteccionSiProtegioAOtroEnElMedio() {
        Medico medico = new Medico();
        Jugador unJugador = new Jugador("unJugador", new Ciudadano());
        Jugador otroJugador = new Jugador("otroJugador", new Ciudadano());

        medico.protegerComoMedico(unJugador);
        medico.protegerComoMedico(otroJugador);

        assertDoesNotThrow(() -> medico.protegerComoMedico(unJugador),
                "Tras proteger a otro, la consecutividad se rompe y puede repetir");
    }

    @Test
    public void unMedicoEliminadoDebeMostrarSuCarta(){
        Rol rolMedico = new Medico();
        Jugador medico = new Jugador("medico", rolMedico);

        medico.morir();

        assertEquals( rolMedico, medico.revelarCarta());
    }

    @Test
    public void unMedicoVivoNoDebeMostrarSuCarta(){
        Rol rolMedico = new Medico();
        Jugador medico = new Jugador("medico", rolMedico);

        assertThrows(JugadorVivoExcepcion.class, medico::revelarCarta,
                "Un medico no debería mostrar su carta si está vivo");
    }
}
