package edu.fiuba.paradigmas.vistas;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

public class ConfiguracionVista extends VBox
{
    private final TextField campoNombre;
    private final ListView<String> listaNombres;
    private final Button agregar;
    private final Button iniciar;


    public ConfiguracionVista(){
        this.campoNombre = new TextField();
        this.campoNombre.setPromptText("Ingrese el nombre del jugador");

        this.agregar = new Button("Agregar a la partida");
        this.listaNombres = new ListView<>();
        this.iniciar = new Button("Iniciar partida");


        agregar.setOnAction(e -> {
            String nombre = campoNombre.getText().trim();
            if (!nombre.isEmpty()){
                listaNombres.getItems().add(nombre);
                campoNombre.clear();
            }
        });

        this.getChildren().addAll(
                new Label("Configuracion de jugadores"),
                campoNombre,
                agregar,
                new Label("Jugadores anotados"),
                listaNombres,
                iniciar
                );
    }

    public List<String> obtenerNombres(){
        return listaNombres.getItems();
    }

    public void alPresionarIniciar(Runnable accion){
        iniciar.setOnAction(e -> accion.run());
    }

    public void mostrarError(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de configuracion");
        alert.setHeaderText("Mazo o cantidad invalida");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
