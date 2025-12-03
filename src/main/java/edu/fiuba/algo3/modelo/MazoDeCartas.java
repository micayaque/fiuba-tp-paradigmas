package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Cartas.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MazoDeCartas {
    ArrayList<CartaDesarrollo> cartas = new ArrayList<>();

    private static final Map<String, Class<? extends CartaDesarrollo>> REGISTRO_TIPOS;

    static {
        REGISTRO_TIPOS = new HashMap<>();
        REGISTRO_TIPOS.put("Caballero", CartaCaballero.class);
        REGISTRO_TIPOS.put("Monopolio", CartaMonopolio.class);
        REGISTRO_TIPOS.put("Descubrimiento", CartaDescubrimiento.class);
        REGISTRO_TIPOS.put("Carreteras", CartaConstruccionCarreteras.class);
        // Punto de Victoria no es jugable manualmente
    }

    public void agregarCarta(CartaDesarrollo carta) {
        if (carta == null) throw new IllegalArgumentException("Carta no puede ser null");
        cartas.add(carta);
    }

    public CartaDesarrollo agarrarCarta(int indice) {
        return cartas.get(indice);
    }

    public <T extends CartaDesarrollo> int cantidadDeTipo(Class<T> tipo) {
        int cantidad = 0;

        for (CartaDesarrollo carta : cartas) {
            if (carta.getClass() == tipo) {
                cantidad++;
            }
        }

        return cantidad;
    }



    // Devuelve una instancia concreta que se pueda usar (para el controlador)
    public CartaDesarrollo agarrarCartaHabilitada(Class<? extends CartaDesarrollo> tipo) {
        return cartas.stream()
                .filter(c -> tipo.isInstance(c) && c.estaDisponible())
                .findFirst()
                .orElse(null);
    }

    public boolean existeCartaJugable(String nombreCarta) {
        Class<? extends CartaDesarrollo> tipo = REGISTRO_TIPOS.get(nombreCarta);

        if (tipo == null) return false;

        return cartas.stream()
                .filter(c -> tipo.isInstance(c))
                .anyMatch(CartaDesarrollo::estaDisponible);
    }

    public CartaDesarrollo buscarCartaJugable(String nombreCarta) {
        Class<? extends CartaDesarrollo> tipo = REGISTRO_TIPOS.get(nombreCarta);

        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de carta '" + nombreCarta + "' no existe en el mazo.");
        }

        return cartas.stream()
                .filter(c -> tipo.isInstance(c))      // Es del tipo correcto (ej: Caballero)
                .filter(CartaDesarrollo::estaDisponible) // Y está habilitada (estado)
                .findFirst()
                .orElse(null); // Retorna null si no hay ninguna jugable
    }

    public void actualizarEstadoDeCartas() {
        for (CartaDesarrollo carta : this.cartas) {
            carta.nuevoTurno();
        }
    }

    public int contarPor(Predicate<CartaDesarrollo> criterio) {
        int cantidad = 0;
        for (CartaDesarrollo carta : cartas) {
            if (criterio.test(carta)) {
                cantidad++;
            }
        }
        return cantidad;
    }
}


