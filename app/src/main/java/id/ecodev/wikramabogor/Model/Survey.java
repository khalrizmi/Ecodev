package id.ecodev.wikramabogor.Model;

public class Survey {
    String id,member_id,longtitude,latitude,temperature,sea_level,place_name,address,created_at,objek_count,state,city;

    public Survey(String id, String member_id, String longtitude, String latitude, String temperature, String sea_level, String place_name, String address, String created_at, String objek_count, String state, String city) {
        this.id = id;
        this.member_id = member_id;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.sea_level = sea_level;
        this.place_name = place_name;
        this.address = address;
        this.created_at = created_at;
        this.objek_count = objek_count;
        this.state = state;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSea_level() {
        return sea_level;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getAddress() {
        return address;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getObjek_count() {
        return objek_count;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }
}
