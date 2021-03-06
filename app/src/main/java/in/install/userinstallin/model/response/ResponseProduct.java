package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import in.install.userinstallin.model.data.Kurir;
import in.install.userinstallin.model.data.Product;

public class ResponseProduct {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private Product product;

    public String getStatus() {
        return status;
    }

    public Product getProduct() {
        return product;
    }

}
