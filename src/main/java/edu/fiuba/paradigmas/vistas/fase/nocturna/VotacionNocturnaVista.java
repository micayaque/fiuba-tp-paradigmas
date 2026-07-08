package edu.fiuba.paradigmas.vistas.fase.nocturna;

import edu.fiuba.paradigmas.vistas.componentes.FondoEstrellas;
import edu.fiuba.paradigmas.vistas.componentes.fase.EncabezadoFaseVista;
import edu.fiuba.paradigmas.vistas.componentes.fase.ListaObjetivosVista;
import edu.fiuba.paradigmas.vistas.componentes.EtiquetaAdvertencia;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.function.Consumer;

public class VotacionNocturnaVista extends StackPane {

    private final ListaObjetivosVista listaObjetivos;
    private final EtiquetaAdvertencia lblAdvertencia;

    public VotacionNocturnaVista(String tituloRol, String iconoRol, String colorTema, String accionPrompt, List<String> objetivos) {

        FondoEstrellas capaFondo = new FondoEstrellas("#060e17", "");

        VBox capaUI = new VBox(20);
        capaUI.setAlignment(Pos.CENTER);
        capaUI.setStyle("-fx-background-color: transparent;");
        capaUI.setPadding(new Insets(30, 20, 20, 20));

        EncabezadoFaseVista encabezado = new EncabezadoFaseVista("🌙", "Noche");

        VBox tarjeta = new VBox(15);
        tarjeta.setAlignment(Pos.TOP_CENTER);
        tarjeta.setStyle("-fx-background-color: #12213d; -fx-background-radius: 20; -fx-padding: 30 20;");
        tarjeta.setMaxWidth(350);

        tarjeta.setMinHeight(380);

        Label lblIcono = new Label(iconoRol);
        lblIcono.setStyle("-fx-font-size: 40px; -fx-text-fill: " + colorTema + "; -fx-border-color: " + colorTema + "; -fx-border-radius: 15; -fx-border-width: 2; -fx-padding: 10 20;");

        Label lblRol = new Label(tituloRol);
        lblRol.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblRol.setStyle("-fx-text-fill: " + colorTema + ";");

        Label lblPrompt = new Label(accionPrompt);
        lblPrompt.setStyle("-fx-text-fill: #a0aec0; -fx-font-size: 14px;");

        this.lblAdvertencia = new EtiquetaAdvertencia();
        this.listaObjetivos = new ListaObjetivosVista(objetivos);

        tarjeta.getChildren().addAll(lblIcono, lblRol, lblPrompt, this.lblAdvertencia, this.listaObjetivos);

        capaUI.getChildren().addAll(encabezado, tarjeta);

        this.getChildren().addAll(capaFondo, capaUI);
    }

    public void configurarAcciones(boolean permitirOmitir, Consumer<String> alSeleccionar) {
        this.listaObjetivos.configurarAcciones(permitirOmitir, alSeleccionar);
    }

    public void mostrarAdvertencia(String mensaje) {
        this.lblAdvertencia.mostrarMensaje(mensaje);
    }
}