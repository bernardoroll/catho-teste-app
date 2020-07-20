package br.com.bernardoroll.catho.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiKeysModel(
    val auth: String?,
    val tips: String?,
    val suggestion: String?,
    val survey: String?
) : Parcelable
