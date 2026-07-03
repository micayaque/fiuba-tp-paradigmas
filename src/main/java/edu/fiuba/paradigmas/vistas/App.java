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

        this.irAConfiguracion();

        this.escenarioPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void irAConfiguracion() {
        ConfiguracionVista vista = new ConfiguracionVista();

        new ConfiguracionController(vista, this);

        Scene escena = new Scene(vista, 400, 500);
        this.escenarioPrincipal.setScene(escena);
    }

    public void irARepartoDeRoles(List<Jugador> jugadoresCreados){
        RepartoVista vista = new RepartoVista();
        
        new RepartoController(vista, this, jugadoresCreados);

        Scene escena = new Scene(vista, 400, 500);
        this.escenarioPrincipal.setScene(escena);
    }

    public void irAEstadoDePartida(List<Jugador> jugadoresCreados) {
        EstadoPartidaVista vista = new EstadoPartidaVista();
        
        
        new EstadoPartidaController(vista, jugadoresCreados);

        Scene escena = new Scene(vista, 400, 300);
        this.escenarioPrincipal.setScene(escena);
    }
}