package com.example.nytimesmostpopulararticlesbyezzat.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleItem(
    val title: String,
    val source: String,
    val published_date: String,
    val media: List<MediaItem>,
    @SerializedName("url") val ur: String,
    val abstract: String
) : Parcelable