package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kurir implements Serializable {

    @SerializedName("ID_KURIR")
    String idKurir;
    @SerializedName("NAMA")
    String namaKurir;
    @SerializedName("NO_KTP")
    String noKtp;
    @SerializedName("NO_HP")
    String noHp;
    @SerializedName("FOTO")
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
