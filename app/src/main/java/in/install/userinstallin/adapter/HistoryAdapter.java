package in.install.userinstallin.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import in.install.userinstallin.R;
import in.install.userinstallin.model.History;
import in.install.userinstallin.model.Product;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<History> listHistory;

    public HistoryAdapter(Context context, List<History> listHistory){
        this.context = context;
        this.listHistory = listHistory;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_history, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final History history = listHistory.get(position);
        Glide.with(context)
                .load(history.getImageOS())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageOS);
        holder.txtNamaOS.setText(history.getNamaOS());
        holder.txtTipeOS.setText(history.getTipeOS());
        holder.txtHargaOS.setText(history.getHargaOS());
        holder.txtTanggal.setText(history.getTanggalTransaksi());
        holder.txtStatus.setText(history.getStatusTransaksi());
        holder.cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+history.getIdTransaksi(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvHistory;
        private TextView txtNamaOS, txtTipeOS, txtHargaOS, txtTanggal, txtStatus;
        private ImageView imageOS;

        public ViewHolder(View itemView) {
            super(itemView);

            cvHistory = (CardView) itemView.findViewById(R.id.cv_history);
            txtNamaOS = (TextView) itemView.findViewById(R.id.tv_nama_os);
            txtTipeOS = (TextView) itemView.findViewById(R.id.tv_tipe_os);
            txtHargaOS = (TextView) itemView.findViewById(R.id.tv_harga_os);
            txtTanggal = (TextView) itemView.findViewById(R.id.tv_tanggal_transaksi);
            txtStatus = (TextView) itemView.findViewById(R.id.tv_status_transaksi);
            imageOS = (ImageView) itemView.findViewById(R.id.image_os);
        }
    }
}