package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.install.userinstallin.model.data.Kurir;

public class ResponseKurir {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Kurir> listKurir;

    public String getStatus() {
        return status;
    }

    public List<Kurir> getListKurir() {
        return listKurir;
    }

}
