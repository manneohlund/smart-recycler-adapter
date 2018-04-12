package com.example.smartrecycleradapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                items.add(new Post("Hello", "World " + i));
            } else {
                items.add(new Post("Hello", "World " + i, true));
            }
        }
        for (int i = 0; i < 100; i++) {
            items.add(new ErrorPost("Hello", "World " + i));
        }
        SmartRecyclerAdapter
                .init(this)
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
                .addViewEventListener(PostViewHolder.class, (view, actionId, position) ->
                        Toast.makeText(DemoActivity.this, getActionName(actionId) + " @ index " + position, Toast.LENGTH_SHORT).show())
                .addViewEventListener(PostViewHolder.class, R.id.more, (view, actionId, position) ->
                        Toast.makeText(DemoActivity.this, "More: " + getActionName(actionId) + " @ index " + position, Toast.LENGTH_SHORT).show())
                .addViewEventListener(ErrorPostViewHolder.class, (view, actionId, position) ->
                        Toast.makeText(DemoActivity.this,  "Error: " + getActionName(actionId) + " @ index " + position, Toast.LENGTH_SHORT).show())
                .addViewEventListener(WarningPostViewHolder.class, (view, actionId, position) ->
                        Toast.makeText(DemoActivity.this, "Warning: " + getActionName(actionId) + " @ index " + position, Toast.LENGTH_SHORT).show())
                .into(recyclerView);
    }

    public String getActionName(int actionId) {
        switch (actionId) {
            case R.id.action_on_click: return "Action Click";
            case R.id.action_on_long_click: return "Action Long Click";
            default: return "Unknown";
        }
    }

    /**
     * View holders
     */

    public static class PostViewHolderStatic extends SmartViewHolder<Post> {

        public PostViewHolderStatic(ViewGroup parentView) {
            super(LayoutInflater.from(parentView.getContext()).inflate(R.layout.simple_list_item, parentView, false));
        }

        @Override
        public void bind(Post mail) {
            ((TextView)itemView).setText(mail.toString());
        }
    }

    public static class WarningMailViewHolderStatic extends PostViewHolderStatic {

        public WarningMailViewHolderStatic(ViewGroup parentView) {
            super(parentView);
        }

        @Override
        public void bind(Post mail) {
            super.bind(mail);
            ((TextView)itemView).setTextColor(Color.YELLOW);
        }
    }

    public static class ErrorPostViewHolder extends PostViewHolderStatic {

        public ErrorPostViewHolder(ViewGroup parentView) {
            super(parentView);
        }

        @Override
        public void bind(Post mail) {
            super.bind(mail);
            ((TextView)itemView).setTextColor(Color.RED);
        }
    }

    /**
     * Data types
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
