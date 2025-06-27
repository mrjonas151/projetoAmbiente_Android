package com.example.projeto4_mobile.services;

import com.example.projeto4_mobile.models.AtividadeSustentavel;
import com.example.projeto4_mobile.models.Estatisticas;
import com.example.projeto4_mobile.models.RelatorioPersonalizado;
import com.example.projeto4_mobile.models.EmailRequest;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface AtividadeService {

    @GET("api/atividades")
    Call<List<AtividadeSustentavel>> getAtividades();

    @GET("api/atividades/{id}")
    Call<AtividadeSustentavel> getAtividadeById(@Path("id") int id);

    @POST("api/atividades")
    Call<AtividadeSustentavel> createAtividade(@Body AtividadeSustentavel atividade);

    @PUT("api/atividades/{id}")
    Call<AtividadeSustentavel> updateAtividade(@Path("id") int id, @Body AtividadeSustentavel atividade);

    @DELETE("api/atividades/{id}")
    Call<Void> deleteAtividade(@Path("id") int id);

    @GET("api/estatisticas")
    Call<Estatisticas> getEstatisticas();

    @GET("api/relatorios/personalizado")
    Call<RelatorioPersonalizado> getRelatorioPersonalizado();

    @POST("api/email/enviar")
    Call<Void> enviarEmailRelatorio(@Query("email") String email, @Body EmailRequest emailRequest);
}