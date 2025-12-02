package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Tablero.Dados;
import edu.fiuba.algo3.vistas.botones.BotonLanzarDados;
import edu.fiuba.algo3.vistas.botones.BotonTerminarTurno;
import edu.fiuba.algo3.vistas.vistas.VistaTablero2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorLanzarDados implements EventHandler<ActionEvent> {
    private final Dados dados;
    private final VistaTablero2 vista;
    private BotonLanzarDados botonLanzar;
    private BotonTerminarTurno botonTerminar;// Referencia al botón para bloquearlo

    public ControladorLanzarDados(Dados dadosModelo, VistaTablero2 vista) {
        this.dados = dadosModelo;
        this.vista = vista;
    }

    // Setter para conectar el botón después de crearlo
    public void setBoton(BotonLanzarDados boton) {
        this.botonLanzar = boton;
    }

    public void setBotonTerminar(BotonTerminarTurno boton) {
        this.botonTerminar = boton;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // 1. Lógica del Modelo
        int suma = dados.tirar();
        System.out.println("Dados lanzados: " + dados.getDado1() + " y " + dados.getDado2() + " (Suma: " + suma + ")");

        // 2. Lógica de la Vista (Actualizar dibujos)
        vista.actualizarDadosVisuales(dados.getDado1(), dados.getDado2());

        // 3. Bloquear el botón
        if (botonLanzar != null) botonLanzar.setDisable(true);

        // 3. DESBLOQUEAR EL BOTÓN TERMINAR (Ahora ya puede terminar)
        if (botonTerminar != null) botonTerminar.setDisable(false);

        // Aquí podrías llamar a manager.repartirRecursos(suma);
    }
}
