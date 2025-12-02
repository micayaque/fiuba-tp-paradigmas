package edu.fiuba.algo3.vistas.vistas;

import edu.fiuba.algo3.controllers.*;
import edu.fiuba.algo3.modelo.Catan;
import edu.fiuba.algo3.modelo.Recursos.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Dados;
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
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class VistaTablero2 extends BorderPane { // CAMBIO: Ahora extendemos BorderPane
    private static final double ANCHO_VENTANA = 1280;
    private static final double ALTO_VENTANA = 720;

    // Variable para controlar la selección de cartas
    private String cartaSeleccionada = null;
    private Catan catan;
    private HBox contenedorDadosVisuales;
    private Stage stage;
    private PantallaPrincipal pantallaPrincipal;
    private HBox contenedorRecursos;
    private HBox contenedorCartasDesarrollo;
    private Label lblNombreJugadorActual;

    public VistaTablero2(Stage stage, PantallaPrincipal pantallaPrincipal,Catan catan) {

        this.setBackground(new Background(new BackgroundFill(Color.web("#233850"), null, null)));
        this.catan = catan;
        this.stage = stage;
        this.pantallaPrincipal = pantallaPrincipal;
        StackPane contenedorMapa = new StackPane(agregarTerrenos());
        contenedorMapa.setAlignment(Pos.CENTER);
        this.setCenter(contenedorMapa);

        // DERECHA: Jugadores + Dados + Fin Turno
        this.setRight(crearPanelDerecho());

        // ABAJO: Inventario + Acciones
        this.setBottom(crearPanelInferior());
    }

    private Group agregarTerrenos() {

        Tablero tablero = this.catan.crearTablero();
        Map<Integer, Terreno> terrenos = tablero.getTerrenos();

        double centerY = 0;
        double centerX = 0;

        Group root = new Group();
        double hexRadius = 50;

        Polygon center = createHexagon(centerX, centerY, hexRadius, terrenos.get(1));
        root.getChildren().add(center);

        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI * i / 6;
            double x = centerX + hexRadius * 1.8 * Math.cos(angle);
            double y = centerY + hexRadius * 1.8 * Math.sin(angle);
            Polygon hexagon = createHexagon(x, y, hexRadius, terrenos.get(i + 1));
            root.getChildren().add(hexagon);
        }
        for (int i = 0; i < 12; i++) {
            double angle = 2.0 * Math.PI * i / 12;
            double x = centerX + hexRadius * 3.6 * Math.cos(angle);
            double y = centerY + hexRadius * 3.6 * Math.sin(angle);
            Polygon hexagon = createHexagon(x, y, hexRadius, terrenos.get(i + 7));
            root.getChildren().add(hexagon);
        }

        Translate moverAbajo = new Translate(); moverAbajo.setY(25);
        Translate moverAbajoDerecha = new Translate(); moverAbajoDerecha.setY(13); moverAbajoDerecha.setX(20);
        Translate moverAbajoIzquierda = new Translate(); moverAbajoIzquierda.setY(13); moverAbajoIzquierda.setX(-20);
        Translate moverArriba = new Translate(); moverArriba.setY(-25);
        Translate moverArribaDerecha = new Translate(); moverArribaDerecha.setY(-13); moverArribaDerecha.setX(20);
        Translate moverArribaIzquierda = new Translate(); moverArribaIzquierda.setY(-13); moverArribaIzquierda.setX(-20);

        root.getChildren().get(8).getTransforms().add(moverArribaIzquierda);
        root.getChildren().get(10).getTransforms().add(moverArriba);
        root.getChildren().get(12).getTransforms().add(moverArribaDerecha);
        root.getChildren().get(14).getTransforms().add(moverAbajoDerecha);
        root.getChildren().get(16).getTransforms().add(moverAbajo);
        root.getChildren().get(18).getTransforms().add(moverAbajoIzquierda);

        return root;
    }

    private Polygon createHexagon(double x, double y, double radius, Terreno terreno) {
        Polygon hexagon = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = (2.0 * Math.PI * i / 6) + (Math.PI / 6);
            double xPos = x + radius * Math.cos(angle);
            double yPos = y + radius * Math.sin(angle);
            hexagon.getPoints().addAll(xPos, yPos);
        }

        String nombreImg = terreno.getTipoTerreno().toLowerCase();
        try {
            Image img = new Image("file:" + System.getProperty("user.dir") + "/src/main/resources/imagenes/" + nombreImg + ".jpg");
            hexagon.setFill(new ImagePattern(img));
        } catch (Exception e) {
            hexagon.setFill(Color.BROWN); // Color fallback si falla la imagen
        }
        hexagon.setStroke(Color.BLACK);
        return hexagon;
    }


    private VBox crearPanelDerecho() {
        VBox panel = new VBox();
        panel.setPadding(new Insets(20));
        panel.setPrefWidth(300);
        panel.setAlignment(Pos.TOP_CENTER);

        // LISTA DE JUGADORES
        List<Jugador> jugadores = this.catan.getJugadores();
        for (Jugador j : jugadores) {
            HBox infoJugador = agregarJugador(j);
            HBox separador = new HBox(); separador.setPrefHeight(15);
            panel.getChildren().addAll(infoJugador, separador);
        }

        //ESPACIADOR
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        panel.getChildren().add(spacer);


        // Los Dados (Visuales)
        // Guardamos la referencia en la variable de clase para actualizarla luego
        this.contenedorDadosVisuales = new HBox(15);
        this.contenedorDadosVisuales.setAlignment(Pos.CENTER);
        actualizarDadosVisuales(1, 1);
        Dados dados = new Dados();

        ControladorLanzarDados controladorLanzar = new ControladorLanzarDados(dados, this);
        BotonLanzarDados btnLanzar = new BotonLanzarDados(controladorLanzar);


        ControladorTerminarTurno controladorTerminarTurno = new ControladorTerminarTurno(this.catan,btnLanzar,this);
        BotonTerminarTurno btnTerminar = new BotonTerminarTurno(controladorTerminarTurno);

        btnTerminar.setDisable(true);

        controladorLanzar.setBoton(btnLanzar);
        controladorLanzar.setBotonTerminar(btnTerminar); // <--- Para habilitarlo

        // El controlador de terminar necesita conocer a su propio botón
        controladorTerminarTurno.setBotonTerminar(btnTerminar); //  Para deshabilitarlo

        // Contenedor de Botones
        HBox contenedorBotones = new HBox(15);
        contenedorBotones.setAlignment(Pos.CENTER);
        contenedorBotones.getChildren().addAll(btnLanzar, btnTerminar);

        //Armar Zona Final
        VBox zonaControl = new VBox(20);
        zonaControl.setAlignment(Pos.CENTER);
        zonaControl.getChildren().addAll(this.contenedorDadosVisuales, contenedorBotones);

        panel.getChildren().add(zonaControl);

        return panel;
    }

    private HBox agregarJugador(Jugador jugador) {
        HBox jugadorBox = new HBox();
        jugadorBox.setPadding(new Insets(10));


        Color colorFondoJavaFX = Color.web(jugador.getColor().getColor());

        //fondo con esquinas redondeadas
        jugadorBox.setBackground(new Background(new BackgroundFill(colorFondoJavaFX, new CornerRadii(8), null)));

        jugadorBox.setStyle("-fx-border-color: rgba(255,255,255,0.3); -fx-border-radius: 8; -fx-border-width: 1;");

        // Calculamos qué color de texto (blanco o negro) se ve mejor sobre ese fondo
        Color colorTexto = obtenerColorTextoContraste(colorFondoJavaFX);


        //Etiqueta del Nombre
        Label nombreJugador = new Label(jugador.getNombre());
        nombreJugador.setPrefWidth(110);
        nombreJugador.setWrapText(true);
        nombreJugador.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        // Aplicamos el color de contraste calculado
        nombreJugador.setTextFill(colorTexto);


        //Sección de Puntos de Victoria
        VBox puntosVictoria = new VBox(2); // Pequeño espacio vertical
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
        HBox panel = new HBox(20); // 20px de espacio inicial
        panel.setPadding(new Insets(15, 20, 15, 20));
        panel.setAlignment(Pos.BOTTOM_CENTER); // Alineado al centro abajo
        panel.setPrefHeight(200);

        // ---  CÁPSULA NEGRA DEL INVENTARIO (VBox) ---
        VBox inventario = new VBox(5);
        inventario.setPrefWidth(600);
        inventario.setPadding(new Insets(10, 15, 10, 15));
        inventario.setAlignment(Pos.TOP_CENTER);

        // Estilo cápsula
        inventario.setStyle("-fx-background-color: #222;" +
                " -fx-background-radius: 20;" +
                " -fx-border-color: black; -fx-border-width: 4; -fx-border-radius: 20;");

        // Nombre del Jugador
        this.lblNombreJugadorActual = new Label("Cargando...");
        this.lblNombreJugadorActual.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 18px; -fx-font-weight: bold;");

        // Título
        Label lblTitulo = new Label("INVENTARIO");
        lblTitulo.setStyle("-fx-text-fill: gray; -fx-font-size: 10px; -fx-font-weight: bold; -fx-letter-spacing: 2;");

        // Fila de Cartas (Recursos + Desarrollo)
        HBox filaDeCartas = new HBox(15);
        filaDeCartas.setAlignment(Pos.CENTER);
        filaDeCartas.setPrefWidth(580);

        this.contenedorRecursos = new HBox(10);
        this.contenedorRecursos.setAlignment(Pos.CENTER);


        this.contenedorCartasDesarrollo = new HBox(10);
        this.contenedorCartasDesarrollo.setAlignment(Pos.CENTER);
        // Borde separador a la izquierda para distinguirlo de los recursos
        this.contenedorCartasDesarrollo.setStyle("-fx-padding: 0 0 0 10; -fx-border-color: gray; -fx-border-width: 0 0 0 2;");

        // Agregamos ambos a la fila
        filaDeCartas.getChildren().addAll(this.contenedorRecursos, this.contenedorCartasDesarrollo);

        // Agregamos al VBox principal (Nombre, Titulo, Fila)
        inventario.getChildren().addAll(this.lblNombreJugadorActual, lblTitulo, filaDeCartas);

        // Llamamos a actualizar para llenar ambos contenedores
        actualizarInventario();


        // GRID DE BOTONES
        GridPane acciones = new GridPane();
        acciones.setHgap(10);
        acciones.setVgap(10);
        acciones.setAlignment(Pos.CENTER_LEFT); // Alineado a la izquierda (cerca del inventario)

        acciones.add(crearBotonAccion("Construir\nPoblado",new ControladorConstruirPoblado(this.catan)), 0, 0);
        acciones.add(crearBotonAccion("Construir\nCamino",new ControladorConstruirCamino(this.catan)), 1, 0);
        acciones.add(crearBotonAccion("Construir\nCiudad",new ControladorConstruirCiudad(this.catan)), 2, 0);

        acciones.add(crearBotonAccion("Banca",new ControladorBanca(this.catan,this)), 0, 1);
        acciones.add(crearBotonAccion("Intercambio",new ControladorIntercambioEntreJugadores(this.catan,this)), 1, 1);
        acciones.add(crearBotonAccion("JUGAR\nCARTA",new ControladorJugarCarta(this.catan,this)), 2, 1);




        panel.setSpacing(30); // Acercamos los botones al inventario
        panel.setAlignment(Pos.BOTTOM_CENTER);

        panel.getChildren().addAll(inventario, acciones);

        return panel;
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
    }

    // Método auxiliar para cargar imágenes de recursos y cartas
    private VBox crearFichaConImagen(String nombre, int cantidad, String nombreImagen, String colorFondoHex) {
        VBox ficha = new VBox(5); // Espacio vertical entre elementos
        ficha.setPrefSize(80, 110); // Un poco más grande para que quepa la imagen
        ficha.setAlignment(Pos.CENTER);

        // Estilo base de la "Carta"
        ficha.setStyle(
                "-fx-background-color: " + colorFondoHex + ";" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 10;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.6), 5, 0, 0, 0);"
        );

        // LA IMAGEN (Arriba)
        // Usamos un StackPane para que si falla la imagen, quede el hueco o un color
        StackPane contenedorImagen = new StackPane();
        contenedorImagen.setPrefSize(60, 60);

        try {
            // Intenta cargar la imagen. Asegúrate que la ruta empiece con "/"
            java.net.URL url = getClass().getResource("/imagenes/" + nombreImagen);
            if (url != null) {
                Image img = new Image(url.toExternalForm());
                javafx.scene.image.ImageView imgView = new javafx.scene.image.ImageView(img);
                imgView.setFitWidth(50);  // Ajusta el tamaño ancho
                imgView.setFitHeight(50); // Ajusta el tamaño alto
                imgView.setPreserveRatio(true);
                contenedorImagen.getChildren().add(imgView);
            } else {
                // Si no encuentra la imagen, pone un texto temporal
                Label lblNoImg = new Label("IMG?");
                lblNoImg.setTextFill(Color.WHITE);
                contenedorImagen.getChildren().add(lblNoImg);
            }
        } catch (Exception e) {
            System.out.println("Error imagen: " + nombreImagen);
        }

        // 2. EL NOMBRE (Medio)
        Label lblNombre = new Label(nombre);
        lblNombre.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px;");
        lblNombre.setEffect(new DropShadow(2, Color.BLACK));

        // 3. LA CANTIDAD (Abajo, Grande)
        Label lblCantidad = new Label(String.valueOf(cantidad));
        lblCantidad.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");
        lblCantidad.setEffect(new DropShadow(2, Color.BLACK));

        ficha.getChildren().addAll(contenedorImagen, lblNombre, lblCantidad);
        return ficha;
    }

    public void actualizarInventario() {
        if (this.contenedorRecursos == null || this.catan == null) return;

        this.contenedorRecursos.getChildren().clear();
        this.contenedorCartasDesarrollo.getChildren().clear();

        Jugador jugadorActual;
        try {
            jugadorActual = this.catan.getManagerTurno().getJugadorActual();
        } catch (Exception e) { return; }

        if (this.lblNombreJugadorActual != null) {
            this.lblNombreJugadorActual.setText(jugadorActual.getNombre());
            this.lblNombreJugadorActual.setTextFill(Color.web(jugadorActual.getColor().getColor()));
        }

        // --- A. LLENAR RECURSOS ---
        this.contenedorRecursos.getChildren().addAll(
                crearFichaConImagen("Madera",   jugadorActual.cantidadMadera(),   "madera.jpg",   "#228B22"),
                crearFichaConImagen("Ladrillo", jugadorActual.cantidadLadrillo(), "ladrillo.jpg", "#B22222"),
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

}
