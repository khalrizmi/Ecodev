package id.ecodev.wikramabogor.ui.AddSurveyScreen;

import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import id.ecodev.wikramabogor.base.BasePresenter;
import id.ecodev.wikramabogor.response.CategoryResponse;
import id.ecodev.wikramabogor.response.ObjekResponse;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSurveyPresenter<Survey extends AddSurveyView> extends BasePresenter {

    Survey addSurveyView;

    public AddSurveyPresenter(Survey addSurveyView) {
        this.addSurveyView = addSurveyView;
    }

    public void getCategories()
    {
        addSurveyView.onShow();
        apiClass.getCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                addSurveyView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    addSurveyView.onSuccessLoadCategories(response.body().getCategories());
                    addSurveyView.onHide();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                addSurveyView.onError(t.getMessage());
                addSurveyView.onHide();
            }
        });
    }

    public void createObjek(String category_id,String member_id,String name,String description,String photo)
    {
        Map<String, RequestBody> map = new HashMap<>();
        File fileObjek = new File(photo);
        RequestBody requestBodyObjek = RequestBody.create(MediaType.parse("*/*"), fileObjek);
        map.put("photo_objek\"; filename=\"" + fileObjek.getName() + "\"", requestBodyObjek);

        RequestBody Category_id = RequestBody.create(MediaType.parse("text/plain"),category_id);
        RequestBody Member_id   = RequestBody.create(MediaType.parse("text/plain"),member_id);
        RequestBody Name        = RequestBody.create(MediaType.parse("text/plain"),name);
        RequestBody Description = RequestBody.create(MediaType.parse("text/plain"),description);

        addSurveyView.onShow();
        apiClass.createObjek(map,Category_id,Member_id,Name,Description).enqueue(new Callback<ObjekResponse>() {
            @Override
            public void onResponse(Call<ObjekResponse> call, Response<ObjekResponse> response) {
                addSurveyView.getHttp(Integer.toString(response.code()));
                if (response.isSuccessful())
                {
                    addSurveyView.onSuccessCreateObjek(response.body().getStatus());
                    addSurveyView.onHide();
                } else {
                    Log.e("Hamz", "Gagal " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ObjekResponse> call, Throwable t) {
                addSurveyView.onError(t.getMessage());
                addSurveyView.onHide();
            }
        });
    }
}
