package com.mota.wp.mota.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by wp on 2016/9/11.
 */
public class Character {
    private String name;
    private int type; // 类型
    private int hp; // 血量
    private int exp; // 经验
    private int attack; //攻击
    private int defense; //防御
    private int level = 1;
    private int gold = 0;
    private int copper_key = 0;
    private int silver_key = 0;
    private int gold_key = 0;

    public Character(){}
    public Character(String name, int type, int hp, int exp, int attack, int defense){
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.exp = exp;
        this.attack = attack;
        this.defense = defense;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getGold_key() {
        return gold_key;
    }

    public void setGold_key(int gold_key) {
        this.gold_key = gold_key;
    }

    public int getSilver_key() {
        return silver_key;
    }

    public void setSilver_key(int silver_key) {
        this.silver_key = silver_key;
    }

    public int getCopper_key() {
        return copper_key;
    }

    public void setCopper_key(int copper_key) {
        this.copper_key = copper_key;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
}
