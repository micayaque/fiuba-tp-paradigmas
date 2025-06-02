package edu.fiuba.algo3.controllers.Parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.controllers.Factory.FactoryModificador;
import edu.fiuba.algo3.controllers.Parseados.EfectoParseado;
import edu.fiuba.algo3.controllers.Parseados.TarotParseado;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ParserTarot {

    public static List<TarotParseado> convertirDeJsonAFakeTarot(String ruta) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ruta)) {
            Type tarotsType = new TypeToken<ListaTarotParseado>() {
            }.getType();
            ListaTarotParseado tarots = gson.fromJson(reader, tarotsType);
            return tarots.getTarots();

        } catch (IOException e) {
            throw new ErrorAlParsearJson();
        }
    }

    public static List<Tarot> parsearDeFakeTarotATarot(List<TarotParseado> tarotsFake) {
        List<Tarot> tarots = new ArrayList<>();

        for (TarotParseado fakeTarot : tarotsFake) {
            String nombre = fakeTarot.getNombre();
            String descripcion = fakeTarot.getDescripcion();
            EfectoParseado efectoParseado = fakeTarot.getEfecto();
            Puntaje efecto = new Puntaje(efectoParseado.getPuntos(), efectoParseado.getMultiplicador());
            Modificador modificador= FactoryModificador.generarModificador(fakeTarot.getSobre(), fakeTarot.getEjemplar());

            Tarot tarot = new Tarot(nombre, descripcion, efecto, modificador);


            tarots.add(tarot);
        }

        return tarots;
    }
}
