package com.si6b.ridermotogp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.si6b.ridermotogp.Model.ModelRider;
import com.si6b.ridermotogp.R;

import java.util.List;

public class AdapterRider extends RecyclerView.Adapter<AdapterRider.HolderRider> {
    private Context ctx;
    private List<ModelRider> listRider;

    public AdapterRider(Context ctx, List<ModelRider> listRider) {
        this.ctx = ctx;
        this.listRider = listRider;
    }

    @NonNull
    @Override
    public HolderRider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rider, parent, false);
        HolderRider HR = new HolderRider(view);
        return HR;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRider holder, int position) {
        ModelRider MR = listRider.get(position);

        holder.tvId.setText(String.valueOf(MR.getId()));
        holder.tvNama.setText(MR.getNama());
        holder.tvSponsor.setText(MR.getSponsor());
        holder.tvNegara.setText(MR.getNegara());
        holder.tvNomor.setText(MR.getNomor());

        Glide
                .with(ctx)
                .load(MR.getFoto())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivRider);
    }

    @Override
    public int getItemCount() {
        if(listRider != null){
            return listRider.size();
        }
        else{
            Toast.makeText(ctx, "Data Tidak Tersedia", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    public class HolderRider extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvSponsor, tvNegara, tvNomor;
        ImageView ivRider;

        public HolderRider(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvSponsor = itemView.findViewById(R.id.tv_sponsor);
            tvNegara = itemView.findViewById(R.id.tv_negara);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            ivRider = itemView.findViewById(R.id.iv_rider);
        }
    }

}
