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

public interface EventApiInterface {
    @GET("event")
    Call<List<Event>> getEvent();

    @FormUrlEncoded
    @POST("event")
    Call<Event> postEvent(@Field("event_title") String title,
                            @Field("event_place") String place,
                            @Field("event_date") String date);

    @FormUrlEncoded
    @PUT("event/{id}")
    Call<Event> putEvent(@Path("id") int id,
                         @Field("event_title") String title,
                         @Field("event_place") String place,
                         @Field("event_date") String date);

    @FormUrlEncoded
    @DELETE("event/{id}")
    Call<Event> deleteEvent(@Path("id") int id);
}
