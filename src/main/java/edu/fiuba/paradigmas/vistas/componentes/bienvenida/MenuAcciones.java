package edu.fiuba.paradigmas.vistas.componentes.bienvenida;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuAcciones extends VBox {

    private final Button btnComenzar;

    public MenuAcciones() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        this.btnComenzar = new Button("☾ JUGAR");
        this.btnComenzar.setPrefWidth(280);
        this.btnComenzar.setStyle("-fx-background-color: linear-gradient(to right, #2b6cb0, #4299e1); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 10; -fx-padding: 15; -fx-cursor: hand;");

        Button btnComoJugar = new Button("Cómo jugar");
        btnComoJugar.setPrefWidth(200);
        btnComoJugar.setStyle("-fx-background-color: transparent; -fx-border-color: #2b6cb0; -fx-border-radius: 10; -fx-text-fill: #a0aec0; -fx-font-size: 12px; -fx-padding: 10; -fx-cursor: hand;");

        this.getChildren().addAll(this.btnComenzar, btnComoJugar);
    }

    public void configurarBotonPrincipal(Runnable accion) {
        this.btnComenzar.setOnAction(e -> accion.run());
    }
}