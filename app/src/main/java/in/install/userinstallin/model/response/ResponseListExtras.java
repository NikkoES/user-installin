package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.install.userinstallin.model.data.Extras;

public class ResponseListExtras {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Extras> listExtras;

    public String getStatus() {
        return status;
    }

    public List<Extras> getListExtras() {
        return listExtras;
    }

}
