package com.example.brenorocha.radioactivity_nave_3003_2016;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by breno.rocha on 14/07/2016.
 */
public class MainCreditsView extends View
{
    private int XT, YT;
    public Bitmap backR, back,bB,bBR;
    public Paint p;
    private Context ctx;
    public static int SX,SY;

    public MainCreditsView(Context context)
    {
        super(context);
        ctx = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        SX = display.getWidth();
        SY = display.getHeight();
        p = new Paint(Color.WHITE);
        back = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundcredits);
        backR = getResizedBitmap(back, SX, SY);
        bB= BitmapFactory.decodeResource(getResources(), R.drawable.backbuttoncredits);
        bBR = getResizedBitmap(bB, SX / 5, SY / 3);
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawBitmap(backR, 0, 0, p);
        canvas.drawBitmap(bB, bB.getWidth()/10f, SY - bB.getHeight() - bB.getHeight()/10 ,p);
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
                if (XT > bB.getWidth() / 10f && XT < bB.getWidth() / 10f + bB.getWidth() && YT > SY - bB.getHeight() - bB.getHeight() / 10 && YT < SY - bB.getHeight() / 10) {
                    System.exit(0);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
