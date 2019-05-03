package id.ecodev.wikramabogor.base;

import android.content.Context;
import android.os.Bundle;

import id.ecodev.wikramabogor.network.ApiClass;
import id.ecodev.wikramabogor.network.ApiClient;
import retrofit2.Retrofit;

public class BasePresenter {

    Retrofit retrofit;



    public ApiClass apiClass = new ApiClient().getClient().create(ApiClass.class);
    public ApiClass apiClass2 = new ApiClient().buildRetrofit2().create(ApiClass.class);
    public Bundle bundle;
    public Context context;

}
