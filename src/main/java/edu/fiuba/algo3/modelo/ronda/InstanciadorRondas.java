package edu.fiuba.algo3.modelo.ronda;
import java.util.List;

public class InstanciadorRondas {
    private List<Ronda> rondas;
/*
    public InstanciadorRondas(String filePath) {
        rondas = new ArrayList<>();
        crearRondas(filePath);
    }

    public void crearRondas(String filePath) {
        List<Map<String, Object>> rondasData = Parser.parsearRondas(filePath);

        List<Ronda> rondas = new ArrayList<Ronda>();
        for (Map<String, Object> datos : rondasData) {
            Ronda ronda = crearRonda(datos);
            rondas.add(ronda);
        }
    }

    private List<Comodin> crearComodines(List<Map<String, Object>> listaComodin) {
        List<Comodin> comodines = new ArrayList<>(Comodin);
        if (listaComodin != null) {
            for (Map<String, Object> comodinData : listaComodin) {
                String nombre = (String) comodinData.get("nombre");
                String descripcion = (String) comodinData.get("descripcion");
                Map<String, String> activacion = (Map<String, String>) comodinData.get("activacion");
                Map<String, Object> puntajeData = (Map<String, Object>) comodinData.get("efecto");
                int puntos = (int) puntajeData.get("puntos");
                int multiplicador = (int) puntajeData.get("multiplicador");
                Puntaje puntaje = new Puntaje(puntos, multiplicador);

                Comodin comodin = new Comodin(nombre, descripcion, activacion, puntaje);
                comodines.add(comodin);
            }
        }
        return comodines;
    }

    private List<Tarot> crearTarots(List<Map<String, Object>> listaTarots) {
        List<Tarot> tarots = new ArrayList<>(Tarot);
        if (listaTarots != null) {
            for (Map<String, Object> tarotData : listaTarots) {
                String nombre = (String) tarotData.get("nombre");
                String descripcion = (String) tarotData.get("descripcion");
                Map<String, Object> puntajeData = (Map<String, Object>) tarotData.get("efecto");
                int puntos = (int) puntajeData.get("puntos");
                int multiplicador = (int) puntajeData.get("multiplicador");
                Puntaje puntaje = new Puntaje(puntos, multiplicador);

                String sobre = (String) tarotData.get("sobre");
                String ejemplar = (String) tarotData.get("ejemplar");

                Tarot tarot = new Tarot(nombre, descripcion, sobre, ejemplar, puntaje);                tarots.add(tarot);
            }
        }
        return tarots;
    }


    private Ronda crearRonda(Map<String, Object> datos) {
        List<Map<String, Object>> listaComodines = (List<Map<String, Object>>) datos.get("comodines");
        List<Map<String, Object>> listaTarots = (List<Map<String, Object>>) datos.get("tarots");

        List<Comodin> comodines = crearComodines(listaComodines);
        List<Tarot> tarots = crearTarots(listaTarots);

        Tienda tienda = new Tienda(tarots, comodines);

        return new Ronda(tienda);
    }
    public Ronda obtenerRonda(int indice) {
        if (indice >= 0 && indice < rondas.size()) {
            return rondas.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice de ronda no válido");
        }
    }

    public List<Ronda> obtenerTodasLasRondas() {
        return rondas;
    }

 */
}












