package com.example.prigonews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=7d5254c453794658a84f7f741a903005")
    Call<veri_data_class>getNews();

    @GET("top-headlines")
    Call<veri_data_class>getNews1(
            @Query("country") String country,
            @Query("category") String category,
            @Query("excludeDomains") String excludeDomains,
            @Query("sortBy") String sortBy,
            @Query("language") String language,
            @Query("apiKey") String apiKey
    );

}
