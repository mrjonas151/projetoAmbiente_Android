package com.example.projeto4_mobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import android.view.View;

import com.example.projeto4_mobile.R;
import com.example.projeto4_mobile.models.RelatorioPersonalizado;
import com.example.projeto4_mobile.models.EmailRequest;
import com.example.projeto4_mobile.network.ApiClient;
import com.example.projeto4_mobile.services.AtividadeService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RelatorioActivity extends AppCompatActivity {

    private TextView tvRelatorio, tvTotalAtividades, tvPeriodoAnalise;
    private Button btnGerarRelatorio, btnEnviarEmail, btnVoltar;
    private ProgressBar progressBar;
    private RelatorioPersonalizado relatorioAtual;
    private static final String EMAIL_FIXO = "testeser259@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        initViews();
        setupListeners();
    }

    private void initViews() {
        tvRelatorio = findViewById(R.id.tvRelatorio);
        tvTotalAtividades = findViewById(R.id.tvTotalAtividades);
        tvPeriodoAnalise = findViewById(R.id.tvPeriodoAnalise);
        btnGerarRelatorio = findViewById(R.id.btnGerarRelatorio);
        btnEnviarEmail = findViewById(R.id.btnEnviarEmail);
        btnVoltar = findViewById(R.id.btnVoltar);
        progressBar = findViewById(R.id.progressBar);

        btnEnviarEmail.setVisibility(View.GONE);
    }

    private void setupListeners() {
        btnGerarRelatorio.setOnClickListener(v -> gerarRelatorio());
        btnEnviarEmail.setOnClickListener(v -> enviarRelatorioEmail());
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void gerarRelatorio() {
        showLoading(true);
        
        AtividadeService service = ApiClient.getRetrofitInstance().create(AtividadeService.class);
        Call<RelatorioPersonalizado> call = service.getRelatorioPersonalizado();

        call.enqueue(new Callback<RelatorioPersonalizado>() {
            @Override
            public void onResponse(Call<RelatorioPersonalizado> call, Response<RelatorioPersonalizado> response) {
                showLoading(false);
                
                if (response.isSuccessful() && response.body() != null) {
                    relatorioAtual = response.body();
                    exibirRelatorio();
                } else {
                    Toast.makeText(RelatorioActivity.this, "Erro ao gerar relatório", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RelatorioPersonalizado> call, Throwable t) {
                showLoading(false);
                Toast.makeText(RelatorioActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirRelatorio() {
        tvRelatorio.setText(relatorioAtual.getRelatorio());
        tvTotalAtividades.setText("Total de Atividades: " + relatorioAtual.getTotalAtividades());
        tvPeriodoAnalise.setText(relatorioAtual.getPeriodoAnalise());
        
        btnEnviarEmail.setVisibility(View.VISIBLE);
        
        btnGerarRelatorio.setText("Gerar Novo Relatório");
    }

    private void enviarRelatorioEmail() {
        if (relatorioAtual == null) {
            Toast.makeText(this, "Gere um relatório primeiro", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoading(true);
        btnEnviarEmail.setEnabled(false);

        EmailRequest emailRequest = new EmailRequest(relatorioAtual.getRelatorio());
        
        AtividadeService service = ApiClient.getRetrofitInstance().create(AtividadeService.class);
        Call<Void> call = service.enviarEmailRelatorio(EMAIL_FIXO, emailRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showLoading(false);
                btnEnviarEmail.setEnabled(true);
                
                if (response.isSuccessful()) {
                    Toast.makeText(RelatorioActivity.this, 
                        "Relatório enviado para " + EMAIL_FIXO + " com sucesso!", 
                        Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RelatorioActivity.this, "Erro ao enviar email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showLoading(false);
                btnEnviarEmail.setEnabled(true);
                Toast.makeText(RelatorioActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        btnGerarRelatorio.setEnabled(!show);
        if (btnEnviarEmail.getVisibility() == View.VISIBLE) {
            btnEnviarEmail.setEnabled(!show);
        }
    }
}