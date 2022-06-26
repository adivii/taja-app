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

public interface AccountApiInterface {
    @GET("account/{username}")
    Call<List<Account>> getAccount(@Path("username") String username);

    @FormUrlEncoded
    @POST("account")
    Call<Account> postAccount(@Field("username") String username,
                              @Field("password") String password,
                              @Field("role") String role);

    @FormUrlEncoded
    @PUT("account/{username}")
    Call<Account> putAccount(@Path("username") String username,
                             @Field("password") String password,
                             @Field("role") String role);

    @FormUrlEncoded
    @DELETE("account/{username}")
    Call<Account> deleteAccount(@Path("username") String username);
}
