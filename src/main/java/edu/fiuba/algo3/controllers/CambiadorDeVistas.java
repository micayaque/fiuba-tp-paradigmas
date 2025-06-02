package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Balatro.Balatro;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Palo.Diamante;
import edu.fiuba.algo3.modelo.Palo.Pica;
import edu.fiuba.algo3.modelo.Palo.Trebol;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.activacion.Activacion;
import edu.fiuba.algo3.modelo.activacion.ActivacionManoDePoker;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.efectos.AumentarPuntaje;
import edu.fiuba.algo3.modelo.efectos.Efecto;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.scene.Scene;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.escenas.VistaTienda;
import edu.fiuba.algo3.modelo.ronda.Tienda;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.ManoDePoker.Par;

public class CambiadorDeVistas {
    public static void cambiarVistaANuevaRonda(Stage stage, Ronda ronda){
        Balatro balatro = Balatro.juego();
     /*   if(balatro.juegoTerminado()){
            //Scene escena = new VistaFinal(stage,1280,720);
            //stage.setScene(escena);
        }else{ */
            Scene escena = crearVista(ronda,stage);
            stage.setScene(escena);
    }

    private static Scene crearVista(Ronda ronda, Stage stage) {
        return new  VistaTienda(stage,1280,720, ronda);
    }
}