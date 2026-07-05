package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.PartidaEnCurso;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import edu.fiuba.paradigmas.vistas.EstadoPartidaVista;
import edu.fiuba.paradigmas.vistas.FaseDiurnaVista;
import edu.fiuba.paradigmas.vistas.FaseNocturnaVista;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControladorDeJuego {

    private final Stage stage;
    private final Moderador moderador;

    public ControladorDeJuego(Stage stage, Moderador moderador) {
        this.stage = stage;
        this.moderador = moderador;
    }

    public void iniciarJuego() {
        // Llamar a Fase de Apertura
        this.evaluarSiguientePaso();
    }

    public void evaluarSiguientePaso() {
        ResultadoPartida resultado = this.moderador.evaluarGanador();
        if (resultado instanceof PartidaEnCurso) {
            if (this.moderador.obtenerFaseActual() instanceof FaseNocturna) {
                this.irAFaseNocturna();
            } else if (this.moderador.obtenerFaseActual() instanceof FaseDiurna) {
                this.irAFaseDiurna();
            }
        } else {
            this.irAPantallaDeVictoria(resultado);
        }
    }

    public void irAFaseNocturna() {
        FaseNocturnaVista vista = new FaseNocturnaVista();
        new FaseNocturnaController(vista, this, this.moderador);
        this.cambiarEscena(vista);
    }

    public void irAFaseDiurna() {
        FaseDiurnaVista vista = new FaseDiurnaVista();
        new FaseDiurnaController(vista, this, this.moderador);
        this.cambiarEscena(vista);
    }

    public void irAEstadoPartidaPreDia(ResultadoFase resultadoNoche) {
        EstadoPartidaVista vista = new EstadoPartidaVista();
        new EstadoPartidaController(vista, this.moderador, resultadoNoche, () -> {
            this.evaluarSiguientePaso();
        }, "Continuar al Día");
        this.cambiarEscena(vista);
    }

    public void irAEstadoPartidaPreNoche(ResultadoFase resultadoDia) {
        EstadoPartidaVista vista = new EstadoPartidaVista();
        new EstadoPartidaController(vista, this.moderador, resultadoDia, () -> {
            this.evaluarSiguientePaso();
        }, "Que caiga la noche");
        this.cambiarEscena(vista);
    }

    private void irAPantallaDeVictoria(ResultadoPartida resultado) {
        EstadoPartidaVista vista = new EstadoPartidaVista();
        String mensajeVictoria = "¡El juego ha terminado!";
        new EstadoPartidaController(vista, this.moderador, null, () -> {
            System.exit(0);
        }, "Cerrar Juego");
        this.cambiarEscena(vista);
    }

    private void cambiarEscena(javafx.scene.Parent nuevaVista) {
        Scene escenaActual = this.stage.getScene();
        if (escenaActual != null) {
            escenaActual.setRoot(nuevaVista);
        } else {
            this.stage.setScene(new Scene(nuevaVista));
        }
    }
}