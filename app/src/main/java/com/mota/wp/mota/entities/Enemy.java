package com.mota.wp.mota.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.mota.wp.mota.floors.Floor;

/**
 * Created by wp on 2016/9/13.
 */
public class Enemy extends Character implements Runnable {
    private  int currdz;		//动作
    public Floor current_floor = null;
    private int[][] enemyImageArr = null;

    public Enemy(Floor current_floor){
        this.current_floor = current_floor;
        new Thread(this).start();
    }

    public void init_draw(Canvas canvas, Bitmap image, int width, int height, int rowSize){
        enemyImageArr = current_floor.getEnemyImageArr();
        drawImageByArr(canvas, image, enemyImageArr, width, height, rowSize);
    }

    public void drawImageByArr(Canvas canvas, Bitmap image, int[][] arrImg,int width,int height,int rowSize) {
        if(arrImg!=null){
            for (int row = 0; row < arrImg.length; row++) {
                for (int column = 0; column < arrImg[row].length; column++) {
                    // 绘制图像
                    int imgIndex = arrImg[row][column];
                    if(imgIndex!=0){
                        //获得本整形元素在图片上的行/列
                        int srow= imgIndex/rowSize;
                        int scolumn= imgIndex%rowSize;
                        drawImg(canvas, image, column * width, row * height, scolumn * width, srow * height, width, height);
                    }
                }
            }
        }
    }
    public void drawImg(Canvas canvas,Bitmap image,int x,int y,int sx,int sy,int w ,int h){
        Rect src =new Rect();
        src.left=currdz * sx;
        src.right=currdz * sx + w;
        src.top=sy;
        src.bottom=sy+h;
        Rect dst =new Rect();
        dst.left=x ;
        dst.right=x +w;//+ extra_length
        dst.top=y;
        dst.bottom=y+h;
        canvas.drawBitmap(image, src, dst, null);
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(300);
                currdz++;
                if(currdz>3){
                    currdz = 0;
                }
            }catch (Exception e) {
                Log.i(Actor.class.toString(), e.getMessage());
            }

        }

    }
}
