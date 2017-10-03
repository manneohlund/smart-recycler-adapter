package com.example.smartrecycleradapter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import smartadapter.SmartRecyclerAdapter;
import smartadapter.listener.ViewEventListener;
import smartadapter.viewholder.SmartViewHolder;
import smartadapter.widget.ViewTypeResolver;

public class DemoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        initSmartRecyclerAdapter();
    }

    private void initSmartRecyclerAdapter() {
        List<Mail> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                items.add(new Mail("Hello", "World " + i));
            } else {
                items.add(new Mail("Hello", "World " + i, true));
            }
        }
        for (int i = 0; i < 100; i++) {
            items.add(new ErrorMail("Hello", "World " + i));
        }
        SmartRecyclerAdapter
                .init(this)
                .items(items)
                //.map(Mail.class, MailViewHolder.class)
                //.map(ErrorMail.class, ErrorMailViewHolder.class)
                .setViewTypeResolver(new ViewTypeResolver() {
                    @Override
                    public Class<? extends SmartViewHolder> getViewType(Object item, int position) {
                        if (item instanceof ErrorMail) {
                            return ErrorMailViewHolder.class;
                        } else if (item instanceof Mail && ((Mail)item).isWarning) {
                            return WarningMailViewHolder.class;
                        }
                        return MailViewHolder.class;
                    }
                })
                .setViewEventListener(new ViewEventListener() {
                    @Override
                    public void onViewEvent(View view, int actionId, int position) {
                        Toast.makeText(DemoActivity.this, "Action: " + actionId, Toast.LENGTH_SHORT).show();
                    }
                })
                .into(recyclerView);
    }

    /**
     * View holders
     */

    public static class MailViewHolder extends SmartViewHolder<Mail> {

        public MailViewHolder(ViewGroup parentView) {
            super(LayoutInflater.from(parentView.getContext()).inflate(android.R.layout.simple_list_item_1, parentView, false));
        }

        @Override
        public void bind(Mail mail) {
            ((TextView)itemView).setText(mail.toString());
        }
    }

    public static class WarningMailViewHolder extends MailViewHolder {

        public WarningMailViewHolder(ViewGroup parentView) {
            super(parentView);
        }

        @Override
        public void bind(Mail mail) {
            super.bind(mail);
            ((TextView)itemView).setTextColor(Color.YELLOW);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyOnItemEvent(view, 1);
                }
            });
        }
    }

    public static class ErrorMailViewHolder extends MailViewHolder {

        public ErrorMailViewHolder(ViewGroup parentView) {
            super(parentView);
        }

        @Override
        public void bind(Mail mail) {
            super.bind(mail);
            ((TextView)itemView).setTextColor(Color.RED);
        }
    }

    /**
     * Data types
     */

    public static class Mail {
        boolean isWarning = false;
        String title, summary;

        public Mail(String title, String summary) {
            this.title = title;
            this.summary = summary;
        }
        public Mail(String title, String summary, boolean isWarning) {
            this(title, summary);
            this.isWarning = isWarning;
        }

        @Override
        public String toString() {
            return title + "\n" + summary;
        }
    }

    public static class ErrorMail extends Mail {

        public ErrorMail(String title, String summary) {
            super(title, summary);
        }
    }
}
