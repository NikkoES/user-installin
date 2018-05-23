package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id_user")
    String idUser;
    @SerializedName("nama_user")
    String namaUser;
    @SerializedName("no_hp")
    String noHp;
    @SerializedName("email")
    String email;
    @SerializedName("alamat")
    String alamat;
    @SerializedName("img_profile")
    String imgProfile;

    public User(String idUser, String namaUser, String noHp, String email, String alamat, String imgProfile) {
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.noHp = noHp;
        this.email = email;
        this.alamat = alamat;
        this.imgProfile = imgProfile;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getImgProfile() {
        return imgProfile;
    }

}
