package com.example.projeto4_mobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projeto4_mobile.R;
import com.example.projeto4_mobile.models.Estatisticas;
import com.example.projeto4_mobile.network.ApiClient;
import com.example.projeto4_mobile.services.AtividadeService;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstatisticasActivity extends AppCompatActivity {

    private TextView tvTotalAtividades, tvImpactoTotal, tvMediaDiaria;
    private PieChart pieChart;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        initViews();
        setupListeners();
        loadEstatisticas();
    }

    private void initViews() {
        tvTotalAtividades = findViewById(R.id.tvTotalAtividades);
        tvImpactoTotal = findViewById(R.id.tvImpactoTotal);
        tvMediaDiaria = findViewById(R.id.tvMediaDiaria);
        pieChart = findViewById(R.id.pieChart);
        btnVoltar = findViewById(R.id.btnVoltar);

        setupPieChart();
    }

    private void setupListeners() {
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void setupPieChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
    }

    private void loadEstatisticas() {
        AtividadeService service = ApiClient.getRetrofitInstance().create(AtividadeService.class);
        Call<Estatisticas> call = service.getEstatisticas();

        call.enqueue(new Callback<Estatisticas>() {
            @Override
            public void onResponse(Call<Estatisticas> call, Response<Estatisticas> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Estatisticas estatisticas = response.body();
                    populateViews(estatisticas);
                } else {
                    Toast.makeText(EstatisticasActivity.this, "Erro ao carregar estatísticas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Estatisticas> call, Throwable t) {
                Toast.makeText(EstatisticasActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateViews(Estatisticas estatisticas) {
        tvTotalAtividades.setText(String.valueOf(estatisticas.getTotalAtividades()));
        tvImpactoTotal.setText(String.format("%.2f", estatisticas.getImpactoTotalPositivo()));
        tvMediaDiaria.setText(String.format("%.3f", estatisticas.getMediaImpactoDiario()));

        setupPieChartData(estatisticas.getAtividadesPorTipo());
    }

    private void setupPieChartData(Map<String, Integer> atividadesPorTipo) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : atividadesPorTipo.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Atividades por Tipo");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}