package com.example.smartrecycleradapter;

/*
 * Created by Manne Öhlund on 29/05/17.
 * Copyright © 2019 All rights reserved.
 */


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartrecycleradapter.viewholder.PostViewHolder;
import com.example.smartrecycleradapter.viewholder.WarningPostViewHolder;

import java.util.ArrayList;
import java.util.List;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.viewholder.SmartViewHolder;

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
        List<Post> items = new ArrayList<>();
        int c = 0;
        for (int i = 0; i < 50; i++, c++) {
            if (i % 2 == 0) {
                items.add(new Post("Hello", "World " + c));
            } else {
                items.add(new Post("Hello", "World " + c, true));
            }
        }
        for (int i = 0; i < 50; i++, c++) {
            items.add(new ErrorPost("Hello", "World " + c));
        }

        SmartRecyclerAdapter
                .items(items)
                .map(Post.class, PostViewHolder.class)
                .setViewTypeResolver((item, position) -> {
                    if (item instanceof ErrorPost) {
                        return ErrorPostViewHolder.class;
                    } else if (item instanceof Post && ((Post)item).isWarning) {
                        return WarningPostViewHolder.class;
                    }
                    return null; // Add default view if needed, else app will crash
                })

                // You need to define your own view event listeners like onClickListener
                .addViewEventListener((view, actionId, position) ->
                        showToast(getActionName(actionId) + " " + position)) // Event action

                /** Adds event listener and also automatically adds row item {@link View.OnClickListener} on all items root view */
                .addViewEventListener(
                        R.id.action_on_click,
                        (view, actionId, position) ->
                                DemoActivity.this.showToast(DemoActivity.this.getActionName(actionId) + " " + position))

                /** Adds event listener and also automatically adds row item {@link View.OnLongClickListener} on all items root view */
                .addViewEventListener(
                        R.id.action_on_long_click,
                        (view, actionId, position) ->
                                showToast(getActionName(actionId) + " " + position))

                // Adds event listener to WarningPostViewHolder only
                .addViewEventListener(
                        WarningPostViewHolder.class, // Target view holder
                        (view, actionId, position) -> showToast(getActionName(actionId) + " " + position)) // Event action

                /** Adds event listener to PostViewHolder only, with {@link View.OnClickListener} on more view */
                .addViewEventListener(
                        PostViewHolder.class, // Target view holder
                        R.id.more, // Target view
                        R.id.action_on_click, // Events for SmartRecyclerAdapter to add automatically
                        (view, actionId, position) -> showToast("More " + getActionName(actionId) + " " + position)) // Event action

                /** Adds event listener to PostViewHolder only, with {@link View.OnLongClickListener} on more view */
                .addViewEventListener(
                        PostViewHolder.class, // Target view holder
                        R.id.more, // Target view
                        R.id.action_on_long_click, // Events for SmartRecyclerAdapter to add automatically
                        (view, actionId, position) -> showToast("More " + getActionName(actionId) + " " + position)) // Event action

                /** Adds event listener to ErrorPostViewHolder only, with {@link View.OnLongClickListener} on item root view */
                .addViewEventListener(
                        ErrorPostViewHolder.class, // Target view holder
                        R.id.action_on_long_click, // Event
                        (view, actionId, position) -> showToast(getActionName(actionId) + " " + position)) // Event action

                /** Adds event listener to WarningPostViewHolder only, with {@link View.OnLongClickListener} on item root view */
                .addViewEventListener(
                        WarningPostViewHolder.class, // Target view holder
                        R.id.action_on_long_click, // Event
                        (view, actionId, position) -> showToast(getActionName(actionId) + " " + position)) // Event action

                .into(recyclerView);
    }

    public void showToast(String message) {
        Toast.makeText(DemoActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public String getActionName(int actionId) {
        switch (actionId) {
            case R.id.action_on_click: return "Action Click";
            case R.id.action_on_long_click: return "Action Long Click";
            case R.id.action_on_check_change: return "Action Check Change";
            default: return "Unknown";
        }
    }

    /**
     * Static view holders
     */

    public static class PostViewHolderStatic extends SmartViewHolder<Post> {

        public PostViewHolderStatic(ViewGroup parentView) {
            super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.simple_list_item, parentView, false));
        }

        @Override
        public void bind(Post post) {
            ((TextView)itemView).setText(post.toString());
        }
    }

    public static class WarningPostViewHolderStatic extends PostViewHolderStatic {

        public WarningPostViewHolderStatic(ViewGroup parentView) {
            super(parentView);
        }

        @Override
        public void bind(Post post) {
            super.bind(post);
            ((TextView)itemView).setTextColor(Color.YELLOW);
        }
    }

    public static class ErrorPostViewHolder extends PostViewHolderStatic {

        public ErrorPostViewHolder(ViewGroup parentView) {
            super(parentView);
        }

        @Override
        public void bind(Post post) {
            super.bind(post);
            ((TextView)itemView).setTextColor(Color.RED);
        }
    }

    /**
     * Static data types
     */

    public static class Post {
        boolean isWarning = false;
        String title, summary;

        public Post(String title, String summary) {
            this.title = title;
            this.summary = summary;
        }
        public Post(String title, String summary, boolean isWarning) {
            this(title, summary);
            this.isWarning = isWarning;
        }

        @Override
        public String toString() {
            return title + "\n" + summary;
        }
    }

    public static class ErrorPost extends Post {

        public ErrorPost(String title, String summary) {
            super(title, summary);
        }
    }
}
