package com.example.mollie.molliegames;

import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * Created by mollie on 3/1/2015.
 */
public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private BalloonSurfaceView balloon;

    public MainThread(SurfaceHolder surfaceHolder, BalloonSurfaceView balloon) {
        this.surfaceHolder = surfaceHolder;
        this.balloon = balloon;
    }

    public void run() {
        Canvas canvas = null;

        while(true) {
            try {
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    balloon.moveBalloon();
                    balloon.draw(canvas);
                }
            }
            finally {
                if(canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }

}
