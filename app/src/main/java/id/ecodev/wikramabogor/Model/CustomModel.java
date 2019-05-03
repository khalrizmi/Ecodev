package id.ecodev.wikramabogor.Model;

public class CustomModel {
    String id,longtitude,latitude,name,email,photo,created_at,member_id,place_name,address,city;

    public CustomModel(String id, String longtitude, String latitude, String name, String email, String photo, String created_at, String member_id, String place_name, String address, String city) {
        this.id = id;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.created_at = created_at;
        this.member_id = member_id;
        this.place_name = place_name;
        this.address = address;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getAddress() {
        return address;
    }
}
