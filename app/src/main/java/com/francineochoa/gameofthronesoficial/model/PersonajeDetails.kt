package com.francineochoa.gameofthronesoficial.model

import com.google.gson.annotations.SerializedName

data class PersonajeDetails(
    @SerializedName("fullName")
    val fullName: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("family")
    val family: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)
