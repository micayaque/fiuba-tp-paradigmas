package edu.fiuba.paradigmas.vistas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RepartoVista extends VBox {
    private final Label mensajePrincipal;
    private final Label labelRol;
    private final Button botonAccion;

    public RepartoVista() {
        this.mensajePrincipal = new Label();
        this.mensajePrincipal.setFont(new Font(18));

        this.labelRol = new Label();
        this.labelRol.setFont(new Font(24));
        this.labelRol.setStyle("-fx-font-weight: bold;");


        this.botonAccion = new Button();
        this.botonAccion.setStyle("-fx-font-size: 16px; -fx-padding: 10 20");

        this.getChildren().addAll(this.mensajePrincipal, this.labelRol, this.botonAccion);
    }

    public void mostrarPantallaOculta(String nombreJugador){
        this.setStyle("-fx-background-color: white;");
        this.mensajePrincipal.setText("Pasale el dispositivo a:\n"  + nombreJugador);
        this.labelRol.setText("???");
        this.labelRol.setVisible(false);
        this.botonAccion.setText("Ver carta");
    }

    public void mostrarRol(String nombreJugador, String nombreRol) {
        this.setStyle("-fx-background-color: #ffeaa7;");
        this.mensajePrincipal.setText("Hola " + nombreJugador + ", tu rol es:");
        this.labelRol.setText(nombreRol);
        this.labelRol.setVisible(true);
        this.botonAccion.setText("Ocultar y pasar al siguiente");
    }

    public void alPresionarBoton(Runnable accion){
        this.botonAccion.setOnAction(e -> accion.run());
    }
}
