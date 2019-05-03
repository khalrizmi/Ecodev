package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import id.ecodev.wikramabogor.Model.Main;

public class WeatherResponse {
    @SerializedName("main")
    Main main;

    @SerializedName("name")
    String name;

    public WeatherResponse(Main main, String name) {
        this.main = main;
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
