package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.controlador.AccionIniciarJuego;
import edu.fiuba.paradigmas.vistas.componentes.BotonPersonalizado;
import edu.fiuba.paradigmas.vistas.componentes.FormularioConfiguracion;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

public class ConfiguracionVista extends VBox
{
    private final FormularioConfiguracion formularioConfiguracion;
    private final BotonPersonalizado iniciar;


    public ConfiguracionVista(){
        this.formularioConfiguracion = new FormularioConfiguracion();
        this.iniciar = new BotonPersonalizado("Iniciar partida");


        this.getChildren().addAll(
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

    public void alPresionarIniciar(AccionIniciarJuego accion){
        iniciar.setOnAction(e -> {
            List<String> nombres = obtenerNombres();
            int cantMafiosos = cantidadDeMafiosos();

            boolean usaPadrino = formularioConfiguracion.usaPadrino();
            boolean usaDetective = formularioConfiguracion.usaDetective();
            boolean usaMedico = formularioConfiguracion.usaMedico();
            boolean usaSheriff = formularioConfiguracion.usaSheriff();

            accion.iniciar(nombres, cantMafiosos, usaPadrino, usaDetective, usaMedico, usaSheriff);
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
