package in.install.userinstallin.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.install.userinstallin.R;
import in.install.userinstallin.adapter.ExtrasAdapter;
import in.install.userinstallin.adapter.ExtrasInvoiceAdapter;
import in.install.userinstallin.api.BaseApiService;
import in.install.userinstallin.api.UtilsApi;
import in.install.userinstallin.model.data.Extras;
import in.install.userinstallin.model.data.Kurir;
import in.install.userinstallin.model.data.Order;
import in.install.userinstallin.model.data.Product;
import in.install.userinstallin.model.response.ResponseKurir;
import in.install.userinstallin.model.response.ResponseListExtras;
import in.install.userinstallin.model.response.ResponseOrder;
import in.install.userinstallin.model.response.ResponsePost;
import in.install.userinstallin.model.response.ResponseProduct;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.concat;

public class DetailHistoryActivity extends AppCompatActivity {

    @BindView(R.id.layout_kurir)
    LinearLayout layoutKurir;
    @BindView(R.id.image_profile_kurir)
    ImageView imageProfileKurir;
    @BindView(R.id.txt_nama_kurir)
    TextView txtNamaKurir;
    @BindView(R.id.txt_id_kurir)
    TextView txtIdKurir;
    @BindView(R.id.txt_no_hp)
    TextView txtNoHpKurir;

    @BindView(R.id.txt_status_transaksi)
    TextView txtStatus;

    @BindView(R.id.tv_nama_os)
    TextView txtNamaOs;
    @BindView(R.id.tv_tipe_os)
    TextView txtTipeOs;
    @BindView(R.id.tv_harga_os)
    TextView txtHargaOs;
    @BindView(R.id.image_os)
    ImageView imageOs;

    @BindView(R.id.list_extras)
    RecyclerView rvExtras;

    @BindView(R.id.txt_id_order)
    TextView txtIdOrder;
    @BindView(R.id.total_biaya)
    TextView txtTotalBiaya;
    @BindView(R.id.txt_alamat_pengambilan)
    TextView txtAlamatPengambilan;
    @BindView(R.id.txt_tanggal_pengembalian)
    TextView txtTanggalPengambilan;
    @BindView(R.id.txt_waktu_pengambilan)
    TextView txtWaktuPengambilan;
    @BindView(R.id.txt_alamat_pengantaran)
    TextView txtAlamatPengantaran;
    @BindView(R.id.txt_tanggal_pengantaran)
    TextView txtTanggalPengantaran;
    @BindView(R.id.txt_waktu_pengantaran)
    TextView txtWaktuPengantaran;

    @BindView(R.id.layout_pengambilan)
    LinearLayout layoutPengambilan;
    @BindView(R.id.layout_pengantaran)
    LinearLayout layoutPengantaran;
    @BindView(R.id.layout_cancel)
    CardView layoutCancel;

    String idUser, idOrder, idProduct, idKurir;

    List<Extras> listExtras = new ArrayList<>();

    BaseApiService apiService;

    private ExtrasInvoiceAdapter adapter;

    String namaKurir, ktpKurir, noHpKurir, fotoKurir;
    String namaOs, descOs, hargaOs, imgOs;
    String status;
    String alamatPengambilan, tanggalPengambilan, waktuPengambilan;
    String alamatPengantaran, tanggalPengantaran, waktuPengantaran;

    int totalBiaya;

    private ProgressDialog loadingDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail History Order");

        ButterKnife.bind(this);

        apiService = UtilsApi.getAPIService();

        adapter = new ExtrasInvoiceAdapter(getApplicationContext(), listExtras);

        rvExtras.setHasFixedSize(true);
        rvExtras.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvExtras.setAdapter(adapter);

        idUser = getIntent().getStringExtra("id_user");
        idOrder = getIntent().getStringExtra("id_order");
        idProduct = getIntent().getStringExtra("id_product");
        idKurir = getIntent().getStringExtra("id_kurir");

        getDataProduct(idProduct);
        getDataOrder(idOrder);
        getDataKurir(idKurir);
        setExtras(idOrder);

        txtIdOrder.setText("#"+idOrder);

        loadingDaftar = new ProgressDialog(this);
        loadingDaftar.setTitle("Loading");
        loadingDaftar.setMessage("Canceling Order..");
        loadingDaftar.setCancelable(false);
    }

    @OnClick(R.id.btn_chat)
    public void btnChat(){
        String urlWhatsapp = "https://api.whatsapp.com/send?phone="+noHpKurir;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlWhatsapp));
        startActivity(intent);
    }

    @OnClick(R.id.btn_cancel)
    public void btnCancel(){
        new AlertDialog.Builder(DetailHistoryActivity.this)
                .setTitle("Anda yakin ingin membatalkan order ini ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        actionCancel(idOrder);
                        Toast.makeText(DetailHistoryActivity.this, "Cancel !", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .show();
    }

    private void actionCancel(String idOrder){
        loadingDaftar.show();
        apiService.updateCanceled(idOrder)
                .enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        if (response.isSuccessful()){
                            loadingDaftar.dismiss();
                            Toast.makeText(getApplicationContext(), "Pesanan dibatalkan..", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        }
                        else {
                            loadingDaftar.dismiss();
                            Toast.makeText(getApplicationContext(), "Pembatalan gagal !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        loadingDaftar.dismiss();
                        Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah !", Toast.LENGTH_SHORT).show();
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

    private void setExtras(String idOrder) {
        apiService.getDataOrderExtrasById(idOrder).enqueue(new Callback<ResponseListExtras>() {
            @Override
            public void onResponse(Call<ResponseListExtras> call, Response<ResponseListExtras> response) {
                if (response.body().getStatus().equals("success")){
                    listExtras = response.body().getListExtras();

                    rvExtras.setAdapter(new ExtrasInvoiceAdapter(getApplicationContext(), listExtras));
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Gagal mendapatkan data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListExtras> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi Internet Bermasalah (extras)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDataOrder(String id){
        apiService.getOrderById(id)
                .enqueue(new Callback<ResponseOrder>() {
                    @Override
                    public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
                        if (response.isSuccessful()){
                            final Order order = response.body().getOrder();
                            status = order.getStatus();
                            totalBiaya = Integer.parseInt(order.getHargaTotal());
                            tanggalPengambilan = order.getTanggalPengambilan();
                            waktuPengambilan = order.getWaktuPengambilan();
                            alamatPengambilan = order.getAlamatPengambilan();
                            tanggalPengantaran = order.getTanggalPengantaran();
                            waktuPengantaran = order.getWaktuPengantaran();
                            alamatPengantaran = order.getAlamatPengantaran();

                            //on confirming
                            if(status.equalsIgnoreCase("0")){
                                layoutKurir.setVisibility(View.GONE);
                                layoutCancel.setVisibility(View.VISIBLE);
                                status = "On Confirming";
                            }

                            //on progress
                            else if(status.equalsIgnoreCase("1")){
                                layoutKurir.setVisibility(View.VISIBLE);
                                status = "On Progress";
                            }

                            //done
                            else if(status.equalsIgnoreCase("2")){
                                layoutKurir.setVisibility(View.VISIBLE);
                                layoutPengambilan.setVisibility(View.GONE);
                                layoutPengantaran.setVisibility(View.VISIBLE);
                                status = "Done";
                            }
                            else if(status.equalsIgnoreCase("3")){
                                layoutKurir.setVisibility(View.GONE);
                                status = "Canceled";
                            }
                            else if(status.equalsIgnoreCase("4")){
                                layoutKurir.setVisibility(View.GONE);
                                status = "Rejected";
                            }

                            txtStatus.setText(status);
                            txtAlamatPengambilan.setText(alamatPengambilan);
                            txtTanggalPengambilan.setText(tanggalPengambilan);
                            txtWaktuPengambilan.setText(waktuPengambilan);
                            txtAlamatPengantaran.setText(alamatPengantaran);
                            txtTanggalPengantaran.setText(tanggalPengantaran);
                            txtWaktuPengantaran.setText(waktuPengantaran);
                            txtTotalBiaya.setText(concat(currencyFormatter(totalBiaya)));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Gagal mengambil data order !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseOrder> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah (order) !", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getDataProduct(String id){
        apiService.getProductById(id)
                .enqueue(new Callback<ResponseProduct>() {
                    @Override
                    public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                        if (response.isSuccessful()){
                            final Product product = response.body().getProduct();
                            namaOs = product.getNamaOS();
                            descOs = product.getTipeOS();
                            hargaOs = product.getHargaOS();
                            imgOs = product.getImageOS();

                            txtNamaOs.setText(namaOs);
                            txtTipeOs.setText(descOs);
                            txtHargaOs.setText(concat(currencyFormatter(Integer.parseInt(hargaOs))));
                            Glide.with(getApplicationContext())
                                    .load(imgOs)
                                    .placeholder(R.drawable.no_image)
                                    .into(imageOs);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Gagal mengambil data order !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseProduct> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah (product) !", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getDataKurir(String id){
        apiService.getKurirById(id)
                .enqueue(new Callback<ResponseKurir>() {
                    @Override
                    public void onResponse(Call<ResponseKurir> call, Response<ResponseKurir> response) {
                        if (response.isSuccessful()){
                            final Kurir kurir = response.body().getKurir();
                            namaKurir = kurir.getNamaKurir();
                            ktpKurir = kurir.getNoKtp();
                            noHpKurir = kurir.getNoHp();
                            fotoKurir = kurir.getFoto();

                            txtNamaKurir.setText(namaKurir);
                            txtIdKurir.setText(ktpKurir);
                            txtNoHpKurir.setText(noHpKurir);
                            Glide.with(getApplicationContext())
                                    .load(fotoKurir)
                                    .placeholder(R.drawable.ic_profile)
                                    .into(imageProfileKurir);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Gagal mengambil data order !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseKurir> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah (kurir) !", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
