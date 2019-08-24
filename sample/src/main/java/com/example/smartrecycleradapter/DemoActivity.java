package com.example.smartrecycleradapter;

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartrecycleradapter.data.MovieDataItems;
import com.example.smartrecycleradapter.extension.PreCachingLinearLayoutManager;
import com.example.smartrecycleradapter.feature.CustomViewEventActivity;
import com.example.smartrecycleradapter.feature.DiffUtilActivity;
import com.example.smartrecycleradapter.feature.DragAndDropHandleItemActivity;
import com.example.smartrecycleradapter.feature.DragAndDropItemActivity;
import com.example.smartrecycleradapter.feature.EndlessScrollActivity;
import com.example.smartrecycleradapter.feature.EndlessScrollLoadMoreButtonActivity;
import com.example.smartrecycleradapter.feature.GridActivity;
import com.example.smartrecycleradapter.feature.MultiSelectCheckBoxItemsActivity;
import com.example.smartrecycleradapter.feature.MultiSelectItemsActivity;
import com.example.smartrecycleradapter.feature.MultiSelectSwitchItemsActivity;
import com.example.smartrecycleradapter.feature.MultipleEventsAndExtensionsActivity;
import com.example.smartrecycleradapter.feature.MultipleExpandableItemActivity;
import com.example.smartrecycleradapter.feature.MultipleViewTypesResolverActivity;
import com.example.smartrecycleradapter.feature.NestedSmartRecyclerAdaptersActivity;
import com.example.smartrecycleradapter.feature.SimpleItemActivity;
import com.example.smartrecycleradapter.feature.SimpleItemOnClickOnLongClickActivity;
import com.example.smartrecycleradapter.feature.SingleExpandableItemActivity;
import com.example.smartrecycleradapter.feature.SingleSelectRadioButtonItemActivity;
import com.example.smartrecycleradapter.feature.SwipeRemoveItemActivity;
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
import com.example.smartrecycleradapter.viewholder.HeaderViewHolder;
import com.example.smartrecycleradapter.viewholder.LargeThumbViewHolder;
import com.example.smartrecycleradapter.viewholder.MyWatchListViewHolder;
import com.example.smartrecycleradapter.viewholder.PosterViewHolder;
import com.example.smartrecycleradapter.viewholder.RecentlyPlayedMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.SampleFabViewHolder;
import com.example.smartrecycleradapter.viewholder.SciFiMoviesViewHolder;
import com.example.smartrecycleradapter.viewholder.SmallThumbViewHolder;
import com.example.smartrecycleradapter.viewholder.ThumbViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import smartadapter.SmartEndlessScrollRecyclerAdapter;
import smartadapter.SmartRecyclerAdapter;
import smartadapter.listener.OnItemClickListener;
import smartadapter.listener.OnItemLongClickListener;
import smartadapter.viewholder.SmartViewHolder;

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
    SmartRecyclerAdapter dialogAdapter;

    AlertDialog moreSamplesDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        recyclerView = findViewById(R.id.recycler_view);

        initNestedSmartRecyclerAdapters();
        initSmartRecyclerAdapter();
        initMoreDemosButton();
    }

    private void initSmartRecyclerAdapter() {
        List<Object> items = new ArrayList<>();

        items.add(new MoviePosterModel(MovieDataItems.INSTANCE.getRandomPoster()));
        items.add(new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_widgets_black_24dp, "More samples"));
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
                .map(SampleFabViewHolder.SimpleFabItem.class, SampleFabViewHolder.class)
                .map(MovieBannerModel.class, BannerViewHolder.class)
                .map(ComingSoonMoviesModel.class, ComingSoonMoviesViewHolder.class)
                .map(MyWatchListModel.class, MyWatchListViewHolder.class)
                .map(ActionMoviesModel.class, ActionMoviesViewHolder.class)
                .map(AdventureMoviesModel.class, AdventureMoviesViewHolder.class)
                .map(AnimatedMoviesModel.class, AnimatedMoviesViewHolder.class)
                .map(SciFiMoviesModel.class, SciFiMoviesViewHolder.class)
                .map(RecentlyPlayedMoviesModel.class, RecentlyPlayedMoviesViewHolder.class)
                .map(CopyrightModel.class, CopyrightViewHolder.class)

                // Map nested SmartRecyclerAdapter
                .map(ComingSoonMoviesViewHolder.class, comingSoonSmartMovieAdapter)
                .map(MyWatchListViewHolder.class, myWatchListSmartMovieAdapter)
                .map(ActionMoviesViewHolder.class, actionMoviesSmartMovieAdapter)
                .map(AdventureMoviesViewHolder.class, adventuresMoviesSmartMovieAdapter)
                .map(AnimatedMoviesViewHolder.class, animatedMoviesSmartMovieAdapter)
                .map(SciFiMoviesViewHolder.class, sciFiMoviesSmartMovieAdapter)
                .map(RecentlyPlayedMoviesViewHolder.class, recentlyPlayedMoviesSmartMovieAdapter)

                .setLayoutManager(PreCachingLinearLayoutManager.getInstance(this))

                // You need to define your own view event listeners like onClickListener on a view
                .addViewEventListener((view, actionId, position) ->
                        showToast(getActionName(actionId) + " " + position))

                /** Adds event listener and also automatically adds row item {@link View.OnClickListener} on all items root view */
                .addViewEventListener(
                        (OnItemClickListener) (view, actionId, position) ->
                                showToast(getActionName(actionId) + " " + position))

                /** Adds event listener and also automatically adds row item {@link View.OnLongClickListener} on all items root view */
                .addViewEventListener(
                        (OnItemLongClickListener) (view, actionId, position) ->
                                showToast(getActionName(actionId) + " " + position))

                .addViewEventListener(
                        (PosterViewHolder.OnItemClickListener) (view, actionId, position) -> {
                            mainSmartMovieAdapter.replaceItem(0, new MoviePosterModel(MovieDataItems.INSTANCE.getRandomPoster()));
                        })

                //.addViewEventListener((PosterViewHolder.OnPlayButtonClickListener)(view, actionId, position) -> showToast("PLAY"))

                .addViewEventListener(
                        (PosterViewHolder.OnStarButtonClickListener) (view, actionId, position) -> showToast("ADD to favorites"))

                .addViewEventListener(
                        (PosterViewHolder.OnInfoButtonClickListener) (view, actionId, position) -> showToast("INFO"))

                .addViewEventListener(
                        (ComingSoonMoviesViewHolder.OnMoreButtonClickListener) (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.COMING_SOON))

                .addViewEventListener(
                        (MyWatchListViewHolder.OnMoreButtonClickListener) (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.MY_WATCH_LIST))

                .addViewEventListener(
                        (ActionMoviesViewHolder.OnMoreButtonClickListener) (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.ACTION))

                .addViewEventListener(
                        (AdventureMoviesViewHolder.OnMoreButtonClickListener) (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.ADVENTURE))

                .addViewEventListener(
                        (AnimatedMoviesViewHolder.OnMoreButtonClickListener) (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.ANIMATED))

                .addViewEventListener(
                        (SciFiMoviesViewHolder.OnMoreButtonClickListener) (view, actionId, position) -> startMovieCategoryDetailsActivity(MovieType.SCI_FI))

                .addViewEventListener((FabOnClickListener) (view, actionId, position) ->
                        moreSamplesDialog.show())
                .into(recyclerView);

        // Endless pagination
        mainSmartMovieAdapter.setAutoLoadMore(false);
        mainSmartMovieAdapter.setOnLoadMoreListener((loadMoreViewHolder) -> {
            int indexBeforeCopyright = 2;
            new Handler().postDelayed(() -> {
                        mainSmartMovieAdapter.addItem(
                                mainSmartMovieAdapter.getItemCount() - indexBeforeCopyright,
                                new MovieBannerModel("More items loaded", MovieDataItems.INSTANCE.getRandomBanner()));
                        loadMoreViewHolder.toggleLoading(false);
                    },
                    800);
        });
    }

    int moreItemsLoadedCount = 0;

    private void initNestedSmartRecyclerAdapters() {
        comingSoonSmartMovieAdapter = SmartEndlessScrollRecyclerAdapter.items(MovieDataItems.INSTANCE.getComingSoonItems())
                .map(MovieModel.class, LargeThumbViewHolder.class)
                .addViewEventListener(
                        (LargeThumbViewHolder.OnItemClickListener) (view, actionId, position) ->
                                showToast("Coming soon \n%s \n%s index: %d", getMovieTitle(comingSoonSmartMovieAdapter, position), getActionName(actionId), position))
                .addViewEventListener(
                        (LargeThumbViewHolder.OnItemLongClickListener) (view, actionId, position) -> {
                            showToast(String.format("Add \n%s \nto My watch list", getMovieTitle(comingSoonSmartMovieAdapter, position)));
                            myWatchListSmartMovieAdapter.addItem(1, comingSoonSmartMovieAdapter.getItem(position));
                        })
                .create();

        // Set custom load more view
        comingSoonSmartMovieAdapter.setCustomLoadMoreLayoutResource(R.layout.custom_loadmore_view);

        // Pagination ends after 3 loads
        comingSoonSmartMovieAdapter.setOnLoadMoreListener((loadMoreViewHolder) -> {
            new Handler().postDelayed(() -> {
                        comingSoonSmartMovieAdapter.addItems(
                                comingSoonSmartMovieAdapter.getItemCount() - 1,
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
                        (ThumbViewHolder.OnItemLongClickListener) (view, actionId, position) -> {
                            showToast("Remove " + getActionName(actionId) + " item: " + position);
                            myWatchListSmartMovieAdapter.removeItem(position);
                        })
                .addViewEventListener(
                        (ThumbViewHolder.OnItemClickListener) (view, actionId, position) ->
                                showToast("My watch list \n%s \n%s index: %d", getMovieTitle(myWatchListSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        actionMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedActionItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        (ThumbViewHolder.OnItemClickListener) (view, actionId, position) ->
                                showToast("Action \n%s \n%s index: %d", getMovieTitle(actionMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        adventuresMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedAdventureItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        (ThumbViewHolder.OnItemClickListener) (view, actionId, position) ->
                                showToast("Adventure \n%s \n%s index: %d", getMovieTitle(adventuresMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        animatedMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedAnimatedItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        (ThumbViewHolder.OnItemClickListener) (view, actionId, position) ->
                                showToast("Animated \n%s \n%s index: %d", getMovieTitle(animatedMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        sciFiMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedSciFiItems())
                .map(MovieModel.class, ThumbViewHolder.class)
                .addViewEventListener(
                        (ThumbViewHolder.OnItemClickListener) (view, actionId, position) ->
                                showToast("Sci-Fi \n%s \n%s index: %d", getMovieTitle(sciFiMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();

        recentlyPlayedMoviesSmartMovieAdapter = SmartRecyclerAdapter.items(MovieDataItems.INSTANCE.getNestedRecentViewedItems())
                .map(MovieModel.class, SmallThumbViewHolder.class)
                .addViewEventListener(
                        (SmallThumbViewHolder.OnItemLongClickListener) (view, actionId, position) -> {
                            showToast("Remove " + getActionName(actionId) + " item: " + position);
                            recentlyPlayedMoviesSmartMovieAdapter.removeItem(position);
                        })
                .addViewEventListener(
                        (SmallThumbViewHolder.OnItemClickListener) (view, actionId, position) ->
                                showToast("Recently played \n%s \n%s index: %d", getMovieTitle(recentlyPlayedMoviesSmartMovieAdapter, position), getActionName(actionId), position))
                .create();
    }

    interface FabOnClickListener extends OnItemClickListener {
        @NonNull
        @Override
        default Class<? extends SmartViewHolder> getViewHolderType() {
            return SampleFabViewHolder.class;
        }

        @Override
        default int getViewId() {
            return R.id.fabItem;
        }
    }

    private void initMoreDemosButton() {
        RecyclerView dialogRecyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.dialog_recycler_view, null);
        moreSamplesDialog = new AlertDialog.Builder(DemoActivity.this)
                .setView(dialogRecyclerView)
                .setCancelable(true)
                .create();

        if (moreSamplesDialog.getWindow() != null) {
            moreSamplesDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        List items = Arrays.asList(
                "More Samples",
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_list_black_24dp, "Simple Item"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_touch_app_black_24dp, "Smart onClick/onLongClick"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_edit_black_24dp, "Custom View Event"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_drag_drop_swap_vert_black_24dp, "Drag & drop"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_drag_handle_black_24dp, "Drag & drop handle"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_swipe_black_24dp, "Swipe remove item"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_multiple_events_gesture_black_24dp, "Drag & Drop + Swipe"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_grid_black_24dp, "Grid + Drag & Drop"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_list_numbered_black_24dp, "Multiple Types Resolver"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_select_all_black_24dp, "Multiple Items Select"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_radio_button_checked_black_24dp, "Single RadioButton Select"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_check_box_black_24dp, "Multiple CheckBox Select"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_switch_black_24dp, "Multiple Switch Select"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_expand_more_black_24dp, "Multiple Expandable item"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_expand_less_black_24dp, "Single Expandable item"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_nested_scroll_layers_black_24dp, "Nested Smart Adapter"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_endless_scroll_arrow_downward_black_24dp, "Endless Scroll"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_endless_scroll_load_more_black_24dp, "Endless Scroll Load More"),
                new SampleFabViewHolder.SimpleFabItem(R.drawable.ic_sample_diff_shuffle_black_24dp, "Diff Util Extension")
        );

        dialogAdapter = SmartRecyclerAdapter.items(items)
                .map(String.class, HeaderViewHolder.class)
                .map(SampleFabViewHolder.SimpleFabItem.class, SampleFabViewHolder.class)
                .addViewEventListener((FabOnClickListener) (view, actionId, position) -> {
                    SampleFabViewHolder.SimpleFabItem sfi = (SampleFabViewHolder.SimpleFabItem) dialogAdapter.getItem(position);
                    switch (sfi.getIcon()) {
                        case R.drawable.ic_sample_list_black_24dp:
                            startActivity(SimpleItemActivity.class);
                            break;
                        case R.drawable.ic_sample_touch_app_black_24dp:
                            startActivity(SimpleItemOnClickOnLongClickActivity.class);
                            break;
                        case R.drawable.ic_sample_edit_black_24dp:
                            startActivity(CustomViewEventActivity.class);
                            break;
                        case R.drawable.ic_sample_list_numbered_black_24dp:
                            startActivity(MultipleViewTypesResolverActivity.class);
                            break;
                        case R.drawable.ic_sample_drag_drop_swap_vert_black_24dp:
                            startActivity(DragAndDropItemActivity.class);
                            break;
                        case R.drawable.ic_drag_handle_black_24dp:
                            startActivity(DragAndDropHandleItemActivity.class);
                            break;
                        case R.drawable.ic_sample_swipe_black_24dp:
                            startActivity(SwipeRemoveItemActivity.class);
                            break;
                        case R.drawable.ic_sample_multiple_events_gesture_black_24dp:
                            startActivity(MultipleEventsAndExtensionsActivity.class);
                            break;
                        case R.drawable.ic_sample_grid_black_24dp:
                            startActivity(GridActivity.class);
                            break;
                        case R.drawable.ic_sample_select_all_black_24dp:
                            startActivity(MultiSelectItemsActivity.class);
                            break;
                        case R.drawable.ic_radio_button_checked_black_24dp:
                            startActivity(SingleSelectRadioButtonItemActivity.class);
                            break;
                        case R.drawable.ic_sample_check_box_black_24dp:
                            startActivity(MultiSelectCheckBoxItemsActivity.class);
                            break;
                        case R.drawable.ic_switch_black_24dp:
                            startActivity(MultiSelectSwitchItemsActivity.class);
                            break;
                        case R.drawable.ic_expand_more_black_24dp:
                            startActivity(MultipleExpandableItemActivity.class);
                            break;
                        case R.drawable.ic_expand_less_black_24dp:
                            startActivity(SingleExpandableItemActivity.class);
                            break;
                        case R.drawable.ic_sample_nested_scroll_layers_black_24dp:
                            startActivity(NestedSmartRecyclerAdaptersActivity.class);
                            break;
                        case R.drawable.ic_sample_endless_scroll_arrow_downward_black_24dp:
                            startActivity(EndlessScrollActivity.class);
                            break;
                        case R.drawable.ic_endless_scroll_load_more_black_24dp:
                            startActivity(EndlessScrollLoadMoreButtonActivity.class);
                            break;
                        case R.drawable.ic_sample_diff_shuffle_black_24dp:
                            startActivity(DiffUtilActivity.class);
                            break;
                    }
                    //moreSamplesDialog.dismiss();
                })
                .into(dialogRecyclerView);
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(DemoActivity.this, clazz);
        ActivityCompat.startActivity(this, intent, null);
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

    public static String getActionName(int actionId) {
        switch (actionId) {
            case R.id.event_on_click:
                return "onClick";
            case R.id.event_on_long_click:
                return "onLongClick";
            case R.id.event_play:
                return "PLAY";
            default:
                return "Unknown";
        }
    }
}
