package edu.uw.tacoma.dionmerz.babblechat_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button startButton = (Button) findViewById(R.id.button_start);

        startButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        final int id = view.getId();

        switch (id) {
            case R.id.button_start:
                intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                break;

            case R.id.button_codeBuilder:
                break;
        }
    }
}
