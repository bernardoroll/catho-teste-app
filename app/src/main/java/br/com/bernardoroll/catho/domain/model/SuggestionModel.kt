package br.com.bernardoroll.catho.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuggestionModel(
    val jobAdTile: String?,
    val company: String?,
    val date: String?,
    val totalPositions: Int?,
    val locations: List<String>?,
    val salary: SalaryModel
) : Parcelable
