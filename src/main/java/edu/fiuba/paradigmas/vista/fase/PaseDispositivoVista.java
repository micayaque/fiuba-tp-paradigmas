package edu.fiuba.paradigmas.vista.fase;

import edu.fiuba.paradigmas.vista.componentes.BotonTurno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PaseDispositivoVista extends StackPane {

    private final BotonTurno btnAvanzar;

    public PaseDispositivoVista(String nombreJugador) {
        this.setStyle("-fx-background-color: #060e17;");

        VBox contenedorPrincipal = new VBox(20);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setPadding(new Insets(30, 20, 20, 20));

        VBox espacioFantasma = new VBox();
        espacioFantasma.setMinHeight(50);

        VBox contenedorCandado = new VBox(20);
        contenedorCandado.setAlignment(Pos.CENTER);
        contenedorCandado.setMinHeight(380);

        Label lblIcono = new Label("🔒");
        lblIcono.setStyle("-fx-font-size: 60px; -fx-text-fill: #4a6274;");

        Label lblPrompt = new Label("Pase el dispositivo a ");
        lblPrompt.setStyle("-fx-text-fill: #a0aec0; -fx-font-size: 18px;");

        Label lblNombre = new Label(nombreJugador);
        lblNombre.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        lblNombre.setStyle("-fx-text-fill: white;");

        contenedorCandado.getChildren().addAll(lblIcono, lblPrompt, lblNombre);

        this.btnAvanzar = new BotonTurno("SOY " + nombreJugador.toUpperCase(), 250);

        contenedorPrincipal.getChildren().addAll(espacioFantasma, contenedorCandado, this.btnAvanzar);
        this.getChildren().add(contenedorPrincipal);
    }

    public void configurarBotonAvanzar(Runnable accion) {
        this.btnAvanzar.setOnAction(e -> accion.run());
    }
}