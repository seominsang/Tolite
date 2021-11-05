package com.fkeo2021.tolit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitService {

    @Headers("Authorization: KakaoAK 3df3e12fcbd392bebd62d96beb860126")
    @GET("/v2/local/search/keyword.json")
    Call<ApiLocalResponse> searchLocalByGson(@Query("query") String query,@Query("x") String longitude,@Query("y") String latitude,@Query("radius") int radius);

}

