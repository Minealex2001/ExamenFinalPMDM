package com.example.examenfinal.Deserializado;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

// Clase que implementa JsonDeserializer para deserializar los efectos de un item
public class EfectosDeserializer implements JsonDeserializer<String> {
    @Override
    // Método para deserializar el elemento JSON
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        StringBuilder str = new StringBuilder();
        JsonArray array = json.getAsJsonArray();

        // Itera sobre cada elemento del array JSON
        for (JsonElement element : array) {
            // Agrega el valor del campo "short_effect" del objeto JSON al StringBuilder
            str.append(element.getAsJsonObject().get("short_effect").getAsString());
        }

        // Retorna el StringBuilder como un String
        return str.toString();
    }
}
