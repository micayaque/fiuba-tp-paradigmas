package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.controladores.ConfiguracionController;
import edu.fiuba.paradigmas.controladores.EstadoPartidaController;
import edu.fiuba.paradigmas.controladores.RepartoController;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {
    private Stage escenarioPrincipal;

    @Override
    public void start(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Paradigmas Mafia");
        this.escenarioPrincipal.setMinWidth(780);
        this.escenarioPrincipal.setMinHeight(620);

        this.irABienvenida();

        this.escenarioPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void irABienvenida() {
        BienvenidaVista vista = new BienvenidaVista();
        vista.alPresionarContinuar(this::irAConfiguracion);

        this.setScene(vista, 780, 620);
    }

    public void irAConfiguracion() {
        ConfiguracionVista vista = new ConfiguracionVista();

        new ConfiguracionController(vista, this);

        this.setScene(vista, 780, 620);
    }

    public void irARepartoDeRoles(List<Jugador> jugadoresCreados){
        RepartoVista vista = new RepartoVista();
        
        new RepartoController(vista, this, jugadoresCreados);

        this.setScene(vista, 780, 620);
    }

    public void irAEstadoDePartida(List<Jugador> jugadoresCreados) {
        EstadoPartidaVista vista = new EstadoPartidaVista();
        
        
        new EstadoPartidaController(vista, jugadoresCreados);

        this.setScene(vista, 780, 520);
    }

    private void setScene(javafx.scene.Parent raiz, int ancho, int alto) {
        Scene escena = new Scene(raiz, ancho, alto);
        this.escenarioPrincipal.setScene(escena);
    }
}