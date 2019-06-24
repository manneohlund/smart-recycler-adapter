package com.example.smartrecycleradapter;

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.smartrecycleradapter.models.ActionMoviesViewModel;
import com.example.smartrecycleradapter.models.AdventureMoviesViewModel;
import com.example.smartrecycleradapter.models.AnimatedMoviesViewModel;
import com.example.smartrecycleradapter.models.ComingSoonMoviewViewModel;
import com.example.smartrecycleradapter.models.MovieBannerViewModel;
import com.example.smartrecycleradapter.models.MoviePosterViewModel;
import com.example.smartrecycleradapter.models.MovieViewModel;
import com.example.smartrecycleradapter.models.RecentlyPlayedMoviesViewModel;
import com.example.smartrecycleradapter.models.SciFiMoviesViewModel;
import com.example.smartrecycleradapter.viewholder.ActionMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.AdventureMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.AnimatedMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.BannerViewHolder;
import com.example.smartrecycleradapter.viewholder.ComingSoonMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.CopyrightViewHolder;
import com.example.smartrecycleradapter.viewholder.LargeThumbViewHolder;
import com.example.smartrecycleradapter.viewholder.PosterViewHolder;
import com.example.smartrecycleradapter.viewholder.RecentlyPlayedMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.SciFiMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.SmallThumbViewHolder;
import com.example.smartrecycleradapter.viewholder.ThumbViewHolder;

import java.util.ArrayList;
import java.util.List;

import smartadapter.SmartRecyclerAdapter;

import static com.example.smartrecycleradapter.data.MoviePosterItems.ACTION_ITEMS;
import static com.example.smartrecycleradapter.data.MoviePosterItems.ADVENTURE_ITEMS;
import static com.example.smartrecycleradapter.data.MoviePosterItems.ANIM_ITEMS;
import static com.example.smartrecycleradapter.data.MoviePosterItems.COMING_SOON_ITEMS;
import static com.example.smartrecycleradapter.data.MoviePosterItems.SCI_FI_ITEMS;
import static com.example.smartrecycleradapter.data.MoviePosterItems.getRandomBanner;
import static com.example.smartrecycleradapter.data.MoviePosterItems.getRandomPoster;

public class DemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        recyclerView = findViewById(R.id.recycler_view);

        initSmartRecyclerAdapter();
    }

    private void initSmartRecyclerAdapter() {
        List<Object> items = new ArrayList<>();

        items.add(new MoviePosterViewModel(getRandomPoster()));

        items.add(new ComingSoonMoviewViewModel("Coming soon"));
        items.add(new MovieBannerViewModel("Recommended", getRandomBanner()));
        items.add(new ActionMoviesViewModel("Action"));
        items.add(new AdventureMoviesViewModel("Adventure"));
        items.add(new MovieBannerViewModel("Trending", getRandomBanner()));
        items.add(new AnimatedMoviesViewModel("Animated"));
        items.add(new SciFiMoviesViewModel("Sci-Fi"));
        items.add(new MovieBannerViewModel("Promotion", getRandomBanner()));
        items.add(new RecentlyPlayedMoviesViewModel("Recently played"));
        items.add("SmartRecyclerAdapter v2.0.0\n\nDeveloped by Manne Öhlund");

        // All items
        List<Object> nestedRecentViewedItems = new ArrayList<>();

        // New
        List<Object> nestedNewItems = new ArrayList<>();
        for (int poster : COMING_SOON_ITEMS) {
            nestedRecentViewedItems.add(new MovieViewModel(poster));
            nestedNewItems.add(new MovieViewModel(poster));
        }

        // Action
        List<Object> nestedActionItems = new ArrayList<>();
        for (int poster : ACTION_ITEMS) {
            nestedActionItems.add(new MovieViewModel(poster));
        }

        // Adventure
        List<Object> nestedAdventureItems = new ArrayList<>();
        for (int poster : ADVENTURE_ITEMS) {
            nestedAdventureItems.add(new MovieViewModel(poster));
        }

        // Sci-Fi
        List<Object> nestedSciFiItems = new ArrayList<>();
        for (int poster : SCI_FI_ITEMS) {
            nestedSciFiItems.add(new MovieViewModel(poster));
        }

        // Animated
        List<Object> nestedAnimatedItems = new ArrayList<>();
        for (int poster : ANIM_ITEMS) {
            nestedAnimatedItems.add(new MovieViewModel(poster));
        }

        SmartRecyclerAdapter
                .items(items)
                .map(MoviePosterViewModel.class, PosterViewHolder.class)
                .map(MovieBannerViewModel.class, BannerViewHolder.class)
                .map(ComingSoonMoviewViewModel.class, ComingSoonMoviesViewHolder.class)
                .map(ActionMoviesViewModel.class, ActionMoviesViewHolder.class)
                .map(AdventureMoviesViewModel.class, AdventureMoviesViewHolder.class)
                .map(AnimatedMoviesViewModel.class, AnimatedMoviesViewHolder.class)
                .map(SciFiMoviesViewModel.class, SciFiMoviesViewHolder.class)
                .map(RecentlyPlayedMoviesViewModel.class, RecentlyPlayedMoviesViewHolder.class)
                .map(String.class, CopyrightViewHolder.class)

                // You need to define your own view event listeners like onClickListener
                .addViewEventListener((view, actionId, position) ->
                        showToast(getActionName(actionId) + " " + position)) // Event action

                /** Adds event listener and also automatically adds row item {@link View.OnClickListener} on all items root view */
                .addViewEventListener(
                        R.id.action_on_click, // Events for SmartRecyclerAdapter to add automatically
                        (view, actionId, position) ->
                                DemoActivity.this.showToast(DemoActivity.this.getActionName(actionId) + " " + position))

                /** Adds event listener and also automatically adds row item {@link View.OnLongClickListener} on all items root view */
                .addViewEventListener(
                        R.id.action_on_long_click, // Events for SmartRecyclerAdapter to add automatically
                        (view, actionId, position) ->
                                showToast(getActionName(actionId) + " " + position))

                .addViewEventListener(
                        PosterViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) -> {
                            ((SmartRecyclerAdapter)recyclerView.getAdapter()).removeItem(0, false);
                            ((SmartRecyclerAdapter)recyclerView.getAdapter()).getItems().add(0, new MoviePosterViewModel(getRandomPoster()));
                            ((SmartRecyclerAdapter)recyclerView.getAdapter()).smartNotifyDataSetChanged();
                        })

                .addViewEventListener(
                        PosterViewHolder.class,
                        R.id.playButton,
                        R.id.action_on_click,
                        (view, actionId, position) -> showToast("PLAY"))

                .addViewEventListener(
                        PosterViewHolder.class,
                        R.id.starButton,
                        R.id.action_on_click,
                        (view, actionId, position) -> showToast("ADD to favorites"))

                .addViewEventListener(
                        PosterViewHolder.class,
                        R.id.infoButton,
                        R.id.action_on_click,
                        (view, actionId, position) -> showToast("INFO"))

                .addViewEventListener(
                        ComingSoonMoviesViewHolder.class,
                        R.id.more,
                        R.id.action_on_click,
                        (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.COMING_SOON))

                .addViewEventListener(
                        ActionMoviesViewHolder.class,
                        R.id.more,
                        R.id.action_on_click,
                        (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.ACTION))

                .addViewEventListener(
                        AdventureMoviesViewHolder.class,
                        R.id.more,
                        R.id.action_on_click,
                        (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.ADVENTURE))

                .addViewEventListener(
                        AnimatedMoviesViewHolder.class,
                        R.id.more,
                        R.id.action_on_click,
                        (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.ANIMATED))

                .addViewEventListener(
                        SciFiMoviesViewHolder.class,
                        R.id.more,
                        R.id.action_on_click,
                        (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.SCI_FI))

                // Map nested SmartRecyclerAdapter
                .map(ComingSoonMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(nestedNewItems)
                                .map(MovieViewModel.class, LargeThumbViewHolder.class)
                                .addViewEventListener(
                                        LargeThumbViewHolder.class,
                                        R.id.action_on_click,
                                        (view, actionId, position) -> showToast("Coming soon " + getActionName(actionId) + " item: " + position)))

                .map(ActionMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(nestedActionItems)
                                .map(MovieViewModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        (view, actionId, position) -> showToast("Action " + getActionName(actionId) + " item: " + position)))

                .map(AdventureMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(nestedAdventureItems)
                                .map(MovieViewModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        (view, actionId, position) -> showToast("Adventure " + getActionName(actionId) + " item: " + position)))

                .map(AnimatedMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(nestedAnimatedItems)
                                .map(MovieViewModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        (view, actionId, position) -> showToast("Animated " + getActionName(actionId) + " item: " + position)))

                .map(SciFiMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(nestedSciFiItems)
                                .map(MovieViewModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        (view, actionId, position) -> showToast("Sci-Fi " + getActionName(actionId) + " item: " + position)))

                .map(RecentlyPlayedMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(nestedRecentViewedItems)
                                .map(MovieViewModel.class, SmallThumbViewHolder.class)
                                .addViewEventListener(
                                        SmallThumbViewHolder.class,
                                        R.id.action_on_click,
                                        (view, actionId, position) -> showToast("Recent " + getActionName(actionId) + " item: " + position)))
                .into(recyclerView);
    }

    private void startMovieCategoryDetailsActivity(MovieType movieType) {
        Intent intent = new Intent(this, MovieCategoryDetailsActivity.class);
        intent.putExtra("MovieType", movieType.ordinal());
        ActivityCompat.startActivity(this, intent, null);
    }

    public void showToast(String message) {
        Toast.makeText(DemoActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public String getActionName(int actionId) {
        switch (actionId) {
            case R.id.action_on_click:
                return "onClick";
            case R.id.action_on_long_click:
                return "onLongClick";
            default:
                return "Unknown";
        }
    }
}
