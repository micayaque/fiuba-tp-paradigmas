package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Catan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorProponerTrato implements EventHandler<ActionEvent> {
    private final Catan catan;
    private final Stage ventana;

    // Referencias a los inputs de la vista
    private final ComboBox<String> cmbJugadorDestino;
    private final ComboBox<String> cmbRecursoDoy;
    private final TextField txtCantDoy;
    private final ComboBox<String> cmbRecursoPido;
    private final TextField txtCantPido;

    public ControladorProponerTrato(Catan catan, Stage ventana,
                                    ComboBox<String> cmbJugador,
                                    ComboBox<String> cmbDoy, TextField txtDoy,
                                    ComboBox<String> cmbPido, TextField txtPido) {
        this.catan = catan;
        this.ventana = ventana;
        this.cmbJugadorDestino = cmbJugador;
        this.cmbRecursoDoy = cmbDoy;
        this.txtCantDoy = txtDoy;
        this.cmbRecursoPido = cmbPido;
        this.txtCantPido = txtPido;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            //Obtener Datos
            String nombreDestino = cmbJugadorDestino.getValue();
            String recursoDoyStr = cmbRecursoDoy.getValue();
            String recursoPidoStr = cmbRecursoPido.getValue();

            // Parsear Cantidades
            int cantDoy = Integer.parseInt(txtCantDoy.getText());
            int cantPido = Integer.parseInt(txtCantPido.getText());

            //Validaciones Básicas
            if (nombreDestino == null || recursoDoyStr == null || recursoPidoStr == null) {
                System.out.println("Por favor completa todos los campos.");
                return;
            }
            if (cantDoy <= 0 || cantPido <= 0) {
                System.out.println("Las cantidades deben ser mayores a 0.");
                return;
            }

            // Convertir Strings a Objetos del Modelo
            // Jugador jugadorDestino = catan.buscarJugador(nombreDestino);

            System.out.println("PROPUESTA: Doy " + cantDoy + " " + recursoDoyStr +
                    " a " + nombreDestino +
                    " por " + cantPido + " " + recursoPidoStr);

            // Llamar al Manager
            // catan.getManagerTurno().intercambiar(jugadorDestino, tipoDoy, cantDoy, tipoPido, cantPido);

            ventana.close();

        } catch (NumberFormatException e) {
            System.out.println("Error: Ingresa números válidos en las cantidades.");
        } catch (Exception e) {
            System.out.println("Error en el intercambio: " + e.getMessage());
        }
    }
}
