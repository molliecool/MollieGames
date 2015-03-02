package com.example.mollie.molliegames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by mollie on 3/1/2015.
 */
public class BalloonSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;
    private int radius;
    private Paint paint;

    private SurfaceHolder surfaceHolder;
    private MainThread thread;
    private boolean exists = true;

    public BalloonSurfaceView(Context context, float x, float y, int radius, float ySpeed) {
        super(context);
        surfaceHolder = getHolder();
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.ySpeed = ySpeed;

        //SurfaceHolder to receive callbacks

        surfaceHolder.addCallback(this);
        this.thread = new MainThread(surfaceHolder, this);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);  //make this a value in your values files
    }

    public void moveBalloon() {
        if (this.y < 0) {
            exists = false;
        } else {
            this.y -= ySpeed;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(this.x, this.y, this.radius, this.paint);
    }


    @Override
     public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = surfaceHolder.lockCanvas();
        canvas.drawRect(10,10,10,10,this.paint);
        surfaceHolder.unlockCanvasAndPost(canvas);
        thread.start();
     }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }

     @Override
     public void surfaceDestroyed(SurfaceHolder holder) {

     }

}
