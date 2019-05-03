package id.ecodev.wikramabogor.network;


import java.util.Map;

import id.ecodev.wikramabogor.response.CategoryResponse;
import id.ecodev.wikramabogor.response.CustomResponse;
import id.ecodev.wikramabogor.response.LoginResponse;
import id.ecodev.wikramabogor.response.MainResponse;
import id.ecodev.wikramabogor.response.ObjekResponse;
import id.ecodev.wikramabogor.response.SlideResponse;
import id.ecodev.wikramabogor.response.SurveyResponse;
import id.ecodev.wikramabogor.response.WeatherResponse;
import id.ecodev.wikramabogor.response.WebsiteResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClass {
    //SliderActivity
    @GET("slide/list")
    Call<SlideResponse> getSliders();

    //CredentialActivity
    @GET("member/check/{id}")
    Call<MainResponse> checkMain(@Path("id") int id);

    @FormUrlEncoded
    @POST("member/login")
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("name") String name,
                              @Field("photo") String photo);

    @GET("category/list")
    Call<CategoryResponse> getCategories();

    @GET("objek/list/{id}")
    Call<ObjekResponse> getObjek(@Path("id") String id);

    @GET("objek/list_survey/{id}")
    Call<ObjekResponse> getSurveyObject(@Path("id") String id);

    @Multipart
    @POST("objek/add")
    Call<ObjekResponse> createObjek(@PartMap Map<String, RequestBody> map,
                                    @Part("category_id") RequestBody category_id,
                                    @Part("member_id") RequestBody member_id,
                                    @Part("name") RequestBody name,
                                    @Part("description") RequestBody description
    );

    @FormUrlEncoded
    @POST("objek/verified")
    Call<ObjekResponse> verifObjek(@Field("id") String id,
                                   @Field("note") String note);

    @GET("objek/delete/{id}")
    Call<ObjekResponse> deleteObject(@Path("id") String id);

    @GET("survey/list/{id}")
    Call<SurveyResponse> getSurvey(@Path("id") String id);

    @GET("survey/list_maps/{as}/{string_data}")
    Call<CustomResponse> getSurveyMaps(@Path("as") String as,
                                       @Path("string_data") String data);



    @GET("weather/")
    Call<WeatherResponse> getWeather(@Query("lat") String latitude,
                                     @Query("lon") String longitude,
                                     @Query("appid") String appId,
                                     @Query("units") String units);

    @FormUrlEncoded
    @POST("survey/add")
    Call<SurveyResponse> createSurvey(@Field("member_id") String member_id,
                                      @Field("longtitude") String longtitude,
                                      @Field("latitude") String latitude,
                                      @Field("temperature") String temperature,
                                      @Field("sea_level") String sea_level,
                                      @Field("place_name") String place_name,
                                      @Field("address") String address,
                                      @Field("city") String city,
                                      @Field("state") String state
    );

    @FormUrlEncoded
    @POST("website/add")
    Call<WebsiteResponse> addWebsite(@Field("title") String title,
                                     @Field("url") String url,
                                     @Field("member_id") String member_id,
                                     @Field("objek_id") String objek_id);

    @GET("website/list/{id}")
    Call<WebsiteResponse> getWebsite(@Path("id") int id);

    @GET("website/delete/{id}")
    Call<WebsiteResponse> deleteWebsite(@Path("id") String id);




}