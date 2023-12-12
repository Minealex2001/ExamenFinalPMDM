package com.example.examenfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.examenfinal.models.Move;
import com.example.examenfinal.models.MoveListItem;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;

public class MovesViewModel extends AndroidViewModel {
    // LiveData para el movimiento seleccionado
    MutableLiveData<Move> selectedMoveMutableLiveData = new MutableLiveData<>();
    // LiveData para la lista de movimientos
    MutableLiveData<List<MoveListItem>> listElementosMutableLiveData = new MutableLiveData<>();
    // Elemento seleccionado de la lista de movimientos
    MoveListItem selected;

    public MovesViewModel(@NonNull Application application) {
        super(application);
        // Obtiene la lista de movimientos de la API de Pokemon
        PokeAPI.getMoveList(listElementosMutableLiveData);
    }

    // Devuelve la lista de movimientos
    MutableLiveData<List<MoveListItem>> getAll(){
        return listElementosMutableLiveData;
    }

    // Establece el movimiento seleccionado
    public void select(MoveListItem moveListItem) {
        selected = moveListItem;
    }

    // Devuelve el movimiento seleccionado
    public MutableLiveData<Move> getSelected() {
        // Obtiene los detalles del movimiento seleccionado de la API de Pokemon
        PokeAPI.getMove(selected.getName(), selectedMoveMutableLiveData);
        return selectedMoveMutableLiveData;
    }
}
