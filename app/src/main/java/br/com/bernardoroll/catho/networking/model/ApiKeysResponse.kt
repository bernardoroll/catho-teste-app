package br.com.bernardoroll.catho.networking.model

import com.google.gson.annotations.SerializedName

data class ApiKeysResponse(
    @SerializedName("auth") val auth: String,
    @SerializedName("tips") val tips: String,
    @SerializedName("suggestion") val suggestion: String,
    @SerializedName("survey") val survey: String
)
