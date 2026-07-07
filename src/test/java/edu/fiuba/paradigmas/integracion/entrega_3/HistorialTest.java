package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.bando.Mafia;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.historial.*;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.rol.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistorialTest {

    @Test
    public void elHistorialGuardaLaSecuenciaCompletaDeEliminacionesConSusContextosTemporales() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador victimaNocturna = new Jugador("victima de la mafia", new Ciudadano());
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> jugadores = List.of(mafioso, victimaNocturna, ciudadano);
        Moderador moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion());

        moderador.comenzarFaseNocturna();
        moderador.registrarVoto(mafioso, victimaNocturna);
        moderador.resolverVotacion();

        moderador.avanzarFase();
        moderador.registrarVoto(ciudadano, mafioso);
        moderador.resolverVotacion();

        List<Memento> eventosGuardados = moderador.historialDePartida().mementos();

        assertEquals(2, eventosGuardados.size(), "Deberían haberse guardado exactamente 2 eventos en el historial.");

        Memento evento1 = eventosGuardados.get(0);
        assertTrue(evento1 instanceof MementoDeNoche, "El primer evento debe estar envuelto en un contexto nocturno.");

        MementoDeNoche mementoNoche = (MementoDeNoche) evento1;
        assertTrue(mementoNoche.contenido() instanceof MementoDeEliminacion, "El contenido de la noche debe ser un asesinato.");

        MementoDeEliminacion asesinato = (MementoDeEliminacion) mementoNoche.contenido();
        assertEquals(victimaNocturna, asesinato.victima(), "La víctima nocturna registrada debe coincidir con la atacada.");

        Memento evento2 = eventosGuardados.get(1);
        assertTrue(evento2 instanceof MementoDeDia, "El segundo evento debe estar envuelto en un contexto diurno.");

        MementoDeDia mementoDia = (MementoDeDia) evento2;
        assertTrue(mementoDia.contenido() instanceof MementoDeEliminacion, "El contenido del día debe ser una eliminación.");

        MementoDeEliminacion eliminacion = (MementoDeEliminacion) mementoDia.contenido();
        assertEquals(mafioso, eliminacion.victima(), "La víctima de la eliminación diurna debe coincidir con el mafioso.");
    }

    @Test
    public void elHistorialGuardaLaInvestigacionDelDetective() {
        Jugador detective = new Jugador("detective", new Detective());
        Jugador sospechoso = new Jugador("investigado mafioso", new Mafioso());

        Moderador moderador = new Moderador(List.of(detective, sospechoso), new EmpateDiurnoSinEliminacion());
        moderador.comenzarFaseNocturna();
        moderador.registrarInvestigacion(detective, sospechoso);

        List<Memento> eventosGuardados = moderador.historialDePartida().mementos();
        assertEquals(1, eventosGuardados.size(), "Debe guardarse 1 evento tras la investigación");

        Memento evento = eventosGuardados.get(0);
        assertTrue(evento instanceof MementoDeNoche, "El evento debe ser nocturno");

        MementoDeNoche eventoNoche = (MementoDeNoche) evento;
        assertTrue(eventoNoche.contenido() instanceof MementoDeInvestigacion, "El evento debe ser una investigación");

        assertEquals(sospechoso, ((MementoDeInvestigacion) eventoNoche.contenido()).sospechoso());
        assertTrue(((MementoDeInvestigacion) eventoNoche.contenido()).bandoDescubierto() instanceof Mafia);
    }

    @Test
    public void elHistorialGuardaLaRevelacionDelSheriff() {
        Jugador sheriff = new Jugador("sheriff", new Sheriff());

        Moderador moderador = new Moderador(List.of(sheriff), new EmpateDiurnoSinEliminacion());
        moderador.comenzarFaseDiurna();
        moderador.registrarRevelacionDeSheriff(sheriff);

        List<Memento> eventosGuardados = moderador.historialDePartida().mementos();
        assertEquals(1, eventosGuardados.size());

        Memento evento = eventosGuardados.get(0);
        assertTrue(evento instanceof MementoDeDia);

        MementoDeDia eventoDia = (MementoDeDia) evento;
        assertTrue(eventoDia.contenido() instanceof MementoDeRevelacionDeSheriff, "El evento debe ser una revelación del sheriff");

        assertEquals(sheriff, ((MementoDeRevelacionDeSheriff) eventoDia.contenido()).sheriff());
    }

    @Test
    public void elHistorialGuardaLaProteccionDelMedico() {
        Jugador medico = new Jugador("médico", new Medico());
        Jugador protegido = new Jugador("ciudadano", new Ciudadano());

        Moderador moderador = new Moderador(List.of(medico, protegido), new EmpateDiurnoSinEliminacion());
        moderador.comenzarFaseNocturna();
        moderador.registrarProteccion(medico, protegido);

        List<Memento> eventosGuardados = moderador.historialDePartida().mementos();
        assertEquals(1, eventosGuardados.size());

        Memento evento = eventosGuardados.get(0);
        assertTrue(evento instanceof MementoDeNoche, "El evento debe ser nocturno");

        MementoDeNoche eventoNoche = (MementoDeNoche) evento;
        assertTrue(eventoNoche.contenido() instanceof MementoDeProteccion, "El evento debe ser una protección");

        assertEquals(protegido, ((MementoDeProteccion) eventoNoche.contenido()).jugadorProtegido());
    }
}