package com.example.lucenestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lucenestudy.R;
import com.example.lucenestudy.model.Jogo;
import com.example.lucenestudy.view.MainActivity;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Jogo> list;
    private Context context;

    public RecyclerAdapter(List<Jogo> list, Context context){
        this.list= list;
        this.context= context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Jogo jogo = list.get(position);
        holder.mandante.setText(jogo.getMandante()+ "  "+jogo.getMandante_placar());
        holder.visitante.setText(jogo.getVisitante_placar()+ "  "+jogo.getVisitante());
        holder.rodada.setText("RODADA "+jogo.getRodada());
        holder.ano.setText("CAMPEONATO BRASILEIRO "+jogo.getData().substring(0, 4));
        holder.dia_mes.setText(jogo.getData().substring(8, 10) +"/"+jogo.getData().substring(5, 7));
        holder.dia_semana.setText(jogo.getDia().toUpperCase());
        holder.hora.setText(jogo.getHora());

        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This application is intended only to search through the Lucene library.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }







    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mandante, visitante, rodada, ano, dia_mes, dia_semana, hora;
        private CardView listItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mandante= itemView.findViewById(R.id.txt_mandante);
            visitante= itemView.findViewById(R.id.txt_visitante);
            rodada= itemView.findViewById(R.id.txt_rodada);
            ano= itemView.findViewById(R.id.txt_ano);
            dia_mes= itemView.findViewById(R.id.txt_diaMes);
            dia_semana= itemView.findViewById(R.id.txt_diaSemana);
            hora= itemView.findViewById(R.id.txt_hora);
            listItem= itemView.findViewById(R.id.listItem);
        }
    }
}
