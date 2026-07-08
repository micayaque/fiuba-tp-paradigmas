package edu.fiuba.paradigmas.vistas.configuracion;

import edu.fiuba.paradigmas.vistas.componentes.configuracion.BarraProgresoNombres;
import edu.fiuba.paradigmas.vistas.componentes.configuracion.EncabezadoConfiguracion;
import edu.fiuba.paradigmas.vistas.componentes.configuracion.IndicadorProgresoConfiguracion;
import edu.fiuba.paradigmas.vistas.componentes.configuracion.PanelIngresoNombres;
import edu.fiuba.paradigmas.vistas.componentes.EtiquetaAdvertencia;
import edu.fiuba.paradigmas.vistas.componentes.BotonContinuar;
import edu.fiuba.paradigmas.vistas.componentes.TituloIngresoNombres;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class NombresJugadoresVista extends VBox {

    private final EncabezadoConfiguracion encabezado;
    private final BarraProgresoNombres barraProgreso;
    private final PanelIngresoNombres panelNombres;
    private final BotonContinuar btnContinuar;
    private final EtiquetaAdvertencia lblAdvertencia;
    private final int cantidadTotalJugadores;

    public NombresJugadoresVista(int cantidadJugadores) {
        this.cantidadTotalJugadores = cantidadJugadores;

        this.setStyle("-fx-background-color: #060e17;");
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setPadding(new Insets(20, 0, 20, 0));

        this.encabezado = new EncabezadoConfiguracion("Configuración del juego");
        IndicadorProgresoConfiguracion progreso = new IndicadorProgresoConfiguracion(2);
        TituloIngresoNombres titulos = new TituloIngresoNombres("Ingrese los nombres de los jugadores", "Cada nombre debe ser único");
        this.lblAdvertencia = new EtiquetaAdvertencia();
        this.barraProgreso = new BarraProgresoNombres(cantidadJugadores);
        this.panelNombres = new PanelIngresoNombres(cantidadJugadores, this::recalcularProgreso);
        this.btnContinuar = new BotonContinuar("➔ CONTINUAR CON LOS ROLES");

        this.getChildren().addAll(this.encabezado, progreso, titulos, this.lblAdvertencia, this.barraProgreso, this.panelNombres, this.btnContinuar);
    }

    private void recalcularProgreso() {
        this.lblAdvertencia.limpiar();
        int completados = this.panelNombres.getCantidadCompletados();
        this.barraProgreso.actualizarProgreso(completados);
        boolean todosCompletos = (completados == this.cantidadTotalJugadores);
        this.btnContinuar.setListoParaAvanzar(todosCompletos);
    }

    public void configurarBotonVolver(Runnable accion) {
        this.encabezado.configurarBotonVolver(accion);
    }

    public void configurarBotonContinuar(Consumer<List<String>> accion) {
        this.btnContinuar.setOnAction(e -> {
            if (this.panelNombres.hayDuplicados()) {
                this.lblAdvertencia.mostrarMensaje("¡Cada jugador debe tener un nombre único!");
            } else {
                this.lblAdvertencia.limpiar();
                accion.accept(this.panelNombres.getNombres());
            }
        });
    }
}