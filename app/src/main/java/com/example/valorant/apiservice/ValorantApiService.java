package com.example.valorant.apiservice;


import com.example.valorant.models.Character;
import com.example.valorant.network.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ValorantApiService {
    @GET("v1/agents?isPlayableCharacter=true")
    Call<ValorantResponse> getAgents();
}



