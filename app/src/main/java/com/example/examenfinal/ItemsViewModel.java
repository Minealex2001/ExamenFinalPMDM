package com.example.examenfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.examenfinal.models.Item;
import com.example.examenfinal.models.ItemListDetails;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;
import java.util.Random;

// Clase que representa un ViewModel para los items en la aplicación
public class ItemsViewModel extends AndroidViewModel {
    // LiveData para el item seleccionado
    MutableLiveData<Item> selectedItemMutableLiveData = new MutableLiveData<>();
    // LiveData para la lista de detalles de items
    MutableLiveData<List<ItemListDetails>> listElementosMutableLiveData = new MutableLiveData<>();
    // Detalle del item seleccionado
    ItemListDetails selected;

    // Constructor de la clase
    public ItemsViewModel(@NonNull Application application) {
        super(application);
        // Obtiene la lista de detalles de items de la API de Pokemon
        PokeAPI.getItemList(listElementosMutableLiveData);
    }

    // Método para obtener la lista de detalles de items
    MutableLiveData<List<ItemListDetails>> getAll(){
        return listElementosMutableLiveData;
    }

    // Método para establecer el detalle del item seleccionado
    public void select(ItemListDetails itemListDetails) {
        selected = itemListDetails;
    }

    // Método para obtener el item seleccionado
    public MutableLiveData<Item> getSelected() {
        // Obtiene los detalles del item seleccionado de la API de Pokemon
        PokeAPI.getItem(selected.getName(), selectedItemMutableLiveData);
        return selectedItemMutableLiveData;
    }

        // Método para seleccionar un elemento aleatorio
        public void selectRandom() {
            List<ItemListDetails> list = listElementosMutableLiveData.getValue();
            if (list != null && !list.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(list.size());
                select(list.get(randomIndex));
            }
        }
    }