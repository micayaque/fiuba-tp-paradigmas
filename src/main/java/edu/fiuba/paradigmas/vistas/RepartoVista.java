package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.vistas.componentes.PanelDeOcultamiento;
import edu.fiuba.paradigmas.vistas.componentes.PanelDeRevelacion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RepartoVista extends VBox {
    private final Label lblProgreso;
    private final PanelDeOcultamiento panelOcultamiento;
    private final PanelDeRevelacion panelRevelacion;

    public RepartoVista() {
        this.setSpacing(40);
        this.setPadding(new Insets(40));
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #0a0a0a;");
        this.lblProgreso = new Label("Repartiendo cartas...");
        this.lblProgreso.setFont(new Font("Georgia", 18));
        this.lblProgreso.setStyle("-fx-text-fill: #888888;");
        this.panelOcultamiento = new PanelDeOcultamiento();
        this.panelRevelacion = new PanelDeRevelacion();
        this.panelRevelacion.setVisible(false);
        this.panelOcultamiento.setVisible(true);

        StackPane areaDinamica = new StackPane(this.panelOcultamiento, this.panelRevelacion);
        this.getChildren().addAll(this.lblProgreso, areaDinamica);
    }

    public void actualizarProgreso(int actual, int total, String nombreJugador) {
        this.lblProgreso.setText("Repartiendo cartas... (" + actual + " de " + total + ")");
        this.panelOcultamiento.setNombreJugador(nombreJugador);
    }

    public void mostrarCarta(String archivoImagen, String descripcion) {
        this.panelRevelacion.setRol(archivoImagen, descripcion);
        this.panelOcultamiento.setVisible(false);
        this.panelRevelacion.setVisible(true);
    }

    public void ocultarCarta() {
        this.panelRevelacion.setVisible(false);
        this.panelOcultamiento.setVisible(true);
    }

    public void alPresionarVerCarta(Runnable accion) {
        this.panelOcultamiento.alPresionarVerCarta(accion);
    }

    public void alPresionarOcultarCarta(Runnable accion) {
        this.panelRevelacion.alPresionarOcultarCarta(accion);
    }
}