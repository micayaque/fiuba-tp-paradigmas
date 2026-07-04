package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.vistas.componentes.BotonPersonalizado;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RepartoVista extends VBox {
    private final Label titulo;
    private final Label mensajePrincipal;
    private final Label labelRol;
    private final Button botonAccion;

    public RepartoVista() {
        this.setSpacing(18);
        this.setPadding(new Insets(24));
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #111827, #334155); -fx-min-width: 780px; -fx-min-height: 620px;");

        this.titulo = new Label("Reparto de roles");
        this.titulo.setFont(new Font(28));
        this.titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        this.mensajePrincipal = new Label();
        this.mensajePrincipal.setFont(new Font(18));
        this.mensajePrincipal.setStyle("-fx-text-fill: #e2e8f0; -fx-alignment: center; -fx-text-alignment: center;");

        this.labelRol = new Label();
        this.labelRol.setFont(new Font(32));
        this.labelRol.setStyle("-fx-font-weight: bold;");

        this.botonAccion = new BotonPersonalizado("");
        this.botonAccion.setStyle("-fx-background-color: linear-gradient(#f59e0b, #d97706); -fx-text-fill: #111827; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 12 24; -fx-background-radius: 10;");

        this.getChildren().addAll(this.titulo, this.mensajePrincipal, this.labelRol, this.botonAccion);
    }

    public void mostrarPantallaOculta(String nombreJugador) {
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #0f172a, #1e293b);");
        this.mensajePrincipal.setText("Pasale el dispositivo a:\n" + nombreJugador);
        this.labelRol.setText("???");
        this.labelRol.setVisible(false);
        this.botonAccion.setText("Ver carta");
    }

    public void mostrarRol(String nombreJugador, String nombreRol) {
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #fef3c7, #fde68a);");
        this.mensajePrincipal.setText("Hola " + nombreJugador + ", tu rol es:");
        this.labelRol.setText(nombreRol);
        this.labelRol.setVisible(true);
        this.botonAccion.setText("Ocultar y pasar al siguiente");
    }

    public void alPresionarBoton(Runnable accion) {
        this.botonAccion.setOnAction(e -> accion.run());
    }
}
