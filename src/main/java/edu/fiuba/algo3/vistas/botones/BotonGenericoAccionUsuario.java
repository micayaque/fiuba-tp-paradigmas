package edu.fiuba.algo3.vistas.botones;

import edu.fiuba.algo3.controllers.ReproductorEfectos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class BotonGenericoAccionUsuario extends Button {

    private ReproductorEfectos sfx = new ReproductorEfectos();

    public BotonGenericoAccionUsuario(EventHandler<ActionEvent> controlador, String texto) {
        super(texto);

        // Estilo Rojo Catan (Consistente con Terminar Turno)
        this.setPrefSize(100, 50);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setWrapText(true);
        this.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        this.setTextFill(Color.WHITE);

        String estiloNormal = "-fx-background-color: #d9534f;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #c9302c;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 10;";

        this.setStyle(estiloNormal);

        DropShadow sombra = new DropShadow();
        sombra.setColor(Color.rgb(0,0,0,0.3));
        this.setEffect(sombra);
        this.setCursor(Cursor.HAND);

        this.setOnAction(e -> {
            if (!this.isDisabled()) {
                sfx.reproducirClick();
                controlador.handle(e);
            }
        });

        // Hover
        this.setOnMouseEntered(e -> {
            if (!this.isDisabled()) this.setStyle("-fx-background-color: #e27c79; -fx-background-radius: 10; -fx-border-color: white; -fx-border-radius: 10;");
        });
        this.setOnMouseExited(e -> {
            if (!this.isDisabled()) this.setStyle(estiloNormal);
        });
    }

}
