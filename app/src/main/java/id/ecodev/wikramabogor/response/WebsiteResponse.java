package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.ecodev.wikramabogor.Model.Website;

public class WebsiteResponse {
    @SerializedName("list")
    List<Website> websites;

    String status,code;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public WebsiteResponse(List<Website> websites) {
        this.websites = websites;
    }

    public List<Website> getWebsites() {
        return websites;
    }
}
