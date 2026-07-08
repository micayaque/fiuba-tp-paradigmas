package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.bando.Mafia;
import edu.fiuba.paradigmas.modelo.historial.*;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.vista.victoria.ResumenHistorialVista;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResumenHistorialControlador {

    public ResumenHistorialControlador(JuegoControlador orquestador, Moderador moderadorFinalizado) {
        List<Memento> mementosCrudos = moderadorFinalizado.historialDePartida().mementos();
        List<String> eventosTraducidos = procesarMementos(mementosCrudos);
        ResumenHistorialVista vista = new ResumenHistorialVista(eventosTraducidos);
        vista.configurarBotonVolver(orquestador::volverAlMenu);
        orquestador.cambiarEscena(vista);
    }

    private List<String> procesarMementos(List<Memento> mementos) {
        List<String> lineasDeTiempo = new ArrayList<>();
        for (Memento memento : mementos) {
            String linea = this.traducir(memento);
            if (!linea.isEmpty()) {
                lineasDeTiempo.add(linea);
            }
        }
        return lineasDeTiempo;
    }

    private String traducir(Memento memento) {
        if (memento instanceof MementoDeNoche) {
            MementoDeNoche noche = (MementoDeNoche) memento;
            return this.traducirDeNoche(noche.contenido());
        }
        if (memento instanceof MementoDeDia) {
            MementoDeDia dia = (MementoDeDia) memento;
            return this.traducirDeDia(dia.contenido());
        }
        if (memento instanceof MementoDeInvestigacion) {
            MementoDeInvestigacion investigacion = (MementoDeInvestigacion) memento;
            String bando = investigacion.bandoDescubierto() instanceof Mafia ? "MAFIA" : "CIUDADANOS";
            return "🔍 " + "El detective investigó a "
                    + investigacion.sospechoso().nombre() + " (Bando: " + bando + ").";
        }
        if (memento instanceof MementoDeRevelacionDeSheriff) {
            MementoDeRevelacionDeSheriff revelacion = (MementoDeRevelacionDeSheriff) memento;
            return "⭐ " + revelacion.sheriff().nombre() + " se reveló como el Sheriff durante el debate.";
        }
        return "";
    }

    private String traducirDeNoche(Memento contenido) {
        if (contenido instanceof MementoDeEliminacion) {
            MementoDeEliminacion eliminacion = (MementoDeEliminacion) contenido;
            return "💀 " + eliminacion.victima().nombre() + " fue asesinado por la mafia.";
        }
        if (contenido instanceof MementoDeProteccion) {
            MementoDeProteccion supervivencia = (MementoDeProteccion) contenido;
            return "🛡️ La mafia atacó a " + supervivencia.jugadorProtegido().nombre() + ", pero el médico lo protegió.";
        }
        if (contenido instanceof MementoDeFaseTranquila) {
            return "🌙 La noche transcurrió en silencio. Nadie fue eliminado.";
        }
        return "";
    }

    private String traducirDeDia(Memento contenido) {
        if (contenido instanceof MementoDeEliminacion) {
            MementoDeEliminacion eliminacion = (MementoDeEliminacion) contenido;
            return "⚖️ " + eliminacion.victima().nombre() + " fue eliminado por la votación diurna.";
        }
        if (contenido instanceof MementoDeFaseTranquila) {
            return "☀️ El pueblo no llegó a un veredicto. Nadie fue eliminado.";
        }
        if (contenido instanceof MementoDeBallotage) {
            MementoDeBallotage ballotage = (MementoDeBallotage) contenido;
            String nombres = ballotage.empatados().stream()
                    .map(Jugador::nombre)
                    .collect(Collectors.joining(" y "));
            return "🗳️ Empate entre " + nombres + ". Se inició un ballotage.";
        }
        return "";
    }
}