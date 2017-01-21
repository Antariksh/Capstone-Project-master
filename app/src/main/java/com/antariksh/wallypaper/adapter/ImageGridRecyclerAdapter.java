package com.antariksh.wallypaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antariksh.wallypaper.ItemDetailActivity;
import com.antariksh.wallypaper.ItemDetailFragment;
import com.antariksh.wallypaper.R;
import com.antariksh.wallypaper.api.PatternModel;
import com.antariksh.wallypaper.data.WallypaperProvider;
import com.antariksh.wallypaper.view.MyImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ahmed Talaat on 09-Apr-16.
 */
public class ImageGridRecyclerAdapter extends CursorRecyclerAdapter<RecyclerView.ViewHolder> {
    Context context;
    private boolean mTwoPane;

    public ImageGridRecyclerAdapter(Context context, Cursor cursor, boolean twoPaneView) {
        super(cursor);
        this.context = context;
        this.mTwoPane = twoPaneView;
    }

    @Override
    public void onBindViewHolderCursor(RecyclerView.ViewHolder holder, Cursor cursor) {
        ((NormalViewHolder) holder).bindView(cursor);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view, parent, false);
        return new NormalViewHolder(itemView);
    }

    class NormalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.imageView)
        MyImageView imageView;

        PatternModel mItem;

        public NormalViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(Cursor cursor) {
            mItem = PatternModel.fromCursor(cursor);
            String imageUrl = cursor.getString(cursor.getColumnIndex(WallypaperProvider.PatternColumns.IMAGE_URL));
            Picasso.with(context).load(imageUrl).into((Target) imageView);
        }

        @Override
        public void onClick(View v) {
            if (mTwoPane) {

                Bundle arguments = new Bundle();
                arguments.putParcelable(ItemDetailFragment.ARG_ITEM, mItem);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();

            } else {
                Context context = v.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM, mItem);

                context.startActivity(intent);
            }

        }
    }
}
