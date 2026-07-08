package edu.fiuba.paradigmas.vistas.fase.nocturna;

import edu.fiuba.paradigmas.vistas.componentes.FondoEstrellas;
import edu.fiuba.paradigmas.vistas.componentes.fase.EncabezadoFaseVista;
import edu.fiuba.paradigmas.vistas.componentes.BotonTurno;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InactivoNocturnoVista extends StackPane {

    private final BotonTurno btnOcultar;

    public InactivoNocturnoVista() {
        FondoEstrellas capaFondo = new FondoEstrellas("#060e17", "");

        VBox capaUI = new VBox(20);
        capaUI.setAlignment(Pos.CENTER);
        capaUI.setPadding(new Insets(30, 20, 20, 20));

        EncabezadoFaseVista encabezado = new EncabezadoFaseVista("🌙", "Night Time");
        encabezado.setMinHeight(50);

        VBox tarjeta = new VBox(20);
        tarjeta.setAlignment(Pos.TOP_CENTER);
        tarjeta.setStyle("-fx-background-color: #12213d; -fx-background-radius: 20; -fx-padding: 30 20;");
        tarjeta.setMaxWidth(350);
        tarjeta.setMinHeight(380);

        Label lblIcono = new Label("💤");
        lblIcono.setStyle("-fx-font-size: 40px; -fx-text-fill: #718096; -fx-border-color: #718096; -fx-border-radius: 15; -fx-border-width: 2; -fx-padding: 10 20;");

        Label lblTexto = new Label("Tu rol está inactivo durante la noche.\nNo reveles tu identidad.");
        lblTexto.setWrapText(true);
        lblTexto.setAlignment(Pos.CENTER);
        lblTexto.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-text-alignment: center;");

        tarjeta.getChildren().addAll(lblIcono, lblTexto);

        this.btnOcultar = new BotonTurno("TERMINAR TURNO", 250);

        capaUI.getChildren().addAll(encabezado, tarjeta, this.btnOcultar);
        this.getChildren().addAll(capaFondo, capaUI);
    }

    public void configurarBotonAvanzar(Runnable accion) {
        this.btnOcultar.setOnAction(e -> accion.run());
    }
}