package edu.fiuba.paradigmas.vista.fase.diurna;

import edu.fiuba.paradigmas.vista.componentes.fase.BotonFantasma;
import edu.fiuba.paradigmas.vista.componentes.fase.IconoDiaVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DebateVista extends VBox {

    private final BotonFantasma btnIniciarVotacion;

    public DebateVista() {
        this.setStyle("-fx-background-color: #8fa0b5;");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(40));

        IconoDiaVista graficoSol = new IconoDiaVista();

        Label lblTitulo = new Label("Día");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        lblTitulo.setStyle("-fx-text-fill: white;");

        DropShadow sombraTexto = new DropShadow();
        sombraTexto.setColor(Color.web("#000000", 0.3));
        sombraTexto.setRadius(5);
        sombraTexto.setOffsetY(3);
        lblTitulo.setEffect(sombraTexto);

        Label lblSubtitulo = new Label("La Aldea está despierta. Discutan y decidan.");
        lblSubtitulo.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        this.btnIniciarVotacion = new BotonFantasma("Iniciar votación");
        VBox.setMargin(this.btnIniciarVotacion, new Insets(50, 0, 0, 0));

        this.getChildren().addAll(graficoSol, lblTitulo, lblSubtitulo, this.btnIniciarVotacion);
    }

    public void configurarBotonIniciar(Runnable accion) {
        this.btnIniciarVotacion.setOnAction(e -> accion.run());
    }
}