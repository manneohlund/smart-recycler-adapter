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

import com.example.smartrecycleradapter.data.MovieDataItems;
import com.example.smartrecycleradapter.models.ActionMoviesModel;
import com.example.smartrecycleradapter.models.AdventureMoviesModel;
import com.example.smartrecycleradapter.models.AnimatedMoviesModel;
import com.example.smartrecycleradapter.models.ComingSoonMoviesModel;
import com.example.smartrecycleradapter.models.CopyrightModel;
import com.example.smartrecycleradapter.models.MovieBannerModel;
import com.example.smartrecycleradapter.models.MovieModel;
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
import com.example.smartrecycleradapter.viewholder.LargeThumbViewHolder;
import com.example.smartrecycleradapter.viewholder.MyWatchListViewHolder;
import com.example.smartrecycleradapter.viewholder.PosterViewHolder;
import com.example.smartrecycleradapter.viewholder.RecentlyPlayedMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.SciFiMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.SmallThumbViewHolder;
import com.example.smartrecycleradapter.viewholder.ThumbViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.listener.NestedViewEventListener;

public class DemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SmartRecyclerAdapter mainSmartMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        recyclerView = findViewById(R.id.recycler_view);

        initSmartRecyclerAdapter();
    }

    private void initSmartRecyclerAdapter() {
        List<Object> items = new ArrayList<>();

        items.add(new MoviePosterModel(MovieDataItems.INSTANCE.getRandomPoster()));
        items.add(new ComingSoonMoviesModel("Coming soon", MovieDataItems.INSTANCE.getComingSoonItems()));
        items.add(new MyWatchListModel("My watch list", MovieDataItems.INSTANCE.getMyWatchListItems()));
        items.add(new MovieBannerModel("Recommended", MovieDataItems.INSTANCE.getRandomBanner()));
        items.add(new ActionMoviesModel("Action"));
        items.add(new AdventureMoviesModel("Adventure"));
        items.add(new MovieBannerModel("Trending", MovieDataItems.INSTANCE.getRandomBanner()));
        items.add(new AnimatedMoviesModel("Animated"));
        items.add(new SciFiMoviesModel("Sci-Fi"));
        items.add(new MovieBannerModel("Promotion", MovieDataItems.INSTANCE.getRandomBanner()));
        items.add(new RecentlyPlayedMoviesModel("Recently played"));
        items.add(new CopyrightModel("SmartRecyclerAdapter v2.0.0\n\nDeveloped by Manne Öhlund"));

        mainSmartMovieAdapter = SmartRecyclerAdapter
                .items(items)
                .map(MoviePosterModel.class, PosterViewHolder.class)
                .map(MovieBannerModel.class, BannerViewHolder.class)
                .map(ComingSoonMoviesModel.class, ComingSoonMoviesViewHolder.class)
                .map(MyWatchListModel.class, MyWatchListViewHolder.class)
                .map(ActionMoviesModel.class, ActionMoviesViewHolder.class)
                .map(AdventureMoviesModel.class, AdventureMoviesViewHolder.class)
                .map(AnimatedMoviesModel.class, AnimatedMoviesViewHolder.class)
                .map(SciFiMoviesModel.class, SciFiMoviesViewHolder.class)
                .map(RecentlyPlayedMoviesModel.class, RecentlyPlayedMoviesViewHolder.class)
                .map(CopyrightModel.class, CopyrightViewHolder.class)

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
                            mainSmartMovieAdapter.replaceItem(0, new MoviePosterModel(MovieDataItems.INSTANCE.getRandomPoster()));
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
                        MyWatchListViewHolder.class,
                        R.id.more,
                        R.id.action_on_click,
                        (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.MY_WATCH_LIST))

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
                        SmartRecyclerAdapter.empty()
                                .map(MovieModel.class, LargeThumbViewHolder.class)
                                .addViewEventListener(
                                        LargeThumbViewHolder.class,
                                        R.id.action_on_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Coming soon \n%s \n%s index: %d", getMovieTitle(smartRecyclerAdapter, position), getActionName(actionId), position);
                                            }
                                        }))

                .map(MyWatchListViewHolder.class,
                        SmartRecyclerAdapter.empty()
                                .map(MovieModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_long_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Remove " + getActionName(actionId) + " item: " + position);
                                                smartRecyclerAdapter.removeItem(position);
                                            }
                                        })
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("My watch list \n%s \n%s index: %d", getMovieTitle(smartRecyclerAdapter, position), getActionName(actionId), position);
                                            }
                                        }))

                .map(ActionMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedActionItems())
                                .map(MovieModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Action \n%s \n%s index: %d", getMovieTitle(smartRecyclerAdapter, position), getActionName(actionId), position);
                                            }
                                        }))

                .map(AdventureMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedAdventureItems())
                                .map(MovieModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Adventure \n%s \n%s index: %d", getMovieTitle(smartRecyclerAdapter, position), getActionName(actionId), position);
                                            }
                                        }))

                .map(AnimatedMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedAnimatedItems())
                                .map(MovieModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Animated \n%s \n%s index: %d", getMovieTitle(smartRecyclerAdapter, position), getActionName(actionId), position);
                                            }
                                        }))

                .map(SciFiMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedSciFiItems())
                                .map(MovieModel.class, ThumbViewHolder.class)
                                .addViewEventListener(
                                        ThumbViewHolder.class,
                                        R.id.action_on_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Sci-Fi \n%s \n%s index: %d", getMovieTitle(smartRecyclerAdapter, position), getActionName(actionId), position);
                                            }
                                        }))

                .map(RecentlyPlayedMoviesViewHolder.class,
                        SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedRecentViewedItems())
                                .map(MovieModel.class, SmallThumbViewHolder.class)
                                .addViewEventListener(
                                        SmallThumbViewHolder.class,
                                        R.id.action_on_long_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Remove " + getActionName(actionId) + " item: " + position);
                                                smartRecyclerAdapter.removeItem(position);
                                            }
                                        })
                                .addViewEventListener(
                                        SmallThumbViewHolder.class,
                                        R.id.action_on_click,
                                        new NestedViewEventListener() {
                                            @Override
                                            public void onViewEvent(SmartRecyclerAdapter smartRecyclerAdapter, View view, int actionId, int position) {
                                                showToast("Recently played \n%s \n%s index: %d", getMovieTitle(smartRecyclerAdapter, position), getActionName(actionId), position);
                                            }
                                        }))
                .into(recyclerView);
    }

    private void startMovieCategoryDetailsActivity(MovieType movieType) {
        Intent intent = new Intent(this, MovieCategoryDetailsActivity.class);
        intent.putExtra("MovieType", movieType.ordinal());
        ActivityCompat.startActivity(this, intent, null);
    }

    private String getMovieTitle(SmartRecyclerAdapter smartRecyclerAdapter, int position) {
        MovieModel movieModel = (MovieModel) smartRecyclerAdapter.getItem(position);
        return movieModel.getTitle();
    }

    public void showToast(String format, Object... args) {
        Toast.makeText(
                DemoActivity.this,
                String.format(Locale.ENGLISH, format, args),
                Toast.LENGTH_LONG).show();
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
