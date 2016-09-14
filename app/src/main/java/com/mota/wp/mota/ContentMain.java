package com.mota.wp.mota;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mota.wp.mota.Util.MainParam;
import com.mota.wp.mota.entities.Actor;
import com.mota.wp.mota.floors.Floor;
import com.mota.wp.mota.floors.Floor001;
import com.mota.wp.mota.views.GameStartView;

/**
 * Created by wp on 2016/9/10.
 */
public class ContentMain extends AppCompatActivity implements View.OnClickListener {
    private Actor actor = null;//玩家
    private Handler handler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private GameStartView game_start_view;
    // 屏幕的高度和宽度
    private static int screenHeight;
    private static int screenWidth;
    // 每个小格子的高度和宽度
    private static int eachHeight;
    private static int eachWidth;
    private static int extra_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        handler = new Handler();
        game_start_view = (GameStartView)findViewById(R.id.main_page);
        actor = Actor.getActor(this);
        //获得玩家位置
        actor.setLeft(actor.getLeft());
        actor.setTop(actor.getTop());
        // 隐藏 头部
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // 获得屏幕的高和宽
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        this.screenHeight = metrics.heightPixels;
        this.screenWidth = metrics.widthPixels;
        this.eachHeight = screenHeight / 32;
        this.eachWidth = screenWidth / 20;

        // button 设置
        Button up = (Button)findViewById(R.id.up);
        up.setOnClickListener(this);
        Button down = (Button)findViewById(R.id.down);
        down.setOnClickListener(this);
        Button left = (Button)findViewById(R.id.left);
        left.setOnClickListener(this);
        Button right = (Button)findViewById(R.id.right);
        right.setOnClickListener(this);

        handler.postDelayed(mTimeTask, 1000);
    }

    @Override
    public void onClick(View v) {
        int step = MainParam.STEP;
        int checkFlag = -1;
        int[] result = {};
        int x, y;
        Floor f = actor.getCurrent_floor();

        switch (v.getId()) {
            case R.id.begin:

                break;
            case R.id.up:
                Log.d("MainActivity", "---------click up---------" + (actor.getTop()-32));
                actor.DIRECTION = actor.BOTTOM;
                result = f.checkNextStep(actor, 3);
                if(result[0] == 0){
                    actor.setTop(actor.getTop() - step);
                }
                checkFlag = result[0];
                break;
            case R.id.down:
                actor.DIRECTION = actor.TOP;
                result = f.checkNextStep(actor, 4);
                if(result[0] == 0) {
                    actor.setTop(actor.getTop() + step);
                }
                checkFlag = result[0];
                break;
            case R.id.left:
                actor.DIRECTION = actor.LEFT;
                result = f.checkNextStep(actor, 1);
                if(result[0] == 0) {
                    actor.setLeft(actor.getLeft() - step);
                }
                checkFlag = result[0];
                break;
            case R.id.right:
                actor.DIRECTION = actor.RIGHT;
                result = f.checkNextStep(actor, 2);
                if(result[0] == 0) {
                    actor.setLeft(actor.getLeft() + step);
                }
                checkFlag = result[0];
                break;
        }
        x = result[1];
        y = result[2];

        switch(checkFlag){
            case 1:
                Toast.makeText(getApplicationContext(), "我是NPC！", Toast.LENGTH_SHORT).show();
            case 2:
                actor.getCurrent_floor().empty_enmey(x, y);
                Toast.makeText(getApplicationContext(), "我是你的敌人，哈哈，你过不去！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    private Runnable runOneStep = new Runnable() {

        @Override
        public void run() {
            actor.setTop(actor.getTop() - MainParam.STEP / 8);
        }
    };

    private Runnable mTimeTask = new Runnable() {

        @Override
        public void run() {
            game_start_view.invalidate();
            handler.postDelayed(this, 100);
        }
    };
}