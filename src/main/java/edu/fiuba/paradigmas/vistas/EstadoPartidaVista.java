package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.modelo.Fase;
import edu.fiuba.paradigmas.modelo.fasediurna.FaseDiurna;
import edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EstadoPartidaVista extends VBox {
    private final Label titulo;
    private final Label ronda;
    private final Label fase;

    public EstadoPartidaVista() {
        this.titulo = new Label("Estado actual de la partida");
        this.titulo.setFont(new Font(22));

        this.ronda = new Label();
        this.ronda.setFont(new Font(18));

        this.fase = new Label();
        this.fase.setFont(new Font(18));

        this.setStyle("-fx-padding: 20; -fx-spacing: 12; -fx-background-color: #f5f5f5;");
        this.getChildren().addAll(this.titulo, this.ronda, this.fase);
    }

    public void mostrarEstado(int numeroDeRonda, Fase faseActual) {
        this.ronda.setText("Ronda actual: " + numeroDeRonda);
        this.fase.setText("Fase activa: " + this.descripcionDeFase(faseActual));
    }

    private String descripcionDeFase(Fase faseActual) {
        if (faseActual instanceof FaseDiurna) {
            return "Fase Diurna";
        }
        if (faseActual instanceof FaseNocturna) {
            return "Fase Nocturna";
        }
        return faseActual.getClass().getSimpleName();
    }
}