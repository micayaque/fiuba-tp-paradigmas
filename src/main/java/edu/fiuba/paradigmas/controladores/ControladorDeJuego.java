package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.vistas.bienvenida.BienvenidaVista;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControladorDeJuego {

    private final Stage escenarioPrincipal;
    private Moderador moderador;

    public ControladorDeJuego(Stage stage) {
        this.escenarioPrincipal = stage;
    }

    public void irABienvenida() {
        BienvenidaVista vista = new BienvenidaVista();
        vista.configurarBotonContinuar(this::irAConfiguracion);
        this.cambiarEscena(vista);
    }

    public void irAConfiguracion() {
        new ConfiguracionControlador(this);
    }

    public void iniciarPartida(Moderador moderadorConfigurado) {
        this.moderador = moderadorConfigurado;
        this.irAFaseApertura();
    }

    private void irAFaseApertura() {
        new FaseAperturaControlador(this, this.moderador);
    }

    public void iniciarPrimeraNoche() {
        new FaseNocturnaControlador(this, this.moderador);
    }

    public void cambiarEscena(Parent nuevaVista) {
        Scene escenaActual = this.escenarioPrincipal.getScene();
        if (escenaActual != null) {
            escenaActual.setRoot(nuevaVista);
        } else {
            this.escenarioPrincipal.setScene(new Scene(nuevaVista));
        }
    }

    public void irAResumen(Moderador moderadorFinalizado) {
        new ResumenHistorialControlador(this, moderadorFinalizado);
    }

    public void volverAlMenu() {
        this.irABienvenida();
    }
}