package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {

    @SerializedName("ID_ORDER")
    String idOrder;
    @SerializedName("ID_KURIR")
    String idKurir;
    @SerializedName("ID")
    String idUser;
    @SerializedName("id_product")
    String idProduct;
    @SerializedName("TGL_ORDER")
    String tanggalOrder;
    @SerializedName("status")
    String status;
    @SerializedName("tempat_pengambilan")
    String alamatPengambilan;
    @SerializedName("tgl_pengambilan")
    String tanggalPengambilan;
    @SerializedName("waktu_pengambilan")
    String waktuPengambilan;
    @SerializedName("tempat_pengantaran")
    String alamatPengantaran;
    @SerializedName("tgl_pengantaran")
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