package com.example.android.booklisting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class BookListingActivity extends AppCompatActivity {
    public static final String LOG_TAG = BookListingActivity.class.getName();
    /**
     * URL to query the Book Api for book information
     */
    public static String BOOK_REQUEST_URL = "";
    public Button searchButton;
    public EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        searchButton = (Button) findViewById(R.id.search);
        searchText = (EditText) findViewById(R.id.search_Text_view);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
                Intent searchIntent = new Intent(BookListingActivity.this, BooksView.class);
                startActivity(searchIntent);
                String searchWord = searchText.getText().toString();
                BOOK_REQUEST_URL += searchWord + "&maxResults=10";
            }
        });
    }
}
