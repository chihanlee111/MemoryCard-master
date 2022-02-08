package com.vongvia.acitivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.trello.rxlifecycle.components.RxActivity;
import com.vongvia.cardsgame.R;

public class rankScore extends RxActivity {
    RecyclerView ranklist ;
    rankAdapter rAdapter;
    SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_score);
        RankDbHelper mdbHelper = new RankDbHelper(this);
        mDb = mdbHelper.getWritableDatabase();
        ranklist = (RecyclerView)findViewById(R.id.recy_view);
        rAdapter = new rankAdapter(this , getAllMemList());
        LinearLayoutManager li = new LinearLayoutManager(this);
        ranklist.setLayoutManager(li);
        ranklist.setAdapter(rAdapter);
    }
    public Cursor getAllMemList(){
        return mDb.query(
                RankDbContract.rankEntry.TABLE_NAME,null ,null ,null,null,null,RankDbContract.rankEntry.COLUMN_TIME
        );
    }

}
