package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import id.ecodev.wikramabogor.Model.User;

public class LoginResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private User user;

    public LoginResponse(String status, User user) {
        this.status = status;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
