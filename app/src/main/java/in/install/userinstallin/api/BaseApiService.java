package in.install.userinstallin.api;

import in.install.userinstallin.model.response.ResponseKurir;
import in.install.userinstallin.model.response.ResponseListExtras;
import in.install.userinstallin.model.response.ResponseListHistory;
import in.install.userinstallin.model.response.ResponseOrder;
import in.install.userinstallin.model.response.ResponsePost;
import in.install.userinstallin.model.response.ResponseListProduct;
import in.install.userinstallin.model.response.ResponseProduct;
import in.install.userinstallin.model.response.ResponseUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("login/")
    Call<ResponsePost> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/")
    Call<ResponseBody> register(@Field("nama") String nama,
                                @Field("no_hp") String noHp, @Field("email") String email, @Field("password") String password,
                                @Field("alamat") String alamat, @Field("foto") String imgProfile);

    @GET("user/{email}")
    Call<ResponseUser> getUserData(@Path("email") String email);

    @FormUrlEncoded
    @POST("user/{id}")
    Call<ResponsePost> updateProfile(@Path("id") String idUser,
                                     @Field("nama") String nama,
                                     @Field("no_hp") String noHp,
                                     @Field("email") String email,
                                     @Field("alamat") String alamat,
                                     @Field("password") String password,
                                     @Field("foto") String foto);

    @GET("products/")
    Call<ResponseListProduct> getAllProduct();

    @GET("extras/{id_product}")
    Call<ResponseListExtras> getDataExtras(@Path("id_product") String idProduct);

    @FormUrlEncoded
    @POST("order_product/")
    Call<ResponsePost> pesanOS(@Field("id_order") String idOrder,
                               @Field("id_kurir") String idKurir,
                                   @Field("id") String idUser,
                                   @Field("id_product") String idProduct,
                                   @Field("tgl_pengambilan") String tanggalPengambilan,
                                   @Field("waktu_pengambilan") String waktuPengambilan,
                                   @Field("tempat_pengambilan") String tempatPengambilan,
                                   @Field("status") String status,
                                   @Field("harga_total") String hargaTotal);

    @FormUrlEncoded
    @POST("order_extras/")
    Call<ResponsePost> pesanExtras(@Field("id_order") String idOrder,
                               @Field("id_extras") String idExtras);

    @GET("order_extras/{id_order}")
    Call<ResponseListExtras> getDataOrderExtrasById(@Path("id_order") String idOrder);

    @GET("kurir/{id_kurir}")
    Call<ResponseKurir> getKurirById(@Path("id_kurir") String idKurir);

    @GET("products/{id_product}")
    Call<ResponseProduct> getProductById(@Path("id_product") String idProduct);

    @GET("order_product/id/{id_order}")
    Call<ResponseOrder> getOrderById(@Path("id_order") String idOrder);

    @GET("order_product/{id_user}")
    Call<ResponseListHistory> getAllOrderById(@Path("id_user") String idUser);

    @DELETE("order_product/{id}")
    Call<ResponsePost> deleteOrder(@Path("id") String idOrder);

    @POST("onprogress/{id}")
    Call<ResponsePost> updateOnProgress(@Path("id") String idOrder);

    @POST("done/{id}")
    Call<ResponsePost> updateDone(@Path("id") String idOrder);

    @POST("canceled/{id}")
    Call<ResponsePost> updateCanceled(@Path("id") String idOrder);

    @POST("rejected/{id}")
    Call<ResponsePost> updateRejected(@Path("id") String idOrder);
//
//    @DELETE("cicilan/{id_cicilan}")
//    Call<ResponsePost> deleteCicilan(@Path("id_cicilan") String idCicilan);
//
//    @GET("nilaipasar/{id_user}")
//    Call<ResponseNilaiPasar> getAllNilaiPasar(@Path("id_user") String id_user);
//
//    @GET("nilaipasar/data/{id_nilai_pasar}")
//    Call<ResponsePropertiNilaiPasar> getDataNilaiPasar(@Path("id_nilai_pasar") String id_nilai_pasar);
//
//    @FormUrlEncoded
//    @POST("nilaipasar/data/")
//    Call<ResponsePost> saveNilaiPasar(@Field("id_nilai_pasar") String idNilaiPasar,
//                                      @Field("keterangan") String keterangan,
//                                      @Field("harga_pasaran_per_meter") String hargaPasaranPerMeter,
//                                      @Field("perbandingan_properti") String perbandinganProperti,
//                                      @Field("catatan_kondisi_bangunan") String catatanKondisiBangunan,
//                                      @Field("catatan_survey_lokasi") String catatanSurveyLokasi,
//                                      @Field("id_user") String idUser);
//
//    @FormUrlEncoded
//    @POST("nilaipasar/properti/")
//    Call<ResponsePost> savePropertiNilaiPasar(@Field("id_nilai_pasar") String idNilaiPasar,
//                                              @Field("id_properti") String idProperti,
//                                              @Field("harga_jual_properti") String hargaJualProperti,
//                                              @Field("luas_tanah") String luasTanah,
//                                              @Field("luas_bangunan") String luasBangunan,
//                                              @Field("usia_bangunan") String usiaBangunan,
//                                              @Field("harga_rata_per_meter") String hargaRataPerMeter,
//                                              @Field("harga_bangunan_baru") String hargaBangunanBaru,
//                                              @Field("harga_bangunan_saat_ini") String hargaBangunanSaatIni,
//                                              @Field("harga_tanah_per_meter") String hargaTanahPerMeter);
//
//    @FormUrlEncoded
//    @PUT("nilaipasar/data/{id_nilai_pasar}")
//    Call<ResponsePost> updateNilaiPasar(@Path("id_nilai_pasar") String idNilaiPasar,
//                                        @Field("keterangan") String keterangan,
//                                        @Field("harga_pasaran_per_meter") String hargaPasaranPerMeter,
//                                        @Field("perbandingan_properti") String perbandinganProperti,
//                                        @Field("catatan_kondisi_bangunan") String catatanKondisiBangunan,
//                                        @Field("catatan_survey_lokasi") String catatanSurveyLokasi,
//                                        @Field("id_user") String idUser);
//
//    @FormUrlEncoded
//    @PUT("nilaipasar/properti/{id_nilai_pasar}/{id_properti}")
//    Call<ResponsePost> updatePropertiNilaiPasar(@Path("id_nilai_pasar") String idNilaiPasar,
//                                                @Path("id_properti") String idProperti,
//                                                @Field("harga_jual_properti") String hargaJualProperti,
//                                                @Field("luas_tanah") String luasTanah,
//                                                @Field("luas_bangunan") String luasBangunan,
//                                                @Field("usia_bangunan") String usiaBangunan,
//                                                @Field("harga_rata_per_meter") String hargaRataPerMeter,
//                                                @Field("harga_bangunan_baru") String hargaBangunanBaru,
//                                                @Field("harga_bangunan_saat_ini") String hargaBangunanSaatIni,
//                                                @Field("harga_tanah_per_meter") String hargaTanahPerMeter);
//
//    @DELETE("nilaipasar/data/{id_nilai_pasar}")
//    Call<ResponsePost> deleteNilaiPasar(@Path("id_nilai_pasar") String idNilaiPasar);
//
//    @GET("cashflow/{id_user}")
//    Call<ResponseCashFlow> getAllCashFlow(@Path("id_user") String idUser);
//
//    @GET("cashflow/data/{id_cash_flow}")
//    Call<ResponseCashFlow> getDataCashFlow(@Path("id_cash_flow") String idCashFlow);
//
//    @GET("cashflow/kamar/{id_cash_flow}")
//    Call<ResponseKamar> getKamarCashFlow(@Path("id_cash_flow") String idCashFlow);
//
//    @GET("cashflow/pemasukan/{id_cash_flow}")
//    Call<ResponsePemasukan> getPemasukanCashFlow(@Path("id_cash_flow") String idCashFlow);
//
//    @GET("cashflow/pengeluaran/{id_cash_flow}")
//    Call<ResponsePengeluaran> getPengeluaranCashFlow(@Path("id_cash_flow") String idCashFlow);
//
//    @GET("cashflow/fasilitas/{id_cash_flow}")
//    Call<ResponseFasilitas> getFasilitasCashFlow(@Path("id_cash_flow") String idCashFlow);
//
//    @GET("cashflow/extras/{id_cash_flow}")
//    Call<ResponseListExtras> getExtrasCashFlow(@Path("id_cash_flow") String idCashFlow);
//
//    @FormUrlEncoded
//    @POST("cashflow/data/")
//    Call<ResponsePost> saveCashFlow(@Field("id_cash_flow") String idCashFlow,
//                                    @Field("keterangan") String keterangan,
//                                    @Field("id_user") String idUser);
//
//    @FormUrlEncoded
//    @POST("cashflow/kamar/")
//    Call<ResponsePost> saveKamar(@Field("id_cash_flow") String idCashFlow,
//                                 @Field("tipe_kamar") String tipeKamar,
//                                 @Field("jumlah_kamar") String jumlahKamar,
//                                 @Field("harga_kamar") String hargaKamar);
//
//    @FormUrlEncoded
//    @POST("cashflow/pemasukan/")
//    Call<ResponsePost> savePemasukan(@Field("id_cash_flow") String idCashFlow,
//                                     @Field("pemasukan") String pemasukan,
//                                     @Field("jumlah_pemasukan") String jumlahPemasukan);
//
//    @FormUrlEncoded
//    @POST("cashflow/pengeluaran/")
//    Call<ResponsePost> savePengeluaran(@Field("id_cash_flow") String idCashFlow,
//                                       @Field("pengeluaran") String pengeluaran,
//                                       @Field("jumlah_pengeluaran") String jumlahPengeluaran);
//
//    @FormUrlEncoded
//    @POST("cashflow/fasilitas/")
//    Call<ResponsePost> saveFasilitas(@Field("id_cash_flow") String idCashFlow,
//                                     @Field("nama_fasilitas") String namaFasilitas,
//                                     @Field("kenaikan_harga") String kenaikanHarga,
//                                     @Field("jumlah_kamar") String jumlahKamar);
//
//    @FormUrlEncoded
//    @POST("cashflow/extras/")
//    Call<ResponsePost> saveExtras(@Field("id_cash_flow") String idCashFlow,
//                                  @Field("occupancy_rate") String occupancyRate,
//                                  @Field("total_penghasilan") String totalPenghasilan,
//                                  @Field("total_pemasukan") String totalPemasukan,
//                                  @Field("total_pengeluaran") String totalPengeluaran,
//                                  @Field("net_operating_income") String netOperatingIncome,
//                                  @Field("net_operating_income_future") String netOperatingIncomeFuture);
//
//    @FormUrlEncoded
//    @PUT("cashflow/data/{id_cash_flow}")
//    Call<ResponsePost> updateCashFlow(@Path("id_cash_flow") String idCashFlow,
//                                      @Field("keterangan") String keterangan,
//                                      @Field("id_user") String idUser);
//
//    @FormUrlEncoded
//    @PUT("cashflow/kamar/{id_cash_flow}")
//    Call<ResponsePost> updateKamar(@Path("id_cash_flow") String idCashFlow,
//                                   @Field("tipe_kamar") String tipeKamar,
//                                   @Field("jumlah_kamar") String jumlahKamar,
//                                   @Field("harga_kamar") String hargaKamar);
//
//    @FormUrlEncoded
//    @PUT("cashflow/pemasukan/{id_cash_flow}")
//    Call<ResponsePost> updatePemasukan(@Path("id_cash_flow") String idCashFlow,
//                                       @Field("pemasukan") String pemasukan,
//                                       @Field("jumlah_pemasukan") String jumlahPemasukan);
//
//    @FormUrlEncoded
//    @PUT("cashflow/pengeluaran/{id_cash_flow}")
//    Call<ResponsePost> updatePengeluaran(@Path("id_cash_flow") String idCashFlow,
//                                         @Field("pengeluaran") String pengeluaran,
//                                         @Field("jumlah_pengeluaran") String jumlahPengeluaran);
//
//    @FormUrlEncoded
//    @PUT("cashflow/fasilitas/{id_cash_flow}")
//    Call<ResponsePost> updateFasilitas(@Path("id_cash_flow") String idCashFlow,
//                                       @Field("nama_fasilitas") String namaFasilitas,
//                                       @Field("kenaikan_harga") String kenaikanHarga,
//                                       @Field("jumlah_kamar") String jumlahKamar);
//
//    @FormUrlEncoded
//    @PUT("cashflow/extras/{id_cash_flow}")
//    Call<ResponsePost> updateExtras(@Path("id_cash_flow") String idCashFlow,
//                                    @Field("occupancy_rate") String occupancyRate,
//                                    @Field("total_penghasilan") String totalPenghasilan,
//                                    @Field("total_pemasukan") String totalPemasukan,
//                                    @Field("total_pengeluaran") String totalPengeluaran,
//                                    @Field("net_operating_income") String netOperatingIncome,
//                                    @Field("net_operating_income_future") String netOperatingIncomeFuture);
//
//    @DELETE("cashflow/data/{id_cash_flow}")
//    Call<ResponsePost> deleteCashFlow(@Path("id_cash_flow") String idCashFlow);

}