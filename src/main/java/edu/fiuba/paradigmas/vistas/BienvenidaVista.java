package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.vistas.componentes.BotonConfigurarPartida;
import edu.fiuba.paradigmas.vistas.componentes.PanelCartasDecorativas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BienvenidaVista extends VBox {
    private final Label titulo;
    private final Label subtitulo;
    private final BotonConfigurarPartida continuar;

    public BienvenidaVista() {
        this.setSpacing(22);
        this.setPadding(new Insets(26));
        this.setAlignment(Pos.CENTER);

        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #050505, #171717);");

        PanelCartasDecorativas escenario = new PanelCartasDecorativas();

        this.titulo = new Label("Bienvenido al juego Mafia");
        this.titulo.setFont(new Font("Georgia", 31));
        this.titulo.setStyle("-fx-text-fill: #f5f1e8; -fx-font-weight: bold;");

        this.subtitulo = new Label("Descubrí quién domina la noche.");
        this.subtitulo.setFont(new Font("Georgia", 15));
        this.subtitulo.setStyle("-fx-text-fill: #d6cfc2;");
        this.subtitulo.setWrapText(true);
        this.subtitulo.setMaxWidth(420);
        this.subtitulo.setAlignment(Pos.CENTER);

        this.continuar = new BotonConfigurarPartida("Configurar partida");
        this.continuar.setMaxWidth(280);
        this.continuar.setStyle("-fx-background-color: linear-gradient(#c21f1f, #8f1010); -fx-text-fill: #f5f1e8; -fx-font-weight: bold; -fx-padding: 12 22; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: rgba(245,241,232,0.25);");

        VBox contenido = new VBox(22, escenario, this.titulo, this.subtitulo, this.continuar);
        contenido.setAlignment(Pos.CENTER);
        contenido.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        StackPane lienzo = new StackPane(contenido);
        VBox.setVgrow(lienzo, Priority.ALWAYS); // Obliga al lienzo a estirarse verticalmente
        this.getChildren().add(lienzo);
    }

    public void alPresionarConfigurarPartida(Runnable accion) {
        this.continuar.setOnAction(e -> accion.run());
    }
}