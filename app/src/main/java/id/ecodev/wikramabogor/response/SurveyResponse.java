package id.ecodev.wikramabogor.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.ecodev.wikramabogor.Model.Survey;

public class SurveyResponse {
    @SerializedName("list")
    List<Survey> surveys;
    String status,code;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public SurveyResponse(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
}
