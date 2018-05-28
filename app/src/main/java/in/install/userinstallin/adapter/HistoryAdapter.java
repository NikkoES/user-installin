package in.install.userinstallin.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import in.install.userinstallin.R;
import in.install.userinstallin.activity.DetailHistoryActivity;
import in.install.userinstallin.api.BaseApiService;
import in.install.userinstallin.api.UtilsApi;
import in.install.userinstallin.model.data.History;

import static android.text.TextUtils.concat;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<History> listHistory;

    BaseApiService apiService;

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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final History history = listHistory.get(position);

        apiService = UtilsApi.getAPIService();

        Glide.with(context)
                .load(history.getImgOs())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageOS);
        holder.txtNamaOS.setText(history.getNamaOs());
        holder.txtTipeOS.setText(history.getTipeOs());
        holder.txtHargaOS.setText(concat(currencyFormatter(Integer.parseInt(history.getHargaTotal()))));
        holder.txtTanggal.setText(history.getTanggalOrder());

        String status = "";
        switch (history.getStatus()){
            case "0" : {
                status = "On Confirming";
                break;
            }
            case "1" : {
                status = "On Progress";
                break;
            }
            case "2" : {
                status = "Done";
                break;
            }
            case "3" : {
                status = "Cancelled";
                break;
            }
            case "4" : {
                status = "Rejected";
                break;
            }
        }
        holder.txtStatus.setText(status);
        holder.cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailHistoryActivity.class);
                i.putExtra("id_order", history.getIdOrder());
                i.putExtra("id_user", history.getIdUser());
                i.putExtra("id_product", history.getIdProduct());
                i.putExtra("id_kurir", history.getIdKurir());
                context.startActivity(i);
                ((Activity) context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

    }

    public String currencyFormatter(int number){
        DecimalFormat kursIndo = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator('.');
        formatRp.setGroupingSeparator('.');

        kursIndo.setDecimalFormatSymbols(formatRp);
        return kursIndo.format(number);
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