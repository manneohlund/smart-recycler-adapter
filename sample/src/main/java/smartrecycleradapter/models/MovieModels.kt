package smartrecycleradapter.models

import smartrecycleradapter.data.MovieDataItems.BANNER_BASE_URL
import smartrecycleradapter.data.MovieDataItems.POSTER_BASE_URL
import smartrecycleradapter.data.MovieDataItems.THUMBS_BASE_URL

/*
 * Created by Manne Öhlund on 2019-06-22.
 * Copyright (c) All rights reserved.
 */

class ComingSoonMoviesModel(title: String) : NestedRecyclerViewModel(title)

class MyWatchListModel(title: String, items: MutableList<Any>? = null) : NestedRecyclerViewModel(title, items)

class ActionMoviesModel(title: String, items: MutableList<Any>? = null) : NestedRecyclerViewModel(title, items)

class AdventureMoviesModel(title: String, items: MutableList<Any>? = null) : NestedRecyclerViewModel(title, items)

class AnimatedMoviesModel(title: String) : NestedRecyclerViewModel(title)

class SciFiMoviesModel(title: String) : NestedRecyclerViewModel(title)

class RecentlyPlayedMoviesModel(title: String) : NestedRecyclerViewModel(title)

open class NestedRecyclerViewModel(var title: String, var items: MutableList<Any>? = null) {

    override fun toString(): String {
        return title
    }
}

class MoviePosterModel(icon: String) : MovieModel("", icon) {
    override val iconUrl: String
        get() = "$POSTER_BASE_URL$icon.jpg"
}

class MovieBannerModel(title: String, icon: String) : MovieModel(title, icon) {
    override val iconUrl: String
        get() = "$BANNER_BASE_URL$icon.jpg"
}

open class MovieModel(val title: String, internal val icon: String) {
    open val iconUrl: String
        get() = "$THUMBS_BASE_URL$icon.jpg"
}

class CopyrightModel(private var summary: String) {
    override fun toString(): String {
        return summary
    }
}
