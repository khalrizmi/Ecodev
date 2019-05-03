package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.ecodev.wikramabogor.Model.Category;

public class CategoryResponse {
    @SerializedName("list")
    List<Category> categories;

    public CategoryResponse(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
