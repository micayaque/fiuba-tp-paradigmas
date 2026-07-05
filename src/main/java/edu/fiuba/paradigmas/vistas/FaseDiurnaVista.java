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

public class FaseDiurnaVista extends StackPane {

    private final SelectorDeJugador selectorJugadores = new SelectorDeJugador();
    private final VBox panelAccion;
    private final PanelDeTransicionDeTurno panelDeTansicionDeTurno;

    private final Label lblTitulo;
    private final Label lblInstruccion;
    private final Button btnAccion;
    private final Label lblMensaje;

    public FaseDiurnaVista() {
        this.setStyle("-fx-background-color: #ecf0f1;");

        this.panelAccion = new VBox(30);
        this.panelAccion.setAlignment(Pos.CENTER);
        this.panelAccion.setPadding(new Insets(50));
        this.panelAccion.setVisible(false);

        this.lblTitulo = new Label();
        this.lblTitulo.setFont(new Font("Georgia", 36));
        this.lblTitulo.setStyle("-fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        this.lblInstruccion = new Label("¿A quién querés votar para eliminar del pueblo?");
        this.lblInstruccion.setFont(new Font("Georgia", 20));
        this.lblInstruccion.setStyle("-fx-text-fill: #34495e;");

        this.btnAccion = new Button("Confirmar Voto");
        this.btnAccion.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px; -fx-padding: 15 30; -fx-cursor: hand;");

        this.lblMensaje = new Label();
        this.lblMensaje.setFont(new Font("Georgia", 16));
        this.lblMensaje.setStyle("-fx-text-fill: #c0392b; -fx-font-weight: bold;");
        this.lblMensaje.setVisible(false);

        this.panelAccion.getChildren().addAll(
                this.lblTitulo, this.lblInstruccion, this.selectorJugadores, this.btnAccion, this.lblMensaje
        );

        this.panelDeTansicionDeTurno = new PanelDeTransicionDeTurno();
        this.panelDeTansicionDeTurno.setTextoBoton("Comenzar turno diurno");

        this.panelDeTansicionDeTurno.alPresionarVerCarta(() -> {
            this.panelDeTansicionDeTurno.setVisible(false);
            this.panelAccion.setVisible(true);
        });

        this.getChildren().addAll(this.panelAccion, this.panelDeTansicionDeTurno);
    }

    public void iniciarTurnoDe(String nombreJugador) {
        this.panelDeTansicionDeTurno.setNombreJugador(nombreJugador);
        this.panelDeTansicionDeTurno.setVisible(true);
        this.panelAccion.setVisible(false);
        this.lblMensaje.setVisible(false);
    }

    public void setTitulo(String texto) {
        this.lblTitulo.setText(texto);
    }

    public void cargarOpciones(List<Jugador> opciones) {
        this.selectorJugadores.cargarOpciones(opciones);
    }

    public Jugador obtenerJugadorSeleccionado() {
        return this.selectorJugadores.jugadorSeleccionado();
    }

    public void mostrarMensaje(String mensaje) {
        this.lblMensaje.setText(mensaje);
        this.lblMensaje.setVisible(true);
    }

    public void configurarBotonVotar(Runnable accion) {
        this.btnAccion.setOnAction(evento -> {
            if (this.obtenerJugadorSeleccionado() != null) {
                accion.run();
            } else {
                this.mostrarMensaje("Por favor, elegí un jugador antes de votar.");
            }
        });
    }
}