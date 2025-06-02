package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.ControladorJugar;
import edu.fiuba.algo3.controllers.ControladorPrincipal;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Palo.Diamante;
import edu.fiuba.algo3.modelo.Palo.Pica;
import edu.fiuba.algo3.modelo.Palo.Trebol;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.ronda.Tienda;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.ArrayList;

public class VistaTienda extends Scene{
    private Stage stage;
    private FlowPane root;
    private Text descripcion;
    private List<Carta> cartas;
    private List<Tarot> tarots;
    private List<Comodin> comodines;
    private Object elementoSeleccionado = null;
    private Button cartaSeleccionada = null;
    private Tienda tienda;
    private Ronda ronda;

    public VistaTienda(Stage stage, double width, double height, Ronda ronda) {
        super(new FlowPane(), width, height);
        this.stage = stage;
        this.root = (FlowPane) this.getRoot();
        this.tienda = ronda.verTienda();
        this.ronda = ronda;
        //background imagen
        BackgroundImage imagenFondo = new BackgroundImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/backgroundShop.png"))),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
        );

        Background fondo = new Background(imagenFondo);
        this.root.setBackground(fondo);


        //titulo shop

        Font fontCreepster = Font.loadFont(getClass().getResourceAsStream("/fonts/vt.ttf"),36);
        if (fontCreepster == null) {
            System.out.println("No se pudo cargar la fuente, usando la fuente predeterminada.");
            fontCreepster = Font.font("Arial", 36);  // Fuente de respaldo
        }

        //panel detalles
        VBox panelDetalles = new VBox();
        panelDetalles.setAlignment(Pos.TOP_LEFT);
        panelDetalles.setPrefWidth(400);

        Text textoTituloSuperior = new Text("TIENDA");
        textoTituloSuperior.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-fill: red; ");
        textoTituloSuperior.setFont(fontCreepster);

        Text titulo = new Text("Detalles de la carta:");
        titulo.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;  -fx-fill:red; ");
        titulo.setFont(fontCreepster);

        descripcion = new Text();
        descripcion.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;  -fx-fill:red;-fx-max-height: 150px; ");
        descripcion.setFont(fontCreepster);
        descripcion.setWrappingWidth(350);

// Crear un HBox para contener el texto y centrarlo
        VBox contenedorTitulo = new VBox(20);  // 10 es el espacio entre los elementos
        contenedorTitulo.getChildren().addAll(textoTituloSuperior, titulo);
        contenedorTitulo.setAlignment(Pos.CENTER);  // Alinear el VBox al centro

        StackPane contenedorDescripcion = new StackPane(descripcion);
        contenedorDescripcion.setAlignment(Pos.CENTER);  // Alinear el VBox dentro del StackPane
        contenedorDescripcion.setMinHeight(100);

        StackPane botonGuardar = new StackPane();
        botonGuardar.setStyle("-fx-background-color: red;");
        botonGuardar.setPrefSize(200, 50);

        Text textoGuardar = new Text("JUGAR CARTAS");
        textoGuardar.setFont(fontCreepster);
        textoGuardar.setFill(javafx.scene.paint.Color.WHITE);

        botonGuardar.getChildren().add(textoGuardar);
        botonGuardar.setOnMouseClicked(event -> {
            ControladorPrincipal controlador = new ControladorPrincipal(stage);
            List<Object> elementosSeleccionados = new ArrayList<Object>();
            elementosSeleccionados.add(this.cartas);
            elementosSeleccionados.add(this.tarots);
            elementosSeleccionados.add(this.comodines);
            controlador.cambiarVistaARonda(elementosSeleccionados, ronda); // Asigna el controlador al evento.
        });

        StackPane botonEliminar = new StackPane();
        botonEliminar.setStyle("-fx-background-color: red;");
        botonEliminar.setPrefSize(200, 50);

        Text textoEliminar = new Text("ELIMINAR CARTA");
        textoEliminar.setFont(fontCreepster);
        textoEliminar.setFill(javafx.scene.paint.Color.WHITE);

        // CARTAS CONTENEDORES
        HBox contenedorCartas = new HBox();
        contenedorCartas.setPrefHeight(150);
        contenedorCartas.setSpacing(10);
        contenedorCartas.setAlignment(Pos.CENTER);

        HBox contenedorTarots = new HBox();
        contenedorTarots.setPrefHeight(150);
        contenedorTarots.setSpacing(10);
        contenedorTarots.setAlignment(Pos.CENTER);

        HBox contenedorComodines = new HBox();
        contenedorComodines.setPrefHeight(150);
        contenedorComodines.setSpacing(10);
        contenedorComodines.setAlignment(Pos.CENTER);

        StackPane cartasStack = new StackPane(contenedorCartas);
        cartasStack.setAlignment(Pos.CENTER);

        StackPane comodinStack = new StackPane(contenedorComodines);
        comodinStack.setAlignment(Pos.CENTER);

        StackPane tarotStack = new StackPane(contenedorTarots);
        tarotStack.setAlignment(Pos.CENTER);

        botonEliminar.getChildren().add(textoEliminar);
        botonEliminar.setOnMouseClicked(event -> {
            descripcion.setText("");
            eliminarElemento();
            actualizarInterfazCartas(contenedorCartas);
            actualizarInterfazTarots(contenedorTarots);
            actualizarInterfazComodines(contenedorComodines);
            System.out.println("Eliminar carta");
        });


        VBox panelBotones = new VBox(20);
        panelBotones.setAlignment(Pos.BOTTOM_CENTER);
        panelBotones.getChildren().addAll( botonGuardar, botonEliminar);
        panelBotones.setMinHeight(100);
        panelBotones.setPrefHeight(300);



        panelDetalles.getChildren().addAll(contenedorTitulo, contenedorDescripcion, panelBotones);

        this.cartas = tienda.obtenerCartas();
        this.tarots = tienda.obtenerTarots();
        this.comodines = tienda.obtenerComodines();

        actualizarInterfazCartas(contenedorCartas);
        actualizarInterfazTarots(contenedorTarots);
        actualizarInterfazComodines(contenedorComodines);

        VBox contenedorCartasTarotsComodines = new VBox();
        contenedorCartasTarotsComodines.setAlignment(Pos.CENTER);

        Label textoCartaNormal = new Label("NORMALES");
        textoCartaNormal.setStyle("-fx-text-fill: red; -fx-padding: 5px;");
        textoCartaNormal.setFont(fontCreepster);
        Label textoCartaTarot = new Label("TAROTS");
        textoCartaTarot.setFont(fontCreepster);
        textoCartaTarot.setStyle("-fx-text-fill: red; -fx-padding: 5px;");
        Label textoComodin = new Label("COMODINES");
        textoComodin.setStyle("-fx-text-fill: red; -fx-padding: 5px;");
        textoComodin.setFont(fontCreepster);


        contenedorCartasTarotsComodines.getChildren().addAll(
                textoCartaNormal, cartasStack,
                textoCartaTarot, tarotStack,
                textoComodin, comodinStack
        );
        contenedorCartasTarotsComodines.setFillWidth(true);
        VBox contenedorPrincipal = new VBox();
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.getChildren().addAll(
                contenedorCartasTarotsComodines
        );
        this.root.getChildren().add(panelDetalles);
        this.root.getChildren().add(contenedorPrincipal);
        this.root.setPrefSize(width, height);
    }


    private String compararPalos(Carta carta){
        Map<String, Carta> palos = new HashMap<>();

        Carta corazon = new Carta(new Corazon(), 2, 2, 1);
        Carta pica = new Carta(new Pica(), 3, 3, 1);
        Carta trebol = new Carta(new Trebol(), 2, 2, 1);
        Carta diamante = new Carta(new Diamante(), 3, 3, 1);

        palos.put("corazones", corazon);
        palos.put("picas", pica);
        palos.put("trebol", trebol);
        palos.put("diamantes", diamante);

        for (Map.Entry<String, Carta> entry : palos.entrySet()) {
            if (carta.sonMismoPalo(entry.getValue())) {
                return entry.getKey();
            }
        }
        return "Palo desconocido";
    }

    private void mostrarDetallesCarta(Object carta) {
        descripcion.setText("");
        String descripcionTexto = obtenerDescripcionCarta(carta);
        descripcion.setText(descripcionTexto); // Actualiza el texto de la descripción    }
    }

    private String obtenerDescripcionCarta(Object carta) {
        if (carta instanceof Carta) {
            return "Carta normal";
        } else if (carta instanceof Tarot) {
            return ((Tarot) carta).getDescripcion();
        } else if (carta instanceof Comodin) {
            return ((Comodin) carta).getDescripcion();
        }
        return "Descripción no disponible";
    }

    private void eliminarElemento() {
        if (elementoSeleccionado != null) {
            if (elementoSeleccionado instanceof Carta) {
                cartas.remove(elementoSeleccionado);
                System.out.println("Carta eliminada: " + elementoSeleccionado);
            } else if (elementoSeleccionado instanceof Tarot) {
                tarots.remove(elementoSeleccionado);
                System.out.println("Tarot eliminado: " + elementoSeleccionado);
            } else if (elementoSeleccionado instanceof Comodin) {
                comodines.remove(elementoSeleccionado);
                System.out.println("Comodín eliminado: " + elementoSeleccionado);
            }
            elementoSeleccionado = null;
        } else {
            System.out.println("No se ha seleccionado ningún elemento para eliminar.");
        }
    }

    private Image obtenerImagenCarta(Carta carta) {
        String palo = compararPalos(carta);
        String valor = carta.obtenerNombre();
        String rutaImagen = "/images/Cartas/" + palo + "/" + valor + ".jpg";
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream(rutaImagen)));
    }

    private Image obtenerImagenTarot(Tarot tarot) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tarot.jpg")));
    }

    private Image obtenerImagenComodin(Comodin comodin) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/comodin.jpg")));
    }

    private void actualizarInterfazCartas(HBox contenedorCartas) {
        actualizarInterfaz(contenedorCartas, cartas, this::obtenerImagenCarta);
    }

    private void actualizarInterfazTarots(HBox contenedorTarots) {
        actualizarInterfaz(contenedorTarots, tarots, this::obtenerImagenTarot);
    }

    private void actualizarInterfazComodines(HBox contenedorComodines) {
        actualizarInterfaz(contenedorComodines, comodines, this::obtenerImagenComodin);
    }

    private <T> void actualizarInterfaz(HBox contenedor, List<T> elementos, Function<T, Image> obtenerImagen) {
        contenedor.getChildren().clear();

        for (T elemento : elementos) {
            Button botonElemento = new Button();

            Image imagen = obtenerImagen.apply(elemento);
            botonElemento.setGraphic(new ImageView(imagen));
            botonElemento.setStyle("-fx-background-color: transparent;");
            ImageView imagenElemento = (ImageView) botonElemento.getGraphic();
            imagenElemento.setFitWidth(80);
            imagenElemento.setFitHeight(120);

     //       botonElemento.setOnMouseEntered(event -> {
     //           imagenElemento.setFitWidth(90);
     //           imagenElemento.setFitHeight(130);
     //       });

     //       botonElemento.setOnMouseExited(event -> {
     //           if (cartaSeleccionada != botonElemento) {
     //               imagenElemento.setFitWidth(80);
     //               imagenElemento.setFitHeight(120);
     //           }
    //        });

            botonElemento.setOnMouseClicked(event -> {
               ImageView imagenPrevio = (ImageView) botonElemento.getGraphic();
               imagenPrevio.setFitWidth(80);
               imagenPrevio.setFitHeight(120);
                // Actualizar la selección
                cartaSeleccionada = botonElemento;
                elementoSeleccionado = elemento;

                imagenElemento.setFitWidth(90);
                imagenElemento.setFitHeight(130);

                mostrarDetallesCarta(elemento);
            });

            contenedor.getChildren().add(botonElemento);
        }
    }


}
