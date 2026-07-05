package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.vistas.componentes.CartaEliminado;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class EstadoPartidaVista extends BorderPane {

    private final Label lblRonda;
    private final Label lblResultado;
    private final VBox contenedorVivos;
    private final FlowPane contenedorEliminados;
    private final Button btnIniciarNoche;

    public EstadoPartidaVista() {
        this.setStyle("-fx-background-color: #0a0a0a;");
        this.setPadding(new Insets(30));

        this.lblRonda = new Label("Ronda actual: 1");
        this.lblRonda.setFont(new Font("Georgia", 36));
        this.lblRonda.setStyle("-fx-text-fill: #f5f1e8; -fx-font-weight: bold;");

        this.lblResultado = new Label("");
        this.lblResultado.setFont(new Font("Georgia", 24));

        VBox panelSuperior = new VBox(15, this.lblRonda, this.lblResultado);
        panelSuperior.setAlignment(Pos.CENTER);
        panelSuperior.setPadding(new Insets(0, 0, 30, 0));
        this.setTop(panelSuperior);

        Label lblTituloVivos = new Label("Jugadores vivos");
        lblTituloVivos.setFont(new Font("Georgia", 22));
        lblTituloVivos.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");

        this.contenedorVivos = new VBox(10);
        this.contenedorVivos.setAlignment(Pos.TOP_CENTER);
        VBox columnaVivos = new VBox(15, lblTituloVivos, this.contenedorVivos);
        columnaVivos.setAlignment(Pos.TOP_CENTER);
        columnaVivos.setPrefWidth(300);

        Label lblTituloEliminados = new Label("Jugadores eliminados");
        lblTituloEliminados.setFont(new Font("Georgia", 22));
        lblTituloEliminados.setStyle("-fx-text-fill: #931621; -fx-font-weight: bold;");

        this.contenedorEliminados = new FlowPane();
        this.contenedorEliminados.setHgap(15);
        this.contenedorEliminados.setVgap(15);
        this.contenedorEliminados.setAlignment(Pos.TOP_CENTER);
        VBox columnaEliminados = new VBox(15, lblTituloEliminados, this.contenedorEliminados);
        columnaEliminados.setAlignment(Pos.TOP_CENTER);
        columnaEliminados.setPrefWidth(600);

        HBox panelCentral = new HBox(50, columnaVivos, columnaEliminados);
        panelCentral.setAlignment(Pos.CENTER);
        this.setCenter(panelCentral);

        this.btnIniciarNoche = new Button("Iniciar Fase Nocturna");
        this.btnIniciarNoche.setStyle("-fx-background-color: linear-gradient(#931621, #4a0808); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 14 35; -fx-background-radius: 8; -fx-font-size: 16px;");
        this.btnIniciarNoche.setCursor(Cursor.HAND);

        HBox panelInferior = new HBox(this.btnIniciarNoche);
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setPadding(new Insets(30, 0, 0, 0));
        this.setBottom(panelInferior);
    }

    public void limpiarTablero(int numeroRonda) {
        this.lblRonda.setText("Ronda actual: " + numeroRonda);
        this.contenedorVivos.getChildren().clear();
        this.contenedorEliminados.getChildren().clear();
    }

    public void agregarJugadorVivo(String nombre) {
        Label lblVivo = new Label("• " + nombre);
        lblVivo.setFont(new Font("Georgia", 18));
        lblVivo.setStyle("-fx-text-fill: #d6cfc2;");
        this.contenedorVivos.getChildren().add(lblVivo);
    }

    public void agregarJugadorEliminado(String nombre, String nombreRol, String archivoImagen) {
        CartaEliminado tarjeta = new CartaEliminado(nombre, nombreRol, archivoImagen);
        this.contenedorEliminados.getChildren().add(tarjeta);
    }

    public void mostrarGanador(String bando) {
        this.lblResultado.setText("¡Victoria de " + bando + "!");
        if (bando.equalsIgnoreCase("Ciudadanos")) {
            this.lblResultado.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold; -fx-background-color: #0d2b18; -fx-padding: 10 20; -fx-background-radius: 8;");
        } else {
            this.lblResultado.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold; -fx-background-color: #2b0d0d; -fx-padding: 10 20; -fx-background-radius: 8;");
        }
        this.btnIniciarNoche.setDisable(true);
        this.btnIniciarNoche.setOpacity(0.5);
    }

    public void alPresionarIniciarRonda(Runnable accion) {
        this.btnIniciarNoche.setOnAction(e -> accion.run());
    }
}