package edu.fiuba.paradigmas.vista.victoria;

import edu.fiuba.paradigmas.vista.componentes.resumen.ListaEventosHistorial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class ResumenHistorialVista extends VBox {

    private final Button btnVolver;

    public ResumenHistorialVista(List<String> eventos) {
        this.setStyle("-fx-background-color: #060e17;");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(30, 20, 20, 20));

        Label lblTitulo = new Label("HISTORIAL DE PARTIDAS");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitulo.setStyle("-fx-text-fill: #e2e8f0;");

        Label lblSubtitulo = new Label("Registro oficial de los eventos de la aldea");
        lblSubtitulo.setStyle("-fx-text-fill: #a0aec0; -fx-font-size: 14px;");

        ListaEventosHistorial listaEventos = new ListaEventosHistorial(eventos);

        this.btnVolver = new Button("Volver al Menú Principal");
        this.btnVolver.setPrefWidth(250);
        this.btnVolver.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 10; -fx-padding: 15; -fx-cursor: hand;");

        this.getChildren().addAll(lblTitulo, lblSubtitulo, listaEventos, this.btnVolver);
    }

    public void configurarBotonVolver(Runnable accion) {
        this.btnVolver.setOnAction(e -> accion.run());
    }
}