package com.bitzmark.feedme2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private SoundMeter sm = null;
    private Button sb,gb;
    private TextView tw;
    private boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = new SoundMeter();
        sb = (Button)findViewById(R.id.startButton);
        sb.setBackgroundColor(Color.GREEN);
        gb = (Button)findViewById(R.id.getButton);
        gb.setEnabled(false);
        tw = (TextView) findViewById(R.id.noicelevel);
        state = false;
    }

    public void activate() throws IOException {
        sm.start();
        sb.setText("STOP");
        sb.setBackgroundColor(Color.RED);
        gb.setEnabled(true);
        state = true;

    }

    public void stop(){
        sm.stop();
        sb.setText("START");
        sb.setBackgroundColor(Color.GREEN);
        gb.setEnabled(false);
        state = false;
        gb.setText("Get Sound");
    }

    public void activateButton(View view) throws IOException {
        if(state == true){
            stop();
        }else {
            activate();
        }
    }

    public void getLevel(View view){
        Double value = sm.getAmplitude();
        double db = (20 * Math.log10(value / 0.1));
        tw.setText(Double.toString(db));
        gb.setText("Getting");
    }



}
