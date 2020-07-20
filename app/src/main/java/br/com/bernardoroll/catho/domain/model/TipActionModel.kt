package br.com.bernardoroll.catho.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TipActionModel(
    val id: String?,
    val createdAt: String?,
    val tipId: String?,
    val action: String?,
    val message: String?
) : Parcelable
