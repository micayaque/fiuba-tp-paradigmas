package edu.fiuba.paradigmas.vista.componentes.configuracion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class PanelIngresoNombres extends ScrollPane {

    private final List<FilaIngresoNombre> filasDeInputs;

    public PanelIngresoNombres(int cantidadJugadores, Runnable alActualizarFila) {
        this.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
        this.setFitToWidth(true);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        this.filasDeInputs = new ArrayList<>();

        VBox contenedorInputs = new VBox(15);
        contenedorInputs.setAlignment(Pos.TOP_CENTER);
        contenedorInputs.setPadding(new Insets(10, 30, 10, 30));
        contenedorInputs.setStyle("-fx-background-color: transparent;");

        for (int i = 0; i < cantidadJugadores; i++) {
            FilaIngresoNombre fila = new FilaIngresoNombre(i + 1, alActualizarFila);
            this.filasDeInputs.add(fila);
            contenedorInputs.getChildren().add(fila);
        }
        this.setContent(contenedorInputs);
    }

    public int getCantidadCompletados() {
        int completados = 0;
        for (FilaIngresoNombre fila : this.filasDeInputs) {
            if (fila.estaCompleto()) completados++;
        }
        return completados;
    }

    public boolean hayDuplicados() {
        List<String> nombres = new ArrayList<>();
        for (FilaIngresoNombre fila : this.filasDeInputs) {
            String nombre = fila.getTexto();
            if (nombre.trim().isEmpty()) continue;
            for (String nombreGuardado : nombres) {
                if (nombreGuardado.equalsIgnoreCase(nombre)) {
                    return true;
                }
            }
            nombres.add(nombre);
        }
        return false;
    }

    public List<String> getNombres() {
        List<String> nombres = new ArrayList<>();
        for (FilaIngresoNombre fila : this.filasDeInputs) {
            nombres.add(fila.getTexto());
        }
        return nombres;
    }
}