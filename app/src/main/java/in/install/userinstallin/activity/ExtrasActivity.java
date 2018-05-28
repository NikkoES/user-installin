package in.install.userinstallin.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.install.userinstallin.R;
import in.install.userinstallin.adapter.ExtrasAdapter;
import in.install.userinstallin.api.BaseApiService;
import in.install.userinstallin.api.UtilsApi;
import in.install.userinstallin.model.data.Extras;
import in.install.userinstallin.model.response.ResponseListExtras;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.concat;

public class ExtrasActivity extends AppCompatActivity {

    @BindView(R.id.et_alamat_pengambilan)
    EditText etAlamatPengambilan;
    @BindView(R.id.et_tanggal_pengambilan)
    EditText etTanggalPengambilan;
    @BindView(R.id.et_waktu_pengambilan)
    EditText etWaktuPengambilan;

    @BindView(R.id.list_extras)
    RecyclerView rvExtras;

    @BindView(R.id.tv_nama_os)
    TextView txtNamaOs;
    @BindView(R.id.tv_tipe_os)
    TextView txtTipeOs;
    @BindView(R.id.tv_harga_os)
    TextView txtHargaOs;
    @BindView(R.id.image_os)
    ImageView imageOs;

    @BindView(R.id.total_biaya)
    TextView txtTotalBiaya;

    private int PLACE_PICKER_REQUEST = 1;

    private int mYear, mMonth, mDay, mHour, mMinute;

    List<Extras> listExtras = new ArrayList<>();
    List<Extras> currentSelectedItems = new ArrayList<>();

    BaseApiService apiService;

    private ExtrasAdapter adapter;

    String idProduct, namaOs, descOs, hargaOs, imgOs;

    int totalBiaya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Form Pemesanan");

        ButterKnife.bind(this);

        idProduct = getIntent().getStringExtra("id_product");
        namaOs = getIntent().getStringExtra("nama_os");
        descOs = getIntent().getStringExtra("desc_os");
        hargaOs = getIntent().getStringExtra("harga_os");
        imgOs = getIntent().getStringExtra("image_os");

        apiService = UtilsApi.getAPIService();

        totalBiaya = Integer.parseInt(hargaOs);

        txtTotalBiaya.setText(concat(currencyFormatter(totalBiaya)));

        adapter = new ExtrasAdapter(getApplicationContext(), listExtras, new ExtrasAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(Extras item) {

            }

            @Override
            public void onItemUncheck(Extras item) {

            }
        });

        rvExtras.setHasFixedSize(true);
        rvExtras.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvExtras.setAdapter(adapter);

        setExtras(idProduct);
        txtNamaOs.setText(namaOs);
        txtTipeOs.setText(descOs);
        txtHargaOs.setText(concat(currencyFormatter(Integer.parseInt(hargaOs))));
        Glide.with(getApplicationContext())
                .load(imgOs)
                .placeholder(R.drawable.no_image)
                .into(imageOs);
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

    private void setExtras(String idProduct) {
        apiService.getDataExtras(idProduct).enqueue(new Callback<ResponseListExtras>() {
            @Override
            public void onResponse(Call<ResponseListExtras> call, Response<ResponseListExtras> response) {
                if (response.body().getStatus().equals("success")){
                    listExtras = response.body().getListExtras();

                    rvExtras.setAdapter(new ExtrasAdapter(getApplicationContext(), listExtras, new ExtrasAdapter.OnItemCheckListener() {
                        @Override
                        public void onItemCheck(Extras item) {
                            totalBiaya = totalBiaya + Integer.parseInt(item.getHargaExtras());
                            txtTotalBiaya.setText(concat(currencyFormatter(totalBiaya)));
                            currentSelectedItems.add(item);
                        }

                        @Override
                        public void onItemUncheck(Extras item) {
                            totalBiaya = totalBiaya - Integer.parseInt(item.getHargaExtras());
                            txtTotalBiaya.setText(concat(currencyFormatter(totalBiaya)));
                            currentSelectedItems.remove(item);
                        }
                    }));
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(ExtrasActivity.this, "Gagal mendapatkan data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListExtras> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn_next)
    public void nextInvoice(){
        if(TextUtils.isEmpty(etAlamatPengambilan.getText().toString()) || TextUtils.isEmpty(etTanggalPengambilan.getText().toString()) || TextUtils.isEmpty(etWaktuPengambilan.getText().toString())){
            Toast.makeText(this, "Data belum lengkap !", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(ExtrasActivity.this, InvoiceActivity.class);
            i.putExtra("id_product", idProduct);
            i.putExtra("nama_os", namaOs);
            i.putExtra("desc_os", descOs);
            i.putExtra("harga_os", hargaOs);
            i.putExtra("image_os", imgOs);
            i.putExtra("alamat", etAlamatPengambilan.getText().toString());
            i.putExtra("tanggal", etTanggalPengambilan.getText().toString());
            i.putExtra("waktu", etWaktuPengambilan.getText().toString());
            i.putExtra("total_biaya", totalBiaya);
            i.putExtra("extras", (ArrayList<Extras>) currentSelectedItems);
            startActivity(i);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    @OnClick(R.id.et_alamat_pengambilan)
    public void getLocation(){
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            //menjalankan place picker
            startActivityForResult(builder.build(ExtrasActivity.this), PLACE_PICKER_REQUEST);
        }
        catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.et_tanggal_pengambilan)
    public void getTanggal(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String sDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy");
                        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
                        try {
                            Date newDate = dateFormat.parse(sDate);
                            dateFormat = new SimpleDateFormat("dd MMM yyyy");
                            dateFormat.setTimeZone(tz);
                            etTanggalPengambilan.setText(dateFormat.format(newDate));
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }

    @OnClick(R.id.et_waktu_pengambilan)
    public void getWaktu(){
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String sTime = hourOfDay + ":" + minute;
                        SimpleDateFormat timeFormat = new SimpleDateFormat("H:m");
                        TimeZone tz = TimeZone.getTimeZone("Asia/Jakarta");
                        try {
                            Date newTime = timeFormat.parse(sTime);
                            timeFormat = new SimpleDateFormat("HH:mm");
                            timeFormat.setTimeZone(tz);
                            etWaktuPengambilan.setText(timeFormat.format(newTime));
                        }
                        catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mHour, mMinute, false);

        timePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
//                String toastMsg = String.format(
//                        "Place: %s \n" +
//                                "Alamat: %s \n" +
//                                "Latlng %s \n", place.getName(), place.getAddress(), place.getLatLng().latitude+" "+place.getLatLng().longitude);
                etAlamatPengambilan.setText(place.getAddress());
            }
        }
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
