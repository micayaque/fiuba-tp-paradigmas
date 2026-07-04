package edu.fiuba.paradigmas.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EstadoPartidaVista extends VBox {
    private final Label titulo;
    private final Label ronda;
    private final Label fase;
    private final Label resultado;

    public EstadoPartidaVista() {
        this.setSpacing(16);
        this.setPadding(new Insets(24));
        this.setAlignment(Pos.CENTER_LEFT);
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8fafc, #e2e8f0); -fx-min-width: 780px; -fx-min-height: 520px;");

        this.titulo = new Label("Estado actual de la partida");
        this.titulo.setFont(new Font(26));
        this.titulo.setStyle("-fx-text-fill: #0f172a; -fx-font-weight: bold;");

        this.ronda = new Label();
        this.ronda.setFont(new Font(18));
        this.ronda.setStyle("-fx-text-fill: #1f2937; -fx-background-color: white; -fx-padding: 12 16; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #cbd5e1;");

        this.fase = new Label();
        this.fase.setFont(new Font(18));
        this.fase.setStyle("-fx-text-fill: #1f2937; -fx-background-color: white; -fx-padding: 12 16; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #cbd5e1;");

        this.resultado = new Label();
        this.resultado.setFont(new Font(18));
        this.resultado.setStyle("-fx-text-fill: #0f172a; -fx-font-weight: bold; -fx-background-color: #dbeafe; -fx-padding: 12 16; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #93c5fd;");

        this.getChildren().addAll(this.titulo, this.ronda, this.fase, this.resultado);
    }
    
    public void mostrarEstado(int numeroDeRonda, String descripcionFase) {
        this.ronda.setText("Ronda actual: " + numeroDeRonda);
        this.fase.setText("Fase activa: " + descripcionFase);
        this.resultado.setText("La partida continúa. Siguiente paso según la fase actual.");
    }

    public void mostrarGanador(String bando) {
        this.resultado.setText("Ganó " + bando);
        this.resultado.setStyle("-fx-text-fill: #0f172a; -fx-font-weight: bold; -fx-background-color: #bbf7d0; -fx-padding: 12 16; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #86efac;");
    }
    
}