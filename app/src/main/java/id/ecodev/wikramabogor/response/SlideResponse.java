package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.ecodev.wikramabogor.Model.Slide;

public class SlideResponse {
    @SerializedName("list")
    ArrayList<Slide> slides;

    public SlideResponse(ArrayList<Slide> slides) {
        this.slides = slides;
    }

    public ArrayList<Slide> getSlides() {
        return slides;
    }

    public void setSlides(ArrayList<Slide> slides) {
        this.slides = slides;
    }
}
