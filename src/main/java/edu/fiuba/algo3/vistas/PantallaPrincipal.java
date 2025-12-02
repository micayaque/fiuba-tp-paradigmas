package edu.fiuba.algo3.vistas;


import edu.fiuba.algo3.vistas.utils.BarraDeMenu;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PantallaPrincipal extends BorderPane {

    private BarraDeMenu menu;

    public PantallaPrincipal(Stage stage) {
        this.setMenu(stage);
    }

    private void setMenu(Stage stage) {
        this.menu = new BarraDeMenu(stage);
        this.setTop(menu);
    }

    public void setCentro(Node centro) {
        this.setCenter(centro);
    }

    public BarraDeMenu getBarraDeMenu() {
        return menu;
    }

}
