package id.ecodev.wikramabogor.Model;

public class Objek {
    String id,category_id,survey_id,member_id,name,description,photo,created_at,verified, note;

    public Objek(String id, String category_id, String survey_id, String member_id, String name, String description, String photo, String created_at, String verified, String note) {
        this.id = id;
        this.category_id = category_id;
        this.survey_id = survey_id;
        this.member_id = member_id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.created_at = created_at;
        this.verified = verified;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getSurvey_id() {
        return survey_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getVerified() {
        return verified;
    }

    public String getNote() {
        return note;
    }

}

