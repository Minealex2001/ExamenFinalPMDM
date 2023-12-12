package com.example.examenfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.examenfinal.databinding.FragmentItemsDetailsBinding;

public class ItemsDetailsFragment extends Fragment {

    private FragmentItemsDetailsBinding binding;
    private ItemsViewModel itemsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializamos el ViewModel
        itemsViewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Observamos los cambios en el item seleccionado y actualizamos la vista
        itemsViewModel.getSelected().observe(getViewLifecycleOwner(), obj -> binding.setItem(obj));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos la vista del fragmento
        return (binding = FragmentItemsDetailsBinding.inflate(inflater, container, false)).getRoot();

    }


}