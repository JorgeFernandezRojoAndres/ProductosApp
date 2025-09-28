package com.example.productosapp;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.productosapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // 📌 ViewBinding
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Toggle (icono hamburguesa)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Escuchar clics del menú lateral
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_cargar) {
                // ✅ Mostrar FormularioProductoFragment en el contenedor
                cargarFragment(new FormularioProductoFragment());

            } else if (id == R.id.nav_listar) {
                // Mostrar ListaProductosFragment en el contenedor
                cargarFragment(new ListaProductosFragment());
            }
            else if (id == R.id.nav_salir) {
                // Confirmación de salida
                new AlertDialog.Builder(this)
                        .setTitle("Salir")
                        .setMessage("¿Desea cerrar la aplicación?")
                        .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                        .setNegativeButton("No", null)
                        .show();
            }

            drawerLayout.closeDrawers(); // Cerrar el menú después de cada selección
            return true;
        });
    }

    // 📌 Método helper para cargar fragments
    private void cargarFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null); // permite volver con el botón atrás
        transaction.commit();
    }
}
