package com.example.nytimesmostpopulararticlesbyezzat.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaItem(

    @SerializedName("media-metadata")
    val metadata: List<MetadataItem>
) : Parcelable