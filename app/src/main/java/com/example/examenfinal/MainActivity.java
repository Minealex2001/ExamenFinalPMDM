package com.example.examenfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.examenfinal.databinding.FragmentDrawerBinding;
import com.example.examenfinal.models.MoveListItem;
import com.example.examenfinal.pokeapi.PokeAPI;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ItemsViewModel itemsViewModel;
    FragmentDrawerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView((binding = FragmentDrawerBinding.inflate(getLayoutInflater())).getRoot());

        // Inicializamos el ViewModel
        itemsViewModel = new ViewModelProvider(this).get(ItemsViewModel.class);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.moveListRecyclerFragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavController navController = ((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_drawer_host_fragment))).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        // Configuramos el NavigationView
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.moveListRecyclerFragment:
                // Navega a la lista de movimientos
                // Asegúrate de que este código se añade después de inicializar `itemsViewModel` en el método `onCreate`.
                NavController navController = Navigation.findNavController(this, R.id.nav_drawer_host_fragment);
                navController.navigate(R.id.moveListRecyclerFragment);
                return true;
            case R.id.itemListRecyclerFragment:
                // Navega a la lista de items
                // Asegúrate de que este código se añade después de inicializar `itemsViewModel` en el método `onCreate`.
                navController = Navigation.findNavController(this, R.id.nav_drawer_host_fragment);
                navController.navigate(R.id.itemListRecyclerFragment);
                return true;
            case R.id.itemsDetailsFragment:
                // Selecciona un item aleatorio
                itemsViewModel.selectRandom();
                navController = Navigation.findNavController(this, R.id.nav_drawer_host_fragment);
                navController.navigate(R.id.itemsDetailsFragment);
                return true;
            // Maneja otros elementos del menú aquí...
        }
        return false;
    }
}