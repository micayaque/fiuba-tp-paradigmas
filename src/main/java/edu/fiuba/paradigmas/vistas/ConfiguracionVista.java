package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.controladores.AccionIniciarJuego;
import edu.fiuba.paradigmas.vistas.componentes.BotonPersonalizado;
import edu.fiuba.paradigmas.vistas.componentes.FormularioConfiguracion;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

import java.util.List;

public class ConfiguracionVista extends VBox {
    private final FormularioConfiguracion formularioConfiguracion;
    private final BotonPersonalizado iniciar;
    private final Label titulo;

    public ConfiguracionVista(){
        this.setSpacing(16);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #050505, #171717); -fx-min-width: 780px; -fx-min-height: 620px;");

        this.titulo = new Label("Configuración de partida");
        this.titulo.setFont(new Font("Georgia", 30));
        this.titulo.setStyle("-fx-text-fill: #f5f1e8; -fx-font-weight: bold;");

        this.formularioConfiguracion = new FormularioConfiguracion();
        this.iniciar = new BotonPersonalizado("Iniciar partida");
        this.iniciar.setMaxWidth(Double.MAX_VALUE);


        this.getChildren().addAll(
            titulo,
            formularioConfiguracion,
            iniciar
        );

    }

    public List<String> obtenerNombres(){
        return formularioConfiguracion.obtenerNombres();
    }

    public int cantidadDeMafiosos() {
        return formularioConfiguracion.cantidadDeMafiosos();
    }

    public void alPresionarIniciar(AccionIniciarJuego accion) {
        iniciar.setOnAction(e -> {
            List<String> nombres = obtenerNombres();
            List<String> rolesSeleccionados = formularioConfiguracion.obtenerRolesSeleccionados();
            accion.iniciar(nombres, rolesSeleccionados);
        });
    }

    public void mostrarError(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de configuracion");
        alert.setHeaderText("Mazo o cantidad invalida");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
