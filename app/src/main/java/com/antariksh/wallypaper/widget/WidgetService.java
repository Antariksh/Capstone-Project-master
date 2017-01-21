package com.antariksh.wallypaper.widget;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.antariksh.wallypaper.R;
import com.antariksh.wallypaper.data.WallypaperProvider;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by Ahmed Talaat on 18-Apr-16.
 */
public class WidgetService extends RemoteViewsService {

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

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WallyPaperViewFactory();
    }

    private class WallyPaperViewFactory implements RemoteViewsService.RemoteViewsFactory {
        private Cursor data = null;

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            if (data != null) {
                data.close();
            }

            // This method is called by the app hosting the widget (e.g., the launcher)
            // However, our ContentProvider is not exported so it doesn't have access to the
            // data. Therefore we need to clear (and finally restore) the calling identity so
            // that calls use our process and permission
            final long identityToken = Binder.clearCallingIdentity();

            data = getContentResolver().query(WallypaperProvider.Patterns.CONTENT_URI, PROJECTION, null, null, null);
            Binder.restoreCallingIdentity(identityToken);
        }

        @Override
        public void onDestroy() {
            if (data != null) {
                data.close();
                data = null;
            }
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.getCount();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (position == AdapterView.INVALID_POSITION ||
                    data == null || !data.moveToPosition(position)) {
                return null;
            }
            RemoteViews view = new RemoteViews(getPackageName(),
                    R.layout.widget_item_view);

            String imageUrl = data.getString(data.getColumnIndex(WallypaperProvider.PatternColumns.IMAGE_URL));

            try {
                Bitmap bitmap = Picasso.with(getBaseContext()).load(imageUrl).get();
                view.setImageViewBitmap(R.id.imageView, bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return view;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            if (data.moveToPosition(position))
                return data.getInt(data.getColumnIndex(WallypaperProvider.PatternColumns._ID));
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
