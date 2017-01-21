package com.antariksh.wallypaper;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.antariksh.wallypaper.adapter.ImageGridRecyclerAdapter;
import com.antariksh.wallypaper.data.WallypaperProvider;
import com.antariksh.wallypaper.sync.WallypaperSyncAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    public static final String[] PROJECTION = new String[]{
            WallypaperProvider.PatternColumns._ID,
            WallypaperProvider.PatternColumns.PATTERN_ID,
            WallypaperProvider.PatternColumns.TITLE,
            WallypaperProvider.PatternColumns.IMAGE_URL,
            WallypaperProvider.PatternColumns.USER_NAME,
            WallypaperProvider.PatternColumns.LIKES,
            WallypaperProvider.PatternColumns.VIEWS,
            WallypaperProvider.PatternColumns.API_URL
    };
    private static final int LOADER_PATTERNS = 20;

    @Bind(R.id.grid_recycler_view)
    RecyclerView mRecyclerView;

    private ImageGridRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View recyclerView = findViewById(R.id.grid_recycler_view);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        WallypaperSyncAdapter.syncImmediately(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        if (mAdView != null) {
            mAdView.loadAd(adRequest);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(LOADER_PATTERNS, null, this);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                }
                return 1;
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        if (mAdapter != null && mAdapter.getCursor() != null) {
            mAdapter.swapCursor(cursor);
        } else {
            mAdapter = new ImageGridRecyclerAdapter(this, cursor, mTwoPane);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, WallypaperProvider.Patterns.CONTENT_URI, PROJECTION, null, null, null);
    }
}
