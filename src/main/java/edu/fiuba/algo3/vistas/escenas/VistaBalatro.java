package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.CartaVista;
import edu.fiuba.algo3.controllers.ControladorPrincipal;
import edu.fiuba.algo3.controllers.GenerarCartasVista;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.ronda.Tienda;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class VistaBalatro extends Scene {
    private Stage stage;
    private BorderPane root;
    private Ronda ronda;
    private List<Carta> cartas;
    private List<Tarot> tarots;
    private List<Comodin> comodines;
    private List<CartaVista> cartasVista;
    private List<Carta> cartasSeleccionadas;
    private ControladorPrincipal controlador;
    private Puntaje puntaje ;
    private List<ImageView> imagenesOrdenadas = null;
    private  Button botonJugada;
    private  Button botonDescarte;

    public VistaBalatro(Stage stage, double width, double height, Ronda ronda)  {
        super(new BorderPane(), width, height);
        this.stage = stage;
        this.root = (BorderPane) this.getRoot();
        this.ronda = ronda;
        Tienda tienda = ronda.verTienda();
        this.cartas = ronda.mostrarCartasDeManos();

        List<CartaVista> cartasVista = GenerarCartasVista.generarCarta(this.cartas);


        this.tarots = tienda.obtenerTarots();
        this.comodines = tienda.obtenerComodines();
        ControladorPrincipal controlador = new ControladorPrincipal(stage);
        if (this.puntaje == null) {
            Puntaje puntaje = new Puntaje(0, 0);
            this.puntaje = puntaje;
        }

        this.controlador = controlador;

        Image fondo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/fondoMenu.jpg")));

        BackgroundImage background = new BackgroundImage(
                fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,true,true,false, true)
        );

        this.root.setBackground(new Background(background));

        //HBox boxCartas = new HBox(10);
        //    boxCartas.setAlignment(Pos.CENTER);
        //    boxCartas.setStyle("-fx-padding: 10;");

        VistaCartas vistaCartas = new VistaCartas(cartasVista, controlador);


        VBox vbox = new VBox();
        vbox.getChildren().add(vistaCartas);
        vbox.setSpacing(20);


         botonJugada = new Button("Jugar Mano");
         botonDescarte = new Button("Descartar");

        botonJugada.getStyleClass().add("btn-azul");
        botonDescarte.getStyleClass().add("btn-rojo");

        Font fontCreepster = Font.loadFont(getClass().getResourceAsStream("/fonts/vt.ttf"),36);
        if (fontCreepster == null) {
            System.out.println("No se pudo cargar la fuente, usando la fuente predeterminada.");
            fontCreepster = Font.font("Arial");  // Fuente de respaldo
        }

        HBox botones = new HBox(100);
        botones.setAlignment(Pos.CENTER);
        botones.getChildren().addAll(botonJugada, botonDescarte);

        vbox.getChildren().add(botones);

        BorderPane.setAlignment(vbox, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(vbox, new Insets(10,10,20,10));
        this.root.setBottom(vbox);

        HBox contenedorTarots = new HBox(15);
        contenedorTarots.setAlignment(Pos.CENTER);

        HBox contenedorComodines = new HBox(15);

        actualizarInterfazTarots(contenedorTarots);
        actualizarInterfazComodines(contenedorComodines);

        HBox contenedorCartasEspeciales = new HBox(200);
        contenedorCartasEspeciales.setAlignment(Pos.CENTER);
        contenedorCartasEspeciales.getChildren().addAll(contenedorTarots, contenedorComodines);
        this.root.setTop(contenedorCartasEspeciales);
        BorderPane.setMargin(contenedorCartasEspeciales, new Insets(30,30,0,30));

        this.root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Botones/estilosBotones.css")).toExternalForm());
        this.root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/estadoDelJuego.css")).toExternalForm());

        VBox contenedorIzquierdo = new VBox(10);
        contenedorIzquierdo.setPadding(new Insets(15)); // Margen interno
        contenedorIzquierdo.setAlignment(Pos.TOP_CENTER); // Alinear al centro
        contenedorIzquierdo.getStyleClass().add("info-panel"); // Clase CSS principal
        actualizarVbox(contenedorIzquierdo);

        this.root.setLeft(contenedorIzquierdo);


        //agrego la carta del mazo aca:
        HBox contenedorMazo = new HBox(10);
        contenedorMazo.setAlignment(Pos.CENTER);

        Image cartaMazo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/cartaVolteada.png")));
        ImageView cartaMazoVista = new ImageView(cartaMazo);
        cartaMazoVista.setFitHeight(200);
        cartaMazoVista.setFitWidth(150);


        aplicarVibracion(cartaMazoVista);
        contenedorMazo.getChildren().add(cartaMazoVista);
        this.root.setRight(contenedorMazo);
        BorderPane.setMargin(contenedorMazo, new Insets(30, 30, 0, 60));
        this.root.setTop(contenedorCartasEspeciales);
        this.root.setBottom(vbox);
        this.root.setCenter(vistaCartas);
        this.root.setLeft(contenedorIzquierdo);
        this.root.setPrefSize(width, height);
    }



    private String obtenerDescripcionCarta(Object carta) {
        if (carta instanceof CartaVista) {
            return "Carta normal";
        } else if (carta instanceof Tarot) {
            return ((Tarot) carta).getDescripcion();
        } else if (carta instanceof Comodin) {
            return ((Comodin) carta).getDescripcion();
        }
        return "Descripción no disponible";
    }


    private Image obtenerImagenTarot(Tarot tarot) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/tarot.jpg")));
    }

    private Image obtenerImagenComodin(Comodin comodin) {
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/comodin.jpg")));
    }


    private void actualizarInterfazTarots(HBox contenedorTarots) {
        actualizarInterfaz(contenedorTarots, tarots, this::obtenerImagenTarot);
    }

    private void actualizarInterfazComodines(HBox contenedorComodines) {
        actualizarInterfaz(contenedorComodines, comodines, this::obtenerImagenComodin);
    }

    private  <T> void actualizarInterfaz(HBox contenedor, List<T> elementos, Function<T, Image> obtenerImagen) {

        contenedor.getChildren().clear();
        List<ImageView> imagenesOrdenadas = new ArrayList<>(); // Lista para guardar las imágenes ordenadas
        for (T elemento : elementos) {
            Image imagen = obtenerImagen.apply(elemento);
            ImageView imagenElemento = new ImageView(imagen);
            imagenElemento.setFitWidth(80);
            imagenElemento.setFitHeight(120);
            //aplicarEfectoLevantarYBajar(imagenElemento);
            imagenElemento.setUserData(elemento);

            if (elemento instanceof Carta) {
                imagenesOrdenadas.add(imagenElemento);
            }
            contenedor.getChildren().add(imagenElemento);
            };
        if (this.imagenesOrdenadas == null) {
            this.imagenesOrdenadas = imagenesOrdenadas;
        }
    }

    public void aplicarEfectoLevantarYBajar(ImageView cartaVista) {
        cartaVista.setOnMouseEntered(event -> {
            cartaVista.setTranslateY(-20);
            cartaVista.setEffect(new DropShadow(10, Color.BLACK));
        });
        cartaVista.setOnMouseExited(event -> {
            cartaVista.setTranslateY(0);
            cartaVista.setEffect(null);
        });
    }

    public void seleccionar(CartaVista cartaVista) {
        ImageView imagen = cartaVista.obtenerVistaImagen();
        imagen.setOnMouseClicked(event -> {
            Carta cartaSeleccionada = cartaVista.obtenerCarta();
            System.out.println("Carta seleccionada: " + cartaSeleccionada);
            if(!cartasSeleccionadas.contains(cartaSeleccionada)) {
                this.cartasSeleccionadas.add(cartaSeleccionada);
            }else{
                this.cartasSeleccionadas.remove(cartaSeleccionada);
            }

        });
    }


    public void aplicarBrillo(ImageView cartaVista) {
        Glow glow = new Glow(0);
        cartaVista.setEffect(glow);
        cartaVista.setOnMouseClicked(event -> {
            if (glow.getLevel() == 0) {
                glow.setLevel(0.8);
            } else {
                glow.setLevel(0);
            }
            cartaVista.setEffect(glow);
        });
    }
    public void aplicarVibracion(ImageView cartaVista) {
        Timeline vibracion = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    cartaVista.setTranslateX(0);
                }),
                new KeyFrame(Duration.millis(50), event -> {
                    cartaVista.setTranslateX(-5);
                }),
                new KeyFrame(Duration.millis(100), event -> {
                    cartaVista.setTranslateX(5);
                }),
                new KeyFrame(Duration.millis(150), event -> {
                    cartaVista.setTranslateX(-5);
                }),
                new KeyFrame(Duration.millis(200), event -> {
                        cartaVista.setTranslateX(0);
                })
        );
        vibracion.setCycleCount(2);
        vibracion.setAutoReverse(true);
        cartaVista.setOnMouseClicked(event -> {
            vibracion.play();
        });
    }

    public void actualizarVbox(VBox contenedor) {
        contenedor.getChildren().clear();
        HBox puntosNecesarios = new HBox(10);
            puntosNecesarios.setAlignment(Pos.CENTER_LEFT); // Alineación izquierda
            puntosNecesarios.getStyleClass().add("hbox-puntos-necesarios");


        int puntajeASuperar = ronda.verPuntajeASuperar();
        String puntajeASuperarStr = String.valueOf(puntajeASuperar);
        Label anotaLabel = new Label("Anota al menos:");
        Label puntajeAnotaLabel = new Label(puntajeASuperarStr);

            anotaLabel.getStyleClass().add("titulo-label"); // Sin el "."
            puntajeAnotaLabel.getStyleClass().add("puntaje-label");
            puntosNecesarios.getChildren().addAll(anotaLabel, puntajeAnotaLabel);
            contenedor.getChildren().add(puntosNecesarios);

        // segundo VBox
        HBox puntuacionRonda = new HBox(10);
            puntuacionRonda.setAlignment(Pos.CENTER_LEFT);
            puntuacionRonda.getStyleClass().add("hbox-puntuacion-ronda");

        AtomicInteger puntajeRonda = new AtomicInteger(ronda.verPuntajeDeRonda());
        String puntajeRondaSTR = String.valueOf(puntajeRonda.get());

        Label puntuacionLabel = new Label("Puntuación ronda:");
        Label puntajeRondaLabel = new Label(puntajeRondaSTR); // Valor dinámico
            puntuacionLabel.getStyleClass().add("titulo-label");
            puntajeRondaLabel.getStyleClass().add("puntaje-label");
            //puntajeRondaLabel.setFont(fontCreepster);

            puntuacionRonda.getChildren().addAll(puntuacionLabel, puntajeRondaLabel);

        // Añadir este HBox al VBox principal
            contenedor.getChildren().add(puntuacionRonda);

        // HBox para "Manos" y "Descartes"
        HBox manosDescartes = new HBox(20); // Espaciado entre los dos grupos
            manosDescartes.setAlignment(Pos.CENTER); // Alineación centrada
            manosDescartes.getStyleClass().add("hbox-manos-descartes");

        int manos = ronda.verManos();
        String manosSTR = String.valueOf(manos);

        // Crear labels para "Manos"
        Label manosLabel = new Label("Manos:");
        Label manosValorLabel = new Label(manosSTR); // Valor dinámico
            manosLabel.getStyleClass().add("titulo-label");
            manosValorLabel.getStyleClass().add("puntaje-label");
            //manosLabel.setFont(fontCreepster);

        // Crear labels para "Descartes"
        int descartes = ronda.verDescartes();
        String descartessSTR = String.valueOf(descartes);

        Label descartesLabel = new Label("Descartes:");
        Label descartesValorLabel = new Label(descartessSTR); // Valor dinámico
            descartesLabel.getStyleClass().add("titulo-label");
            descartesValorLabel.getStyleClass().add("puntaje-label");
           // descartesValorLabel.setFont(fontCreepster);

        // Crear VBox para cada sección
        VBox manosBox = new VBox(5); // espacio
            manosBox.setAlignment(Pos.CENTER);
            manosBox.getChildren().addAll(manosLabel, manosValorLabel);

        VBox descartesBox = new VBox(5); // Espacio txt y valor
            descartesBox.setAlignment(Pos.CENTER);
            descartesBox.getChildren().addAll(descartesLabel, descartesValorLabel);

        // Añadir VBoxes al HBox
            manosDescartes.getChildren().addAll(manosBox, descartesBox);

        // Añadir este HBox al VBox principal
        contenedor.getChildren().add(manosDescartes);
        HBox contenedorMultiYValor= new HBox(10);
        contenedorMultiYValor.getStyleClass().add("hbox-multiplicador-valor");

        double multiplicador = puntaje.getMultiplicador();
        String multiplicadorSTR = String.valueOf(multiplicador);

        contenedorMultiYValor.setAlignment(Pos.CENTER);
        Label multiplicadorLabel= new Label("Multiplicador: ");
        Label multiValor = new Label(multiplicadorSTR); // el dinamico

        multiplicadorLabel.getStyleClass().add("titulo-label");
        multiValor.getStyleClass().add("puntaje-label");
        //multiplicadorLabel.setFont(fontCreepster);
        //multiValor.setFont(fontCreepster);

        int valorPunto = puntaje.obtenerValor();
        String valorSTR = String.valueOf(valorPunto);

        Label valorLabel = new Label("valor: ");
        Label valor = new Label(valorSTR); // dinamico
        valorLabel.getStyleClass().add("titulo-label");
        valor.getStyleClass().add("puntaje-label");
        contenedorMultiYValor.getChildren().addAll(multiplicadorLabel,multiValor,valorLabel,valor);
        contenedor.getChildren().add(contenedorMultiYValor);
        //valor.setFont(fontCreepster);
        //valorLabel.setFont(fontCreepster);
        botonJugada.setOnAction(event -> {
            puntajeRonda.addAndGet(this.controlador.jugarMano(cartasSeleccionadas));
        });

    }


}
