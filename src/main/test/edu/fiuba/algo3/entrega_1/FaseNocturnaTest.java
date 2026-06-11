package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.VictimaInvalida;
import edu.fiuba.algo3.modelo.fases.FaseNocturna;
import edu.fiuba.algo3.modelo.fases.ResultadoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

public class FaseNocturnaTest {

    private List<Jugador> jugadores;
    private List<Jugador> mafiosos;
    private List<Jugador> ciudadanos;
    private FaseNocturna faseNocturna;

    @BeforeEach
    public void setUp() {

        this.jugadores = JugadorTestFactory.crearJugadores(12);
        this.mafiosos = jugadores.stream()
                                .filter(j -> j.rol().getClass().equals(Mafioso.class))
                                .collect(Collectors.toList());
        this.ciudadanos = jugadores.stream()
                                   .filter(j -> j.rol().getClass().equals(Ciudadano.class))
                                   .collect(Collectors.toList());
            faseNocturna = new FaseNocturna(jugadores);
    }

    @Test
    public void laMafiaSeleccionaUnaVictimaValidaDuranteLaFaseNocturna() {

        Jugador victimaEsperada = this.ciudadanos.get(0);
        jugadores.remove(victimaEsperada);
        jugadores.add(0, victimaEsperada);

        ResultadoNocturno resultado = faseNocturna.ejecutar();

        Jugador victima = resultado.victimaElegida();

        assertNotNull(victima, "La mafia debería haber elegido a una víctima");
        assertTrue(victima.estaVivo(), "La víctima elegida debería ser un jugador vivo y eliminarse al finalizar la fase nocturna");

        for (Jugador mafioso : mafiosos) {
            assertNotEquals(mafioso, victima, "La mafia no puede elegir a otro mafioso como víctima");
        }
    }

    @Test
    public void laMafiaNoPuedeElegirUnaVictimaYaEliminada() {
        
        jugadores.forEach(j -> j.eliminar());
        FaseNocturna faseNocturnaJugadoresEliminados = new FaseNocturna(jugadores);

        assertThrows(VictimaInvalida.class, () -> { faseNocturnaJugadoresEliminados.ejecutar();}, "No se puede elegir a un jugador muerto");
    }

    @Test
    public void laMafiaNoPuedeElegirAOtroMiembroDeLaMafiaComoVictima() {

        FaseNocturna faseNocturnaSoloMafiosos = new FaseNocturna(mafiosos);

        assertThrows(VictimaInvalida.class, () -> { faseNocturnaSoloMafiosos.ejecutar();}, "No se puede elegir a un jugador mafioso");
    }

    @Test
    public void siElMedicoProtegeALaVictimaLaEliminacionSeAnulaYSigueViva() {
        Jugador victimaEsperada = ciudadanos.get(0);
        jugadores.remove(victimaEsperada);
        jugadores.add(0, victimaEsperada);
        Jugador protegidoEsperado = victimaEsperada;
        jugadores.add(1, protegidoEsperado);

        ResultadoNocturno resultado = faseNocturna.ejecutar();

        if(resultado.huboEliminacion()) {
            resultado.victimaElegida().eliminar();
        }
        
        assertFalse(resultado.huboEliminacion(), "El resultado debe indicar que la eliminación fue anulada");
        assertTrue(victimaEsperada.estaVivo(), "El ciudadano debe seguir vivo gracias a la protección del médico");
    }

    @Test
    public void siLaVictimaNoEstaProtegidaEsEliminadaAlTerminarLaNoche() {
        Jugador protegidoPorMedico = mafiosos.get(0);
        Jugador victimaDeLaMafia = ciudadanos.get(0);

        jugadores.remove(protegidoPorMedico);
        jugadores.remove(victimaDeLaMafia);
        
        jugadores.add(1, protegidoPorMedico);
        jugadores.add(0, victimaDeLaMafia);

        ResultadoNocturno resultado = faseNocturna.ejecutar();

        if (resultado.huboEliminacion()) {
            resultado.victimaElegida().eliminar();
        }

        assertFalse(victimaDeLaMafia.estaVivo(), "El ciudadano atacado debe estar en estado eliminado");
    }

}