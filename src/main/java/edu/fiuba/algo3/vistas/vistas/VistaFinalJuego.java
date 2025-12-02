package edu.fiuba.algo3.vistas.vistas;

import edu.fiuba.algo3.controllers.ControladorCantidadJugadores;
import edu.fiuba.algo3.controllers.ControladorFinalizarJuego;
import edu.fiuba.algo3.controllers.ControladorReiniciarJuego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.vistas.Grillas.GrillaBase;
import edu.fiuba.algo3.vistas.PantallaPrincipal;
import edu.fiuba.algo3.vistas.botones.BotonGenerico;
import edu.fiuba.algo3.vistas.mensajes.CatanMensaje;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class VistaFinalJuego extends StackPane{

    private static final String IMAGEN_RUTA = "imagenes/pantalla-iniciael.png";
    private static final double ANCHO_VENTANA = 1280;
    private static final double ALTO_VENTANA = 720;
    private static final double ESPACIADO_CENTRAL = 40;
    private ComboBox<String> comboBoxCantidadJugadores;


    public VistaFinalJuego(Stage stage, PantallaPrincipal pantallaPrincipal, Jugador jugador){

        configurarFondo();

        ControladorReiniciarJuego reiniciarJuego = new ControladorReiniciarJuego(stage,pantallaPrincipal);
        ControladorFinalizarJuego finalizarJuego = new ControladorFinalizarJuego();

        BotonGenerico botonReiniciar = new BotonGenerico(reiniciarJuego,"Reiniciar Juego");
        BotonGenerico botonFinalizar = new BotonGenerico(finalizarJuego,"Finalizar Juego");
        GrillaBase grilla = new GrillaBase(ANCHO_VENTANA, ALTO_VENTANA);
        VBox botones = new VBox();
        botones.setAlignment(Pos.CENTER);
        botones.setSpacing(ESPACIADO_CENTRAL);
        botones.getChildren().add(botonReiniciar);
        botones.getChildren().add(botonFinalizar);

        grilla.getChildren().addAll(botones);

    }

    private VBox crearNombreJuego() {
        VBox nombreJuego = new VBox();
        nombreJuego.setAlignment(Pos.CENTER);
        CatanMensaje catanMensaje = new CatanMensaje();
        catanMensaje.setScaleX(0.8);
        catanMensaje.setScaleY(0.8);
        nombreJuego.getChildren().add(catanMensaje);
        return nombreJuego;
    }


    private void configurarFondo() {
        Image imagen = new Image(IMAGEN_RUTA);
        BackgroundImage fondoImagen = new BackgroundImage(imagen,
                BackgroundRepeat.ROUND,
                BackgroundRepeat.SPACE,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false));
        Background fondo = new Background(fondoImagen);
        super.setBackground(fondo);
    }

    private VBox crearBoton(String mensaje, EventHandler<ActionEvent> evento) {
        VBox botonConfirmado = new VBox(0);
        botonConfirmado.setAlignment(Pos.BOTTOM_CENTER);
        BotonGenerico botonSiguiente = new BotonGenerico(evento,mensaje);
        botonSiguiente.disableProperty().bind(comboBoxCantidadJugadores.valueProperty().isNull());
        botonConfirmado.getChildren().add(botonSiguiente);
        return botonConfirmado;
    }
}
