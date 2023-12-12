package com.example.examenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenfinal.databinding.FragmentMoveDetailBinding;
import com.example.examenfinal.models.Move;
import com.example.examenfinal.models.PokemonListItem;


public class MoveDetailFragment extends Fragment {

    // Binding para interactuar con los elementos de la vista
    private FragmentMoveDetailBinding binding;
    // ViewModel para manejar los datos de los movimientos
    MovesViewModel movesViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtiene una instancia del ViewModel
        movesViewModel = new ViewModelProvider(requireActivity()).get(MovesViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Observa los cambios en el ViewModel y actualiza la vista cuando estos ocurren
        movesViewModel.getSelected().observe(getViewLifecycleOwner(), m -> binding.setMove(m));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla la vista del fragmento a partir del archivo de diseño XML correspondiente
        return (binding = FragmentMoveDetailBinding.inflate(inflater, container, false)).getRoot();

    }

}