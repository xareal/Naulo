package com.xar.naulo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.xar.naulo.models.Post;

import java.util.ArrayList;

import static java.util.ResourceBundle.getBundle;

public class PostAdapter extends ArrayAdapter<Post> {

    private int mColorResourceId;

    public PostAdapter (Context context, ArrayList<Post> posts, int colorResourceId) {
        super(context, 0, posts);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the (@link Post) object located at this position in the list
        Post currentPost = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID title_text_view
//        Bundle bundle = getIntent().getExtras();
//        String title = bundle.getString("key_1");
//        String url = bundle.getString("key_2");

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_text_view);
        TextView urlTextView = (TextView) listItemView.findViewById(R.id.url_text_view);

        titleTextView.setText(currentPost.getTitleId());
        urlTextView.setText(currentPost.getUrlId());

//        titleTextView.setText(title);
//        urlTextView.setText(url);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentPost.hasImage()) {
            imageView.setImageResource(currentPost.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}

