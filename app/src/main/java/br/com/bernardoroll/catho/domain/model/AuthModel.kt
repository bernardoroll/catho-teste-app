package br.com.bernardoroll.catho.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthModel(
    val id: String?,
    val name: String?,
    val token: String?,
    val photo: String?
) : Parcelable
