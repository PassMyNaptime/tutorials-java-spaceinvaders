package com.example.android.spaceinvadersactivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class SpaceInvadersView extends SurfaceView implements Runnable {

    Context context;

    Thread gameThread = null;

    SurfaceHolder ourHolder;

    boolean playing;
    boolean paused = true;

    Canvas canvas;
    Paint paint;

    long fps;

    long timeThisFrame;

    int screenX;
    int screenY;

    PlayerShip playerShip;
    Bullet bullet;

    Bullet[] invadersBullets = new Bullet[200];
    int nextBullet;
    int maxInvaderBullets = 10;

    Invader[] invaders = new Invader[60];
    int numInvaders = 0;

    DefenceBrick[] bricks = new DefenceBrick[400];
    int numBricks;

    SoundPool soundPool;
    int playerExplodeID = -1;
    int invaderExplodeID = -1;
    int shootID = -1;
    int damageShelterID = -1;
    int uhID = -1;
    int ohID = -1;

    int score = 0;
    int lives = 3;

    long menaceInterval = 1000;
    boolean uhOroh;
    long lastMenaceTime = System.currentTimeMillis();


    public SpaceInvadersView(Context context, int x, int y) {

        super(context);

        this.context = context;

        ourHolder = getHolder();
        paint = new Paint();

        screenX = x;
        screenY = y;

    /*
        Ignore the code dealing with sound as the objects used have been removed from Android Studio
     */

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        try {
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            // Load our fx in memory ready for use
            descriptor = assetManager.openFd("shoot.ogg");
            shootID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("invaderexplode.ogg");
            invaderExplodeID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("damageshelter.ogg");
            damageShelterID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("playerexplode.ogg");
            playerExplodeID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("damageshelter.ogg");
            damageShelterID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("uh.ogg");
            uhID = soundPool.load(descriptor, 0);

            descriptor = assetManager.openFd("oh.ogg");
            ohID = soundPool.load(descriptor, 0);

        } catch(IOException e) {
            // Print an error message to the console
            Log.e("Error", "Failed to load sound files");
        }

        prepareLevel();

    }

    private void prepareLevel() {

        playerShip = new PlayerShip(context, screenX, screenY);
        // Make a new player space ship

        // Prepare the players bullet

        // Initialize the invadersBullets array

        // Build an army of invaders

        // Build the shelters

    }


    @Override
    public void run() {
        while (playing) {

            long startFrameTime = System.currentTimeMillis();

            if (!paused) {
                update();
            }

            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    private void update() {
        boolean bumped = false;
        boolean lost = false;

        // Move the player's ship
        playerShip.update(fps);

        // Update the invaders if visible

        // Update all the invaders bullets if active

        // Did an invader bump into the edge of the screen

        if (lost) {
            prepareLevel();
        }

        // Update the players bullet

        // Has the player's bullet hit the top of the screen

        // Has an invaders bullet hit the bottom of the screen

        // Has the player's bullet hit an invader

        // Has an alien bullet hit a shelter brick

        // Has a player bullet hit a shelter brick

        // Has an invader bullet hit the player ship
    }

    private void draw() {

        if (ourHolder.getSurface().isValid()) {

            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255,26,128,182));

            paint.setColor(Color.argb(255,255,255,255));

            // Draw the player spaceship
            canvas.drawBitmap(playerShip.getBitmap(), playerShip.getX(), screenY - 50, paint);

            // Draw the invaders

            // Draw the bricks if visible

            // Draw the players bullet if active

            // Draw the invaders bullets if active


            paint.setColor(Color.argb(255,249,129,0));
            paint.setTextSize(40);
            canvas.drawText("Score: " + score + "     Lives: " + lives, 10, 50, paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }


    public void pause() {
        playing = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "Joining thread");
        }
    }


    public void resume() {
        playing = true;

        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


}