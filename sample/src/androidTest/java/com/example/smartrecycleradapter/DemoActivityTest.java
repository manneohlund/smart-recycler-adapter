package com.example.smartrecycleradapter;

/*
 * Created by Manne Öhlund on 31/05/17.
 * Copyright © 2017 All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAssertion;
import androidx.test.rule.ActivityTestRule;

import com.example.smartrecycleradapter.models.ActionMoviesModel;
import com.example.smartrecycleradapter.models.AdventureMoviesModel;
import com.example.smartrecycleradapter.models.AnimatedMoviesModel;
import com.example.smartrecycleradapter.models.ComingSoonMoviesModel;
import com.example.smartrecycleradapter.models.CopyrightModel;
import com.example.smartrecycleradapter.models.MovieBannerModel;
import com.example.smartrecycleradapter.models.MoviePosterModel;
import com.example.smartrecycleradapter.models.MyWatchListModel;
import com.example.smartrecycleradapter.models.RecentlyPlayedMoviesModel;
import com.example.smartrecycleradapter.models.SciFiMoviesModel;
import com.example.smartrecycleradapter.viewholder.ActionMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.AdventureMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.AnimatedMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.BannerViewHolder;
import com.example.smartrecycleradapter.viewholder.ComingSoonMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.CopyrightViewHolder;
import com.example.smartrecycleradapter.viewholder.MyWatchListViewHolder;
import com.example.smartrecycleradapter.viewholder.PosterViewHolder;
import com.example.smartrecycleradapter.viewholder.RecentlyPlayedMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.SciFiMoviesViewHolder;

import org.junit.Rule;
import org.junit.Test;

import smartadapter.SmartRecyclerAdapter;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DemoActivityTest {

    @Rule
    public ActivityTestRule<DemoActivity> rule  = new ActivityTestRule<>(DemoActivity.class);

    @Test
    public void testSmartRecyclerAdapter_getItems() {
        SmartRecyclerAdapter adapter = (SmartRecyclerAdapter) rule.getActivity().recyclerView.getAdapter();
        assertNotNull("Check SmartRecyclerAdapter is not null", adapter);
        assertEquals("MoviePosterModel count", 1, adapter.getItems(MoviePosterModel.class).size());
        assertEquals("MovieBannerModel count", 3, adapter.getItems(MovieBannerModel.class).size());
        assertEquals("ComingSoonMoviesModel count", 1, adapter.getItems(ComingSoonMoviesModel.class).size());
        assertEquals("MyWatchListModel count", 1, adapter.getItems(MyWatchListModel.class).size());
        assertEquals("ActionMoviesModel count", 1, adapter.getItems(ActionMoviesModel.class).size());
        assertEquals("AdventureMoviesModel count", 1, adapter.getItems(AdventureMoviesModel.class).size());
        assertEquals("AnimatedMoviesModel count", 1, adapter.getItems(AnimatedMoviesModel.class).size());
        assertEquals("SciFiMoviesModel count", 1, adapter.getItems(SciFiMoviesModel.class).size());
        assertEquals("RecentlyPlayedMoviesModel count", 1, adapter.getItems(RecentlyPlayedMoviesModel.class).size());
        assertEquals("CopyrightModel count", 1, adapter.getItems(CopyrightModel.class).size());
    }

    @Test
    public void testSmartRecyclerAdapter_matchesViewHolderAtPosition() {
        onView(withId(R.id.recycler_view))
                .check(matchesViewHolderAtPosition(0, PosterViewHolder.class))
                .perform(scrollToPosition(1))
                .check(matchesViewHolderAtPosition(1, ComingSoonMoviesViewHolder.class))
                .perform(scrollToPosition(2))
                .check(matchesViewHolderAtPosition(2, MyWatchListViewHolder.class))
                .perform(scrollToPosition(3))
                .check(matchesViewHolderAtPosition(3, BannerViewHolder.class))
                .perform(scrollToPosition(4))
                .check(matchesViewHolderAtPosition(4, ActionMoviesViewHolder.class))
                .perform(scrollToPosition(5))
                .check(matchesViewHolderAtPosition(5, AdventureMoviesViewHolder.class))
                .perform(scrollToPosition(6))
                .check(matchesViewHolderAtPosition(6, BannerViewHolder.class))
                .perform(scrollToPosition(7))
                .check(matchesViewHolderAtPosition(7, AnimatedMoviesViewHolder.class))
                .perform(scrollToPosition(8))
                .check(matchesViewHolderAtPosition(8, SciFiMoviesViewHolder.class))
                .perform(scrollToPosition(9))
                .check(matchesViewHolderAtPosition(9, BannerViewHolder.class))
                .perform(scrollToPosition(10))
                .check(matchesViewHolderAtPosition(10, RecentlyPlayedMoviesViewHolder.class))
                .perform(scrollToPosition(11))
                .check(matchesViewHolderAtPosition(11, CopyrightViewHolder.class));
    }

    private static ViewAssertion matchesViewHolderAtPosition(int position, Class<?> target) {
        return (view, noViewFoundException) -> {
            RecyclerView recyclerView = (RecyclerView) view;
            Class<? extends RecyclerView.ViewHolder> source = recyclerView.findViewHolderForAdapterPosition(position).getClass();
            assertTrue(String.format("Is <%s> assignable from <%s>", source, target), source.isAssignableFrom(target));
        };
    }
}