package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Extras implements Serializable {

    @SerializedName("id_extras")
    String idExtras;
    @SerializedName("nama_extras")
    String namaExtras;
    @SerializedName("rincian")
    String rincian;
    @SerializedName("harga_extras")
    String hargaExtras;

    public Extras(String idExtras, String namaExtras, String rincian, String hargaExtras) {
        this.idExtras = idExtras;
        this.namaExtras = namaExtras;
        this.rincian = rincian;
        this.hargaExtras = hargaExtras;
    }

    public String getIdExtras() {
        return idExtras;
    }

    public String getNamaExtras() {
        return namaExtras;
    }

    public String getRincian() {
        return rincian;
    }

    public String getHargaExtras() {
        return hargaExtras;
    }
}
