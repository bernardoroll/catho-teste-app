package br.com.bernardoroll.catho.networking.model

import com.google.gson.annotations.SerializedName

data class SalaryResponse(
    @SerializedName("real") val real: String?,
    @SerializedName("range")val range: String?
)
