package in.install.userinstallin.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import in.install.userinstallin.R;
import in.install.userinstallin.model.data.Extras;

import static android.text.TextUtils.concat;

public class ExtrasAdapter extends RecyclerView.Adapter<ExtrasAdapter.ViewHolder> {

    private Context context;
    private List<Extras> listExtras;

    public interface OnItemCheckListener {
        void onItemCheck(Extras item);
        void onItemUncheck(Extras item);
    }

    @NonNull
    public OnItemCheckListener onItemCheckListener;

    public ExtrasAdapter(Context context, List<Extras> listExtras, @NonNull OnItemCheckListener onItemCheckListener){
        this.context = context;
        this.listExtras = listExtras;
        this.onItemCheckListener = onItemCheckListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_extras, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Extras extras = listExtras.get(position);
        holder.txtNamaExtras.setText(extras.getNamaExtras());
        holder.txtKeterangan.setText(extras.getRincian());
        holder.txtHargaExtras.setText(concat(currencyFormatter(Integer.parseInt(extras.getHargaExtras()))));

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.checkExtras.setChecked(!holder.checkExtras.isChecked());
                if (holder.checkExtras.isChecked()) {
                    onItemCheckListener.onItemCheck(extras);
                }
                else {
                    onItemCheckListener.onItemUncheck(extras);
                }
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
        return listExtras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkExtras;
        private TextView txtNamaExtras, txtKeterangan, txtHargaExtras;

        public ViewHolder(View itemView) {
            super(itemView);

            checkExtras = (CheckBox) itemView.findViewById(R.id.check_extras);
            checkExtras.setClickable(false);
            txtNamaExtras = (TextView) itemView.findViewById(R.id.nama_extras);
            txtKeterangan = (TextView) itemView.findViewById(R.id.keterangan_extras);
            txtHargaExtras = (TextView) itemView.findViewById(R.id.harga_extras);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }
    }
}
