package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


//HTTP CALL
public interface OpenAIService{
    @Headers({
            "Authorization: Bearer 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", // Replace with your OpenAI API key
            "Content-Type: application/json"
    })
    @POST("v1/chat/completions")
    Call<OpenAIResponse> getItinerary(@Body OpenAIRequest request);
}
