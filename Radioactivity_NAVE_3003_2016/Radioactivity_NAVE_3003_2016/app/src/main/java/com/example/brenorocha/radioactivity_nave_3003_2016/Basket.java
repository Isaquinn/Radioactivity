package com.example.brenorocha.radioactivity_nave_3003_2016;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by breno.rocha on 08/07/2016.
 */
public class Basket
{
    public int x,y,w,h;
    public Context ctx;
    public Paint p;
    Bitmap myImage,imageResized;


    public Basket(Context c)
    {

        ctx = c;
        p = new Paint(Color.WHITE);
        myImage = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.basket);
        w = (int)(myImage.getWidth() / 4f);
        h = (int)(myImage.getHeight() / 4f);
        imageResized = MainGameView.getResizedBitmap(this.myImage, w, h);
        x = (int)(MainGameView.SX - imageResized.getWidth() * 1.25f);
        y = (int)(MainGameView.SY - imageResized.getHeight() * 1.25f);
    }

    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(this.imageResized,x , y, p);
    }
}
