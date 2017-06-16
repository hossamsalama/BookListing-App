package com.example.android.booklisting;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by hossam on 6/9/2017.
 * Custom BookAdapter class to show three TextView
 */

public class BookAdapter extends ArrayAdapter<BooksList> {


    public BookAdapter(Activity context, ArrayList<BooksList> books) {
        super(context, 0, books);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_book, parent, false);
        }

        // Find the book at the given position in the list of books
        BooksList currentBook = getItem(position);

        // Find the TextView with view ID title
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        TextView authors = (TextView) listItemView.findViewById(R.id.authors);
        ImageView bookImage = (ImageView) listItemView.findViewById(R.id.book_image);
        RatingBar ratingBar = (RatingBar) listItemView.findViewById(R.id.rating_bar);

        title.setText(currentBook.getTitle());
        authors.setText(currentBook.getAuthors());

        bookImage.setImageBitmap(currentBook.getImageBitmap());

        if (currentBook.hasRating()) {
            ratingBar.setRating(currentBook.getRating());
            ratingBar.setVisibility(View.VISIBLE);
        } else {
            ratingBar.setVisibility(View.GONE);
        }

        // Return the completed view to render on screen
        return listItemView;
    }

}
