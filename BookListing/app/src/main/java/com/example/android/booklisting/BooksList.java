package com.example.android.booklisting;

import android.graphics.Bitmap;

/**
 * Created by hossam on 6/8/2017.
 * BooksList class to represent books data
 */

public class BooksList {
    private static final int NO_RATING = -1;
    private String mTitle;
    private String mAuthors;
    private Bitmap mImageBitmap;
    private float mRating = NO_RATING;
    private String mURL;

    public BooksList(String title, String authors, Bitmap imageBitmap, float rating, String url) {
        mTitle = title;
        mAuthors = authors;
        mImageBitmap = imageBitmap;
        mRating = rating;
        mURL = url;
    }

    public BooksList(String title, String authors, Bitmap imageBitmap, String url) {
        mTitle = title;
        mAuthors = authors;
        mImageBitmap = imageBitmap;
        mURL = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthors() {
        return mAuthors;
    }

    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

    public float getRating() {
        return mRating;
    }

    public boolean hasRating() {
        return mRating != NO_RATING;
    }

    public String getURL() {
        return mURL;
    }
}
