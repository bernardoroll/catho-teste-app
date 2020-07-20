package br.com.bernardoroll.catho.networking.model

import com.google.gson.annotations.SerializedName

class ButtonResponse(
    @SerializedName("show") val show: Boolean?,
    @SerializedName("label") val label: String?,
    @SerializedName("url") val url: String?
)
