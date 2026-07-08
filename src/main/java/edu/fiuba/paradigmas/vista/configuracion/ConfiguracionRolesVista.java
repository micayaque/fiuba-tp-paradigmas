package edu.fiuba.paradigmas.vista.configuracion;

import edu.fiuba.paradigmas.vista.componentes.configuracion.EncabezadoConfiguracion;
import edu.fiuba.paradigmas.vista.componentes.configuracion.IndicadorProgresoConfiguracion;
import edu.fiuba.paradigmas.vista.componentes.configuracion.PanelSeleccionRoles;
import edu.fiuba.paradigmas.vista.componentes.configuracion.SwitchConfiguracion;
import edu.fiuba.paradigmas.vista.componentes.EtiquetaAdvertencia;
import edu.fiuba.paradigmas.vista.componentes.BotonContinuar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ConfiguracionRolesVista extends VBox {

    private final SwitchConfiguracion switchBallotage;

    private final EncabezadoConfiguracion encabezado;
    private final BotonContinuar btnIniciarJuego;
    private final Label lblContadorTotal;
    private final EtiquetaAdvertencia lblAdvertencia;
    private final PanelSeleccionRoles panelRoles;

    private final int totalJugadoresPermitidos;

    public ConfiguracionRolesVista(int cantidadJugadores) {
        this.totalJugadoresPermitidos = cantidadJugadores;

        this.setStyle("-fx-background-color: #060e17;");
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(20, 0, 20, 0));

        this.encabezado = new EncabezadoConfiguracion("Configuración del juego");
        IndicadorProgresoConfiguracion progreso = new IndicadorProgresoConfiguracion(3);

        this.lblContadorTotal = new Label("Roles asignados: 0 / " + cantidadJugadores);
        this.lblContadorTotal.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        this.lblContadorTotal.setStyle("-fx-text-fill: white;");

        this.switchBallotage = new SwitchConfiguracion("Empate por ballotage");

        this.lblAdvertencia = new EtiquetaAdvertencia();
        this.panelRoles = new PanelSeleccionRoles(this::actualizarContadores);
        this.btnIniciarJuego = new BotonContinuar("INICIAR JUEGO");

        this.getChildren().addAll(
                this.encabezado,
                progreso,
                this.lblContadorTotal,
                this.switchBallotage,
                this.lblAdvertencia,
                this.panelRoles,
                this.btnIniciarJuego
        );
        this.actualizarContadores();
    }

    private void actualizarContadores() {
        int total = this.panelRoles.getTotalAsignados();
        this.lblContadorTotal.setText("Roles asignados: " + total + " / " + this.totalJugadoresPermitidos);
        this.lblAdvertencia.limpiar();
        this.panelRoles.actualizarEstadosBotones();
        boolean todosAsignados = (total == this.totalJugadoresPermitidos);
        this.btnIniciarJuego.setListoParaAvanzar(todosAsignados);
    }

    public void mostrarAdvertencia(String mensaje) {
        this.lblAdvertencia.mostrarMensaje(mensaje);
    }

    public void configurarBotonVolver(Runnable accion) {
        this.encabezado.configurarBotonVolver(accion);
    }

    public void configurarBotonIniciar(Runnable accion) {
        this.btnIniciarJuego.setOnAction(e -> accion.run());
    }

    public int getCantCiudadanos() { return this.panelRoles.getCantCiudadanos(); }
    public int getCantMafiosos() { return this.panelRoles.getCantMafiosos(); }
    public int getCantPadrinos() { return this.panelRoles.getCantPadrinos(); }
    public int getCantSheriffs() { return this.panelRoles.getCantSheriffs(); }
    public int getCantMedicos() { return this.panelRoles.getCantMedicos(); }
    public int getCantDetectives() { return this.panelRoles.getCantDetectives(); }

    public boolean isBallotageActivado() {
        return this.switchBallotage.isActivado();
    }
}