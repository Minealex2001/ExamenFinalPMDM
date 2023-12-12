package com.example.examenfinal.Deserializado;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

// Clase que implementa JsonDeserializer para deserializar la categoría de un item
public class CategoryDeserializer implements JsonDeserializer<String> {
    @Override
    // Método para deserializar el elemento JSON
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        // Retorna el valor del campo "name" del objeto JSON
        return json.getAsJsonObject().get("name").getAsString();
    }
}