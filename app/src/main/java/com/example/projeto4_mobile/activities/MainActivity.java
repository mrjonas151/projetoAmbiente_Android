package com.example.projeto4_mobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;

import com.example.projeto4_mobile.R;
import com.example.projeto4_mobile.adapters.AtividadeAdapter;
import com.example.projeto4_mobile.models.AtividadeSustentavel;
import com.example.projeto4_mobile.network.ApiClient;
import com.example.projeto4_mobile.services.AtividadeService;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AtividadeAdapter adapter;
    private List<AtividadeSustentavel> listaAtividades = new ArrayList<>();
    private Button btnAdicionar, btnEstatisticas, btnRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnEstatisticas = findViewById(R.id.btnEstatisticas);
        btnRelatorio = findViewById(R.id.btnRelatorio);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AtividadeAdapter(listaAtividades, this::onAtividadeClick);
        recyclerView.setAdapter(adapter);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
        });

        btnEstatisticas.setOnClickListener(v -> {
            Intent intent = new Intent(this, EstatisticasActivity.class);
            startActivity(intent);
        });

        btnRelatorio.setOnClickListener(v -> {
            Intent intent = new Intent(this, RelatorioActivity.class);
            startActivity(intent);
        });

        loadAtividades();
    }

    private void loadAtividades() {
        AtividadeService service = ApiClient.getRetrofitInstance().create(AtividadeService.class);
        Call<List<AtividadeSustentavel>> call = service.getAtividades();
        
        call.enqueue(new Callback<List<AtividadeSustentavel>>() {
            @Override
            public void onResponse(Call<List<AtividadeSustentavel>> call, Response<List<AtividadeSustentavel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaAtividades.clear();
                    listaAtividades.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar atividades", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AtividadeSustentavel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onAtividadeClick(AtividadeSustentavel atividade) {
        Intent intent = new Intent(this, DetalhesActivity.class);
        intent.putExtra("atividade_id", atividade.getId());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAtividades(); 
    }
}