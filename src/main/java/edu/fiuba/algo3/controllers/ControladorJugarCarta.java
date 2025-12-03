package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Cartas.*;
import edu.fiuba.algo3.modelo.Catan;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.*;
import edu.fiuba.algo3.vistas.vistas.VistaTablero2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
import java.util.Optional;

public class ControladorJugarCarta implements EventHandler<ActionEvent> {
    private final VistaTablero2 vista;

    public ControladorJugarCarta(Catan instance, VistaTablero2 vista) {
        this.vista = vista;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        // 1. Obtener qué carta seleccionó el usuario visualmente
        String nombreCarta = vista.getCartaSeleccionada();

        if (nombreCarta == null) {
            mostrarAlerta("Atención", "Debes seleccionar una carta habilitada primero.");
            return;
        }

        try {
            Jugador jugador = Catan.getInstance().getManagerTurno().getJugadorActual();

            // 2. Pedir al modelo la carta exacta (polimorfismo puro)
            // Si la carta es "nueva" y no se puede usar, este método lanzará error o devolverá null
            // según cómo lo definimos en el Mazo.
            CartaDesarrollo carta = jugador.buscarCartaParaJugar(nombreCarta);

            if (carta == null) {
                mostrarAlerta("Error", "No puedes jugar esa carta en este turno.");
                return;
            }

            boolean exito = false;

            // 3. Lógica de Interfaz según el tipo concreto de carta
            // (El modelo no sabe de JavaFX, así que el controlador debe pedir los datos)

            if (carta instanceof CartaCaballero) {
                vista.mostrarAlerta("Caballero", "Mueve el ladrón para activar el efecto.");
                vista.setModoCaballero(carta);
                vista.setModoRobo(true);
                exito = true;

            } else if (carta instanceof CartaMonopolio) {
                TipoDeRecurso recurso = pedirRecurso("Elige el recurso a monopolizar:");
                if (recurso != null) {
                    ((CartaMonopolio) carta).setRecursoElegido(recurso);
                    carta.ejecutarEfecto(jugador, Catan.getInstance().getTablero(), Catan.getInstance().getJugadores());
                    mostrarAlerta("Monopolio", "Has robado todos los recursos de tipo " + recurso.nombre());
                    exito = true;
                }

            } else if (carta instanceof CartaDescubrimiento) {
                List<TipoDeRecurso> recursos = pedirDosRecursos();
                if (recursos != null) {
                    ((CartaDescubrimiento) carta).setRecursosDeseados(recursos);
                    carta.ejecutarEfecto(jugador, null, null);
                    mostrarAlerta("Descubrimiento", "Has recibido 2 recursos del banco.");
                    exito = true;
                }

            } else if (carta instanceof CartaConstruccionCarreteras) {
                vista.activarModoCarreterasGratis((CartaConstruccionCarreteras) carta);
                vista.mostrarAlerta("Carreteras", "Construye 2 carreteras gratis.");
                exito = true;
            }

            // 4. Finalización
            if (exito) {
                // Marca en la vista que ya se jugó una carta (bloquea las demás)
                vista.marcarCartaJugada();
                vista.actualizarInventario();
                vista.verificarGanador();
            }

        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }

    // --- Métodos Auxiliares para Diálogos ---

    private TipoDeRecurso pedirRecurso(String mensaje) {
        List<String> opciones = List.of("Madera", "Ladrillo", "Lana", "Grano", "Mineral");
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Madera", opciones);
        dialog.setTitle("Selección");
        dialog.setHeaderText(mensaje);
        Optional<String> result = dialog.showAndWait();
        return result.map(this::stringARecurso).orElse(null);
    }

    private List<TipoDeRecurso> pedirDosRecursos() {
        TipoDeRecurso r1 = pedirRecurso("Elige el 1er Recurso:");
        if (r1 == null) return null;
        TipoDeRecurso r2 = pedirRecurso("Elige el 2do Recurso:");
        if (r2 == null) return null;
        return List.of(r1, r2);
    }

    private TipoDeRecurso stringARecurso(String s) {
        switch (s) {
            case "Madera": return new Madera(0);
            case "Ladrillo": return new Ladrillo(0);
            case "Lana": return new Lana(0);
            case "Grano": return new Grano(0);
            case "Mineral": return new Mineral(0);
            default: return null;
        }
    }

    private void mostrarAlerta(String t, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(t);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}
