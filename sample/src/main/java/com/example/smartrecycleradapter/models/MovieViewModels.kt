package com.example.smartrecycleradapter.models


/*
 * Created by Manne Öhlund on 2019-06-22.
 * Copyright (c) All rights reserved.
 */

class ComingSoonMoviewViewModel(title: String) : NestedRecyclerViewModel(title)

class ActionMoviesViewModel(title: String) : NestedRecyclerViewModel(title)

class AdventureMoviesViewModel(title: String) : NestedRecyclerViewModel(title)

class AnimatedMoviesViewModel(title: String) : NestedRecyclerViewModel(title)

class SciFiMoviesViewModel(title: String) : NestedRecyclerViewModel(title)

class RecentlyPlayedMoviesViewModel(title: String) : NestedRecyclerViewModel(title)

open class NestedRecyclerViewModel(var title: String) {

    override fun toString(): String {
        return title
    }
}

class MoviePosterViewModel(icon: Int) : MovieViewModel(icon)

class MovieBannerViewModel(var title: String, icon: Int) : MovieViewModel(icon)

open class MovieViewModel(var icon: Int)

class CopyrightViewModel(private var summary: String) {

    override fun toString(): String {
        return summary
    }
}