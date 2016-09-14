package com.mota.wp.mota.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mota.wp.mota.entities.Enemy;
import com.mota.wp.mota.entities.Npc;
import com.mota.wp.mota.floors.Floor;
import com.mota.wp.mota.R;
import com.mota.wp.mota.entities.Actor;

public class GameStartView extends View{
    private static Paint paint;
    private static Bitmap bitmapImgall;
    private static Bitmap act_img;
    private static Bitmap bitmapNpc;
    private static Bitmap bitmapEnemy;
    private static Context context;
    private static Actor actor = null;//玩家
    private static Npc npc = null;//npc
    private static Enemy enemy = null;//敌人
    private static Floor current_floor = null;//楼层

    public GameStartView(Context context) {
        super(context);
    }

    public GameStartView(Context context, AttributeSet attrSet) {
        super(context, attrSet);
        this.context = context;
        Log.d("Start", "GameStartView");
        actor=Actor.getActor(context);
        npc = new Npc(actor.getCurrent_floor());
        enemy = new Enemy(actor.getCurrent_floor());
        current_floor = actor.getCurrent_floor();
        bitmapImgall = ((BitmapDrawable) getResources().getDrawable(R.mipmap.imgall)).getBitmap();
        act_img = ((BitmapDrawable) getResources().getDrawable(R.mipmap.actor)).getBitmap();
        bitmapNpc = ((BitmapDrawable) getResources().getDrawable(actor.getCurrent_floor().getBitmapNpcRid())).getBitmap();
        bitmapEnemy = ((BitmapDrawable) getResources().getDrawable(R.mipmap.enemy)).getBitmap();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
//        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
//        paint.setColor(Color.BLUE);

        current_floor.draw(canvas, bitmapImgall, 32, 32, 24, "bg");
        current_floor.draw(canvas, bitmapImgall, 32, 32, 24, "bg2");
        current_floor.draw(canvas, bitmapImgall, 32, 32, 24, "zaw");
          npc.init_draw(canvas, bitmapNpc, 32, 32, 4);
        enemy.init_draw(canvas, bitmapEnemy, 32, 32, 4);
        actor.init_draw(canvas, 32, 32, act_img);
    }
}
