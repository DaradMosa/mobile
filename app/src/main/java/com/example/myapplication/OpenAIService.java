package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


//HTTP CALL
public interface OpenAIService{
    @Headers({
            "Authorization: Bearer ", // Replace with your OpenAI API key
            "Content-Type: application/json"
    })
    @POST("v1/chat/completions")
    Call<OpenAIResponse> getItinerary(@Body OpenAIRequest request);
}
