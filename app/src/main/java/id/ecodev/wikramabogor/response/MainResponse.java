package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

public class MainResponse {
    @SerializedName("status")
    private boolean status;

    public MainResponse(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
