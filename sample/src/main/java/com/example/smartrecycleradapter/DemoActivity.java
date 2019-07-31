package com.example.smartrecycleradapter;

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import smartadapter.SmartEndlessScrollRecyclerAdapter;
import smartadapter.SmartRecyclerAdapter;

public class DemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SmartEndlessScrollRecyclerAdapter mainSmartMovieAdapter;
    SmartEndlessScrollRecyclerAdapter comingSoonSmartMovieAdapter;
    SmartRecyclerAdapter myWatchListSmartMovieAdapter;
    SmartRecyclerAdapter actionMoviesSmartMovieAdapter;
    SmartRecyclerAdapter adventuresMoviesSmartMovieAdapter;
    SmartRecyclerAdapter animatedMoviesSmartMovieAdapter;
    SmartRecyclerAdapter sciFiMoviesSmartMovieAdapter;
    SmartRecyclerAdapter recentlyPlayedMoviesSmartMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        recyclerView = findViewById(R.id.recycler_view);

        initNestedSmartRecyclerAdapters();
        initSmartRecyclerAdapter();
    }

    private void initSmartRecyclerAdapter() {
        List<Object> items = new ArrayList<>();

        items.add(new MoviePosterModel(MovieDataItems.INSTANCE.getRandomPoster()));
        items.add(new ComingSoonMoviesModel("Coming soon"));
        items.add(new MyWatchListModel("My watch list"));
        items.add(new MovieBannerModel("Recommended", MovieDataItems.INSTANCE.getRandomBanner()));
        items.add(new ActionMoviesModel("Action"));
        items.add(new AdventureMoviesModel("Adventure"));
        items.add(new MovieBannerModel("Trending", MovieDataItems.INSTANCE.getRandomBanner()));
        items.add(new AnimatedMoviesModel("Animated"));
        items.add(new SciFiMoviesModel("Sci-Fi"));
        items.add(new MovieBannerModel("Promotion", MovieDataItems.INSTANCE.getRandomBanner()));
        items.add(new RecentlyPlayedMoviesModel("Recently played"));
        items.add(new CopyrightModel(String.format("SmartRecyclerAdapter v%s\n\nDeveloped by Manne Öhlund", BuildConfig.VERSION_NAME)));

        mainSmartMovieAdapter = SmartEndlessScrollRecyclerAdapter
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

                // You need to define your own view event listeners like onClickListener on a view
                .addViewEventListener((view, actionId, position) ->
                        showToast(getActionName(actionId) + " " + position))

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
                .map(ComingSoonMoviesViewHolder.class, comingSoonSmartMovieAdapter)
                .map(MyWatchListViewHolder.class, myWatchListSmartMovieAdapter)
                .map(ActionMoviesViewHolder.class, actionMoviesSmartMovieAdapter)
                .map(AdventureMoviesViewHolder.class, adventuresMoviesSmartMovieAdapter)
                .map(AnimatedMoviesViewHolder.class, animatedMoviesSmartMovieAdapter)
                .map(SciFiMoviesViewHolder.class, sciFiMoviesSmartMovieAdapter)
                .map(RecentlyPlayedMoviesViewHolder.class, recentlyPlayedMoviesSmartMovieAdapter)

                .into(recyclerView);

        // Endless pagination
        mainSmartMovieAdapter.setOnLoadMoreListener(() -> {
            Toast.makeText(getApplicationContext(), "LoadMore", Toast.LENGTH_SHORT).show();

            int indexBeforeCopyright = 2;
            new Handler().postDelayed(() -> {
                        mainSmartMovieAdapter.addItem(
                                mainSmartMovieAdapter.getItemCount() - indexBeforeCopyright,
                                new MovieBannerModel("More items loaded", MovieDataItems.INSTANCE.getRandomBanner())
                        );
                    },
                    800);
        });
    }

    int moreItemsLoadedCount = 0;
    private void initNestedSmartRecyclerAdapters() {
        comingSoonSmartMovieAdapter = SmartEndlessScrollRecyclerAdapter.items(MovieDataItems.INSTANCE.getComingSoonItems())
                .map(MovieModel.class, LargeThumbViewHolder.class)
                .addViewEventListener(
                        LargeThumbViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) ->
                            showToast("Coming soon \n%s \n%s index: %d", getMovieTitle(comingSoonSmartMovieAdapter, position), getActionName(actionId), position))
                .addViewEventListener(
                        LargeThumbViewHolder.class,
                        R.id.action_on_long_click,
                        (view, actionId, position) -> {
                            showToast("Add to My watch list");
                            myWatchListSmartMovieAdapter.addItem(1, comingSoonSmartMovieAdapter.getItem(position));
                        })
                .create();

        //
        comingSoonSmartMovieAdapter.setCustomLoadMoreLayoutResource(R.layout.custom_loadmore_view);

        // Pagination ends after 3 loads
        comingSoonSmartMovieAdapter.setOnLoadMoreListener(() -> {
            Toast.makeText(getApplicationContext(), "LoadMore", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(() -> {
                        comingSoonSmartMovieAdapter.addItems(
                                comingSoonSmartMovieAdapter.getItemCount()-1,
                                MovieDataItems.INSTANCE.getLoadMoreItems()
                        );
                        if (moreItemsLoadedCount++ == 2)
                            comingSoonSmartMovieAdapter.setEndlessScrollEnabled(false);
                    },
                    1000);
        });

        myWatchListSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getMyWatchListItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        ThumbViewHolder.class,
                        R.id.action_on_long_click,
                        (view, actionId, position) -> {
                            showToast("Remove " + getActionName(actionId) + " item: " + position);
                            myWatchListSmartMovieAdapter.removeItem(position);
                        })
                .addViewEventListener(
                        ThumbViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) ->
                            showToast("My watch list \n%s \n%s index: %d", getMovieTitle(myWatchListSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        actionMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedActionItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        ThumbViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) ->
                            showToast("Action \n%s \n%s index: %d", getMovieTitle(actionMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        adventuresMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedAdventureItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        ThumbViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) ->
                            showToast("Adventure \n%s \n%s index: %d", getMovieTitle(adventuresMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        animatedMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedAnimatedItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        ThumbViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) ->
                            showToast("Animated \n%s \n%s index: %d", getMovieTitle(animatedMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        sciFiMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedSciFiItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        ThumbViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) ->
                            showToast("Sci-Fi \n%s \n%s index: %d", getMovieTitle(sciFiMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        recentlyPlayedMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedRecentViewedItems())
                .map(MovieModel.class, SmallThumbViewHolder.class)
                .addViewEventListener(
                        SmallThumbViewHolder.class,
                        R.id.action_on_long_click,
                        (view, actionId, position) -> {
                            showToast("Remove " + getActionName(actionId) + " item: " + position);
                            recentlyPlayedMoviesSmartMovieAdapter.removeItem(position);
                        })
                .addViewEventListener(
                        SmallThumbViewHolder.class,
                        R.id.action_on_click,
                        (view, actionId, position) ->
                            showToast("Recently played \n%s \n%s index: %d", getMovieTitle(recentlyPlayedMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();
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
