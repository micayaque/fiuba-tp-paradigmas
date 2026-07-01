package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.controlador.AccionIniciarJuego;
import edu.fiuba.paradigmas.vistas.componentes.BotonPersonalizado;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ConfiguracionVista extends VBox
{
    private final TextField campoNombre;
    private final ListView<String> listaNombres;
    private final Spinner<Integer> spinnerMafiosos;
    private final CheckBox detective;
    private final CheckBox medico;
    private final CheckBox padrino;
    private final CheckBox sheriff;
    private final Label resumenConfiguracion;
    private final Button agregar;
    private final BotonPersonalizado iniciar;


    public ConfiguracionVista(){
        this.campoNombre = new TextField();
        this.campoNombre.setPromptText("Ingrese el nombre del jugador");

        this.agregar = new Button("Agregar a la partida");
        this.listaNombres = new ListView<>();
        this.spinnerMafiosos = new Spinner<>(1, 1, 1);
        this.spinnerMafiosos.setEditable(false);
        this.detective = new CheckBox("Detective");
        this.medico = new CheckBox("Médico");
        this.padrino = new CheckBox("Padrino");
        this.sheriff = new CheckBox("Sheriff");
        this.resumenConfiguracion = new Label("Elegí jugadores para habilitar la configuración.");
        this.iniciar = new BotonPersonalizado("Iniciar partida");


        agregar.setOnAction(e -> {
            String nombre = campoNombre.getText().trim();
            if (!nombre.isEmpty()){
                listaNombres.getItems().add(nombre);
                campoNombre.clear();
            actualizarConfiguracionDisponible();
            }
        });

        this.spinnerMafiosos.valueProperty().addListener((observable, anterior, nueva) -> actualizarResumenConfiguracion());
        this.detective.selectedProperty().addListener((observable, anterior, nueva) -> actualizarResumenConfiguracion());
        this.medico.selectedProperty().addListener((observable, anterior, nueva) -> actualizarResumenConfiguracion());
        this.padrino.selectedProperty().addListener((observable, anterior, nueva) -> actualizarResumenConfiguracion());
        this.sheriff.selectedProperty().addListener((observable, anterior, nueva) -> actualizarResumenConfiguracion());

        GridPane configuracionRoles = new GridPane();
        configuracionRoles.setHgap(10);
        configuracionRoles.setVgap(8);
        configuracionRoles.add(new Label("Mafiosos"), 0, 0);
        configuracionRoles.add(this.spinnerMafiosos, 1, 0);
        configuracionRoles.add(this.detective, 0, 1);
        configuracionRoles.add(this.medico, 1, 1);
        configuracionRoles.add(this.padrino, 0, 2);
        configuracionRoles.add(this.sheriff, 1, 2);

        this.getChildren().addAll(
            new Label("Configuracion de partida"),
            new Label("Jugadores"),
            campoNombre,
            agregar,
            new Label("Jugadores anotados"),
            listaNombres,
            new Label("Composicion del mazo"),
            configuracionRoles,
            resumenConfiguracion,
            iniciar
            );

        actualizarConfiguracionDisponible();
    }

    public List<String> obtenerNombres(){
        return listaNombres.getItems();
    }

    public int cantidadDeMafiosos() {
        return this.spinnerMafiosos.getValue();
    }

    public void alPresionarIniciar(AccionIniciarJuego accion){
        iniciar.setOnAction(e -> {
            List<String> nombres = obtenerNombres();
            int cantMafiosos = cantidadDeMafiosos();
            boolean usaPadrino = padrino.isSelected();
            boolean usaDetective = detective.isSelected();
            boolean usaMedico = medico.isSelected();
            boolean usaSheriff = sheriff.isSelected();

            accion.iniciar(nombres, cantMafiosos, usaPadrino, usaDetective, usaMedico, usaSheriff);
        });
    }

    private void actualizarConfiguracionDisponible() {
        int cantidadJugadores = this.listaNombres.getItems().size();
        int maximoMafiosos = Math.max(1, cantidadJugadores - 1);
        SpinnerValueFactory.IntegerSpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, maximoMafiosos, Math.min(this.spinnerMafiosos.getValue(), maximoMafiosos));
        this.spinnerMafiosos.setValueFactory(factory);
        actualizarResumenConfiguracion();
    }

    private void actualizarResumenConfiguracion() {
        int cantidadJugadores = this.listaNombres.getItems().size();
        int cantidadMafiosos = this.spinnerMafiosos.getValue();
        StringBuilder builder = new StringBuilder();
        builder.append("Jugadores: ").append(cantidadJugadores).append(" | Mafiosos: ").append(cantidadMafiosos);
        builder.append(" | Especiales: ");
        if (this.detective.isSelected()) builder.append("Detective ");
        if (this.medico.isSelected()) builder.append("Médico ");
        if (this.padrino.isSelected()) builder.append("Padrino ");
        if (this.sheriff.isSelected()) builder.append("Sheriff ");
        if (!this.detective.isSelected() && !this.medico.isSelected() && !this.padrino.isSelected() && !this.sheriff.isSelected()) {
            builder.append("ninguno");
        }
        this.resumenConfiguracion.setText(builder.toString());
    }

    public void mostrarError(String mensaje){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de configuracion");
        alert.setHeaderText("Mazo o cantidad invalida");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
