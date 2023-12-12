package com.example.examenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenfinal.databinding.FragmentItemListRecyclerBinding;
import com.example.examenfinal.databinding.ViewholderItemListBinding;
import com.example.examenfinal.models.ItemListDetails;

import java.util.List;

// Clase que representa un Fragmento que contiene un RecyclerView para mostrar una lista de items
public class ItemListRecyclerFragment extends Fragment {
    // Binding para interactuar con los elementos de la vista
    private FragmentItemListRecyclerBinding binding;
    // ViewModel para manejar los datos de los items
    private ItemsViewModel itemsViewModel;
    // Controlador de navegación para manejar la navegación entre fragmentos
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla la vista del fragmento a partir del archivo de diseño XML correspondiente
        return (binding = FragmentItemListRecyclerBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtiene una instancia del ViewModel y del NavController
        itemsViewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        navController = Navigation.findNavController(view);
        // Crea un nuevo adaptador para el RecyclerView y lo asigna
        ItemAdapter itemAdapter = new ItemAdapter();
        binding.itemRecyclerView.setAdapter(itemAdapter);

        // Cuando cambia el viewmodel se actualiza la lista con setList(List<ItemListDetails> itemList)
        itemsViewModel.getAll().observe(getViewLifecycleOwner(), itemAdapter::setList);
    }

    // Clase que representa un adaptador para el RecyclerView
    class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        // Lista de detalles de items
        List<ItemListDetails> itemsList;

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Infla la vista de cada elemento de la lista a partir del archivo de diseño XML correspondiente
            return new ItemViewHolder(ViewholderItemListBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            // Obtiene el elemento de la lista correspondiente a la posición actual y lo asigna a la vista
            ItemListDetails element = itemsList.get(position);
            holder.binding.setItem(element);
            // Define un listener para el evento de clic en el elemento, que actualiza el ViewModel y navega a otro fragmento
            holder.itemView.setOnClickListener(v -> {
                itemsViewModel.select(element);
                navController.navigate(R.id.action_itemListRecyclerFragment_to_itemsDetailsFragment);
            });
        }

        @Override
        public int getItemCount() {
            // Devuelve el número de elementos en la lista, o 0 si la lista es nula
            return itemsList != null ? itemsList.size() : 0;
        }

        public void setList(List<ItemListDetails> itemList){
            // Actualiza la lista y notifica al RecyclerView que los datos han cambiado
            this.itemsList = itemList;
            notifyDataSetChanged();
        }
    }

    // Clase que representa un ViewHolder para el RecyclerView
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        // Binding para interactuar con los elementos de la vista
        private final ViewholderItemListBinding binding;

        public ItemViewHolder(ViewholderItemListBinding binding) {
            super(binding.getRoot());
            // Guarda la referencia al binding para poder acceder a los elementos de la vista
            this.binding = binding;
        }
    }
}