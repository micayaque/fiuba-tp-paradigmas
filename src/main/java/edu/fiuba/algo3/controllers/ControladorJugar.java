package edu.fiuba.algo3.controllers;
import java.io.File;
import java.util.List;

import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.ronda.Tienda;
import edu.fiuba.algo3.vistas.escenas.VistaBalatro;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.escenas.VistaTienda;
import edu.fiuba.algo3.modelo.ronda.Ronda;


public class ControladorJugar{
    protected Stage stage;

    public ControladorJugar(Stage stage) {
        this.stage = stage;
    }

    public void cambiarAVistaRonda(List<Object> cartasSeleccionadas, Ronda ronda) {
        // Guardar las cartas en el modelo
        //Juego juego = Juego.getInstancia();
        //juego.agregarCartasSeleccionadas(cartasSeleccionadas);
        crearVistaBalatro(ronda);
    }

    private void crearVistaBalatro(Ronda ronda){
        /* Juego j = Juego.getInstancia(); */
        /*Ronda ronda = new Ronda();*/
        VistaBalatro nuevaVista = new VistaBalatro(this.stage, this.stage.getScene().getWidth(), this.stage.getScene().getHeight(), ronda);
        this.stage.setScene(nuevaVista);
    }

}
