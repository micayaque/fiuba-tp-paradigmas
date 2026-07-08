package edu.fiuba.paradigmas.vista.componentes.configuracion;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PanelSeleccionRoles extends VBox {

    private final FilaContadorRol filaCiudadano;
    private final FilaContadorRol filaMafioso;
    private final FilaContadorRol filaPadrino;
    private final FilaContadorRol filaSheriff;
    private final FilaContadorRol filaMedico;
    private final FilaContadorRol filaDetective;

    public PanelSeleccionRoles(Runnable alActualizarFila) {
        this.setStyle("-fx-background-color: transparent;");
        this.setSpacing(10);
        this.setPadding(new Insets(10, 30, 10, 30));

        this.filaCiudadano = new FilaContadorRol("Ciudadano", false);
        this.filaMafioso = new FilaContadorRol("Mafioso", false);
        this.filaPadrino = new FilaContadorRol("Padrino", true);
        this.filaSheriff = new FilaContadorRol("Sheriff", true);
        this.filaMedico = new FilaContadorRol("Médico", true);
        this.filaDetective = new FilaContadorRol("Detective", true);

        this.filaCiudadano.configurarAcciones(alActualizarFila, alActualizarFila);
        this.filaMafioso.configurarAcciones(alActualizarFila, alActualizarFila);
        this.filaPadrino.configurarAcciones(alActualizarFila, alActualizarFila);
        this.filaSheriff.configurarAcciones(alActualizarFila, alActualizarFila);
        this.filaMedico.configurarAcciones(alActualizarFila, alActualizarFila);
        this.filaDetective.configurarAcciones(alActualizarFila, alActualizarFila);

        this.getChildren().addAll(
                this.filaCiudadano, this.filaMafioso,
                this.filaPadrino, this.filaSheriff, this.filaMedico, this.filaDetective
        );

        this.getChildren().filtered(n -> n instanceof Label).forEach(n -> n.setStyle("-fx-text-fill: #a0aec0; -fx-padding: 10 0 0 0;"));
    }

    public void actualizarEstadosBotones() {
        this.filaCiudadano.deshabilitarResta(this.filaCiudadano.getCantidad() == 0);
        this.filaMafioso.deshabilitarResta(this.filaMafioso.getCantidad() == 0);
        this.filaPadrino.deshabilitarResta(this.filaPadrino.getCantidad() == 0);
        this.filaSheriff.deshabilitarResta(this.filaSheriff.getCantidad() == 0);
        this.filaMedico.deshabilitarResta(this.filaMedico.getCantidad() == 0);
        this.filaDetective.deshabilitarResta(this.filaDetective.getCantidad() == 0);
    }

    public int getTotalAsignados() {
        return getCantCiudadanos() + getCantMafiosos() + getCantPadrinos() +
                getCantSheriffs() + getCantMedicos() + getCantDetectives();
    }

    public int getCantCiudadanos() { return this.filaCiudadano.getCantidad(); }
    public int getCantMafiosos() { return this.filaMafioso.getCantidad(); }
    public int getCantPadrinos() { return this.filaPadrino.getCantidad(); }
    public int getCantSheriffs() { return this.filaSheriff.getCantidad(); }
    public int getCantMedicos() { return this.filaMedico.getCantidad(); }
    public int getCantDetectives() { return this.filaDetective.getCantidad(); }
}