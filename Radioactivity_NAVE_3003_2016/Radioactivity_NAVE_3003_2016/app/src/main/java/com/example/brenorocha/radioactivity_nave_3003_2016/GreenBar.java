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
public class GreenBar
{
    public float x,y,AuxX;
    public int w,h;
    public boolean Catch,Destroyed;
    public Context ctx;
    public Paint p;
    Bitmap myImage;
    Bitmap imageResized;

    public GreenBar(int x1, int y1,int w1, int h1, Context c)
    {
        x = x1;
        y = y1;
        w = w1;
        h = h1;
        ctx = c;
        p = new Paint(Color.WHITE);
        myImage = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.green);
        imageResized = MainGameView.getResizedBitmap(this.myImage, w, h);
    }

    public void Draw(Canvas canvas)
    {
        if(!Destroyed)
        {
            canvas.drawBitmap(this.imageResized, x, y, p);
        }
    }

    public void Update(int xt, int yt, int cH,int cWidth, float Vx)
    {
        AuxX += Vx;
        if(!Destroyed)
        {
            if (!Catch)
            {
                y = cH;
                x = AuxX;
            }
            else
            {

                x = xt - w / 2;
                y = yt - h / 2;
            }
            if(x > cWidth)
            {
                Destroy();
                MainGameView.Life -= 1;
            }
        }
        else if(AuxX <= cWidth/3)
        {
            AuxX += Vx;
        }
    }

    public void Destroy()
    {
        x = 5000;
        y = 5000;
        w = 0;
        h = 0;
        Destroyed = true;
    }
}
