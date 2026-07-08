package edu.fiuba.paradigmas.vistas.configuracion;

import edu.fiuba.paradigmas.vistas.componentes.configuracion.EncabezadoConfiguracion;
import edu.fiuba.paradigmas.vistas.componentes.configuracion.IndicadorProgresoConfiguracion;
import edu.fiuba.paradigmas.vistas.componentes.configuracion.ListaSeleccionCantidadJugadores;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.function.Consumer;

public class CantidadJugadoresVista extends VBox {

    private final EncabezadoConfiguracion encabezado;
    private final ListaSeleccionCantidadJugadores listaSeleccion;

    public CantidadJugadoresVista() {
        this.setStyle("-fx-background-color: #0b1426;");
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(30);
        this.setPadding(new Insets(20, 0, 0, 0));

        this.encabezado = new EncabezadoConfiguracion("Configuración del juego");

        IndicadorProgresoConfiguracion progreso = new IndicadorProgresoConfiguracion(1);

        VBox cajaTextos = new VBox(5);
        cajaTextos.setAlignment(Pos.CENTER);

        Label lblPregunta = new Label("Cuantos jugadores?");
        lblPregunta.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblPregunta.setStyle("-fx-text-fill: white;");

        Label lblSubtitulo = new Label("Seleccioná el número de personas que se unirán al juego.");
        lblSubtitulo.setStyle("-fx-text-fill: #a0aec0; -fx-font-size: 12px;");

        cajaTextos.getChildren().addAll(lblPregunta, lblSubtitulo);

        this.listaSeleccion = new ListaSeleccionCantidadJugadores();

        this.getChildren().addAll(this.encabezado, progreso, cajaTextos, this.listaSeleccion);
    }

    public void configurarBotonVolver(Runnable accion) {
        this.encabezado.configurarBotonVolver(accion);
    }

    public void cargarOpciones(int min, int max, Consumer<Integer> accionAlSeleccionar) {
        this.listaSeleccion.popularOpciones(min, max, accionAlSeleccionar);
    }
}