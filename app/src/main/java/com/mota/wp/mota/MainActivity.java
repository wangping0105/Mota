package com.mota.wp.mota;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mota.wp.mota.entities.Actor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button begin = (Button)findViewById(R.id.begin);
        begin.setOnClickListener(this);
        Button countinue = (Button)findViewById(R.id.countinue);
        countinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.begin:
                Actor.setActor(null);
                startActivity(new Intent(MainActivity.this, ContentMain.class));
                break;
            case R.id.countinue:
                startActivity(new Intent(MainActivity.this, ContentMain.class));
                break;
        }
    }
}
