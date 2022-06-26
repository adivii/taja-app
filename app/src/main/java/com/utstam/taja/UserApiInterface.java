package com.utstam.taja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApiInterface {
    @GET("user/{username}")
    Call<List<User>> getUser(@Path("username") String username);

    @FormUrlEncoded
    @POST("user")
    Call<User> postUser(@Field("username") String username,
                        @Field("first_name") String first_name,
                        @Field("last_name") String last_name);

    @FormUrlEncoded
    @PUT("user/{username}")
    Call<User> putUser(@Path("username") String username,
                       @Field("first_name") String password,
                       @Field("last_name") String role);

//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
//    Call<Account> deleteKontak(@Field("username") String username);
}
