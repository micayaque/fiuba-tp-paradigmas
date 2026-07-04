package edu.fiuba.paradigmas.vistas.componentes;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PanelSeleccionRoles extends VBox {
    private final List<BotonCartaRol> mazoCompleto = new ArrayList<>();
    private Runnable onCambio;

    public PanelSeleccionRoles() {
        this.setSpacing(16);
        VBox panelCartas = new VBox(20);
        panelCartas.setAlignment(Pos.CENTER);

        for (int i = 0; i < 6; i++) mazoCompleto.add(new BotonCartaRol("ciudadano.png", "Ciudadano"));
        for (int i = 0; i < 3; i++) mazoCompleto.add(new BotonCartaRol("mafioso.png", "Mafioso"));
        mazoCompleto.add(new BotonCartaRol("medico.png", "Medico"));
        mazoCompleto.add(new BotonCartaRol("detective.png", "Detective"));
        mazoCompleto.add(new BotonCartaRol("sheriff.png", "Sheriff"));
        mazoCompleto.add(new BotonCartaRol("padrino.png", "Padrino"));

        HBox fila1 = new HBox(20);
        fila1.setAlignment(Pos.CENTER);

        HBox fila2 = new HBox(20);
        fila2.setAlignment(Pos.CENTER);

        HBox fila3 = new HBox(20);
        fila3.setAlignment(Pos.CENTER);

        fila1.getChildren().addAll(mazoCompleto.subList(0, 5));
        fila2.getChildren().addAll(mazoCompleto.subList(5, 9));
        fila3.getChildren().addAll(mazoCompleto.subList(9, 13));

        panelCartas.getChildren().addAll(fila1, fila2, fila3);

        for (BotonCartaRol carta : mazoCompleto) {
            carta.selectedProperty().addListener((obs, viejo, nuevo) -> {
                if (onCambio != null) onCambio.run();
            });
        }

        ScrollPane scrollCartas = new ScrollPane(panelCartas);
        scrollCartas.setFitToWidth(true);
        scrollCartas.setFitToHeight(true);
        scrollCartas.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollCartas.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollCartas.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-padding: 0;");
        VBox.setVgrow(scrollCartas, Priority.ALWAYS);

        for (BotonCartaRol carta : mazoCompleto) {
            carta.setMinWidth(50);
            carta.setMinHeight(70);

            carta.prefWidthProperty().bind(
                    Bindings.min(140.0, scrollCartas.widthProperty().divide(5.5))
            );

            carta.prefHeightProperty().bind(
                    carta.prefWidthProperty().multiply(1.4)
            );
        }

        this.getChildren().addAll(scrollCartas);
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