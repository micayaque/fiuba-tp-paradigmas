package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.vistas.componentes.BotonRepartirCartas;
import edu.fiuba.paradigmas.vistas.componentes.PanelJugadores;
import edu.fiuba.paradigmas.vistas.componentes.PanelSeleccionRoles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.List;

public class ConfiguracionVista extends VBox {
    private final PanelJugadores panelJugadores;
    private final PanelSeleccionRoles panelRoles;
    private final Button btnIniciar;
    private Runnable onCambioDeCualquierDato;

    public ConfiguracionVista() {
        this.setSpacing(24);
        this.setPadding(new Insets(26));
        this.setStyle("-fx-background-color: #0a0a0a;");

        this.setAlignment(Pos.CENTER);

        Label titulo = new Label("Configuración de partida");
        titulo.setFont(new Font("Georgia", 24));
        titulo.setStyle("-fx-text-fill: #f5f1e8; -fx-font-weight: bold;");
        titulo.setMaxWidth(Double.MAX_VALUE);
        titulo.setAlignment(Pos.CENTER);

        this.panelJugadores = new PanelJugadores();
        this.panelRoles = new PanelSeleccionRoles();

        Runnable notificadorGlobal = () -> {
            int rolesElegidos = panelRoles.obtenerRolesSeleccionados().size();
            panelJugadores.actualizarEstadisticas(rolesElegidos);
            if (onCambioDeCualquierDato != null) onCambioDeCualquierDato.run();
        };
        panelJugadores.setOnCambio(notificadorGlobal);
        panelRoles.setOnCambio(notificadorGlobal);

        HBox cuerpoCentral = new HBox(40, panelJugadores, panelRoles);
        cuerpoCentral.setMaxWidth(1000);
        VBox.setVgrow(cuerpoCentral, Priority.ALWAYS);
        HBox.setHgrow(panelRoles, Priority.ALWAYS);

        this.btnIniciar = new BotonRepartirCartas();
        this.btnIniciar.setMaxWidth(Double.MAX_VALUE);

        this.getChildren().addAll(titulo, cuerpoCentral, btnIniciar);
    }

    public void alPresionarIniciar(Runnable accion) {
        this.btnIniciar.setOnAction(e -> accion.run());
    }

    public List<String> obtenerNombres() {
        return panelJugadores.obtenerNombres();
    }

    public List<String> obtenerRoles() {
        return panelRoles.obtenerRolesSeleccionados();
    }

    public void escucharCambiosEnTiempoReal(Runnable accion) {
        this.onCambioDeCualquierDato = accion;
    }

    public void limpiarBloqueosVisuales() { panelRoles.limpiarBloqueos(); }
    public void bloquearTipoCarta(String tipo, String motivo) { panelRoles.bloquearTipoCarta(tipo, motivo); }
    public void bloquearMazoSobrante(String motivo) { panelRoles.bloquearTodasLasSobrantes(motivo); }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error de configuración");
        alerta.setHeaderText("No se puede iniciar la partida");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}