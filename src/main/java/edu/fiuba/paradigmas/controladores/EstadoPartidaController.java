package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.historial.*;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.*;
import edu.fiuba.paradigmas.vistas.EstadoPartidaVista;

import java.util.List;
import java.util.stream.Collectors;

public class EstadoPartidaController {
    private final EstadoPartidaVista vista;
    private final Moderador moderador;
    private final TraductorVisualRol traductorRol;

    public EstadoPartidaController(EstadoPartidaVista vista, Moderador moderador, Memento resultadoPrevio, Runnable accionBoton, String textoBoton) {
        this.vista = vista;
        this.moderador = moderador;
        this.traductorRol = new TraductorVisualRol();

        this.actualizarPantalla(resultadoPrevio);

        ResultadoPartida resultadoPartida = this.moderador.evaluarGanador();

        if (resultadoPartida instanceof VictoriaMafia || resultadoPartida instanceof VictoriaCiudadanos) {
            this.vista.configurarTextoBoton("Ver Resultados Finales");
        } else {
            this.vista.configurarTextoBoton(textoBoton);
            this.vista.alPresionarIniciarRonda(accionBoton);
        }
    }

    private void actualizarPantalla(Memento resultadoPrevio) {
        int ronda = this.moderador.numeroDeRonda();
        this.vista.limpiarTablero(ronda);

        List<Jugador> vivos = this.moderador.jugadoresVivos();
        List<Jugador> eliminados = this.moderador.jugadoresEliminados();

        vivos.forEach(j -> this.vista.agregarJugadorVivo(j.nombre()));

        for (Jugador jugador : eliminados) {
            String nombreRol = this.traductorRol.traducirRolDe(jugador);
            String archivoImagen = nombreRol.toLowerCase() + ".png";
            this.vista.agregarJugadorEliminado(jugador.nombre(), nombreRol, archivoImagen);
        }

        if (resultadoPrevio == null) {
            return;
        }

        if (resultadoPrevio instanceof MementoDeNoche) {
            MementoDeNoche noche = (MementoDeNoche) resultadoPrevio;
            this.vista.mostrarResultadoFaseAnterior(this.describirNoche(noche.contenido()));
        } else if (resultadoPrevio instanceof MementoDeDia) {
            MementoDeDia dia = (MementoDeDia) resultadoPrevio;
            this.vista.mostrarResultadoFaseAnterior(this.describirDia(dia.contenido()));
        }
    }

    private String describirNoche(Memento contenido) {
        if (contenido instanceof MementoDeEliminacion) {
            MementoDeEliminacion eliminacion =  (MementoDeEliminacion) contenido;
            String nombreRol = this.traductorRol.traducirRolDe(eliminacion.victima());
            return eliminacion.victima().nombre() + " fue asesinado por la mafia. Era " + nombreRol + ".";
        }
        if (contenido instanceof MementoDeProteccion) {
            MementoDeProteccion supervivencia  = (MementoDeProteccion) contenido;
            return "La mafia atacó a " + supervivencia.jugadorProtegido().nombre() + ", pero el médico lo protegió.";
        }
        if (contenido instanceof MementoDeFaseTranquila) {
            return "La noche transcurrió en silencio. Nadie fue eliminado.";
        }
        return "";
    }

    private String describirDia(Memento contenido) {
        if (contenido instanceof MementoDeEliminacion) {
            MementoDeEliminacion eliminacion = (MementoDeEliminacion) contenido;
            String nombreRol = this.traductorRol.traducirRolDe(eliminacion.victima());
            return eliminacion.victima().nombre() + " fue linchado por el pueblo. Era " + nombreRol + ".";
        }
        if (contenido instanceof MementoDeFaseTranquila) {
            return "El pueblo no llegó a un veredicto. Nadie fue eliminado.";
        }
        if (contenido instanceof MementoDeBallotage) {
            MementoDeBallotage ballotage = (MementoDeBallotage) contenido;
            String nombres = ballotage.empatados().stream()
                    .map(Jugador::nombre)
                    .collect(Collectors.joining(" y "));
            return "Empate entre " + nombres + ". ¡Inicia ballotage!";
        }
        return "";
    }
}