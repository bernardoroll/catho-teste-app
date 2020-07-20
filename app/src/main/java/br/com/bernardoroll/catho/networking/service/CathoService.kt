package br.com.bernardoroll.catho.networking.service

import br.com.bernardoroll.catho.networking.model.ApiKeysResponse
import br.com.bernardoroll.catho.networking.model.AuthResponse
import br.com.bernardoroll.catho.networking.model.SuggestionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CathoService {

    @GET("/keys")
    suspend fun getApiKeys(): Response<ApiKeysResponse>

    @GET("/auth/{userId}")
    suspend fun getAuth(
        @Header("x-api-key") apiKey: String,
        @Path("userId") userId: String
    ): Response<AuthResponse>

    @GET("/suggestion")
    suspend fun getSuggestions(
        @Header("x-api-key") apiKey: String,
        @Header("Authorization") token: String
    ): Response<List<SuggestionResponse>>
}
