package in.install.userinstallin.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("id_product")
    String idProduct;
    @SerializedName("nama_os")
    String namaOS;
    @SerializedName("tipe_os")
    String tipeOS;
    @SerializedName("harga_os")
    String hargaOS;
    @SerializedName("image_os")
    String imageOS;

    public Product(String idProduct, String namaOS, String tipeOS, String hargaOS, String imageOS) {
        this.idProduct = idProduct;
        this.namaOS = namaOS;
        this.tipeOS = tipeOS;
        this.hargaOS = hargaOS;
        this.imageOS = imageOS;
    }

    public String getIdProduct() {
        return idProduct;
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
}
