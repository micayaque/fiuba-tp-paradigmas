package edu.fiuba.algo3.controllers.Factory;
import edu.fiuba.algo3.controllers.Parseados.*;
import edu.fiuba.algo3.controllers.Parser.ParserComodin;
import edu.fiuba.algo3.controllers.Parser.ParserJuego;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Tienda;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FactoryRondas {
    String ruta;
    FactoryDeTarot factoryDeTarot;
    FactoryDeMazo factoryDeMazo;
    FactoryComodines factoryComodines;
    ParserComodin parserComodin;


    public FactoryRondas(String ruta, FactoryDeTarot factoryDeTarot, FactoryDeMazo factoryDeMazo, FactoryComodines factoryComodines) {
        this.ruta = ruta;
        this.factoryDeTarot = factoryDeTarot;
        this.factoryDeMazo = factoryDeMazo;
        this.factoryComodines = factoryComodines;
    }


    public List<Ronda> generarRondas() throws IOException {
        List<Ronda> rondas = new ArrayList<>();

        List<Tarot> tarotsGenerados = factoryDeTarot.generarTarots();
        List<Carta> cartasGeneradas = factoryDeMazo.generarCartas();
        List<Comodin> comodinesGenerados = factoryComodines.generarComodines();

        List<RondaParseada> rondasFake = ParserJuego.parseRondas(this.ruta);
        for (RondaParseada fakeRonda : rondasFake) {
            TiendaParseado tiendaFake = fakeRonda.getTienda();


            List<String> nombresTarots = tiendaFake.getTarots() != null
                    ? tiendaFake.getTarots().stream().map(TarotParseado::getNombre).collect(Collectors.toList())
                    : new ArrayList<>();
            List<Tarot> tarotsFiltrados = tarotsGenerados.stream()
                    .filter(tarot -> nombresTarots.contains(tarot.obtenerNombre()))
                    .collect(Collectors.toList());

            Carta cartaFiltrada = null;
            if (tiendaFake.getCarta() != null) {
                String nombreCarta = tiendaFake.getCarta().getNumero(); // Obtener el nombre de la carta
                cartaFiltrada = cartasGeneradas.stream()
                        .filter(carta -> carta.obtenerNombre().equals(nombreCarta)) // Buscar coincidencia exacta
                        .findFirst()
                        .orElse(null);
            }

            List<String> nombresComodines = tiendaFake.getComodines() != null
                    ? tiendaFake.getComodines().stream().map(ComodinParseado::getNombre).collect(Collectors.toList())
                    : new ArrayList<>();
            Map<String, Comodin> comodinesMap = comodinesGenerados.stream()
                    .filter(comodin -> nombresComodines.contains(comodin.obtenerNombre()))
                    .collect(Collectors.toMap(Comodin::obtenerNombre, comodin -> comodin, (comodin1, comodin2) -> comodin1));
            List<Comodin> comodinesFiltrados = new ArrayList<>(comodinesMap.values());

            Tienda tienda = new Tienda(tarotsFiltrados, comodinesFiltrados, cartaFiltrada != null ? Collections.singletonList(cartaFiltrada) : new ArrayList<>());

            Ronda ronda = ParserJuego.parsearDeFakeRondaARonda(fakeRonda, tienda);

            rondas.add(ronda);
        }

        return rondas;
    }

}




