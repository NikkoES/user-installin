package in.install.userinstallin.model.response;

import com.google.gson.annotations.SerializedName;

import in.install.userinstallin.model.data.User;

public class ResponseUser {

    @SerializedName("status")
    String status;
    @SerializedName("data")
    User user;

    public ResponseUser(String status, User user) {
        this.status = status;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

}