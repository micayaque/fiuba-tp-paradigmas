package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.accionFase.EliminarJugador;
import edu.fiuba.paradigmas.modelo.accionFase.FaseSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.accionFase.IniciarBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.*;
import edu.fiuba.paradigmas.vistas.EstadoPartidaVista;

import java.util.List;

public class EstadoPartidaController {
    private final EstadoPartidaVista vista;
    private final Moderador moderador;
    private final TraductorVisualRol traductorRol;

    public EstadoPartidaController(EstadoPartidaVista vista, Moderador moderador, AccionFase resultadoPrevio, Runnable accionBoton, String textoBoton) {
        this.vista = vista;
        this.moderador = moderador;

        this.traductorRol = new TraductorVisualRol();

        this.actualizarPantalla(resultadoPrevio);

        ResultadoPartida resultadoPartida = this.moderador.evaluarGanador();

        if (resultadoPartida instanceof VictoriaMafia) {
            this.vista.configurarTextoBoton("Ver Resultados Finales");
        } else if (resultadoPartida instanceof VictoriaCiudadanos) {
            this.vista.configurarTextoBoton("Ver Resultados Finales");
        } else {
            this.vista.configurarTextoBoton(textoBoton);
            this.vista.alPresionarIniciarRonda(accionBoton);
        }
    }

    private void actualizarPantalla(AccionFase resultadoPrevio) {
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

        if (resultadoPrevio != null) {

            if (resultadoPrevio instanceof EliminarJugador) {
                EliminarJugador eliminacion = (EliminarJugador) resultadoPrevio;
                Jugador victima = eliminacion.victima();
                if(vivos.contains(victima)) {
                    this.vista.mostrarResultadoFaseAnterior("Nadie fue eliminado." + victima.nombre() + "fue protegido.");
                } else {
                    this.vista.mostrarResultadoFaseAnterior(victima.nombre() + " fue asesinado anoche.");
                }
            } else if (resultadoPrevio instanceof FaseSinJugadorEliminado) {
                this.vista.mostrarResultadoFaseAnterior("La fase transcurrió en silencio. Nadie fue eliminado.");
            } else if (resultadoPrevio instanceof IniciarBallotage) {
                IniciarBallotage empate = (IniciarBallotage) resultadoPrevio;
                String n1 = empate.empatados().get(0).nombre();
                String n2 = empate.empatados().get(1).nombre();
                this.vista.mostrarResultadoFaseAnterior("Empate entre " + n1 + " y " + n2 + ". ¡Inicia ballotage!");
            }
        }
    }
}