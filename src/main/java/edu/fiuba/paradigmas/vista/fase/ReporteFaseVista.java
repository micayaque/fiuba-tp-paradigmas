package edu.fiuba.paradigmas.vista.fase;

import edu.fiuba.paradigmas.vista.componentes.reportefase.CabeceraPeriodico;
import edu.fiuba.paradigmas.vista.componentes.reportefase.SeccionNoticias;
import edu.fiuba.paradigmas.vista.componentes.reportefase.TarjetaCita;
import edu.fiuba.paradigmas.vista.componentes.reportefase.BotonPeriodico;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class ReporteFaseVista extends VBox {

    private final BotonPeriodico btnContinuar;

    public ReporteFaseVista(String edicion, String titular, String bajada, List<SeccionNoticias> noticias, String textoBoton) {
        this.setStyle("-fx-background-color: #e3d5c1; -fx-font-smoothing-type: gray;");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(20));

        CabeceraPeriodico cabecera = new CabeceraPeriodico(edicion);

        Label lblTitular = new Label(titular);
        lblTitular.setFont(Font.font("Serif", FontWeight.BOLD, 22));
        lblTitular.setStyle("-fx-text-fill: #1a1a1a; -fx-text-alignment: center;");
        lblTitular.setOnMousePressed(Event::consume);

        Label lblBajada = new Label(bajada);
        lblBajada.setFont(Font.font("Serif", 14));
        lblBajada.setStyle("-fx-font-style: italic; -fx-text-fill: #4a4a4a;");
        lblBajada.setOnMousePressed(Event::consume);

        VBox tarjetaNoticias = new VBox(15);
        tarjetaNoticias.setStyle("-fx-background-color: #f7f4ec; -fx-background-radius: 10; -fx-padding: 20;");
        tarjetaNoticias.setMaxWidth(320);
        tarjetaNoticias.setOnMousePressed(Event::consume);
        tarjetaNoticias.getChildren().addAll(noticias);

        TarjetaCita tarjetaCita = new TarjetaCita("Se hará justicia. Los culpables no escaparán.", "Anciano de la aldea.");

        VBox contenidoCentral = new VBox(20, lblTitular, lblBajada, tarjetaNoticias, tarjetaCita);
        contenidoCentral.setAlignment(Pos.TOP_CENTER);

        this.btnContinuar = new BotonPeriodico(textoBoton);
        VBox.setMargin(this.btnContinuar, new Insets(30, 0, 0, 0));

        this.getChildren().addAll(cabecera, contenidoCentral, this.btnContinuar);
    }

    public void configurarBotonContinuar(Runnable accion) {
        this.btnContinuar.setOnAction(e -> accion.run());
    }
}