package com.si6b.ridermotogp.API;

import com.si6b.ridermotogp.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieveAllData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ModelResponse> ardCreateData(
            @Field("nama") String nama,
            @Field("nomor") String nomor,
            @Field("sponsor") String sponsor,
            @Field("negara") String negara
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ModelResponse> ardDeleteData(
        @Field("id") int id
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<ModelResponse> ardUpdateData(
            @Field("id") int id,
            @Field("nama") String nama,
            @Field("nomor") String nomor,
            @Field("sponsor") String sponsor,
            @Field("negara") String negara
    );
}
