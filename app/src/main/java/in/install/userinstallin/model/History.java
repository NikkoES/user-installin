package in.install.userinstallin.model;

public class History {

    String idTransaksi;
    String namaOS;
    String tipeOS;
    String hargaOS;
    String imageOS;
    String tanggalTransaksi;
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
