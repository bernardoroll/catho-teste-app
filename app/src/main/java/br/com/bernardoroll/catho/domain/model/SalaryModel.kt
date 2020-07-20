package br.com.bernardoroll.catho.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalaryModel(
    val real: String?,
    val range: String?
) : Parcelable
