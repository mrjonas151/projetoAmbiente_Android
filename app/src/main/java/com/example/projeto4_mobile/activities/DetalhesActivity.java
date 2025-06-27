package com.example.projeto4_mobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projeto4_mobile.R;
import com.example.projeto4_mobile.models.AtividadeSustentavel;
import com.example.projeto4_mobile.network.ApiClient;
import com.example.projeto4_mobile.services.AtividadeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesActivity extends AppCompatActivity {

    private TextView tvTipo, tvDescricao, tvData, tvImpacto, tvDataCriacao, tvDataAtualizacao;
    private Button btnEditar, btnExcluir, btnVoltar;
    private AtividadeSustentavel atividade;
    private int atividadeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        atividadeId = getIntent().getIntExtra("atividade_id", -1);
        if (atividadeId == -1) {
            Toast.makeText(this, "Erro: ID da atividade não encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initViews();
        setupListeners();
        loadAtividade();
    }

    private void initViews() {
        tvTipo = findViewById(R.id.tvTipo);
        tvDescricao = findViewById(R.id.tvDescricao);
        tvData = findViewById(R.id.tvData);
        tvImpacto = findViewById(R.id.tvImpacto);
        tvDataCriacao = findViewById(R.id.tvDataCriacao);
        tvDataAtualizacao = findViewById(R.id.tvDataAtualizacao);
        btnEditar = findViewById(R.id.btnEditar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnVoltar = findViewById(R.id.btnVoltar);
    }

    private void setupListeners() {
        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(this, EdicaoActivity.class);
            intent.putExtra("atividade_id", atividadeId);
            startActivity(intent);
        });

        btnExcluir.setOnClickListener(v -> confirmarExclusao());
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void loadAtividade() {
        AtividadeService service = ApiClient.getRetrofitInstance().create(AtividadeService.class);
        Call<AtividadeSustentavel> call = service.getAtividadeById(atividadeId);

        call.enqueue(new Callback<AtividadeSustentavel>() {
            @Override
            public void onResponse(Call<AtividadeSustentavel> call, Response<AtividadeSustentavel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    atividade = response.body();
                    populateViews();
                } else {
                    Toast.makeText(DetalhesActivity.this, "Erro ao carregar atividade", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AtividadeSustentavel> call, Throwable t) {
                Toast.makeText(DetalhesActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void populateViews() {
        tvTipo.setText(atividade.getTipo());
        tvDescricao.setText(atividade.getDescricao());
        tvImpacto.setText(String.format("%.1f %s", atividade.getImpactoAmbiental(), atividade.getUnidadeImpacto()));

        tvData.setText(formatDate(atividade.getData()));
        tvDataCriacao.setText(formatDate(atividade.getDataCriacao()));
        tvDataAtualizacao.setText(formatDate(atividade.getDataAtualizacao()));
    }

    private String formatDate(String dateString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (Exception e) {
            return dateString;
        }
    }

    private void confirmarExclusao() {
        new AlertDialog.Builder(this)
            .setTitle("Confirmar Exclusão")
            .setMessage("Tem certeza que deseja excluir esta atividade?")
            .setPositiveButton("Sim", (dialog, which) -> excluirAtividade())
            .setNegativeButton("Não", null)
            .show();
    }

    private void excluirAtividade() {
        AtividadeService service = ApiClient.getRetrofitInstance().create(AtividadeService.class);
        Call<Void> call = service.deleteAtividade(atividadeId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetalhesActivity.this, "Atividade excluída com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetalhesActivity.this, "Erro ao excluir atividade", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DetalhesActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAtividade();
    }
}