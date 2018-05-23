package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.install.userinstallin.model.data.History;

public class ResponseHistory {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<History> listHistory;

    public String getStatus() {
        return status;
    }

    public List<History> getListHistory() {
        return listHistory;
    }

}
