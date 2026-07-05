package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.controladores.*;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {
    private Stage escenarioPrincipal;

    @Override
    public void start(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Mafia");
        this.escenarioPrincipal.setMinWidth(780);
        this.escenarioPrincipal.setMinHeight(620);
        this.escenarioPrincipal.setResizable(true);

        this.escenarioPrincipal.setMaximized(true);

        this.irABienvenida();
        this.escenarioPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void irABienvenida() {
        BienvenidaVista vista = new BienvenidaVista();
        new BienvenidaController(vista, this);
        this.escenarioPrincipal.setScene(new Scene(vista));
    }

    public void irAConfiguracion() {
        ConfiguracionVista vista = new ConfiguracionVista();
        new ConfiguracionController(vista, this);
        this.setScene(vista);
    }

    public void irARepartoDeRoles(List<Jugador> jugadoresCreados){
        RepartoVista vista = new RepartoVista();
        new RepartoController(vista, this, jugadoresCreados);
        this.setScene(vista);
    }

    public void iniciarPartida(List<Jugador> jugadoresListos) {
        Moderador moderadorInicial = new Moderador(jugadoresListos, new EmpateDiurnoSinEliminacion());
        moderadorInicial.comenzarFaseNocturna();
        this.irAFaseNocturna(moderadorInicial);
    }

    public void irAFaseNocturna(Moderador moderadorActual) {
        FaseNocturnaVista vistaNocturna = new FaseNocturnaVista();
        new FaseNocturnaController(vistaNocturna, this, moderadorActual);
        this.setScene(vistaNocturna);
    }

    private void cargarPantallaDeEstado(Moderador moderador, ResultadoFase resultadoPrevio, Runnable accionBoton, String textoBoton) {
        EstadoPartidaVista vista = new EstadoPartidaVista();

        new EstadoPartidaController(vista, moderador, resultadoPrevio, accionBoton, textoBoton);

        this.setScene(vista);
    }

    public void irAEstadoPartidaPreDia(Moderador moderador, ResultadoFase resultadoPrevio) {
        this.cargarPantallaDeEstado(
                moderador,
                resultadoPrevio,
                () -> {
                    moderador.comenzarFaseDiurna();
                    this.irAFaseDiurna(moderador);
                },
                "Comenzar Debate (Día)"
        );
    }

    public void irAFaseDiurna(Moderador moderador) {}

    private void setScene(javafx.scene.Parent raiz) {
        Scene escenaActual = this.escenarioPrincipal.getScene();
        escenaActual.setRoot(raiz);
    }
}