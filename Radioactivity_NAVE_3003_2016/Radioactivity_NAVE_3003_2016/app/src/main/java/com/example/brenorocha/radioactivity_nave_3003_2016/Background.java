package com.example.brenorocha.radioactivity_nave_3003_2016;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by breno.rocha on 08/07/2016.
 */
public class Background
{
    public Context ctx;
    public Paint p;
    Bitmap myImage,imageResized;


    public Background(int sx, int sy,Context c)
    {
        ctx = c;
        p = new Paint(Color.WHITE);
        myImage = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.background);
        imageResized = MainGameView.getResizedBitmap(this.myImage, sx,sy);
    }

    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(this.imageResized, 0, 0, p);
    }

}
