package id.ecodev.wikramabogor.Model;

import com.google.gson.annotations.SerializedName;

public class Slide {
    @SerializedName("image_slide")
    String image;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;

    public Slide(String image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
