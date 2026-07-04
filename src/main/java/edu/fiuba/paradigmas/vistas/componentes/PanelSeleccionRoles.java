package edu.fiuba.paradigmas.vistas.componentes;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PanelSeleccionRoles extends VBox {
    private final List<BotonCartaRol> mazoCompleto = new ArrayList<>();
    private Runnable onCambio;

    public PanelSeleccionRoles() {
        this.setSpacing(16);
        
        Label lblMazo = new Label("Elegir Roles");
        lblMazo.setStyle("-fx-text-fill: #d6cfc2; -fx-font-weight: bold;");

        FlowPane panelCartas = new FlowPane();
        panelCartas.setHgap(16);
        panelCartas.setVgap(16);

        for (int i = 0; i < 6; i++) {
            mazoCompleto.add(new BotonCartaRol("ciudadano.png", "Ciudadano"));
        }

        for (int i = 0; i < 3; i++) {
            mazoCompleto.add(new BotonCartaRol("mafioso.png", "Mafioso"));
        }

        mazoCompleto.add(new BotonCartaRol("medico.png", "Medico"));
        mazoCompleto.add(new BotonCartaRol("detective.png", "Detective"));
        mazoCompleto.add(new BotonCartaRol("sheriff.png", "Sheriff"));
        mazoCompleto.add(new BotonCartaRol("padrino.png", "Padrino"));

        panelCartas.getChildren().addAll(mazoCompleto);

        for (BotonCartaRol carta : mazoCompleto) {
            carta.selectedProperty().addListener((obs, viejo, nuevo) -> {
                if (onCambio != null) onCambio.run();
            });
        }

        this.getChildren().addAll(lblMazo, panelCartas);
    }

    public void setOnCambio(Runnable accion) {
        this.onCambio = accion;
    }

    public List<String> obtenerRolesSeleccionados() {
        return mazoCompleto.stream()
                .filter(BotonCartaRol::isSelected)
                .map(BotonCartaRol::obtenerIdentificadorRol)
                .collect(Collectors.toList());
    }

    public void limpiarBloqueos() {
        mazoCompleto.forEach(carta -> carta.setBloqueado(false, ""));
    }

    public void bloquearTipoCarta(String identificador, String motivo) {
        mazoCompleto.stream()
                .filter(carta -> carta.obtenerIdentificadorRol().equals(identificador))
                .filter(carta -> !carta.isSelected())
                .forEach(carta -> carta.setBloqueado(true, motivo));
    }

    public void bloquearTodasLasSobrantes(String motivo) {
        mazoCompleto.stream()
                .filter(carta -> !carta.isSelected())
                .forEach(carta -> carta.setBloqueado(true, motivo));
    }
}