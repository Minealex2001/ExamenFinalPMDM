package com.example.examenfinal.models;

import com.example.examenfinal.Deserializado.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

// Clase que representa un Item en la aplicación
public class Item {
    // Nombre del item
    private String name;
    // Categoría del item, se utiliza un deserializador personalizado para obtener el valor
    @SerializedName("category")
    @JsonAdapter(CategoryDeserializer.class)
    private String category;
    // Costo del item
    private String cost;
    // Efecto del item, se utiliza un deserializador personalizado para obtener el valor
    @SerializedName("effect_entries")
    @JsonAdapter(EfectosDeserializer.class)
    private String effect;

    // Método getter para obtener el nombre del item
    public String getName() {
        return name;
    }

    // Método getter para obtener la categoría del item
    public String getCategory() {
        return category;
    }

    // Método getter para obtener el costo del item
    public String getCost() {
        return cost;
    }

    // Método getter para obtener el efecto del item
    public String getEffect() {
        return effect;
    }

    // Método para obtener la URL de la imagen del item
    public String getUrl() {
        String index = this.name;
        return String.format("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/%s.png", index);
    }
}