package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.booklisting.BookListingActivity.BOOK_REQUEST_URL;
import static com.example.android.booklisting.BookListingActivity.LOG_TAG;

public class BooksView extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BooksList>> {

    /**
     * Constant value for the book loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;
    /**
     * Adapter for the list of books
     */
    private BookAdapter mAdapter;
    private View circularProgressBar;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_view);

        Log.e(LOG_TAG, "onCreate BooksView");
        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        emptyView = (TextView) findViewById(R.id.empty_view);

        circularProgressBar = findViewById(R.id.loading_spinner);

        bookListView.setEmptyView(emptyView);
        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<BooksList>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected book.
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the current book that was clicked on
                BooksList currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookUri = Uri.parse(currentBook.getURL());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
            Log.e("OnCreate", "After calling initLoader");
        } else {
            circularProgressBar.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.e(LOG_TAG, "onCreateLoader");
        // Create a new loader for the given URL
        return new BookLoader(this, BOOK_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<BooksList>> loader, List<BooksList> books) {
        Log.e(LOG_TAG, "onLoadFinished");
        circularProgressBar.setVisibility(View.GONE);
        // Set empty state text to display "No books found."
        emptyView.setText(R.string.no_books);
        // Clear the adapter of previous book data
        mAdapter.clear();
        // If there is a valid list of {@link BookList}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BooksList>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
