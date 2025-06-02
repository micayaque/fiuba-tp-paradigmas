package edu.fiuba.algo3.controllers.Factory;

import edu.fiuba.algo3.modelo.Mano.Mano;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;
import edu.fiuba.algo3.modelo.activacion.Activacion;
import edu.fiuba.algo3.modelo.activacion.ActivacionDescarte;
import edu.fiuba.algo3.modelo.activacion.ActivacionManoDePoker;
import edu.fiuba.algo3.modelo.activacion.ActivacionSiempre;

import java.util.Map;

public class ActivacionFactory {

    public static Activacion crearActivacion(Object activacion) {
        if (activacion instanceof String) {
            return crearActivacionDesdeString((String) activacion);
        } else if (activacion instanceof Map) {
            Map<String, Object> activacionMap = (Map<String, Object>) activacion;
            return crearActivacionDesdeMap(activacionMap);
        } else {
            throw new IllegalArgumentException("Tipo de activación no soportado: " + activacion.getClass().getName());
        }
    }

    public static Activacion crearActivacionDesdeString(String tipo) {
        switch (tipo) {
            case "Descarte":
                return new ActivacionDescarte();
            case "Siempre":
                return new ActivacionSiempre();
            default:
                throw new IllegalArgumentException("Tipo de activación desconocido: " + tipo);
        }
    }

    private static Activacion crearActivacionDesdeMap(Map<String, Object> activacionMap) {
        FactoryDeManosDePoker factoryDeManosDePoker = new FactoryDeManosDePoker();
        if (activacionMap.containsKey("Mano Jugada")) {
            String valor = activacionMap.get("Mano Jugada").toString();
            ManoDePoker manoEsperada = factoryDeManosDePoker.generarManoDePokerPorNombre(valor);
            return new ActivacionManoDePoker(manoEsperada);

        }
        if (activacionMap.containsKey("1 en")) {
            double valor = (double) activacionMap.get("1 en");
            ManoDePoker manoEsperada = factoryDeManosDePoker.generarManoDePokerPorProbabilidad(valor);
            return new ActivacionManoDePoker(manoEsperada);

        }
        throw new IllegalArgumentException("No se encontró un tipo válido de activación en el mapa");
    }
}
