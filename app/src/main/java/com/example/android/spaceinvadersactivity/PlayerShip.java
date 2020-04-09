package com.example.android.spaceinvadersactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class PlayerShip {

    RectF rect;

    private Bitmap bitmap;
    float length;
    float height;
    float x;
    float y;

    float shipSpeed;

    final int STOPPED = 0;
    final int LEFT = 1;
    final int RIGHT = 2;

    int shipMoving = STOPPED;

    public PlayerShip(Context context, int screenX, int screenY) {

        rect = new RectF();

        length = screenX / 10;
        height = screenY / 10;

        x = screenX / 2;
        y = screenY - 20;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playership);

        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        shipSpeed = 350;
    }


    public RectF getRect() {
        return rect;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }


    public float getX() {
        return x;
    }


    public float getLength() {
        return length;
    }


    public void setMovementState(int state) {
        shipMoving= state;
    }

    public void update(long fps) {

        switch (shipMoving) {
            case LEFT:
                x = x - (shipSpeed / fps);
                break;
            case RIGHT:
                x = x + (shipSpeed / fps);
                break;
        }

//        if (shipMoving == LEFT) {
//            x = x - shipSpeed / fps;
//        }
//
//        if (shipMoving == RIGHT) {
//            x = x + shipSpeed / fps;
//        }

        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;

    }
}

