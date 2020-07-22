package br.com.bernardoroll.catho.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TipModel(
    val id: String?,
    val description: String?,
    val button: ButtonModel?
) : Parcelable
