package edu.fiuba.paradigmas.vista.fase.apertura;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RevelarRolVista extends StackPane {

    private final Button btnAvanzar;

    public RevelarRolVista(String nombreJugador, String nombreRol, String textoComplices, String colorTema) {
        this.setStyle("-fx-background-color: #060e17;");

        VBox contenedorPrincipal = new VBox(30);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.setPadding(new Insets(20));

        Label lblPildora = new Label("📱 " + nombreJugador);
        lblPildora.setStyle("-fx-background-color: #1a2a40; -fx-text-fill: #a0aec0; -fx-padding: 5 15; -fx-background-radius: 15;");

        VBox tarjeta = new VBox(20);
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setStyle("-fx-background-color: transparent; -fx-border-color: #3182ce; -fx-border-radius: 15; -fx-border-width: 2; -fx-padding: 30;");
        tarjeta.setMaxWidth(350);

        tarjeta.setMinHeight(400);

        Label lblYouAre = new Label("SOS");
        lblYouAre.setStyle("-fx-text-fill: #a0aec0; -fx-font-size: 16px;");

        Label lblRol = new Label(nombreRol);
        lblRol.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        lblRol.setStyle("-fx-text-fill: white;");

        VBox cajaComplices = new VBox(5);
        cajaComplices.setAlignment(Pos.CENTER);
        if (textoComplices != null && !textoComplices.isEmpty()) {
            Label lblTituloComplices = new Label("Tus cómplices son:");
            lblTituloComplices.setStyle("-fx-text-fill: " + colorTema + "; -fx-font-weight: bold; -fx-font-size: 16px;");

            Label lblNombresComplices = new Label(textoComplices);
            lblNombresComplices.setStyle("-fx-text-fill: " + colorTema + "; -fx-font-weight: bold; -fx-font-size: 16px;");

            cajaComplices.getChildren().addAll(lblTituloComplices, lblNombresComplices);
        }

        Label lblAdvertencia = new Label("⚠ ¡Memoriza tu rol! \n No podes volver a revisarlo.");
        lblAdvertencia.setWrapText(true);
        lblAdvertencia.setStyle("-fx-background-color: #4a401c; -fx-text-fill: #eab308; -fx-padding: 15; -fx-background-radius: 10; -fx-font-size: 14px;");

        tarjeta.getChildren().addAll(lblYouAre, lblRol, cajaComplices, lblAdvertencia);

        this.btnAvanzar = new Button("✔ ROL MEMORIZADO");
        this.btnAvanzar.setPrefWidth(250);
        this.btnAvanzar.setStyle("-fx-background-color: #3182ce; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 10; " +
                "-fx-padding: 15; " +
                "-fx-cursor: hand;");
        contenedorPrincipal.getChildren().addAll(lblPildora, tarjeta, this.btnAvanzar);
        this.getChildren().add(contenedorPrincipal);
    }

    public void configurarBotonAvanzar(Runnable accion) {
        this.btnAvanzar.setOnAction(e -> accion.run());
    }
}