package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.ecodev.wikramabogor.Model.Objek;

public class ObjekResponse {
    @SerializedName("list")
    List<Objek> objeks;
    String status;
    String code;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public ObjekResponse(List<Objek> objeks) {
        this.objeks = objeks;
    }

    public List<Objek> getObjeks() {
        return objeks;
    }

    public void setObjeks(List<Objek> objeks) {
        this.objeks = objeks;
    }
}
