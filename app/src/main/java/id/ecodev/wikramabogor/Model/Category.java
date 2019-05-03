package id.ecodev.wikramabogor.Model;

public class Category {
    String id,title,icon_category;

    public Category(String id, String title, String icon_category) {
        this.id = id;
        this.title = title;
        this.icon_category = icon_category;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon_category() {
        return icon_category;
    }
}
