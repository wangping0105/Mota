package com.mota.wp.mota.floors;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.mota.wp.mota.R;
import com.mota.wp.mota.Util.MainParam;
import com.mota.wp.mota.entities.Actor;
import com.mota.wp.mota.entities.Enemy;
import com.mota.wp.mota.entities.Npc;

/**
 * Created by wp on 2016/9/11.
 */
public class Floor {
    private int current_floor_no = 1;
    private int[][] bgImageArr;
    private int[][] bg2ImageArr;
    private int[][] zawImageArr;
    private int[][] npcImageArr = null;
    private int[][] enemyImageArr = null;

    private int bitmapNpcRid = 0;

    public Floor(){}

    public Floor(int[][] bgImageArr, int[][] bg2ImageArr, int[][] zawImageArr, int[][] npcImageArr,
                 int[][] enemyImageArr,int bitmapNpcRid, int current_floor_no){
        this.bgImageArr = bgImageArr;
        this.bg2ImageArr = bg2ImageArr;
        this.zawImageArr = zawImageArr;
        this.npcImageArr = npcImageArr;
        this.enemyImageArr = enemyImageArr;

        this.bitmapNpcRid = bitmapNpcRid;
        this.current_floor_no = current_floor_no;
    }

    public void draw(Canvas canvas, Bitmap image,int width,int height,int rowSize, String type){
        int[][] arr;
        switch(type){
            case "zaw":
                arr = zawImageArr;
                break;
            case "bg2":
                arr = bg2ImageArr;
                break;
            case "bg":
                arr = bgImageArr;
                break;
            default:
                arr = bgImageArr;
                break;
        }
        if(arr != null){
            drawImageByArr(canvas, image, arr, width,height,rowSize);
        }
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
        src.left=sx;
        src.right=sx+w;
        src.top=sy;
        src.bottom=sy+h;
        Rect dst =new Rect();
        dst.left=x ;
        dst.right=x +w;//+ extra_length
        dst.top=y;
        dst.bottom=y+h;
        canvas.drawBitmap(image, src, dst, null);
    }

    public int[] checkNextStep(Actor actor, int dir){
        int step = MainParam.STEP;
        int x = actor.getLeft();
        int y = actor.getTop();
        int flag = -1;

        switch (dir) {
            case 1:
                x = x - step;
                break;
            case 2:
                x = x + step;
                break;
            case 3:
                y = y - step;
                break;
            case 4:
                y = y + step;
                break;
        }

        x = (int)Math.ceil(x/32.00);
        y = (int)Math.ceil(y/32.00);

        // flag = 1的时候 npc 遇到
        // flag = 2的时候 敌人 遇到
        if(x<=14 && y<=18 ){
            if(npcImageArr[y][x] != 0) {
                flag = 1;
            }else if(enemyImageArr[y][x] != 0){
                flag = 2;
            }else if(bg2ImageArr[y][x] == 0 && zawImageArr[y][x] == 0){
                flag = 0;
            }
        }
        int[] result = {flag, x, y};
        return result;
    }

    public int[][] getNpcImageArr() {
        return npcImageArr;
    }
    public int[][] getEnemyImageArr() {
        return enemyImageArr;
    }

    public int getBitmapNpcRid() {
        return bitmapNpcRid;
    }

    public void empty_enmey(int x, int y){
        switch(current_floor_no){
            case 1:
                Floor001.enemyImageArr[y][x] = 0;
                break;
        }
        System.out.println(Floor001.enemyImageArr[y][x]);
        System.out.println(y+ "----"+x);
    }

    public void setCurrent_floor_no(int current_floor_no) {
        this.current_floor_no = current_floor_no;
    }

    public void setBg2ImageArr(int[][] bg2ImageArr) {
        this.bg2ImageArr = bg2ImageArr;
    }

    public void setBitmapNpcRid(int bitmapNpcRid) {
        this.bitmapNpcRid = bitmapNpcRid;
    }

    public void setEnemyImageArr(int[][] enemyImageArr) {
        this.enemyImageArr = enemyImageArr;
    }

    public void setNpcImageArr(int[][] npcImageArr) {
        this.npcImageArr = npcImageArr;
    }

    public void setZawImageArr(int[][] zawImageArr) {
        this.zawImageArr = zawImageArr;
    }

    public void setBgImageArr(int[][] bgImageArr) {
        this.bgImageArr = bgImageArr;
    }
}
