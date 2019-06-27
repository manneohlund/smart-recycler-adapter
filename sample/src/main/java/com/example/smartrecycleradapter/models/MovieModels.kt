package com.example.smartrecycleradapter.models


/*
 * Created by Manne Öhlund on 2019-06-22.
 * Copyright (c) All rights reserved.
 */

class ComingSoonMoviesModel(title: String, val adapterItems: List<Any>) : NestedRecyclerViewModel(title)

class MyWatchListModel(title: String, val adapterItems: List<Any>) : NestedRecyclerViewModel(title)

class ActionMoviesModel(title: String) : NestedRecyclerViewModel(title)

class AdventureMoviesModel(title: String) : NestedRecyclerViewModel(title)

class AnimatedMoviesModel(title: String) : NestedRecyclerViewModel(title)

class SciFiMoviesModel(title: String) : NestedRecyclerViewModel(title)

class RecentlyPlayedMoviesModel(title: String) : NestedRecyclerViewModel(title)

open class NestedRecyclerViewModel(var title: String) {

    override fun toString(): String {
        return title
    }
}


class MoviePosterModel(icon: Int) : MovieModel("", icon)

class MovieBannerModel(title: String, icon: Int) : MovieModel(title, icon)

open class MovieModel(val title: String, val icon: Int)


class CopyrightModel(private var summary: String) {

    override fun toString(): String {
        return summary
    }
}