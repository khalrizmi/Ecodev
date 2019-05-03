package id.ecodev.wikramabogor.Model;

public class Website {
    String id,title,url,member_id,name,email,status,code,created_at;

    public Website(String id, String title, String url, String member_id, String name, String email, String status, String code, String created_at) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.member_id = member_id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.code = code;
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
