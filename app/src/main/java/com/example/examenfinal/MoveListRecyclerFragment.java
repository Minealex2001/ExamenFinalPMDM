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
import com.example.examenfinal.databinding.FragmentMoveListRecyclerBinding;
import com.example.examenfinal.databinding.ViewholderMoveListBinding;

import com.example.examenfinal.models.MoveListItem;

import java.util.List;

public class MoveListRecyclerFragment extends Fragment {
    private FragmentMoveListRecyclerBinding binding;
    private MovesViewModel movesViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla la vista del fragmento a partir del archivo de diseño XML correspondiente
        return (binding = FragmentMoveListRecyclerBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtiene una instancia del ViewModel y del NavController
        movesViewModel = new ViewModelProvider(requireActivity()).get(MovesViewModel.class);
        navController = Navigation.findNavController(view);
        // Crea un nuevo adaptador para el RecyclerView y lo asigna
        MovesAdapter movesAdapter = new MovesAdapter();
        binding.recyclerView.setAdapter(movesAdapter);

        // Observa los cambios en el ViewModel y actualiza la lista cuando estos ocurren
        movesViewModel.getAll().observe(getViewLifecycleOwner(), movesAdapter::setList);
    }

    class MovesAdapter extends RecyclerView.Adapter<MoveViewHolder> {
        List<MoveListItem> moveList;

        @NonNull
        @Override
        public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Infla la vista de cada elemento de la lista a partir del archivo de diseño XML correspondiente
            return new MoveViewHolder(ViewholderMoveListBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MoveViewHolder holder, int position) {
            // Obtiene el elemento de la lista correspondiente a la posición actual y lo asigna a la vista
            MoveListItem element = moveList.get(position);
            holder.binding.setMove(element);
            // Define un listener para el evento de clic en el elemento, que actualiza el ViewModel y navega a otro fragmento
            holder.itemView.setOnClickListener(v -> {
                movesViewModel.select(element);
                navController.navigate(R.id.action_moveListRecyclerFragment_to_moveDetailFragment);
            });
        }

        @Override
        public int getItemCount() {
            // Devuelve el número de elementos en la lista, o 0 si la lista es nula
            return moveList != null ? moveList.size() : 0;
        }

        public void setList(List<MoveListItem> moveList){
            // Actualiza la lista y notifica al RecyclerView que los datos han cambiado
            this.moveList = moveList;
            notifyDataSetChanged();
        }
    }

    static class MoveViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderMoveListBinding binding;

        public MoveViewHolder(ViewholderMoveListBinding binding) {
            super(binding.getRoot());
            // Guarda la referencia al binding para poder acceder a los elementos de la vista
            this.binding = binding;
        }
    }
}