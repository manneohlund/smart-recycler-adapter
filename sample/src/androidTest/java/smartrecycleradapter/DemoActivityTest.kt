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
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import smartadapter.Position
import smartadapter.SmartRecyclerAdapter
import smartrecycleradapter.models.*
import smartrecycleradapter.viewholder.*
import kotlin.reflect.KClass

class DemoActivityTest {

    @get:Rule
    var rule = ActivityTestRule(DemoActivity::class.java)

    @Test
    fun testSmartRecyclerAdapter_getItems() {
        val adapter = rule.activity.recyclerView.adapter as SmartRecyclerAdapter
        assertNotNull("Check SmartRecyclerAdapter is not null", adapter)
        assertEquals("MoviePosterModel count", 1, adapter.getItems(MoviePosterModel::class).size.toLong())
        assertEquals("MovieBannerModel count", 3, adapter.getItems(MovieBannerModel::class).size.toLong())
        assertEquals("ComingSoonMoviesModel count", 1, adapter.getItems(ComingSoonMoviesModel::class).size.toLong())
        assertEquals("MyWatchListModel count", 1, adapter.getItems(MyWatchListModel::class).size.toLong())
        assertEquals("ActionMoviesModel count", 1, adapter.getItems(ActionMoviesModel::class).size.toLong())
        assertEquals("AdventureMoviesModel count", 1, adapter.getItems(AdventureMoviesModel::class).size.toLong())
        assertEquals("AnimatedMoviesModel count", 1, adapter.getItems(AnimatedMoviesModel::class).size.toLong())
        assertEquals("SciFiMoviesModel count", 1, adapter.getItems(SciFiMoviesModel::class).size.toLong())
        assertEquals("RecentlyPlayedMoviesModel count", 1, adapter.getItems(RecentlyPlayedMoviesModel::class).size.toLong())
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
