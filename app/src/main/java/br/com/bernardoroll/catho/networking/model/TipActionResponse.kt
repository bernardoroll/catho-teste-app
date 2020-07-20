package br.com.bernardoroll.catho.networking.model

import com.google.gson.annotations.SerializedName

data class TipActionResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("created-at") val createdAt: String?,
    @SerializedName("tipId") val tipId: String?,
    @SerializedName("action") val action: String?,
    @SerializedName("message") val message: String?
)
