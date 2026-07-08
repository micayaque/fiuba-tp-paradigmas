package edu.fiuba.paradigmas.vistas.fase.diurna;

import edu.fiuba.paradigmas.vistas.componentes.fase.IconoDiaVista;
import edu.fiuba.paradigmas.vistas.componentes.fase.ListaObjetivosVista;
import edu.fiuba.paradigmas.vistas.componentes.EtiquetaAdvertencia;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.function.Consumer;

public class VotacionDiurnaVista extends VBox {

    private final ListaObjetivosVista listaObjetivos;
    private final EtiquetaAdvertencia lblAdvertencia;

    public VotacionDiurnaVista(List<String> objetivosDisponibles, boolean esBallotage) {
        this.setStyle("-fx-background-color: #8fa0b5;");
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(30, 20, 20, 20));

        IconoDiaVista graficoSol = new IconoDiaVista();

        Label lblTitulo = new Label("Día");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        lblTitulo.setStyle("-fx-text-fill: white;");

        DropShadow sombraTexto = new DropShadow();
        sombraTexto.setColor(Color.web("#000000", 0.3));
        sombraTexto.setRadius(5);
        sombraTexto.setOffsetY(3);
        lblTitulo.setEffect(sombraTexto);

        VBox contenedorEncabezado = new VBox(0);
        contenedorEncabezado.setAlignment(Pos.CENTER);
        contenedorEncabezado.getChildren().addAll(graficoSol, lblTitulo);
        VBox tarjeta = new VBox(15);
        tarjeta.setAlignment(Pos.TOP_CENTER);
        tarjeta.setStyle("-fx-background-color: #718096; -fx-background-radius: 20; -fx-padding: 30 20;");
        tarjeta.setMaxWidth(350);

        String textoPrompt = esBallotage ? "⚖ BALLOTAGE: Desempate" : "Elegí a quién eliminar";
        Label lblPrompt = new Label(textoPrompt);

        String colorTexto = esBallotage ? "#eab308" : "white";
        lblPrompt.setStyle("-fx-text-fill: " + colorTexto + "; -fx-font-size: 18px; -fx-font-weight: bold;");

        this.lblAdvertencia = new EtiquetaAdvertencia();
        this.listaObjetivos = new ListaObjetivosVista(objetivosDisponibles);

        tarjeta.getChildren().addAll(lblPrompt, this.lblAdvertencia, this.listaObjetivos);

        this.getChildren().addAll(contenedorEncabezado, tarjeta);
    }

    public void configurarAcciones(boolean permitirOmitir, Consumer<String> alSeleccionar) {
        this.listaObjetivos.configurarAcciones(permitirOmitir, alSeleccionar);
    }

    public void mostrarAdvertencia(String mensaje) {
        this.lblAdvertencia.mostrarMensaje(mensaje);
    }
}