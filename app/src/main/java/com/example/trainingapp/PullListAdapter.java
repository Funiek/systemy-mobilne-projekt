package com.example.trainingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingapp.db.Pull;

import java.util.List;

public class PullListAdapter extends RecyclerView.Adapter<PullListAdapter.PullViewHolder>{
    private Context context;
    private List<Pull> pullList;

    public PullListAdapter(Context context) {
        this.context = context;
    }

    public void setPullList(List<Pull> pullList) {
        this.pullList = pullList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PullViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new PullViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PullViewHolder holder, int position) {

        holder.tvPullName.setText("Trening "+(position+1));
        holder.tvPullMartwy.setText(this.pullList.get(position).martwy);
        holder.tvPullNegatyw.setText(this.pullList.get(position).negatyw);
        holder.tvPullSciaganieDrazka.setText(this.pullList.get(position).sciaganieDrazkaGora);
        holder.tvPullOHP.setText(this.pullList.get(position).ohpHantelkami);
        holder.tvPullModlitewnik.setText(this.pullList.get(position).modlitewnik);
        holder.tvPullWioslowanie.setText(this.pullList.get(position).wioslowanie);
        holder.tvPullMlotki.setText(this.pullList.get(position).mlotki);
        holder.tvPullPrzedramiona.setText(this.pullList.get(position).przedramiona);
    }

    @Override
    public int getItemCount() {
        return this.pullList.size();
    }

    public class PullViewHolder extends RecyclerView.ViewHolder {
        TextView tvPullName;
        TextView tvPullMartwy;
        TextView tvPullNegatyw;
        TextView tvPullSciaganieDrazka;
        TextView tvPullOHP;
        TextView tvPullModlitewnik;
        TextView tvPullWioslowanie;
        TextView tvPullMlotki;
        TextView tvPullPrzedramiona;

        public PullViewHolder(View view) {
            super(view);

            tvPullName = view.findViewById(R.id.tvPullName);

            tvPullMartwy = view.findViewById(R.id.tvPullMartwy);

            tvPullNegatyw = view.findViewById(R.id.tvPullNegatyw);

            tvPullSciaganieDrazka = view.findViewById(R.id.tvPullSciaganieDrazka);

            tvPullOHP = view.findViewById(R.id.tvPullOHP);

            tvPullModlitewnik = view.findViewById(R.id.tvPullModlitewnik);

            tvPullWioslowanie = view.findViewById(R.id.tvPullWioslowanie);

            tvPullMlotki = view.findViewById(R.id.tvPullMlotki);

            tvPullPrzedramiona = view.findViewById(R.id.tvPullPrzedramiona);
        }
    }
}
