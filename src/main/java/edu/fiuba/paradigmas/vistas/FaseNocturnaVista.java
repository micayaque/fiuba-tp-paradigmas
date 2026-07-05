package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.vistas.componentes.PanelDeTransicionDeTurno;
import edu.fiuba.paradigmas.vistas.componentes.SelectorDeJugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class FaseNocturnaVista extends StackPane {

    private final SelectorDeJugador selectorJugadores = new SelectorDeJugador();

    private final VBox panelAccion;
    private final PanelDeTransicionDeTurno panelOcultamiento;

    private final Label lblTitulo;
    private final Label lblInstruccion;
    private final Label lblResultado;
    private final Button btnAccion;

    public FaseNocturnaVista() {
        this.setStyle("-fx-background-color: #1a1a1a;");

        this.panelAccion = new VBox(25);
        this.panelAccion.setAlignment(Pos.CENTER);
        this.panelAccion.setPadding(new Insets(50));
        this.panelAccion.setVisible(false);

        this.lblTitulo = new Label();
        this.lblTitulo.setFont(new Font("Georgia", 32));
        this.lblTitulo.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");

        this.lblInstruccion = new Label();
        this.lblInstruccion.setFont(new Font("Georgia", 18));
        this.lblInstruccion.setStyle("-fx-text-fill: #d6cfc2;");

        this.lblResultado = new Label();
        this.lblResultado.setFont(new Font("Georgia", 16));
        this.lblResultado.setStyle("-fx-text-fill: #3498db; -fx-font-style: italic;");
        this.lblResultado.setVisible(false);

        this.btnAccion = new Button();
        this.btnAccion.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 10 20; -fx-cursor: hand;");

        this.panelAccion.getChildren().addAll(
                this.lblTitulo, this.lblInstruccion, this.selectorJugadores, this.btnAccion, this.lblResultado
        );

        this.panelOcultamiento = new PanelDeTransicionDeTurno();
        this.panelOcultamiento.setTextoBoton("Comenzar turno");

        this.panelOcultamiento.alPresionarVerCarta(() -> {
            this.panelOcultamiento.setVisible(false);
            this.panelAccion.setVisible(true);
        });

        this.getChildren().addAll(this.panelAccion, this.panelOcultamiento);
    }

    public void iniciarTurnoOcultoDe(String nombreJugador) {
        this.panelOcultamiento.setNombreJugador(nombreJugador);
        this.panelOcultamiento.setVisible(true);
        this.panelAccion.setVisible(false);
        this.lblResultado.setVisible(false);

        this.selectorJugadores.setVisible(true);
        this.selectorJugadores.setManaged(true);
    }

    public void ocultarSelector() {
        this.selectorJugadores.setVisible(false);
        this.selectorJugadores.setManaged(false);
    }

    public void setTitulo(String texto) {
        this.lblTitulo.setText(texto);
        this.lblResultado.setVisible(false);
    }

    public void setInstruccion(String texto) {
        this.lblInstruccion.setText(texto);
    }

    public void mostrarMensaje(String mensaje) {
        this.lblResultado.setText(mensaje);
        this.lblResultado.setVisible(true);
    }

    public void cargarOpciones(List<Jugador> opciones) {
        this.selectorJugadores.cargarOpciones(opciones);
    }

    public Jugador obtenerJugadorSeleccionado() {
        return this.selectorJugadores.jugadorSeleccionado();
    }

    public void configurarBotonConValidacionDeSeleccion(String texto, Runnable accion) {
        this.btnAccion.setText(texto);
        this.btnAccion.setOnAction(evento -> {
            if (this.obtenerJugadorSeleccionado() != null) {
                accion.run();
            } else {
                this.mostrarMensaje("Por favor, elegí un jugador antes de continuar.");
            }
        });
    }

    public void configurarBotonLibre(String texto, Runnable accion) {
        this.btnAccion.setText(texto);
        this.btnAccion.setOnAction(evento -> accion.run());
    }
}