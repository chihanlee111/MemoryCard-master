package com.vongvia.acitivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.trello.rxlifecycle.components.RxActivity;
import com.vongvia.cardsgame.R;

public class Main2Activity extends RxActivity {
    RadioButton easyRadio , medianRadio , hardRadio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        medianRadio = (RadioButton)findViewById(R.id.radio_median);
        hardRadio = (RadioButton)findViewById(R.id.radio_hard);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void hard(View n){
        Intent mintent = new Intent(Main2Activity.this , MainActivity.class);

        switch (n.getId()){
            case R.id.radio_median:
                mintent.putExtra("hard" ,"2");
                break;
            case R.id.radio_hard:
                mintent.putExtra("hard" ,"3");
                break;
        }
        startActivity(mintent);
    }
}
