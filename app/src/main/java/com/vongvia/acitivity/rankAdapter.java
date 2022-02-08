package com.vongvia.acitivity;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vongvia.cardsgame.R;

/**
 * Created by liqihan on 2017/6/18.
 */

public class rankAdapter extends RecyclerView.Adapter<rankAdapter.SessViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    public rankAdapter(Context context ,Cursor cursor) {
        this.mCursor = cursor;
        this.mContext = context;
    }

    @Override
    public rankAdapter.SessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item , parent,false);
        return new SessViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SessViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        int time = mCursor.getInt(mCursor.getColumnIndex(RankDbContract.rankEntry.COLUMN_TIME));
        holder.ranktextView.setText(Integer.valueOf(position+1).toString()+" : ");
        holder.timeTextView.setText(Integer.valueOf(time).toString()+" 秒");

    }
    public void swapCursor(Cursor cursor){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor = cursor;
        if(cursor!=null){
            this.notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class SessViewHolder extends RecyclerView.ViewHolder{

        TextView ranktextView;
        TextView timeTextView;
        public SessViewHolder(View itemView) {
            super(itemView);
            ranktextView = (TextView)itemView.findViewById(R.id.rank_text_view);
            timeTextView = (TextView)itemView.findViewById(R.id.time_text_view);
        }


    }
}
