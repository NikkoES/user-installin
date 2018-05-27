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
import in.install.userinstallin.activity.ExtrasActivity;
import in.install.userinstallin.model.data.Product;

import static android.text.TextUtils.concat;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> listProduct;

    public ProductAdapter(Context context, List<Product> listProduct){
        this.context = context;
        this.listProduct = listProduct;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product product = listProduct.get(position);
        Glide.with(context)
                .load(product.getImageOS())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageOS);
        holder.txtNamaOS.setText(product.getNamaOS());
        holder.txtTipeOS.setText(product.getTipeOS());
        holder.txtHargaOS.setText(concat(currencyFormatter(Integer.parseInt(product.getHargaOS()))));
        holder.cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ExtrasActivity.class);
                i.putExtra("id_product", product.getIdProduct());
                i.putExtra("nama_os", product.getNamaOS());
                i.putExtra("desc_os", product.getTipeOS());
                i.putExtra("harga_os", product.getHargaOS());
                i.putExtra("image_os", product.getImageOS());
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
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvProduct;
        private TextView txtNamaOS, txtTipeOS, txtHargaOS;
        private ImageView imageOS;

        public ViewHolder(View itemView) {
            super(itemView);

            cvProduct = (CardView) itemView.findViewById(R.id.cv_product);
            txtNamaOS = (TextView) itemView.findViewById(R.id.tv_nama_os);
            txtTipeOS = (TextView) itemView.findViewById(R.id.tv_tipe_os);
            txtHargaOS = (TextView) itemView.findViewById(R.id.tv_harga_os);
            imageOS = (ImageView) itemView.findViewById(R.id.image_os);
        }
    }
}