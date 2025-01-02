package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


//HTTP CALL
public interface OpenAIService{
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer YOUR_API_KEY" // Replace with your OpenAI API key
    })
    @POST("v1/chat/completions")
    Call<OpenAIResponse> getItinerary(@Body OpenAIRequest request);
}
