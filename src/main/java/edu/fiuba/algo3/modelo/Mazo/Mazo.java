package edu.fiuba.algo3.modelo.Mazo;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import edu.fiuba.algo3.modelo.Prooveedor.ProveedorDeCartas;
import edu.fiuba.algo3.modelo.Seleccionable.Seleccionable;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.comodin.Comodin;

public class Mazo implements ProveedorDeCartas {
    final int TAMANIOMANO = 8;
    private List<Carta> cartas;

    public Mazo(ProveedorDeCartas proveedor) {
        this.cartas = proveedor.generarCartas();
        this.mezclar();
    }

    public void mezclar() {
        Collections.shuffle(this.cartas);
    }

    public List<Carta> generarCartas() {
        List<Carta> cartasJugador = new ArrayList<>();

        for (int i = 0; i < TAMANIOMANO; i++) {
            cartasJugador.add(cartas.get(i));
        }
        cartas.subList(0, TAMANIOMANO).clear();

        return cartasJugador;
    }

    public List<Carta> repartirCartas() {        //Nunca usamos repartir en mano
        this.mezclar();
        return this.generarCartas();
    }

    private void repartirConUnaCantidad(List<Carta> cartasJugador) {
        int cantidadDeCartas;

        cantidadDeCartas = TAMANIOMANO - cartasJugador.size();

        if (cantidadDeCartas > this.cartas.size()) {
            throw new TamanioMazoInsuficiente();
        }
        this.mezclar();
        List<Carta> manoJugador = new ArrayList<>(this.cartas.subList(0, cantidadDeCartas));

        cartasJugador.addAll(manoJugador);
    }

    public void guardar(Seleccionable carta){
        //primero tendría que verificar que es una carta
        this.cartas.add((Carta) carta);
    }

    public void reponer(List<Carta> cartasJugador) {
        repartirConUnaCantidad(cartasJugador);
    }

    public int obtenerCantidadDeCartas() {
        return this.cartas.size();
    }

    public void agregarAlMazo(Carta carta){
        this.cartas.add(carta);
    }




}
