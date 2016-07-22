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
public class Esteira
{
    public int w,h;
    public float x, y;
    public Context ctx;
    public Paint p;
    Bitmap myImage,imageResized;


    public Esteira(int B1, Context c)
    {
        w = MainGameView.SX;
        h = (int)(MainGameView.SY/2.5f);
        ctx = c;
        p = new Paint(Color.WHITE);
        myImage = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.esteira);
        imageResized = MainGameView.getResizedBitmap(this.myImage, w, h);
        if(B1 == 0)
        {
            x = 0;
        }
        else
        {
            x = -w;
        }
        y = (int)(MainGameView.SY/2 - imageResized.getHeight()/2);
    }
    public void Update(float Vx)
    {
        x += Vx;
        if(x > w)
        {
            x = -w;
        }
    }

    public void Draw(Canvas canvas)
    {
        canvas.drawBitmap(this.imageResized, x, y, p);
    }
}
