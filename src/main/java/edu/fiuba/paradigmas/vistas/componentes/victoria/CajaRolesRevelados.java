package edu.fiuba.paradigmas.vistas.componentes.victoria;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class CajaRolesRevelados extends VBox {

    public CajaRolesRevelados(Map<String, String> rolesRevelados) {
        this.setSpacing(10);
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: #1a253a; -fx-background-radius: 15; -fx-padding: 20;");
        this.setMaxWidth(300);

        Label lblRolesTitulo = new Label("Roles revelados");
        lblRolesTitulo.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        this.getChildren().add(lblRolesTitulo);

        VBox contenedorFilas = new VBox(10);
        contenedorFilas.setAlignment(Pos.TOP_CENTER);
        contenedorFilas.setStyle("-fx-background-color: transparent;");

        for (Map.Entry<String, String> entrada : rolesRevelados.entrySet()) {
            HBox filaRol = new HBox(10);
            filaRol.setAlignment(Pos.CENTER_LEFT);
            filaRol.setStyle("-fx-background-color: #2a1b24; -fx-background-radius: 5; -fx-padding: 10;");

            Label lblOjo = new Label("👁");
            lblOjo.setStyle("-fx-text-fill: #e74c3c;");

            Label lblInfo = new Label(entrada.getKey() + " - " + entrada.getValue().toUpperCase());
            lblInfo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

            filaRol.getChildren().addAll(lblOjo, lblInfo);
            this.getChildren().add(filaRol);
            contenedorFilas.getChildren().add(filaRol);
        }

        ScrollPane scrollPane = new ScrollPane(contenedorFilas);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
        scrollPane.setMaxHeight(220);
        scrollPane.setPrefHeight(220);

        this.getChildren().add(scrollPane);
    }
}