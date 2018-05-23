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

import org.w3c.dom.Text;

import java.util.List;

import in.install.userinstallin.R;
import in.install.userinstallin.model.Product;

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
        holder.txtHargaOS.setText(product.getHargaOS());
        holder.cvProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+product.getIdProduct(), Toast.LENGTH_SHORT).show();
            }
        });
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