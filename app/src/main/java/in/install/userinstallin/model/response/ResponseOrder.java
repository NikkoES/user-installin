package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.install.userinstallin.model.data.Order;

public class ResponseOrder {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private Order order;

    public String getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }

}
