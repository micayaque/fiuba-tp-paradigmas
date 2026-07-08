package edu.fiuba.paradigmas.vista.victoria;

import edu.fiuba.paradigmas.vista.componentes.FondoEstrellas;
import edu.fiuba.paradigmas.vista.componentes.fase.IconoDiaVista;
import edu.fiuba.paradigmas.vista.componentes.victoria.BotonNuevoJuego;
import edu.fiuba.paradigmas.vista.componentes.victoria.BotonResumen;
import edu.fiuba.paradigmas.vista.componentes.victoria.CajaRolesRevelados;
import edu.fiuba.paradigmas.vista.componentes.victoria.IconoBando;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;
import java.util.Map;

public class PantallaVictoriaVista extends StackPane {

    private final BotonResumen btnVerResumen;
    private final BotonNuevoJuego btnNuevoJuego;

    public PantallaVictoriaVista(boolean ganaronCiudadanos, Map<String, String> rolesRevelados) {

        String colorFondo = ganaronCiudadanos ? "#0c3022" : "#1a0b10";
        String colorIcono = ganaronCiudadanos ? "#2ecc71" : "#e74c3c";

        Label lblTituloLinea2 = new Label(ganaronCiudadanos ? "GANÓ" : "WINS");
        Label lblTituloLinea1 = new Label(ganaronCiudadanos ? "CIUDADANOS!" : "MAFIA!");

        String subtituloTexto = ganaronCiudadanos ? "¡Se ha hecho justicia!" : "La aldea ha caído";

        this.setStyle("-fx-background-color: " + colorFondo + ";");
        FondoEstrellas capaFondo = new FondoEstrellas(colorFondo, colorIcono);

        VBox capaUI = new VBox(20);
        capaUI.setAlignment(Pos.CENTER);
        capaUI.setPadding(new Insets(40, 20, 40, 20));

        Node nodoGraficoTop;
        if (ganaronCiudadanos) {
            nodoGraficoTop = new IconoDiaVista();
        } else {
            nodoGraficoTop = new IconoBando("💀", colorIcono);
        }

        for (Label linea : List.of(lblTituloLinea1, lblTituloLinea2)) {
            linea.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 36));
            linea.setStyle("-fx-text-fill: " + colorIcono + ";");
        }

        VBox titulo = new VBox(lblTituloLinea1, lblTituloLinea2);
        titulo.setAlignment(Pos.CENTER);

        Label lblSubtitulo = new Label(subtituloTexto);
        lblSubtitulo.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        CajaRolesRevelados cajaRoles = new CajaRolesRevelados(rolesRevelados);

        VBox cajaBotones = new VBox(15);
        cajaBotones.setAlignment(Pos.CENTER);

        this.btnVerResumen = new BotonResumen("↻ VER RESUMEN");
        this.btnNuevoJuego = new BotonNuevoJuego("Nuevo Juego");
        cajaBotones.getChildren().addAll(this.btnVerResumen, this.btnNuevoJuego);

        capaUI.getChildren().addAll(nodoGraficoTop, titulo, lblSubtitulo, cajaRoles, cajaBotones);

        this.getChildren().addAll(capaFondo, capaUI);
    }

    public void configurarBotonResumen(Runnable accion) {
        this.btnVerResumen.setOnAction(e -> accion.run());
    }

    public void configurarBotonNuevoJuego(Runnable accion) {
        this.btnNuevoJuego.setOnAction(e -> accion.run());
    }
}