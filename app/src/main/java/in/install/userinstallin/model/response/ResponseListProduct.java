package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.install.userinstallin.model.data.Product;

public class ResponseListProduct {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<Product> listProduct;

    public String getStatus() {
        return status;
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

}
