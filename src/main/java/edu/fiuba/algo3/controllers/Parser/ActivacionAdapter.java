package edu.fiuba.algo3.controllers.Parser;
import com.google.gson.*;
import java.lang.reflect.Type;

public class ActivacionAdapter implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
            return json.getAsString();
        }

        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            return context.deserialize(jsonObject, Object.class);
        }

        return null;
    }
}
