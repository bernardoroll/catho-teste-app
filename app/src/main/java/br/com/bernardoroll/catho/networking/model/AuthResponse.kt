package br.com.bernardoroll.catho.networking.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("token") val token: String,
    @SerializedName("photo") val photo: String
)
