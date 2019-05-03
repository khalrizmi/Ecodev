package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.ecodev.wikramabogor.Model.CustomModel;

public class CustomResponse {
    @SerializedName("list")
    List<CustomModel> location;

    public CustomResponse(List<CustomModel> location) {
        this.location = location;
    }

    public List<CustomModel> getLocation() {
        return location;
    }
}
