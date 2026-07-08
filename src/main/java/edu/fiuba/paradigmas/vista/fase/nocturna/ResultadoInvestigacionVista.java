package edu.fiuba.paradigmas.vista.fase.nocturna;

import edu.fiuba.paradigmas.vista.componentes.FondoEstrellas;
import edu.fiuba.paradigmas.vista.componentes.fase.EncabezadoFase;
import edu.fiuba.paradigmas.vista.componentes.BotonTurno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ResultadoInvestigacionVista extends StackPane {

    private final BotonTurno btnContinuar;

    public ResultadoInvestigacionVista(String nombreSospechoso, String bando) {
        FondoEstrellas capaFondo = new FondoEstrellas("#060e17", "");

        VBox capaUI = new VBox(20);
        capaUI.setAlignment(Pos.CENTER);
        capaUI.setPadding(new Insets(30, 20, 20, 20));

        EncabezadoFase encabezado = new EncabezadoFase("🌙", "Noche");
        encabezado.setMinHeight(50);

        VBox tarjeta = new VBox(20);
        tarjeta.setAlignment(Pos.TOP_CENTER);
        tarjeta.setStyle("-fx-background-color: #12213d; -fx-background-radius: 20; -fx-padding: 30 20;");
        tarjeta.setMaxWidth(350);
        tarjeta.setMinHeight(280);

        Label lblIcono = new Label("🔍");
        lblIcono.setStyle("-fx-font-size: 40px; -fx-text-fill: #3182ce; -fx-border-color: #3182ce; -fx-border-radius: 15; -fx-border-width: 2; -fx-padding: 10 20;");

        Label lblTitulo = new Label("Reporte Confidencial");
        lblTitulo.setStyle("-fx-text-fill: #3182ce; -fx-font-size: 20px; -fx-font-weight: bold;");

        Label lblTexto = new Label("La investigación sobre " + nombreSospechoso + " determinó que su bando es:\n\n" + bando.toUpperCase());
        lblTexto.setWrapText(true);
        lblTexto.setAlignment(Pos.CENTER);
        lblTexto.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-text-alignment: center;");

        tarjeta.getChildren().addAll(lblIcono, lblTitulo, lblTexto);

        this.btnContinuar = new BotonTurno("OCULTAR Y CONTINUAR", 250);

        capaUI.getChildren().addAll(encabezado, tarjeta, this.btnContinuar);
        this.getChildren().addAll(capaFondo, capaUI);
    }

    public void configurarBotonAvanzar(Runnable accion) {
        this.btnContinuar.setOnAction(e -> accion.run());
    }
}