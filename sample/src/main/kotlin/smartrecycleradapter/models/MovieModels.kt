package smartrecycleradapter.models

import com.google.gson.annotations.SerializedName
import smartadapter.nestedadapter.SmartNestedItem

/*
 * Created by Manne Öhlund on 2019-06-22.
 * Copyright (c) All rights reserved.
 */

data class MovieData(
    @SerializedName("categories")
    val categories: MutableList<MovieCategory>
)

data class MovieCategory(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("items")
    override var items: MutableList<MovieModel>
) : SmartNestedItem<MovieModel> {

    override fun toString(): String {
        return title
    }
}

data class MovieModel(
    @SerializedName("title")
    val title: String,
    @SerializedName("icon")
    internal val icon: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("size")
    val size: Int = MEDIUM
) {
    val iconUrl: String
        get() = when (size) {
            POSTER -> "$POSTER_BASE_URL$icon.jpg"
            BANNER -> "$BANNER_BASE_URL$icon.jpg"
            else -> "$THUMBS_BASE_URL$icon.jpg"
        }

    companion object {
        const val POSTER = 4
        const val BANNER = 3
        const val LARGE = 2
        const val MEDIUM = 1
        const val SMALL = 0

        const val POSTER_BASE_URL =
            "https://raw.githubusercontent.com/manneohlund/smart-recycler-adapter-resources/master/posters/"
        const val BANNER_BASE_URL =
            "https://raw.githubusercontent.com/manneohlund/smart-recycler-adapter-resources/master/banners/"
        const val THUMBS_BASE_URL =
            "https://raw.githubusercontent.com/manneohlund/smart-recycler-adapter-resources/master/thumbs/"
    }
}

class CopyrightModel(private var summary: String) {
    override fun toString(): String {
        return summary
    }
}
