package edu.uw.tacoma.dionmerz.babblechat_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class CodeActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);


        SeekBar bar_one = (SeekBar) findViewById(R.id.slider_code_1);
        SeekBar bar_two = (SeekBar) findViewById(R.id.slider_code_2);
        SeekBar bar_three = (SeekBar) findViewById(R.id.slider_code_3);
        SeekBar bar_four = (SeekBar) findViewById(R.id.slider_code_4);

        Button button_cancel = (Button) findViewById(R.id.button_cancel_code);
        Button button_save = (Button) findViewById(R.id.button_save_code);

        bar_one.setOnSeekBarChangeListener(this);
        bar_two.setOnSeekBarChangeListener(this);
        bar_three.setOnSeekBarChangeListener(this);
        bar_four.setOnSeekBarChangeListener(this);

        button_cancel.setOnClickListener(this);
        button_save.setOnClickListener(this);


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        final int id = seekBar.getId();

        TextView digitText;

        switch(id) {

            case R.id.slider_code_1:

                digitText = (TextView) findViewById(R.id.text_code_1);
                digitText.setText(String.valueOf(seekBar.getProgress()));

                break;

            case R.id.slider_code_2:

                digitText = (TextView) findViewById(R.id.text_code_2);
                digitText.setText(String.valueOf(seekBar.getProgress()));
                break;


            case R.id.slider_code_3:

                digitText = (TextView) findViewById(R.id.text_code_3);
                digitText.setText(String.valueOf(seekBar.getProgress()));
                break;

            case R.id.slider_code_4:

                digitText = (TextView) findViewById(R.id.text_code_4);
                digitText.setText(String.valueOf(seekBar.getProgress()));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();

        switch (id) {
            case R.id.button_cancel_code:
                finish();
                break;

            case R.id.button_save_code:
                break;
        }
    }
}
