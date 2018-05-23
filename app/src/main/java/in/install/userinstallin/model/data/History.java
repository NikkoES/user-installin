package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class History implements Serializable {

    @SerializedName("id_transaksi")
    String idTransaksi;
    @SerializedName("nama_os")
    String namaOS;
    @SerializedName("tipe_os")
    String tipeOS;
    @SerializedName("harga_os")
    String hargaOS;
    @SerializedName("image_os")
    String imageOS;
    @SerializedName("tanggal_transaksi")
    String tanggalTransaksi;
    @SerializedName("status_transaksi")
    String statusTransaksi;

    public History(String idTransaksi, String namaOS, String tipeOS, String hargaOS, String imageOS, String tanggalTransaksi, String statusTransaksi) {
        this.idTransaksi = idTransaksi;
        this.namaOS = namaOS;
        this.tipeOS = tipeOS;
        this.hargaOS = hargaOS;
        this.imageOS = imageOS;
        this.tanggalTransaksi = tanggalTransaksi;
        this.statusTransaksi = statusTransaksi;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public String getNamaOS() {
        return namaOS;
    }

    public String getTipeOS() {
        return tipeOS;
    }

    public String getHargaOS() {
        return hargaOS;
    }

    public String getImageOS() {
        return imageOS;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }
}
