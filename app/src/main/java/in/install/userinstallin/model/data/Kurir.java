package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kurir implements Serializable {

    @SerializedName("id_kurir")
    String idKurir;
    @SerializedName("nama_kurir")
    String namaKurir;
    @SerializedName("no_ktp")
    String noKtp;
    @SerializedName("no_hp")
    String noHp;
    @SerializedName("foto")
    String foto;

    public Kurir(String idKurir, String namaKurir, String noKtp, String noHp, String foto) {
        this.idKurir = idKurir;
        this.namaKurir = namaKurir;
        this.noKtp = noKtp;
        this.noHp = noHp;
        this.foto = foto;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getFoto() {
        return foto;
    }
}
