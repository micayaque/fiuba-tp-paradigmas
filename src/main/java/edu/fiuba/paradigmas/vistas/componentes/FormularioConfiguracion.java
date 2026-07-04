package edu.fiuba.paradigmas.vistas.componentes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormularioConfiguracion extends VBox {
    private final PanelJugadores panelJugadores;
    private final Label resumenConfiguracion;

    private final List<BotonCartaRol> mazoCiudadanos = new ArrayList<>();
    private final List<BotonCartaRol> mazoMafia = new ArrayList<>();
    private final List<BotonCartaRol> mazoEspeciales = new ArrayList<>();
    private final BotonCartaRol cartaPadrino;

    public FormularioConfiguracion() {
        this.setSpacing(14);
        this.setPadding(new Insets(18));
        this.setAlignment(Pos.TOP_LEFT);
        this.setStyle("-fx-background-color: rgba(10,10,10,0.92); -fx-background-radius: 16; -fx-border-radius: 16; -fx-border-color: rgba(212,175,55,0.35); -fx-border-width: 1; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.35), 18, 0.15, 0, 6);");

        this.panelJugadores = new PanelJugadores();
        this.resumenConfiguracion = new Label("Elegí nombres y cartas para armar la partida.");
        this.resumenConfiguracion.setStyle("-fx-text-fill: #d4af37; -fx-font-weight: bold; -fx-font-size: 14px;");

        for (int i = 0; i < 6; i++) mazoCiudadanos.add(new BotonCartaRol("ciudadano.png", "Ciudadano"));
        for (int i = 0; i < 3; i++) mazoMafia.add(new BotonCartaRol("mafioso.png", "Mafioso"));

        mazoEspeciales.add(new BotonCartaRol("medico.png", "Medico"));
        mazoEspeciales.add(new BotonCartaRol("detective.png", "Detective"));
        mazoEspeciales.add(new BotonCartaRol("sheriff.png", "Sheriff"));

        this.cartaPadrino = new BotonCartaRol("padrino.png", "Padrino");

        configurarAcciones();
        armarDisenio();
        actualizarResumenYLimites();
    }

    private void configurarAcciones() {
        panelJugadores.setOnCambioEnLista(this::actualizarResumenYLimites);

        List<BotonCartaRol> todasLasCartas = obtenerTodasLasCartas();
        for (BotonCartaRol carta : todasLasCartas) {
            carta.selectedProperty().addListener((obs, viejo, nuevo) -> actualizarResumenYLimites());
        }
    }

    private void armarDisenio() {
        VBox columnaIzquierda = new VBox(10, panelJugadores, resumenConfiguracion);

        VBox columnaDerecha = new VBox(16);
        HBox.setHgrow(columnaDerecha, Priority.ALWAYS);

        Label lblMazo = new Label("Elegir Roles");
        lblMazo.setStyle("-fx-text-fill: #d6cfc2; -fx-font-weight: bold;");

        FlowPane panelCartas = new FlowPane();
        panelCartas.setHgap(16);
        panelCartas.setVgap(16);

        panelCartas.getChildren().addAll(mazoCiudadanos);
        panelCartas.getChildren().addAll(mazoMafia);
        panelCartas.getChildren().addAll(mazoEspeciales);
        panelCartas.getChildren().add(cartaPadrino);

        columnaDerecha.getChildren().addAll(lblMazo, panelCartas);

        HBox divisionPantalla = new HBox(40, columnaIzquierda, columnaDerecha);
        this.getChildren().add(divisionPantalla);
    }

    private void actualizarResumenYLimites() {
        int cantidadJugadores = panelJugadores.cantidadJugadores();
        int ciudadanosElegidos = (int) mazoCiudadanos.stream().filter(BotonCartaRol::isSelected).count();
        int mafiososElegidos = (int) mazoMafia.stream().filter(BotonCartaRol::isSelected).count();
        int especialesElegidos = (int) mazoEspeciales.stream().filter(BotonCartaRol::isSelected).count();
        int padrinoElegido = cartaPadrino.isSelected() ? 1 : 0;

        int totalBandoMafia = mafiososElegidos + padrinoElegido;
        int totalBandoCiudadano = ciudadanosElegidos + especialesElegidos; // ¡NUEVO! Acá sumamos a los buenos especiales

        int totalEspeciales = especialesElegidos + padrinoElegido;
        int totalRoles = ciudadanosElegidos + totalBandoMafia + especialesElegidos;

        this.resumenConfiguracion.setText("Jugadores: " + cantidadJugadores + "\nRoles elegidos: " + totalRoles);

        obtenerTodasLasCartas().forEach(carta -> carta.setBloqueado(false, ""));

        if (totalRoles >= 12) {
            String msj12 = "La mesa ya tiene el máximo de 12 cartas permitidas.";
            aplicarBloqueo(mazoCiudadanos, msj12);
            aplicarBloqueo(mazoMafia, msj12);
            aplicarBloqueo(mazoEspeciales, msj12);
            if (!cartaPadrino.isSelected()) cartaPadrino.setBloqueado(true, msj12);
            return;
        }

        int maxMafiaPorMazo = (totalRoles < 6) ? 2 : 3;
        int maxEspecialesPorMazo = (totalRoles < 6) ? 1 : ((totalRoles < 9) ? 2 : 4);

        int maxMafiaPorCiudadanos = Math.max(0, totalBandoCiudadano - 1);

        if (totalBandoMafia >= maxMafiaPorCiudadanos) {
            String msj = "El bando mafioso debe ser estrictamente menor al bando ciudadano (incluyendo roles especiales). Agregá más ciudadanos primero.";
            aplicarBloqueo(mazoMafia, msj);
            if (!cartaPadrino.isSelected()) cartaPadrino.setBloqueado(true, msj);
        } else if (totalBandoMafia >= maxMafiaPorMazo) {
            String msj = "Ya alcanzaste el límite de mafiosos recomendados para una partida de este tamaño.";
            aplicarBloqueo(mazoMafia, msj);
            if (!cartaPadrino.isSelected()) cartaPadrino.setBloqueado(true, msj);
        }

        if (totalEspeciales >= maxEspecialesPorMazo) {
            String msj = "Ya alcanzaste el límite de roles especiales para una partida de este tamaño.";
            aplicarBloqueo(mazoEspeciales, msj);
            if (!cartaPadrino.isSelected()) cartaPadrino.setBloqueado(true, msj);
        }
    }

    private void aplicarBloqueo(List<BotonCartaRol> cartas, String motivo) {
        for (BotonCartaRol carta : cartas) {
            if (!carta.isSelected()) {
                carta.setBloqueado(true, motivo);
            }
        }
    }

    public List<String> obtenerNombres() {
        return panelJugadores.obtenerNombres();
    }

    public List<String> obtenerRolesSeleccionados() {
        return obtenerTodasLasCartas().stream()
                .filter(BotonCartaRol::isSelected)
                .map(BotonCartaRol::obtenerIdentificadorRol)
                .collect(Collectors.toList());
    }

    private List<BotonCartaRol> obtenerTodasLasCartas() {
        List<BotonCartaRol> todas = new ArrayList<>();
        todas.addAll(mazoCiudadanos);
        todas.addAll(mazoMafia);
        todas.addAll(mazoEspeciales);
        todas.add(cartaPadrino);
        return todas;
    }

    public boolean esConfiguracionValida(int cantidadJugadores) {
        int totalRoles = (int) obtenerTodasLasCartas().stream().filter(BotonCartaRol::isSelected).count();
        int ciudadanosElegidos = (int) mazoCiudadanos.stream().filter(BotonCartaRol::isSelected).count();
        int mafiososElegidos = (int) mazoMafia.stream().filter(BotonCartaRol::isSelected).count();
        int especialesElegidos = (int) mazoEspeciales.stream().filter(BotonCartaRol::isSelected).count() + (cartaPadrino.isSelected() ? 1 : 0);

        if (cantidadJugadores < 5 || cantidadJugadores > 12) return false;
        if (totalRoles != cantidadJugadores) return false;
        if (ciudadanosElegidos < 3) return false;

        if (cantidadJugadores <= 6) return mafiososElegidos >= 1 && mafiososElegidos <= 2 && especialesElegidos <= 1;
        if (cantidadJugadores <= 9) return mafiososElegidos >= 2 && mafiososElegidos <= 3 && especialesElegidos <= 2;
        return mafiososElegidos == 3 && especialesElegidos <= 4;
    }

    public String obtenerMensajeValidacion(int cantidadJugadores) {
        int totalRoles = (int) obtenerTodasLasCartas().stream().filter(BotonCartaRol::isSelected).count();
        int ciudadanosElegidos = (int) mazoCiudadanos.stream().filter(BotonCartaRol::isSelected).count();

        if (cantidadJugadores < 5 || cantidadJugadores > 12) return "La partida debe tener entre 5 y 12 jugadores.";
        if (totalRoles != cantidadJugadores) return "La cantidad de nombres debe coincidir con la cantidad de cartas elegidas.";
        if (ciudadanosElegidos < 3) return "Como mínimo deben quedar seleccionados 3 ciudadanos.";

        if (cantidadJugadores <= 6) return "Para 5 o 6 jugadores: 1 o 2 mafiosos y hasta 1 rol especial.";
        if (cantidadJugadores <= 9) return "Para 7, 8 o 9 jugadores: 2 o 3 mafiosos y hasta 2 roles especiales.";
        return "Para 10, 11 o 12 jugadores: 3 mafiosos y hasta 4 roles especiales.";
    }

    public int cantidadDeMafiosos() {
        return (int) mazoMafia.stream()
                .filter(BotonCartaRol::isSelected)
                .count();
    }
}