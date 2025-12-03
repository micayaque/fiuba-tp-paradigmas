package edu.fiuba.algo3.vistas.vistas;

import edu.fiuba.algo3.ControladorComprarCarta;
import edu.fiuba.algo3.controllers.*;
import edu.fiuba.algo3.modelo.Catan;

import edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Tablero.Dados;
import edu.fiuba.algo3.modelo.Tablero.Factory.Coordenada;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terrenos.Terreno;
import edu.fiuba.algo3.vistas.PantallaPrincipal;
import edu.fiuba.algo3.vistas.botones.BotonGenericoAccionUsuario;
import edu.fiuba.algo3.vistas.botones.BotonLanzarDados;
import edu.fiuba.algo3.vistas.botones.BotonTerminarTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VistaTablero2 extends BorderPane { // CAMBIO: Ahora extendemos BorderPane
    private static final double ANCHO_VENTANA = 1280;
    private static final double ALTO_VENTANA = 720;

    // Variable para controlar la selección de cartas
    private String cartaSeleccionada = null;
    private HBox contenedorDadosVisuales;
    private Stage stage;
    private PantallaPrincipal pantallaPrincipal;
    private HBox contenedorRecursos;
    private HBox contenedorCartasDesarrollo;
    private Label lblNombreJugadorActual;

    private Circle ladronVisual;
    private boolean esperandoSeleccionHexagono = false;

    private BotonGenericoAccionUsuario btnConstruirPoblado;
    private BotonGenericoAccionUsuario btnConstruirCamino;
    private BotonGenericoAccionUsuario btnConstruirCiudad;
    private BotonGenericoAccionUsuario btnBanca;
    private BotonGenericoAccionUsuario btnIntercambioJugadores;
    private BotonGenericoAccionUsuario btnJugarCarta;
    private BotonGenericoAccionUsuario btnMoverLadron;

    private BotonGenericoAccionUsuario btnComprarCarta;

    private BotonLanzarDados btnLanzar;
    private BotonTerminarTurno btnTerminar;

    private Map<Coordenada, Point2D> mapaVisualVertices = new HashMap<>();

    public VistaTablero2(Stage stage, PantallaPrincipal pantallaPrincipal) {

        this.setBackground(new Background(new BackgroundFill(Color.web("#233850"), null, null)));
        this.stage = stage;
        this.pantallaPrincipal = pantallaPrincipal;
        StackPane contenedorMapa = new StackPane(agregarTerrenos());
        contenedorMapa.setAlignment(Pos.CENTER);
        this.setCenter(contenedorMapa);

        // ABAJO: Inventario + Acciones
        this.setBottom(crearPanelInferior());

        // DERECHA: Jugadores + Dados + Fin Turno
        this.setRight(crearPanelDerecho());

        actualizarInventario();
    }

    private Group agregarTerrenos() {
        Tablero tablero = Catan.getInstance().crearTablero();
        Map<Integer, Terreno> terrenos = tablero.getTerrenos();

        Group root = new Group();
        double hexRadius = 50;

        // Variables para guardar la posición inicial del ladrón
        double xDesierto = 0;
        double yDesierto = 0;

        // --- CENTRO (ID 1) ---
        // El centro es 0,0
        if (esDesierto(terrenos.get(1))) { xDesierto = 0; yDesierto = 0; }
        root.getChildren().add(createHexagon(0, 0, hexRadius, terrenos.get(1), 1));

        // --- PRIMER ANILLO (IDs 2-7) ---
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI * i / 6;
            double x = hexRadius * 1.8 * Math.cos(angle);
            double y = hexRadius * 1.8 * Math.sin(angle);

            if (esDesierto(terrenos.get(i + 2))) { xDesierto = x; yDesierto = y; }
            root.getChildren().add(createHexagon(x, y, hexRadius, terrenos.get(i + 2), i + 2));
        }

        // --- SEGUNDO ANILLO (IDs 8-19) ---
        // Aquí aplicamos los ajustes de posición (offsets) DIRECTAMENTE a X e Y
        for (int i = 0; i < 12; i++) {
            double angle = 2.0 * Math.PI * i / 12;
            double x = hexRadius * 3.6 * Math.cos(angle);
            double y = hexRadius * 3.6 * Math.sin(angle);

            // Ajustes manuales para que encajen los hexágonos (Reemplazo de tus Transforms anteriores)
            // IDs problemáticos en tu lógica anterior: 8, 10, 12, 14, 16, 18 (índices del loop + 7)
            int idReal = i + 8;

            // Estos valores imitan tus Translates anteriores pero modificando la coordenada
            if (i == 1) { x += -20; y += -13; } // Arriba Izq
            if (i == 3) { y += -25; }           // Arriba
            if (i == 5) { x += 20; y += -13; }  // Arriba Der
            if (i == 7) { x += 20; y += 13; }   // Abajo Der
            if (i == 9) { y += 25; }            // Abajo
            if (i == 11){ x += -20; y += 13; }  // Abajo Izq

            if (esDesierto(terrenos.get(idReal))) { xDesierto = x; yDesierto = y; }
            root.getChildren().add(createHexagon(x, y, hexRadius, terrenos.get(idReal), idReal));
        }

        // --- CREAR LADRÓN ---
        // Lo colocamos en la coordenada que encontramos del desierto
        ladronVisual = new Circle(20, Color.web("#333333"));
        ladronVisual.setStroke(Color.BLACK);
        ladronVisual.setStrokeWidth(2);
        ladronVisual.setMouseTransparent(true); // IMPORTANTE: Para que los clicks pasen al hexágono de abajo

        // Posición inicial detectada
        ladronVisual.setTranslateX(xDesierto);
        ladronVisual.setTranslateY(yDesierto);

        root.getChildren().add(ladronVisual);

        return root;
    }

    // Método auxiliar simple para verificar el desierto
    private boolean esDesierto(Terreno t) {
        if (t == null) return false;
        // Compara ignorando mayúsculas/minúsculas por seguridad
        return t.getTipoTerreno().toString().equalsIgnoreCase("DESIERTO");
    }


    // He unificado tus métodos createHexagon y añadido lógica de Ladrón
    private Polygon createHexagon(double x, double y, double radius, Terreno terreno, int hexId) {
        Polygon hexagon = new Polygon();
        double angleOffset = Math.PI / 6;

        for (int i = 0; i < 6; i++) {
            double angle = (2.0 * Math.PI * i / 6) + angleOffset;
            double xPos = x + radius * Math.cos(angle);
            double yPos = y + radius * Math.sin(angle);
            hexagon.getPoints().addAll(xPos, yPos);
        }

        if (terreno != null) {
            String nombreImg = terreno.getTipoTerreno().toLowerCase();
            try {
                // Intento de carga seguro
                java.net.URL url = getClass().getResource("/imagenes/" + nombreImg + ".jpg");
                if (url != null) {
                    hexagon.setFill(new ImagePattern(new Image(url.toExternalForm())));
                } else {
                    hexagon.setFill(Color.BROWN);
                }
            } catch (Exception e) { hexagon.setFill(Color.BROWN); }
        } else {
            hexagon.setFill(Color.LIGHTGRAY);
        }

        hexagon.setStroke(Color.BLACK);
        hexagon.setStrokeWidth(2);

        hexagon.setOnMouseClicked(e -> {
            if (this.esperandoSeleccionHexagono) {

                // 1. Mover visualmente
                moverLadronVisualmente(x, y);

                // 2. Resetear estado de selección
                this.esperandoSeleccionHexagono = false;
                this.getScene().setCursor(javafx.scene.Cursor.DEFAULT);

                // 3. SALIR DEL MODO ROBO (Esto restaura todos los botones)
                setModoRobo(false);

                // 4. Lógica de negocio
                System.out.println("Ladrón movido al hexágono: " + hexId);
                // controlador.moverLadron(hexId);

            } else {
                System.out.println("Hexágono: " + terreno.getTipoTerreno());
            }
        });

        return hexagon;
    }

    public void moverLadronVisualmente(double x, double y) {
        if (ladronVisual != null) {
            ladronVisual.setTranslateX(x);
            ladronVisual.setTranslateY(y);
            ladronVisual.toFront(); // Lo traemos al frente por si acaso quedó tapado
        }
    }



    private VBox crearPanelDerecho() {
        VBox panel = new VBox();
        panel.setPadding(new Insets(20));
        panel.setPrefWidth(300);
        panel.setAlignment(Pos.TOP_CENTER);

        List<Jugador> jugadores = Catan.getInstance().getJugadores();
        for (Jugador j : jugadores) {
            HBox infoJugador = agregarJugador(j);
            HBox separador = new HBox(); separador.setPrefHeight(15);
            panel.getChildren().addAll(infoJugador, separador);
        }

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        panel.getChildren().add(spacer);

        this.contenedorDadosVisuales = new HBox(15);
        this.contenedorDadosVisuales.setAlignment(Pos.CENTER);

        // Inicializar dados visuales


        Dados dados = new Dados(); // O obtener de Catan.getInstance()
        ControladorLanzarDados controladorLanzar = new ControladorLanzarDados(dados, this);
        this.btnLanzar = new BotonLanzarDados(controladorLanzar);

        ControladorTerminarTurno controladorTerminarTurno = new ControladorTerminarTurno(btnLanzar, this);
        this.btnTerminar = new BotonTerminarTurno(controladorTerminarTurno);
        btnTerminar.setDisable(true);

        controladorLanzar.setBoton(btnLanzar);
        controladorLanzar.setBotonTerminar(btnTerminar);
        controladorTerminarTurno.setBotonTerminar(btnTerminar);


        HBox contenedorBotones = new HBox(15);
        contenedorBotones.setAlignment(Pos.CENTER);
        contenedorBotones.getChildren().addAll(btnLanzar, btnTerminar);

        VBox zonaControl = new VBox(20);
        zonaControl.setAlignment(Pos.CENTER);
        zonaControl.getChildren().addAll(this.contenedorDadosVisuales, contenedorBotones);

        panel.getChildren().add(zonaControl);
        actualizarDadosVisuales(1, 1);
        return panel;
    }

    private HBox agregarJugador(Jugador jugador) {
        HBox jugadorBox = new HBox();
        jugadorBox.setPadding(new Insets(10));
        Color colorFondoJavaFX = Color.web(jugador.getColor().getColor());
        jugadorBox.setBackground(new Background(new BackgroundFill(colorFondoJavaFX, new CornerRadii(8), null)));
        jugadorBox.setStyle("-fx-border-color: rgba(255,255,255,0.3); -fx-border-radius: 8; -fx-border-width: 1;");
        Color colorTexto = obtenerColorTextoContraste(colorFondoJavaFX);

        Label nombreJugador = new Label(jugador.getNombre());
        nombreJugador.setPrefWidth(110);
        nombreJugador.setWrapText(true);
        nombreJugador.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        nombreJugador.setTextFill(colorTexto);

        VBox puntosVictoria = new VBox(2);
        puntosVictoria.setAlignment(Pos.CENTER);

        Label pvLabel = new Label("Puntos de\nVictoria");
        pvLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 10));
        pvLabel.setTextAlignment(TextAlignment.CENTER);
        pvLabel.setTextFill(colorTexto);

        String puntosReales = String.valueOf(jugador.totalPuntos());
        Label pvValue = new Label(puntosReales);
        pvValue.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        pvValue.setAlignment(Pos.CENTER);
        pvValue.setTextFill(colorTexto);

        puntosVictoria.getChildren().addAll(pvLabel, pvValue);
        jugadorBox.getChildren().addAll(nombreJugador, puntosVictoria);

        return jugadorBox;
    }

    private HBox crearPanelInferior() {
        HBox panel = new HBox(20);
        panel.setPadding(new Insets(15, 20, 15, 20));
        panel.setAlignment(Pos.BOTTOM_CENTER);
        panel.setPrefHeight(200);

        // INVENTARIO
        VBox inventario = new VBox(5);
        inventario.setPrefWidth(600);
        inventario.setPadding(new Insets(10, 15, 10, 15));
        inventario.setAlignment(Pos.TOP_CENTER);
        inventario.setStyle("-fx-background-color: #222; -fx-background-radius: 20; -fx-border-color: black; -fx-border-width: 4; -fx-border-radius: 20;");

        this.lblNombreJugadorActual = new Label("Cargando...");
        this.lblNombreJugadorActual.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label lblTitulo = new Label("INVENTARIO");
        lblTitulo.setStyle("-fx-text-fill: gray; -fx-font-size: 10px; -fx-font-weight: bold; -fx-letter-spacing: 2;");

        HBox filaDeCartas = new HBox(15);
        filaDeCartas.setAlignment(Pos.CENTER);
        filaDeCartas.setPrefWidth(580);

        this.contenedorRecursos = new HBox(10);
        this.contenedorRecursos.setAlignment(Pos.CENTER);

        this.contenedorCartasDesarrollo = new HBox(10);
        this.contenedorCartasDesarrollo.setAlignment(Pos.CENTER);
        this.contenedorCartasDesarrollo.setStyle("-fx-padding: 0 0 0 10; -fx-border-color: gray; -fx-border-width: 0 0 0 2;");

        filaDeCartas.getChildren().addAll(this.contenedorRecursos, this.contenedorCartasDesarrollo);
        inventario.getChildren().addAll(this.lblNombreJugadorActual, lblTitulo, filaDeCartas);

        actualizarInventario();

        // GRID DE ACCIONES
        GridPane acciones = new GridPane();
        acciones.setHgap(15);
        acciones.setVgap(15);
        acciones.setAlignment(Pos.CENTER_LEFT);

        this.btnConstruirPoblado = crearBotonAccion("Construir\nPoblado", new ControladorConstruirPoblado(Catan.getInstance()));
        this.btnConstruirCamino = crearBotonAccion("Construir\nCamino", new ControladorConstruirCamino(Catan.getInstance()));
        this.btnConstruirCiudad = crearBotonAccion("Construir\nCiudad", new ControladorConstruirCiudad(Catan.getInstance()));
        this.btnBanca = crearBotonAccion("Banca", new ControladorBanca(Catan.getInstance(), this));
        this.btnIntercambioJugadores = crearBotonAccion("Intercambio", new ControladorIntercambioEntreJugadores(Catan.getInstance(), this));
        this.btnJugarCarta = crearBotonAccion("JUGAR\nCARTA", new ControladorJugarCarta(Catan.getInstance(), this));
        this.btnMoverLadron = crearBotonAccion("MOVER\nLADRÓN", e -> {
            this.esperandoSeleccionHexagono = true;
            mostrarAlerta("Mover Ladrón", "Haz clic en un hexágono para colocar al ladrón.");
            // Opcional: Cambiar cursor para feedback visual
            this.getScene().setCursor(javafx.scene.Cursor.HAND);
        });
        this.btnMoverLadron.setDisable(true);

        // --- BOTON COMPRAR CARTA (IMPLEMENTADO) ---
        // Asumiendo que ControladorComprarCarta existe y recibe (Catan, VistaTablero) o similar
        // Si no tienes el controlador aún, usa uno genérico para probar
        this.btnComprarCarta = crearBotonAccion("Comprar\nCarta D.", e -> {
            System.out.println("Comprar carta presionado");
            // Catan.getInstance().getManagerTurno().getJugadorActual().comprarCartaDesarrollo(...)
            actualizarInventario(); // Refrescar vista tras compra
        });

        // this.btnMoverLadron = crearBotonAccion("MOVER\nLADRÓN", new ControladorMoverLadron()); // Opcional

        acciones.add(this.btnConstruirPoblado, 0, 0);
        acciones.add(this.btnConstruirCamino, 1, 0);
        acciones.add(this.btnConstruirCiudad, 2, 0);
        acciones.add(this.btnBanca, 0, 1);
        acciones.add(this.btnIntercambioJugadores, 1, 1);
        acciones.add(this.btnJugarCarta, 2, 1);

        acciones.add(this.btnComprarCarta, 0, 2);
        acciones.add(this.btnMoverLadron, 2, 2);

        panel.setSpacing(30);
        panel.setAlignment(Pos.BOTTOM_CENTER);
        panel.getChildren().addAll(inventario, acciones);

        return panel;
    }

    public void actualizarEstadoBotones() {
        try {
            Jugador jugador = Catan.getInstance().getManagerTurno().getJugadorActual();
            boolean tieneCamino = jugador.cantidadMadera() >= 1 && jugador.cantidadLadrillo() >= 1;
            this.btnConstruirCamino.setDisable(!tieneCamino);

            boolean tienePoblado = jugador.cantidadMadera() >= 1 && jugador.cantidadLadrillo() >= 1 &&
                    jugador.cantidadLana() >= 1 && jugador.cantidadGrano() >= 1;
            this.btnConstruirPoblado.setDisable(!tienePoblado);

            boolean tieneCiudad = jugador.cantidadMineral() >= 3 && jugador.cantidadGrano() >= 2;
            this.btnConstruirCiudad.setDisable(!tieneCiudad);

            // Validar compra carta (Lana + Grano + Mineral)
            boolean tieneRecursosCarta = jugador.cantidadLana() >= 1 && jugador.cantidadGrano() >= 1 && jugador.cantidadMineral() >= 1;
            this.btnComprarCarta.setDisable(!tieneRecursosCarta);

        } catch(Exception e) {
            // Manejo silencioso si el juego no ha iniciado bien
        }
    }

    private VBox crearCartaInteractiva(String nombre, Color color) {
        VBox carta = new VBox();
        carta.setPrefSize(60, 80);
        carta.setStyle("-fx-background-color: " + toHex(color) + "; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 5;");
        carta.setAlignment(Pos.CENTER);

        Label lbl = new Label(nombre.substring(0, 3)); // Solo 3 letras
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        carta.getChildren().add(lbl);

        carta.setOnMouseClicked(e -> {
            this.cartaSeleccionada = nombre;
            // Limpiar selección de hermanos
            HBox padre = (HBox) carta.getParent();
            padre.getChildren().forEach(n -> n.setStyle(n.getStyle().replace("-fx-border-color: yellow;", "-fx-border-color: white;")));
            // Seleccionar actual
            carta.setStyle(carta.getStyle().replace("-fx-border-color: white;", "-fx-border-color: yellow;"));
        });
        return carta;
    }

    private BotonGenericoAccionUsuario crearBotonAccion(String texto, EventHandler<ActionEvent> event) {
        BotonGenericoAccionUsuario btn = new BotonGenericoAccionUsuario (event,texto);
        return btn;
    }

    private String toHex(Color c) {
        return String.format("#%02X%02X%02X", (int)(c.getRed()*255), (int)(c.getGreen()*255), (int)(c.getBlue()*255));
    }
    private Color obtenerColorTextoContraste(Color colorFondo) {

        double luminancia = 0.2126 * colorFondo.getRed() +
                0.7152 * colorFondo.getGreen() +
                0.0722 * colorFondo.getBlue();

        //  Si la luminancia es mayor a 0.5, el color es "claro" -> Texto NEGRO
        // Si es menor o igual, el color es "oscuro" -> Texto BLANCO
        // Usamos 0.55 punto medio
        return (luminancia > 0.55) ? Color.BLACK : Color.WHITE;
    }

    private StackPane crearDadoVisual(int valor) {
        //EL FONDO DEL DADO
        StackPane dado = new StackPane();
        dado.setPrefSize(60, 60); // Tamaño del dado

        javafx.scene.shape.Rectangle fondo = new javafx.scene.shape.Rectangle(60, 60);
        fondo.setArcWidth(15); // Redondez de esquinas
        fondo.setArcHeight(15);
        fondo.setFill(Color.web("#d9534f")); // Color ROJO (Igual a tus botones)
        fondo.setStroke(Color.WHITE); // Borde blanco
        fondo.setStrokeWidth(2);

        dado.getChildren().add(fondo);

        GridPane puntosGrid = new GridPane();
        puntosGrid.setAlignment(Pos.CENTER);
        puntosGrid.setHgap(5); // Espacio horizontal entre puntos
        puntosGrid.setVgap(5); // Espacio vertical entre puntos

        //posiciones lógicas de los puntos en una matriz 3x3
        // (columna, fila)
        // 0,0  1,0  2,0
        // 0,1  1,1  2,1
        // 0,2  1,2  2,2

        // PUNTOS SEGÚN EL NÚMERO
        // Centro (para 1, 3, 5)
        if (valor % 2 != 0) {
            puntosGrid.add(crearPunto(), 1, 1);
        }

        // Esquinas Superior-Izquierda e Inferior-Derecha (para 2, 3, 4, 5, 6)
        if (valor > 1) {
            puntosGrid.add(crearPunto(), 0, 0);
            puntosGrid.add(crearPunto(), 2, 2);
        }

        // Esquinas Superior-Derecha e Inferior-Izquierda (para 4, 5, 6)
        if (valor > 3) {
            puntosGrid.add(crearPunto(), 2, 0);
            puntosGrid.add(crearPunto(), 0, 2);
        }

        // Medio-Izquierda y Medio-Derecha (solo para 6)
        if (valor == 6) {
            puntosGrid.add(crearPunto(), 0, 1);
            puntosGrid.add(crearPunto(), 2, 1);
        }

        dado.getChildren().add(puntosGrid);
        return dado;
    }

    private javafx.scene.shape.Circle crearPunto() {
        javafx.scene.shape.Circle punto = new javafx.scene.shape.Circle(5); // Radio 5
        punto.setFill(Color.WHITE);
        return punto;
    }

    public void actualizarDadosVisuales(int valor1, int valor2) {
        this.contenedorDadosVisuales.getChildren().clear();
        StackPane d1 = crearDadoVisual(valor1);
        StackPane d2 = crearDadoVisual(valor2);
        this.contenedorDadosVisuales.getChildren().addAll(d1, d2);

        int suma = valor1 + valor2;

        if (suma == 7) {
            // ACTIVAR MODO ROBO (Bloquea todo,habilita a el ladron )
            setModoRobo(true);
        } else {
            // FLUJO NORMAL
            actualizarInventario();
            // Aseguramos que los botones de construcción estén correctos según recursos
            actualizarEstadoBotones();

            // Habilitar otros botones estáticos
            btnIntercambioJugadores.setDisable(false);
            btnBanca.setDisable(false);
            btnJugarCarta.setDisable(false);
        }
    }

    public void habilitarMoverLadron(boolean habilitar) {
        if (this.btnMoverLadron != null) {
            this.btnMoverLadron.setDisable(!habilitar);
        }
    }


    // Método auxiliar para cargar imágenes de recursos y cartas
    private VBox crearFichaConImagen(String nombre, int cantidad, String nombreImagen, String colorFondoHex) {
        VBox ficha = new VBox(5);
        ficha.setPrefSize(80, 110);
        ficha.setAlignment(Pos.CENTER);
        ficha.setStyle("-fx-background-color: " + colorFondoHex + "; -fx-background-radius: 10; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 0);");

        StackPane contenedorImagen = new StackPane();
        contenedorImagen.setPrefSize(60, 60);

        try {
            java.net.URL url = getClass().getResource("/imagenes/" + nombreImagen);
            if (url != null) {
                Image img = new Image(url.toExternalForm());
                javafx.scene.image.ImageView imgView = new javafx.scene.image.ImageView(img);
                imgView.setFitWidth(50);
                imgView.setFitHeight(50);
                imgView.setPreserveRatio(true);
                contenedorImagen.getChildren().add(imgView);
            } else {
                Label lblNoImg = new Label(nombre.substring(0,1));
                lblNoImg.setTextFill(Color.WHITE);
                contenedorImagen.getChildren().add(lblNoImg);
            }
        } catch (Exception e) { }

        Label lblNombre = new Label(nombre);
        lblNombre.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px;");
        lblNombre.setEffect(new DropShadow(2, Color.BLACK));

        Label lblCantidad = new Label(String.valueOf(cantidad));
        lblCantidad.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");
        lblCantidad.setEffect(new DropShadow(2, Color.BLACK));

        ficha.getChildren().addAll(contenedorImagen, lblNombre, lblCantidad);
        return ficha;
    }

    public void actualizarInventario() {
        if (this.contenedorRecursos == null || Catan.getInstance() == null) return;

        this.contenedorRecursos.getChildren().clear();
        this.contenedorCartasDesarrollo.getChildren().clear();

        Jugador jugadorActual;
        try {
            jugadorActual = Catan.getInstance().getManagerTurno().getJugadorActual();
        } catch (Exception e) { return; }

        if (this.lblNombreJugadorActual != null) {
            this.lblNombreJugadorActual.setText(jugadorActual.getNombre());
            this.lblNombreJugadorActual.setTextFill(Color.web(jugadorActual.getColor().getColor()));
        }

        // --- A. LLENAR RECURSOS ---
        this.contenedorRecursos.getChildren().addAll(
                crearFichaConImagen("Madera",   jugadorActual.cantidadMadera(),   "madera.jpg",   "#228B22"),
                crearFichaConImagen("Ladrillo", jugadorActual.cantidadLadrillo(), "ladrilo.jpg", "#B22222"),
                crearFichaConImagen("Lana",     jugadorActual.cantidadLana(),     "lana.jpg",     "#7CB342"),
                crearFichaConImagen("Grano",    jugadorActual.cantidadGrano(),    "grano.jpg",    "#FFD700"),
                crearFichaConImagen("Mineral",   jugadorActual.cantidadMineral(),  "piedra.jpg",   "#708090")
        );

        // --- LLENAR CARTAS DE DESARROLLO ---
        // Aquí deberías obtener la cantidad real que tiene el jugador.
        // Por ahora pongo "1" o "0" como ejemplo visual.

        // Ejemplo: int cantCaballeros = jugadorActual.getCantidadCartas("Caballero");
        int cantCaballeros = 0;
        int cantMonopolio = 0;
        int cantPuntos = 0;
        int cantDesc = 0;
        int cantCarreteras = 0;

        this.contenedorCartasDesarrollo.getChildren().addAll(
                crearCartaInteractiva("Caballero", cantCaballeros, "caballero.jpg", "#A9A9A9"), // Gris Claro
                crearCartaInteractiva("Monopolio", cantMonopolio,  "monopolio.jpg", "#90EE90"), // Verde Claro
                crearCartaInteractiva("Punto Vic.", cantPuntos,    "PV.jpg",     "#FFD700"),
                crearCartaInteractiva("Descubrimiento",cantDesc,"descubrimiento.jpg","#FFD700"),
                crearCartaInteractiva("Carreteras",cantCarreteras,"carreteras.jpg","#FFD700")
        );
    }
    private VBox crearCartaInteractiva(String nombre, int cantidad, String nombreImagen, String colorFondoHex) {
        VBox carta = crearFichaConImagen(nombre, cantidad, nombreImagen, colorFondoHex);

        // 2. Le agregamos la interactividad (Click)
        carta.setOnMouseClicked(e -> {
            this.cartaSeleccionada = nombre;
            System.out.println("Seleccionaste: " + nombre);

            //  Limpiar borde de todas las cartas hermanas
            HBox padre = (HBox) carta.getParent();
            padre.getChildren().forEach(n -> {

                n.setStyle(n.getStyle().replace("-fx-border-color: yellow;", "-fx-border-color: white;"));
            });

            carta.setStyle(carta.getStyle().replace("-fx-border-color: white;", "-fx-border-color: yellow;"));
        });

        return carta;
    }


    private void mostrarAlerta(String titulo, String mensaje) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    /**
     * Activa o desactiva el "Modo Robo".
     * Si activar es true: Bloquea todo excepto el botón del ladrón.
     * Si activar es false: Restaura los botones según los recursos del jugador.
     */
    public void setModoRobo(boolean activar) {
        if (activar) {
            // 1. Bloquear TODAS las acciones de construcción y fin de turno
            btnConstruirCamino.setDisable(true);
            btnConstruirPoblado.setDisable(true);
            btnConstruirCiudad.setDisable(true);
            btnComprarCarta.setDisable(true);
            btnIntercambioJugadores.setDisable(true);
            btnJugarCarta.setDisable(true);
            btnBanca.setDisable(true);

            if (btnTerminar != null) btnTerminar.setDisable(true);

            // 2. Habilitar SOLO lo relacionado al ladrón
            if (btnMoverLadron != null) btnMoverLadron.setDisable(false);

            mostrarAlerta("¡LADRÓN ACTIVO!", "Se han bloqueado las acciones.\nDebes mover el ladrón para continuar.");

        } else {
            // 1. Deshabilitar botón ladrón (ya se usó)
            if (btnMoverLadron != null) btnMoverLadron.setDisable(true);

            // 2. Habilitar botón terminar
            if (btnTerminar != null) btnTerminar.setDisable(false);

            // 3. Restaurar botones de construcción según recursos
            actualizarEstadoBotones();

            // 4. Reactivar otros botones genéricos si corresponde
            btnIntercambioJugadores.setDisable(false);
            btnJugarCarta.setDisable(false);
            btnBanca.setDisable(false);
        }
    }







}
