package edu.fiuba.paradigmas.vistas.componentes;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

import java.util.List;

public class SelectorDeJugador extends ComboBox<Jugador> {

    public SelectorDeJugador() {
        this.setStyle("-fx-font-size: 16px; -fx-pref-width: 250px;");
        this.setCellFactory(param -> crearCelda());
        this.setButtonCell(crearCelda());
    }

    private ListCell<Jugador> crearCelda() {
        return new ListCell<>() {
            @Override
            protected void updateItem(Jugador item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.nombre());
            }
        };
    }

    public void cargarOpciones(List<Jugador> opciones) {
        this.getItems().clear();
        this.getItems().addAll(opciones);
    }

    public Jugador jugadorSeleccionado() {
        return this.getValue();
    }
}