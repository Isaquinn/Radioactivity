package com.example.brenorocha.radioactivity_nave_3003_2016;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by breno.rocha on 12/07/2016.
 */
public class MainMenuView extends View
{
    private int XT, YT;
    public Bitmap backR, back,bP,bPR,bC,bCR,bE,bER;
    public Paint p;
    private Context ctx;
    public static int SX,SY;
    public MainMenuView(Context context)
    {
        super(context);
        ctx = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        SX = display.getWidth();
        SY = display.getHeight();
        p = new Paint(Color.WHITE);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.menu);
        backR = getResizedBitmap(back, SX, SY);
        bP= BitmapFactory.decodeResource(getResources(), R.drawable.b_play);
        bPR = getResizedBitmap(bP, SX / 4, (int)(SY / 2.5f));
        bC = BitmapFactory.decodeResource(getResources(), R.drawable.b_credits);
        bCR = getResizedBitmap(bC, (int)(SX / 5.5f), (int)(SY / 3.5f));
        bE = BitmapFactory.decodeResource(getResources(), R.drawable.b_exit);
        bER = getResizedBitmap(bE, (int)(SX / 5.5f), (int)(SY / 3.5f));
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(backR, 0, 0, p);
        canvas.drawBitmap(bPR, SX/2 - bPR.getWidth()/2, SY/2 - bPR.getHeight()/2,p);
        canvas.drawBitmap(bCR, 0 + bCR.getWidth(), SY - (bCR.getHeight() * 1.25f) ,p);
        canvas.drawBitmap(bER, SX - bER.getWidth()*2, SY - (bER.getHeight() * 1.25f) ,p);
    }
    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        XT = (int) motionEvent.getX();
        YT = (int) motionEvent.getY();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (XT > SX / 2 - bPR.getWidth() / 2 && XT < SX / 2 - bPR.getWidth() / 2 + bPR.getWidth() && YT > SY / 2 - bPR.getHeight() / 2 && YT < SY / 2 - bPR.getHeight() / 2 + bPR.getHeight()) {
                    Intent i = new Intent(ctx, MainGame.class);
                    ctx.startActivity(i);
                }
                else if (XT > 0 + bCR.getWidth() && XT < 0 + bCR.getWidth()*2 && YT > SY - (bPR.getHeight() * 1.25f) && YT < SY - (bCR.getHeight() * 1.25f) + bCR.getHeight()) {
                    Intent i = new Intent(ctx, MainCredits.class);
                    ctx.startActivity(i);
                }
                else if(XT > SX - bER.getWidth()*2 && YT > SY - (bER.getHeight() * 1.25f) && XT < SX - bER.getWidth() && YT < SY - (bER.getHeight() * 1.25f) + bER.getHeight())
                {
                    System.exit(0);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
