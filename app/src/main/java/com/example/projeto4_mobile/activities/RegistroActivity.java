package com.example.projeto4_mobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

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

public class RegistroActivity extends AppCompatActivity {

    private Spinner spinnerTipo;
    private EditText etDescricao, etImpacto, etUnidade;
    private Button btnSalvar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        initViews();
        setupSpinner();
        setupListeners();
    }

    private void initViews() {
        spinnerTipo = findViewById(R.id.spinnerTipo);
        etDescricao = findViewById(R.id.etDescricao);
        etImpacto = findViewById(R.id.etImpacto);
        etUnidade = findViewById(R.id.etUnidade);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancelar = findViewById(R.id.btnCancelar);
    }

    private void setupSpinner() {
        String[] tipos = {"Economia de Água", "Plantio", "Reutilização", "Reciclagem", "Transporte Sustentável", "Energia Renovável"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);
    }

    private void setupListeners() {
        btnSalvar.setOnClickListener(v -> salvarAtividade());
        btnCancelar.setOnClickListener(v -> finish());
    }

    private void salvarAtividade() {
        String tipo = spinnerTipo.getSelectedItem().toString();
        String descricao = etDescricao.getText().toString().trim();
        String impactoStr = etImpacto.getText().toString().trim();
        String unidade = etUnidade.getText().toString().trim();

        if (descricao.isEmpty() || impactoStr.isEmpty() || unidade.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double impacto = Double.parseDouble(impactoStr);
            
            AtividadeSustentavel atividade = new AtividadeSustentavel();
            atividade.setTipo(tipo);
            atividade.setDescricao(descricao);
            atividade.setImpactoAmbiental(impacto);
            atividade.setUnidadeImpacto(unidade);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            atividade.setData(sdf.format(new Date()));

            AtividadeService service = ApiClient.getRetrofitInstance().create(AtividadeService.class);
            Call<AtividadeSustentavel> call = service.createAtividade(atividade);

            call.enqueue(new Callback<AtividadeSustentavel>() {
                @Override
                public void onResponse(Call<AtividadeSustentavel> call, Response<AtividadeSustentavel> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RegistroActivity.this, "Atividade salva com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegistroActivity.this, "Erro ao salvar atividade", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AtividadeSustentavel> call, Throwable t) {
                    Toast.makeText(RegistroActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor de impacto inválido", Toast.LENGTH_SHORT).show();
        }
    }
}