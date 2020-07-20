package br.com.bernardoroll.catho.networking.model

import com.google.gson.annotations.SerializedName

data class TipResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("button") val button: ButtonResponse?
)
