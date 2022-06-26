package com.utstam.taja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TutorialApiInterface {
    @GET("tutorial")
    Call<List<Tutorial>> getTutorial();

    @GET("tutorial/{id}")
    Call<List<Tutorial>> getTutorial(@Path("id") int id);

    @FormUrlEncoded
    @POST("tutorial")
    Call<Tutorial> postTutorial(@Field("title") String title,
                              @Field("date") String date,
                              @Field("content") String content);

    @FormUrlEncoded
    @PUT("tutorial/{id}")
    Call<Tutorial> putTutorial(@Path("id") int id,
                             @Field("title") String title,
                             @Field("date") String date,
                             @Field("content") String content);

    @FormUrlEncoded
    @DELETE("tutorial/{id}")
    Call<Tutorial> deleteTutorial(@Path("id") int id);
}
