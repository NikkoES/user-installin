package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {

    @SerializedName("id_order")
    String idOrder;
    @SerializedName("id_kurir")
    String idKurir;
    @SerializedName("id_user")
    String idUser;
    @SerializedName("id_product")
    String idProduct;
    @SerializedName("tanggal_order")
    String tanggalOrder;
    @SerializedName("status")
    String status;
    @SerializedName("alamat_pengambilan")
    String alamatPengambilan;
    @SerializedName("tanggal_pengambilan")
    String tanggalPengambilan;
    @SerializedName("waktu_pengambilan")
    String waktuPengambilan;
    @SerializedName("alamat_pengantaran")
    String alamatPengantaran;
    @SerializedName("tanggal_pengantaran")
    String tanggalPengantaran;
    @SerializedName("waktu_pengantaran")
    String waktuPengantaran;
    @SerializedName("harga_total")
    String hargaTotal;

    public Order(String idOrder, String idKurir, String idUser, String idProduct, String tanggalOrder, String status, String alamatPengambilan, String tanggalPengambilan, String waktuPengambilan, String alamatPengantaran, String tanggalPengantaran, String waktuPengantaran, String hargaTotal) {
        this.idOrder = idOrder;
        this.idKurir = idKurir;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.tanggalOrder = tanggalOrder;
        this.status = status;
        this.alamatPengambilan = alamatPengambilan;
        this.tanggalPengambilan = tanggalPengambilan;
        this.waktuPengambilan = waktuPengambilan;
        this.alamatPengantaran = alamatPengantaran;
        this.tanggalPengantaran = tanggalPengantaran;
        this.waktuPengantaran = waktuPengantaran;
        this.hargaTotal = hargaTotal;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getTanggalOrder() {
        return tanggalOrder;
    }

    public String getStatus() {
        return status;
    }

    public String getAlamatPengambilan() {
        return alamatPengambilan;
    }

    public String getTanggalPengambilan() {
        return tanggalPengambilan;
    }

    public String getWaktuPengambilan() {
        return waktuPengambilan;
    }

    public String getAlamatPengantaran() {
        return alamatPengantaran;
    }

    public String getTanggalPengantaran() {
        return tanggalPengantaran;
    }

    public String getWaktuPengantaran() {
        return waktuPengantaran;
    }

    public String getHargaTotal() {
        return hargaTotal;
    }
}