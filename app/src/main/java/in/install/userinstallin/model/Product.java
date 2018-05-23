package in.install.userinstallin.model;

public class Product {

    String idProduct;
    String namaOS;
    String tipeOS;
    String hargaOS;
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
