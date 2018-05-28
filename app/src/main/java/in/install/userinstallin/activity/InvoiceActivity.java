package in.install.userinstallin.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

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
import in.install.userinstallin.model.response.ResponsePost;
import in.install.userinstallin.utils.SharedPreferencesUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.concat;

public class InvoiceActivity extends AppCompatActivity {

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

    @BindView(R.id.total_biaya)
    TextView txtTotalBiaya;
    @BindView(R.id.txt_alamat_pengambilan)
    TextView txtAlamatPengambilan;
    @BindView(R.id.txt_tanggal_pengembalian)
    TextView txtTanggalPengambilan;
    @BindView(R.id.txt_waktu_pengambilan)
    TextView txtWaktuPengambilan;

    List<Extras> listExtras = new ArrayList<>();

    BaseApiService apiService;

    private ExtrasInvoiceAdapter adapter;

    String idUser;
    String idProduct, namaOs, descOs, hargaOs, imgOs;
    String alamatPengambilan, tanggalPengambilan, waktuPengambilan;

    int totalBiaya;

    private SharedPreferencesUtils userDataSharedPreferences;

    JSONObject userProfile;

    String userData;

    private ProgressDialog loadingDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Review");

        ButterKnife.bind(this);

        userDataSharedPreferences = new SharedPreferencesUtils(getApplicationContext(), "UserData");

        try {
            userData = userDataSharedPreferences.getPreferenceData("userProfile");
            userProfile = new JSONObject(userData);
            idUser = userProfile.get("id_user").toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        apiService = UtilsApi.getAPIService();

        idProduct = getIntent().getStringExtra("id_product");
        namaOs = getIntent().getStringExtra("nama_os");
        descOs = getIntent().getStringExtra("desc_os");
        hargaOs = getIntent().getStringExtra("harga_os");
        imgOs = getIntent().getStringExtra("image_os");
        alamatPengambilan = getIntent().getStringExtra("alamat");
        tanggalPengambilan = getIntent().getStringExtra("tanggal");
        waktuPengambilan = getIntent().getStringExtra("waktu");
        totalBiaya = getIntent().getIntExtra("total_biaya", 0);
        listExtras = (ArrayList<Extras>)getIntent().getSerializableExtra("extras");

        txtNamaOs.setText(namaOs);
        txtTipeOs.setText(descOs);
        txtHargaOs.setText(concat(currencyFormatter(Integer.parseInt(hargaOs))));
        Glide.with(getApplicationContext())
                .load(imgOs)
                .placeholder(R.drawable.no_image)
                .into(imageOs);
        txtAlamatPengambilan.setText(alamatPengambilan);
        txtTanggalPengambilan.setText(tanggalPengambilan);
        txtWaktuPengambilan.setText(waktuPengambilan);
        txtTotalBiaya.setText(concat(currencyFormatter(totalBiaya)));

        adapter = new ExtrasInvoiceAdapter(getApplicationContext(), listExtras);

        rvExtras.setHasFixedSize(true);
        rvExtras.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvExtras.setAdapter(adapter);

        loadingDaftar = new ProgressDialog(this);
        loadingDaftar.setTitle("Loading");
        loadingDaftar.setMessage("Requesting Order..");
        loadingDaftar.setCancelable(false);
    }

    @OnClick(R.id.btn_pesan)
    public void actionPesan(){
        new AlertDialog.Builder(InvoiceActivity.this)
                .setTitle("Anda yakin ingin semua pesanan sudah lengkap ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pesan();
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

    private void pesan(){
        final int idOrder = (int)(Math.random() * (999 - 99) + 1) + 99;
        loadingDaftar.show();
        apiService.pesanOS(""+idOrder, "2", idUser, idProduct, tanggalPengambilan, waktuPengambilan, alamatPengambilan, "0", ""+totalBiaya)
                .enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        if (response.isSuccessful()){
                            for(int i=0; i<listExtras.size(); i++){
                                final String idExtras = listExtras.get(i).getIdExtras();
                                pesanExtras(idOrder, idExtras);
                            }
                            loadingDaftar.dismiss();
                            Toast.makeText(InvoiceActivity.this, "Pesanan akan segera diproses..", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        }
                        else {
                            loadingDaftar.dismiss();
                            Toast.makeText(InvoiceActivity.this, "Pesanan gagal !", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                        loadingDaftar.dismiss();
                        Toast.makeText(getApplicationContext(), "Koneksi internet bermasalah !", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void pesanExtras(int idOrder, String idExtras) {
        apiService.pesanExtras(""+idOrder, idExtras)
                .enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        if (response.isSuccessful()){

                        }
                        else {
                            loadingDaftar.dismiss();
                            Toast.makeText(InvoiceActivity.this, "Pesanan Extras gagal !", Toast.LENGTH_SHORT).show();
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
