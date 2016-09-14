package com.mota.wp.mota.entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.mota.wp.mota.floors.Floor;
import com.mota.wp.mota.floors.Floor001;

/**
 * Created by wp on 2016/9/11.
 */
public class Actor extends Character implements Runnable{
    public  Bitmap image;
    public int left = 8*32;
    public int top = 18*32;
    public Floor current_floor = null;
    public int LEFT =32;
    public int RIGHT =32*2;
    public int TOP =0;
    public int BOTTOM =32*3;
    public int DIRECTION = BOTTOM;
    public Context context;
    private static Actor actor=null;
    private  int currdz;		//动作

    public Actor(Context context){
        this.context = context;
        current_floor = new Floor001();
        new Thread(this).start();
    }
    public Actor(String name, int type, int hp, int exp, int attack, int defense) {
        super(name, type, hp, exp, attack, defense);
    }

    public static Actor getActor(Context context) {
        if(actor==null){
            actor=new Actor(context);
        }
        return actor;
    }
    public void init_draw(Canvas canvas,int width,int height, Bitmap image){
        this.image = image;
        drawImg1(canvas, dir_rect(DIRECTION));
    }

    public void drawImg1(Canvas canvas, Rect src){
        Rect dst =new Rect();
        dst.left=left ;
        dst.right=left + 32;
        dst.top=top;
        dst.bottom=top + 32;
        canvas.drawBitmap(image, src, dst, null);
    }

    public Rect dir_rect(int x){
        Rect src =new Rect();
        src.left= currdz * 32;
        src.right= currdz * 32+32;
        src.top=x;
        src.bottom=x+32;

        return src;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public Floor getCurrent_floor() {
        return current_floor;
    }

    public void setCurrent_floor(Floor current_floor) {
        this.current_floor = current_floor;
    }

    public static void setActor(Actor actor) {
        Actor.actor = actor;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(300);
                currdz++;
                if(currdz>3){
                    currdz=0;
                }
            }catch (Exception e) {
                Log.i(Actor.class.toString(), e.getMessage());
            }

        }

    }
}
