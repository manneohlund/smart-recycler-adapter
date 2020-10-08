package smartrecycleradapter

/*
 * Created by Manne Öhlund on 31/05/17.
 * Copyright © 2017 All rights reserved.
 */

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartrecycleradapter.models.CopyrightModel
import smartrecycleradapter.models.MovieCategory
import smartrecycleradapter.viewholder.ActionMoviesViewHolder
import smartrecycleradapter.viewholder.AdventureMoviesViewHolder
import smartrecycleradapter.viewholder.AnimatedMoviesViewHolder
import smartrecycleradapter.viewholder.BannerViewHolder
import smartrecycleradapter.viewholder.ComingSoonMoviesViewHolder
import smartrecycleradapter.viewholder.CopyrightViewHolder
import smartrecycleradapter.viewholder.MyWatchListViewHolder
import smartrecycleradapter.viewholder.PosterViewHolder
import smartrecycleradapter.viewholder.RecentlyPlayedMoviesViewHolder
import smartrecycleradapter.viewholder.SampleFabViewHolder
import smartrecycleradapter.viewholder.SciFiMoviesViewHolder
import kotlin.reflect.KClass

class DemoActivityTest {

    @get:Rule
    var rule = ActivityTestRule(DemoActivity::class.java)

    @Test
    fun testSmartRecyclerAdapter_getItems() {
        val adapter = rule.activity.recyclerView.adapter as SmartRecyclerAdapter
        assertNotNull("Check SmartRecyclerAdapter is not null", adapter)
        val moviewCategories = adapter.getItems(MovieCategory::class)
        assertEquals("MoviePosterModel count", 1, moviewCategories.filter { it.type == "poster" }.size)
        assertEquals("MovieBannerModel count", 3, moviewCategories.filter { it.type == "banner" }.size)
        assertEquals("ComingSoonMoviesModel count", 1, moviewCategories.filter { it.id == "coming-soon" }.size)
        assertEquals("MyWatchListModel count", 1, moviewCategories.filter { it.id == "watch-list" }.size)
        assertEquals("ActionMoviesModel count", 1, moviewCategories.filter { it.id == "action" }.size)
        assertEquals("AdventureMoviesModel count", 1, moviewCategories.filter { it.id == "adventure" }.size)
        assertEquals("AnimatedMoviesModel count", 1, moviewCategories.filter { it.id == "anim" }.size)
        assertEquals("SciFiMoviesModel count", 1, moviewCategories.filter { it.id == "sci-fi" }.size)
        assertEquals("RecentlyPlayedMoviesModel count", 1, moviewCategories.filter { it.id == "recent" }.size)
        assertEquals("CopyrightModel count", 1, adapter.getItems(CopyrightModel::class).size.toLong())
    }

    @Test
    fun testSmartRecyclerAdapter_matchesViewHolderAtPosition() {
        onView(withId(R.id.recycler_view))
                .check(matchesViewHolderAtPosition(0, PosterViewHolder::class))
                .perform(scrollToPosition<PosterViewHolder>(1))
                .check(matchesViewHolderAtPosition(1, SampleFabViewHolder::class))
                .perform(scrollToPosition<SampleFabViewHolder>(2))
                .check(matchesViewHolderAtPosition(2, ComingSoonMoviesViewHolder::class))
                .perform(scrollToPosition<ComingSoonMoviesViewHolder>(3))
                .check(matchesViewHolderAtPosition(3, MyWatchListViewHolder::class))
                .perform(scrollToPosition<MyWatchListViewHolder>(4))
                .check(matchesViewHolderAtPosition(4, BannerViewHolder::class))
                .perform(scrollToPosition<BannerViewHolder>(5))
                .check(matchesViewHolderAtPosition(5, ActionMoviesViewHolder::class))
                .perform(scrollToPosition<ActionMoviesViewHolder>(6))
                .check(matchesViewHolderAtPosition(6, AdventureMoviesViewHolder::class))
                .perform(scrollToPosition<AdventureMoviesViewHolder>(7))
                .check(matchesViewHolderAtPosition(7, BannerViewHolder::class))
                .perform(scrollToPosition<BannerViewHolder>(8))
                .check(matchesViewHolderAtPosition(8, AnimatedMoviesViewHolder::class))
                .perform(scrollToPosition<AnimatedMoviesViewHolder>(9))
                .check(matchesViewHolderAtPosition(9, SciFiMoviesViewHolder::class))
                .perform(scrollToPosition<SciFiMoviesViewHolder>(   10))
                .check(matchesViewHolderAtPosition(10, BannerViewHolder::class))
                .perform(scrollToPosition<BannerViewHolder>(11))
                .check(matchesViewHolderAtPosition(11, RecentlyPlayedMoviesViewHolder::class))
                .perform(scrollToPosition<RecentlyPlayedMoviesViewHolder>(12))
                .check(matchesViewHolderAtPosition(12, CopyrightViewHolder::class))
    }

    private fun matchesViewHolderAtPosition(position: Position, target: KClass<*>): ViewAssertion {
        return ViewAssertion { view: View?, _: NoMatchingViewException? ->
            val recyclerView = view as RecyclerView
            val source = recyclerView.findViewHolderForAdapterPosition(position)!!
            assertTrue(String.format("Is <%s> assignable from <%s>", source::class, target), source::class == target)
        }
    }
}
