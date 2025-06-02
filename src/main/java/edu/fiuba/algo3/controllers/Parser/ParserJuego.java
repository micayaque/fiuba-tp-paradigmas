package edu.fiuba.algo3.controllers.Parser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.*;
import edu.fiuba.algo3.controllers.Parseados.*;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import  edu.fiuba.algo3.modelo.ronda.Tienda;

public class ParserJuego {
    String filePath;


    public static List<RondaParseada> parseRondas(String filePath)  throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Object.class, new ActivacionAdapter())
                .create();

        JuegoParseado juego = gson.fromJson(jsonString, JuegoParseado.class);

        return juego.getRondas();
    }

    public static Ronda parsearDeFakeRondaARonda(RondaParseada fakeRonda, Tienda tienda){
        int nro = fakeRonda.getNro() ;
        int manos = fakeRonda.getManos();
        int descartes = fakeRonda.getDescarte();
        int puntajeASuperar = fakeRonda.getPuntajeASuperar();
        return new Ronda(tienda, nro, manos, descartes, puntajeASuperar);
    }
}
