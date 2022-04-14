package com.si6b.ridermotogp.API;

import com.si6b.ridermotogp.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieveAllData();
}
