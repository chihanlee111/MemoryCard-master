package com.vongvia.acitivity;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.plattysoft.leonids.ParticleSystem;
import com.trello.rxlifecycle.components.RxActivity;
import com.vongvia.cardsgame.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends RxActivity implements View.OnClickListener {

    SQLiteDatabase mdb;
    static ImageView[] PictureBoxs = new ImageView[12];
    TableRow row1 , row2 , row3;
    static int[] Index = new int[12];
    static  int pictureAmount = 12;
    private int mhard =0;
    private int Count = 0;
    private int[] ClickID = new int[2];

    int wrongnum = 0;

    private int alstart = 0;

    int alltime = 0;
    private TextView tvShow;
    private int socore = 0;
    int setWrongnum = 3;
    int settime = 600000;
    int memtime = 3;
    Boolean canTouch = false;
    Button btn_exit, btn_newgame ,btn_rank;
    SharedPreferences.Editor sp_edit;
    public static int[] ImageSource =
            {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6
                    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RankDbHelper dbhelper = new RankDbHelper(this);
        mdb = dbhelper.getWritableDatabase();
        row1 = (TableRow)findViewById(R.id.tableRow1);
        row2 = (TableRow)findViewById(R.id.tableRow2);
        row3 = (TableRow)findViewById(R.id.tableRow3);
        btn_exit = (Button) findViewById(R.id.Button_Exit);
        btn_newgame = (Button) findViewById(R.id.Button_NewGame);
        btn_rank = (Button) findViewById(R.id.Button_rank);
        tvShow = (TextView) findViewById(R.id.tv_Time);
        PictureBoxs[0] = (ImageView) findViewById(R.id.ImageView00);
        PictureBoxs[1] = (ImageView) findViewById(R.id.ImageView01);
        PictureBoxs[2] = (ImageView) findViewById(R.id.ImageView02);
        PictureBoxs[3] = (ImageView) findViewById(R.id.ImageView03);
        PictureBoxs[4] = (ImageView) findViewById(R.id.ImageView04);
        PictureBoxs[5] = (ImageView) findViewById(R.id.ImageView05);
        PictureBoxs[6] = (ImageView) findViewById(R.id.ImageView06);
        PictureBoxs[7] = (ImageView) findViewById(R.id.ImageView07);
        PictureBoxs[8] = (ImageView) findViewById(R.id.ImageView08);
        PictureBoxs[9] = (ImageView) findViewById(R.id.ImageView09);
        PictureBoxs[10] = (ImageView) findViewById(R.id.ImageView10);
        PictureBoxs[11] = (ImageView) findViewById(R.id.ImageView11);
        btn_newgame.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_rank.setOnClickListener(this);
        Intent intent = getIntent();
        String hard = intent.getStringExtra("hard");
        if(hard != null){
            try {
                mhard = Integer.parseInt(hard);
                setPictureAmount(mhard);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("testtest    "+mhard);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            isExit.setTitle("離開");
            isExit.setMessage("確定離開遊戲嗎？");
            isExit.setButton("確定", listener);
            isExit.setButton2("取消", listener);
            isExit.show();

        }
        return false;
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE: {
                    finish();
                    break;
                }
                case AlertDialog.BUTTON_NEGATIVE:
                    break;
                default:
                    break;
            }
        }
    };

    private void setPictureAmount(int hard){
        switch (hard){
            case 1:
                pictureAmount =4;
                System.out.println("aaaa"+pictureAmount);
                row2.setVisibility(View.INVISIBLE);
                row3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                row3.setVisibility(View.INVISIBLE);
                pictureAmount =8;
                System.out.println("aaaa"+pictureAmount);
                break;
            case 3:
                pictureAmount =12;
                System.out.println("aaaa"+pictureAmount);
                break;
            default:
                break;
        }
        newgame();
    }
    private void reset(View root) {

        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                reset(parent.getChildAt(i));
            }
        } else {
            root.setScaleX(1);
            root.setScaleY(1);
            root.setAlpha(1);
        }
    }
    public static void CardTurn() {
        for (int i = 0; i < pictureAmount; i++) {
            PictureBoxs[i].setImageResource(R.drawable.card);
        }
    }


    public void initPic_Press() {

        for (int i = 0; i <= pictureAmount -1; i++) {
            final int x = i;
            RxView.clicks(PictureBoxs[i])
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void avoid) {
                            if (canTouch) {
                                if (alstart == 0 || (false && alstart != 0)) {
                                    Toast.makeText(MainActivity.this, "開始遊戲", Toast.LENGTH_SHORT).show();
                                } else {
                                    Count++;
                                    ClickID[Count - 1] = x;
                                    PictureBoxs[x].setImageResource(ImageSource[Index[x]]);

                                    if (Count == 2) {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                checkPic();
                                            }
                                        }, 500);
                                    }
                                }
                            }
                        }
                    });
        }

    }

    private void checkPic() {
        if (ClickID[0] != ClickID[1] && Index[ClickID[0]] == Index[ClickID[1]]) {
            socore++;


            if (socore == pictureAmount/2) {// 2 * 6

                View v = findViewById(R.id.ImageView00);
                Toast.makeText(getApplicationContext(), "勝利！！！！！", Toast.LENGTH_LONG).show();
                addToRank();
                canTouch = false;
            }
        } else {
            PictureBoxs[ClickID[0]].setImageResource(R.drawable.card);
            PictureBoxs[ClickID[1]].setImageResource(R.drawable.card);

        }
        Count = 0;
    }
    public long addToRank(){
        String timeuse = (String) tvShow.getText();
        System.out.println("timeuse "+timeuse);
        ContentValues cv = new ContentValues();
        cv.put(RankDbContract.rankEntry.COLUMN_TIME , timeuse);
        return mdb.insert(RankDbContract.rankEntry.TABLE_NAME ,null,cv);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button_rank:
                Intent newIntent1 = new Intent(MainActivity.this , rankScore.class);
                startActivity(newIntent1);
                break;
            case R.id.Button_NewGame:
                Intent newIntent = new Intent(MainActivity.this , Main2Activity.class);
                startActivity(newIntent);
                break;
            case R.id.Button_Exit:
                int i [] = new int[1];
                System.out.println(i[2]);
                break;

        }

    }

    private void newgame() {
        alltime = 0;
        wrongnum = 0;
        socore = 0;
        canTouch = false;

        Index = GetIndex.Getnum(pictureAmount/2);
        for(int i =0;i<pictureAmount;i++){
            System.out.println(Index[i]);
        }


        for (int i = 0; i < pictureAmount; i++) {
            PictureBoxs[i].setImageResource(ImageSource[Index[i]]);
        }
        startTime();
        initPic_Press();
        alstart++;
    }

    private void startTime() {
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(this.<Long>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Action1<Long>() {
                               @Override
                               public void call(Long aLong) {
                                   tvShow.setText("" + Integer.toString(alltime++));
                                   if (alltime == memtime) {
                                       CardTurn();
                                       canTouch = true;
                                   } else if (alltime == settime) {
                                       canTouch = false;

                                       Toast.makeText(getApplicationContext(), "End", Toast.LENGTH_LONG).show();
                                       tvShow.setText("" + alltime);
                                       alltime = 0;
                                   }
                               }
                           }

                );
    }

}
