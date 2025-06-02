package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.CartaSeleccionadaListener;
import edu.fiuba.algo3.controllers.CartaVista;
import edu.fiuba.algo3.controllers.ControladorPrincipal;
import edu.fiuba.algo3.modelo.carta.Carta;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class VistaCartas extends HBox {
    private final List<CartaVista> cartasVista;
    private final List<CartaVista> cartasSeleccionadas;
    private final ControladorPrincipal listener;
    public VistaCartas(List<CartaVista> cartasVista , ControladorPrincipal listener) {
        super(10);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-padding: 10;");
        this.cartasVista = cartasVista;
        this.cartasSeleccionadas = new ArrayList<>();
        this.listener = listener;
        inicializarCartas();
        }


    private void inicializarCartas() {
        this.getChildren().clear();
        System.out.println(cartasVista.size());
        for (CartaVista cartaVista : cartasVista) {
            ImageView imagenElemento = cartaVista.obtenerVistaImagen();
            aplicarEfectoLevantarYBajar(imagenElemento);
            imagenElemento.setOnMouseClicked(event -> {
                if (cartasSeleccionadas.contains(cartaVista)) {
                    deseleccionarCarta(cartaVista);

                } else {
                    seleccionarCarta(cartaVista);
                }
            });
            this.getChildren().add(imagenElemento);
        }
    }

    private void seleccionarCarta(CartaVista cartaVista) {
        cartasSeleccionadas.add(cartaVista);
        Carta carta = cartaVista.obtenerCarta();
        this.listener.onCartaSeleccionada( carta );
        ImageView imagen = cartaVista.obtenerVistaImagen();
        System.out.println("Carta seleccionada: " + cartaVista.obtenerCarta().obtenerNombre());
        aplicarEfectoLevantarYBajar(imagen);
    }

    private void deseleccionarCarta(CartaVista cartaVista) {
        cartasSeleccionadas.remove(cartaVista);
        Carta carta = cartaVista.obtenerCarta();
        this.listener.deselecionarCarta( carta );
        cartaVista.obtenerVistaImagen().setEffect(null);
    }

    private void aplicarEfectoLevantarYBajar(ImageView imagenElemento) {
        imagenElemento.setOnMouseEntered(event -> imagenElemento.setTranslateY(-10));
        imagenElemento.setOnMouseExited(event -> imagenElemento.setTranslateY(0));
    }

    public List<Carta> getCartasSeleccionadas() {
        List<Carta> cartas = new ArrayList<>();
        for (CartaVista cartaVista : cartasSeleccionadas) {
            cartas.add(cartaVista.obtenerCarta());
        }
        return cartas;
    }
}
