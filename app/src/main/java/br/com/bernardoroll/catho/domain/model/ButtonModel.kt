package br.com.bernardoroll.catho.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ButtonModel(
    val show: Boolean?,
    val label: String?,
    val url: String?
) : Parcelable
