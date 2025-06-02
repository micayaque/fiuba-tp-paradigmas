package edu.fiuba.algo3.controllers.Parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.controllers.Factory.ActivacionFactory;
import edu.fiuba.algo3.controllers.Factory.EfectoFactory;
import edu.fiuba.algo3.controllers.Parseados.ComodinParseado;
import edu.fiuba.algo3.modelo.activacion.Activacion;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.efectos.Efecto;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParserComodin {

    public static List<ComodinParseado> convertirDeJsonAFakeComodin(String direccionDeJson) {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(direccionDeJson)) {

            Type mapType = new TypeToken<Map<String, JsonObject>>() {}.getType();
            Map<String, JsonObject> data = gson.fromJson(reader, mapType);

            List<ComodinParseado> comodines = new ArrayList<>();

            for (Map.Entry<String, JsonObject> entry : data.entrySet()) {
                JsonObject comodinData = entry.getValue();
                if (comodinData.has("comodines")) {
                    procesarComodinesRecursivamente(comodinData.getAsJsonArray("comodines"), comodines, gson);
                }
            }
            return comodines;

        } catch (IOException e) {
            throw new ErrorAlParsearJson();
        }
    }

    private static void procesarComodinesRecursivamente(JsonArray jsonArray, List<ComodinParseado> comodines, Gson gson) {
        Type comodinType = new TypeToken<ComodinParseado>() {}.getType();

        for (JsonElement element : jsonArray) {
            JsonObject comodinObj = element.getAsJsonObject();

            if (comodinObj.has("comodines")) {
                procesarComodinesRecursivamente(comodinObj.getAsJsonArray("comodines"), comodines, gson);
            } else {
                ComodinParseado comodin = gson.fromJson(comodinObj, comodinType);
                comodines.add(comodin);
            }
        }
    }

    public static List<Comodin> parsearDeFakeComodinAComodin(List<ComodinParseado> fakeComodines) {
        List<Comodin> comodines = new ArrayList<>();

        for (ComodinParseado fakeComodin : fakeComodines) {
            String nombreStr = fakeComodin.getNombre();
            String descripcion = fakeComodin.getDescripcion();
            Object activacionData = fakeComodin.getActivacion();
            Activacion activacion = ActivacionFactory.crearActivacion(activacionData);
            List<Activacion> listaActivaciones = new ArrayList<>();
            listaActivaciones.add(activacion);
            Efecto efecto = EfectoFactory.crearEfecto(fakeComodin.getEfecto());
            Comodin comodin = new Comodin(nombreStr, descripcion, efecto, listaActivaciones);
            comodines.add(comodin);
        }

        return comodines;
    }
}
