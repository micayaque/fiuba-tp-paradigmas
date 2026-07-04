package edu.fiuba.paradigmas.vistas.componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelJugadores extends VBox {
    private final TextField campoNombre;
    private final ListView<String> listaNombres;
    private final Button agregar;
    private final Label estadisticas;
    private Runnable onCambio;

    public PanelJugadores() {
        this.setSpacing(10);
        this.setPrefWidth(320);
        this.setMinWidth(300);

        Label lblJugadores = new Label("Ingresar nombre de usuario:");
        lblJugadores.setStyle("-fx-text-fill: #d6cfc2; -fx-font-weight: bold;");

        this.campoNombre = new TextField();
        this.campoNombre.setPromptText("Ingrese el nombre del jugador");
        this.campoNombre.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #d4af37; -fx-padding: 10; -fx-background-color: #111111; -fx-text-fill: #f5f1e8;");

        this.agregar = new Button("Agregar jugador");

        this.listaNombres = new ListView<>();
        this.listaNombres.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #d4af37; -fx-background-color: #111111; -fx-control-inner-background: #111111; -fx-text-fill: #f5f1e8;");
        VBox.setVgrow(this.listaNombres, Priority.ALWAYS);

        this.estadisticas = new Label("Jugadores: 0\nRoles elegidos: 0");
        this.estadisticas.setStyle("-fx-text-fill: #d4af37; -fx-font-weight: bold; -fx-font-size: 14px;");

        configurarAcciones();

        this.getChildren().addAll(lblJugadores, campoNombre, agregar, listaNombres, estadisticas);
    }

    public void setOnCambio(Runnable accion) {
        this.onCambio = accion;
    }

    public List<String> obtenerNombres() {
        return listaNombres.getItems();
    }

    public void actualizarEstadisticas(int cantidadRoles) {
        this.estadisticas.setText("Jugadores: " + listaNombres.getItems().size() + "\nRoles elegidos: " + cantidadRoles);
    }

    private void notificarCambio() {
        if (onCambio != null) onCambio.run();
    }

    private void configurarAcciones() {
        agregar.setOnAction(e -> {
            String nombre = campoNombre.getText().trim();
            if (!nombre.isEmpty()) {
                if (listaNombres.getItems().size() >= 12) {
                    mostrarAlerta("Límite alcanzado", "Mesa llena", "No se pueden agregar más de 12 jugadores a la partida.");
                    campoNombre.clear();
                    return;
                }

                boolean duplicado = listaNombres.getItems().stream().anyMatch(n -> n.equalsIgnoreCase(nombre));
                if (!duplicado) {
                    listaNombres.getItems().add(nombre);
                    campoNombre.clear();
                    campoNombre.setPromptText("Ingrese el nombre del jugador");
                    notificarCambio();
                } else {
                    mostrarAlerta("Jugador duplicado", "Nombre ya ingresado", "El jugador '" + nombre + "' ya está anotado.");
                    campoNombre.clear();
                }
            }
        });

        campoNombre.setOnAction(e -> agregar.fire());

        this.listaNombres.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String nombre, boolean empty) {
                super.updateItem(nombre, empty);
                if (empty || nombre == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("-fx-background-color: transparent;");
                } else {
                    TextField campoEdicion = new TextField(nombre);
                    campoEdicion.setStyle("-fx-background-color: transparent; -fx-text-fill: #f5f1e8; -fx-font-size: 14px; -fx-padding: 0;");

                    campoEdicion.focusedProperty().addListener((obs, estabaEnFoco, estaEnFoco) -> {
                        if (!estaEnFoco) {
                            String nuevoNombre = campoEdicion.getText().trim();
                            if (!nuevoNombre.isEmpty()) {
                                getListView().getItems().set(getIndex(), nuevoNombre);
                            } else {
                                campoEdicion.setText(nombre);
                            }
                            notificarCambio();
                        }
                    });

                    Region espaciador = new Region();
                    HBox.setHgrow(espaciador, Priority.ALWAYS);
                    Button btnEliminar = new Button("X");
                    btnEliminar.setStyle("-fx-background-color: #8b0000; -fx-text-fill: #f5f1e8; -fx-font-weight: bold; -fx-background-radius: 4; -fx-padding: 2 6 2 6; -fx-cursor: hand;");

                    btnEliminar.setOnAction(e -> {
                        getListView().getItems().remove(getItem());
                        notificarCambio();
                    });

                    HBox contenedor = new HBox(campoEdicion, espaciador, btnEliminar);
                    contenedor.setAlignment(Pos.CENTER_LEFT);
                    contenedor.setPadding(new Insets(4, 8, 4, 8));
                    setGraphic(contenedor);
                    setText(null);
                    setStyle("-fx-background-color: #1a1a1a; -fx-border-color: #333333; -fx-border-width: 0 0 1 0;");
                }
            }
        });
    }

    private void mostrarAlerta(String titulo, String cabecera, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}