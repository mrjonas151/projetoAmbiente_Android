package com.example.projeto4_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto4_mobile.R;
import com.example.projeto4_mobile.models.AtividadeSustentavel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.ViewHolder> {
    private List<AtividadeSustentavel> listaAtividades;
    private OnAtividadeClickListener clickListener;

    public interface OnAtividadeClickListener {
        void onAtividadeClick(AtividadeSustentavel atividade);
    }

    public AtividadeAdapter(List<AtividadeSustentavel> lista, OnAtividadeClickListener listener) {
        this.listaAtividades = lista;
        this.clickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTipo, tvDescricao, tvData, tvImpacto;
        
        public ViewHolder(View itemView) {
            super(itemView);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvData = itemView.findViewById(R.id.tvData);
            tvImpacto = itemView.findViewById(R.id.tvImpacto);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_atividade, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AtividadeSustentavel atividade = listaAtividades.get(position);
        
        holder.tvTipo.setText(atividade.getTipo());
        holder.tvDescricao.setText(atividade.getDescricao());
        
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            Date date = inputFormat.parse(atividade.getData());
            holder.tvData.setText(outputFormat.format(date));
        } catch (Exception e) {
            holder.tvData.setText(atividade.getData());
        }
        
        holder.tvImpacto.setText(String.format("%.1f %s", 
            atividade.getImpactoAmbiental(), 
            atividade.getUnidadeImpacto()));

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onAtividadeClick(atividade);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAtividades.size();
    }
}