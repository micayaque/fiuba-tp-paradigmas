package edu.fiuba.algo3.controllers.Parser;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.controllers.Factory.PaloFactory;
import edu.fiuba.algo3.controllers.Parseados.CartaParseada;
import edu.fiuba.algo3.controllers.Parseados.MazoParseado;
import edu.fiuba.algo3.modelo.Palo.Palo;
import edu.fiuba.algo3.modelo.carta.Carta;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class ParserMazo {

    public static List<CartaParseada> convertirDeJsonAFakeCartas(String direccionDeJson) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(direccionDeJson)) {
            Type mazoType = new TypeToken<MazoParseado>(){}.getType();
            MazoParseado mazo = gson.fromJson(reader, mazoType);

            return mazo.getMazo();
        } catch (IOException e) {
            throw new ErrorAlParsearJson();
        }
    }



    public static List<Carta> parsearDeFakeCartaACarta(List<CartaParseada> fakeCartas) {
        List<Carta> cartas = new ArrayList<>();

        for (CartaParseada fakeCarta : fakeCartas) {
            String paloStr = fakeCarta.getPalo();
            String valorStr = fakeCarta.getNumero();
            String puntosStr = fakeCarta.getPuntos();
            String multiplicadorStr = fakeCarta.getMultiplicador();
            Palo palo = PaloFactory.crearPalo(paloStr);
            int valor = traducirNumeroACarta(valorStr);
            int puntos = Integer.parseInt(puntosStr);
            int multiplicador = Integer.parseInt(multiplicadorStr);

            Carta carta = new Carta(palo, valor, puntos, multiplicador);


            cartas.add(carta);
        }

        return cartas;
    }

    private static int traducirNumeroACarta(String numero) {
        switch (numero) {
            case "As": return 14;
            case "Rey": return 13;
            case "Reina": return 12;
            case "Jota": return 11;
            default: return Integer.parseInt(numero);
        }
    }
}




