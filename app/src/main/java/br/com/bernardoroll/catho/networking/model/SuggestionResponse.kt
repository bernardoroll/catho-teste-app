package br.com.bernardoroll.catho.networking.model

import com.google.gson.annotations.SerializedName

data class SuggestionResponse(
    @SerializedName("jobAdTile") val jobAdTile: String?,
    @SerializedName("company") val company: String?,
    @SerializedName("date") val date: String?,
    @SerializedName("totalPositions") val totalPositions: Int?,
    @SerializedName("locations") val locations: List<String>?,
    @SerializedName("salary") val salary: SalaryResponse
)
