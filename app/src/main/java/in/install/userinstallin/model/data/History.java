package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class History implements Serializable {

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
    @SerializedName("tgl_pengambilan")
    String tanggalPengambilan;
    @SerializedName("waktu_pengambilan")
    String waktuPengambilan;
    @SerializedName("tempat_pengambilan")
    String tempatPengambilan;
    @SerializedName("tgl_pengantaran")
    String tanggalPengantaran;
    @SerializedName("waktu_pengantaran")
    String waktuPengantaran;
    @SerializedName("tempat_pengantaran")
    String tempatPengantaran;
    @SerializedName("status")
    String status;
    @SerializedName("harga_total")
    String hargaTotal;
    @SerializedName("NAMA")
    String namaOs;
    @SerializedName("HARGA")
    String hargaOs;
    @SerializedName("atribut")
    String tipeOs;
    @SerializedName("img_product")
    String imgOs;

    public History(String idOrder, String idKurir, String idUser, String idProduct, String tanggalOrder, String tanggalPengambilan, String waktuPengambilan, String tempatPengambilan, String tanggalPengantaran, String waktuPengantaran, String tempatPengantaran, String status, String hargaTotal, String namaOs, String hargaOs, String tipeOs, String imgOs) {
        this.idOrder = idOrder;
        this.idKurir = idKurir;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.tanggalOrder = tanggalOrder;
        this.tanggalPengambilan = tanggalPengambilan;
        this.waktuPengambilan = waktuPengambilan;
        this.tempatPengambilan = tempatPengambilan;
        this.tanggalPengantaran = tanggalPengantaran;
        this.waktuPengantaran = waktuPengantaran;
        this.tempatPengantaran = tempatPengantaran;
        this.status = status;
        this.hargaTotal = hargaTotal;
        this.namaOs = namaOs;
        this.hargaOs = hargaOs;
        this.tipeOs = tipeOs;
        this.imgOs = imgOs;
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

    public String getTanggalPengambilan() {
        return tanggalPengambilan;
    }

    public String getWaktuPengambilan() {
        return waktuPengambilan;
    }

    public String getTempatPengambilan() {
        return tempatPengambilan;
    }

    public String getTanggalPengantaran() {
        return tanggalPengantaran;
    }

    public String getWaktuPengantaran() {
        return waktuPengantaran;
    }

    public String getTempatPengantaran() {
        return tempatPengantaran;
    }

    public String getStatus() {
        return status;
    }

    public String getHargaTotal() {
        return hargaTotal;
    }

    public String getNamaOs() {
        return namaOs;
    }

    public String getHargaOs() {
        return hargaOs;
    }

    public String getTipeOs() {
        return tipeOs;
    }

    public String getImgOs() {
        return imgOs;
    }
}
