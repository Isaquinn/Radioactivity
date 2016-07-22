package com.example.brenorocha.radioactivity_nave_3003_2016;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by breno.rocha on 12/07/2016.
 */
public class Heart
{
    public Context ctx;
    public Paint p;
    Bitmap myImage,imageResized;
    public int w,h;

    public Heart(int sx, int sy,Context c)
    {
        ctx = c;
        p = new Paint(Color.WHITE);
        myImage = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.heart);
        w = sx/10;
        h = sy/7;
        imageResized = MainGameView.getResizedBitmap(this.myImage, w, h);
    }

    public void Draw(Canvas canvas, int MX)
    {
        canvas.drawBitmap(this.imageResized,MainGameView.SX - MX, MainGameView.SY/50, p);
    }

}
