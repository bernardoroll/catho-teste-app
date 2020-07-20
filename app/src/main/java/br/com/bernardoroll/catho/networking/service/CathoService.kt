package br.com.bernardoroll.catho.networking.service

import br.com.bernardoroll.catho.networking.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
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

    @GET("/tips")
    suspend fun getTips(
        @Header("x-api-key") apiKey: String
    ): Response<List<TipResponse>>

    @POST("/survey/tips/{tipId}/{action}")
    suspend fun postTipAction(
        @Header("x-api-key") apiKey: String,
        @Header("Authorization") token: String,
        @Path("tipId") tipId: String,
        @Path("action") action: String
    ): Response<TipActionResponse>
}
