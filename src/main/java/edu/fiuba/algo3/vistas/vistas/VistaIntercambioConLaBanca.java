package edu.fiuba.algo3.vistas.vistas;

import edu.fiuba.algo3.modelo.Catan;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaIntercambioConLaBanca extends Stage {

    public VistaIntercambioConLaBanca(Stage owner, Catan catan) {
        this.initOwner(owner);
        this.initModality(Modality.APPLICATION_MODAL); // Pausa la ventana principal
        this.initStyle(StageStyle.UNDECORATED); // Sin bordes de ventana

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        // Estilo oscuro consistente con tu juego
        layout.setStyle("-fx-background-color: #2B3A55; -fx-border-color: #FFD700; -fx-border-width: 2;");

        Label titulo = new Label("COMERCIO CON BANCA");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        // --- SECCIÓN: YO DOY ---
        Label lblDoy = new Label("Yo entrego:");
        lblDoy.setStyle("-fx-text-fill: white;");
        ComboBox<String> cmbDoy = new ComboBox<>();
        cmbDoy.getItems().addAll("Madera", "Ladrillo", "Oveja", "Trigo", "Piedra");
        cmbDoy.setPromptText("Recurso a dar");

        // --- SECCIÓN: YO RECIBO ---
        Label lblPido = new Label("A cambio de:");
        lblPido.setStyle("-fx-text-fill: white;");
        ComboBox<String> cmbPido = new ComboBox<>();
        cmbPido.getItems().addAll("Madera", "Ladrillo", "Oveja", "Trigo", "Piedra");
        cmbPido.setPromptText("Recurso a recibir");

        // --- BOTONES ---
        Button btnConfirmar = new Button("Intercambiar");
        btnConfirmar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-font-weight: bold;");

        // ACCIONES
        btnCancelar.setOnAction(e -> this.close());

        btnConfirmar.setOnAction(e -> {
            String recursoDoy = cmbDoy.getValue();
            String recursoPido = cmbPido.getValue();

            if (recursoDoy != null && recursoPido != null) {
                try {
                    // LLAMADA AL MODELO
                    // catan.intercambiarConBanca(recursoDoy, recursoPido);
                    System.out.println("Intercambio Banca: Doy " + recursoDoy + " -> Recibo " + recursoPido);
                    this.close();
                } catch (Exception ex) {
                    // Mostrar alerta de error si no tiene recursos suficientes
                    System.out.println("Error en intercambio: " + ex.getMessage());
                }
            }
        });

        layout.getChildren().addAll(titulo, lblDoy, cmbDoy, lblPido, cmbPido, btnConfirmar, btnCancelar);

        Scene scene = new Scene(layout, 300, 350);
        this.setScene(scene);
    }
}
