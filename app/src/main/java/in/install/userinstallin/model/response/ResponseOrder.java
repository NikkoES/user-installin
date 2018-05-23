package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.install.userinstallin.model.data.Order;

public class ResponseOrder {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Order> listOrder;

    public String getStatus() {
        return status;
    }

    public List<Order> getListOrder() {
        return listOrder;
    }

}
