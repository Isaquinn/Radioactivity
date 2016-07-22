package com.example.brenorocha.radioactivity_nave_3003_2016;

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
import java.util.Random;

/**
 * Created by breno.rocha on 01/07/2016.
 **/
public class MainGameView extends View implements Runnable
{
    android.os.Handler handler;
    private int score, XT, YT;
    public float velocityX, timeTo;
    private boolean CountPoint,CountFault,BarraY, BarraG, GoNext,NextGame;
    public static int Life;
    public Context ctx;
    private Bitmap imgScore, exmpY, resizedScore;
    private int NY, NG;
    private Background background;
    private Esteira esteira1,esteira2;
    private Basket basket;
    private YellowBar[] yellowBar;
    private GreenBar[] greenBar;
    private Heart[] hearts;
    public static int SX,SY;
    private Intent i, newGame;
    Paint p,Letter;

    public MainGameView(Context context)
    {
        super(context);
        handler = new android.os.Handler();
        handler.post(this);
        i = new Intent(context, MainMenu.class);
        newGame = new Intent();
        newGame.setAction("JAMV");
        newGame.putExtra("letter","R3");
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        ctx = context;
        SX = display.getWidth();
        SY = display.getHeight();
        NY = 0;
        NG = 0;
        Life = 3;
        NextGame = false;
        GoNext = true;
        timeTo = 100;
        velocityX = 2;
        CountPoint = false;
        imgScore = BitmapFactory.decodeResource(getResources(), R.drawable.score);
        resizedScore = getResizedBitmap(imgScore, (int) (imgScore.getWidth() / 5f), (int) (imgScore.getHeight() / 5f));
        Letter = new Paint(Color.BLACK);
        Letter.setTextSize(50);
        exmpY = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.yellow), (int) (BitmapFactory.decodeResource(getResources(), R.drawable.yellow).getWidth() / 1.5f), (int) (BitmapFactory.decodeResource(getResources(), R.drawable.yellow).getHeight() / 1.5f));
        yellowBar = new YellowBar[4000];
        greenBar = new GreenBar[4000];
        hearts = new Heart[3];
        for(int i = 0; i < 3; i++)
        {
            hearts[i] = new Heart(SX,SY,context);
        }
        esteira1 = new Esteira(0,context);
        esteira2 = new Esteira(1,context);
        basket = new Basket(context);
        background = new Background(SX,SY,context);
        score = 100;
    }

    @Override
    public void run()
    {
        handler.postDelayed(this, 30);
        update();
        invalidate();
    }

    public void update()
    {
        velocityX += 0.004f;
        if(CountPoint)
        {
            CountPoint = false;
            score += 10;
        }
        else if(CountFault)
        {
            CountFault = false;
            score -= 10;
        }
        if (GoNext)
        {
            if (NY == 0 && NG == 0)
            {
                    yellowBar[NY] = new YellowBar(0 - exmpY.getWidth() - 150, 0, exmpY.getWidth(), exmpY.getHeight(), ctx);
                    NY += 1;
                    BarraY = true;
                    GoNext = false;
            }
            else
            {
                Random rand = new Random();
                int n = rand.nextInt(3);
                Log.d("N:", Integer.toString(n));
                if (n >= 1)
                {
                    yellowBar[NY] = new YellowBar(0 - exmpY.getWidth() - 150, 0, exmpY.getWidth(), exmpY.getHeight(), ctx);
                    NY += 1;
                    BarraY = true;
                    GoNext = false;
                }
                else
                {
                    greenBar[NG] = new GreenBar(0 - exmpY.getWidth() - 150, 0, exmpY.getWidth(), exmpY.getHeight(), ctx);
                    NG += 1;
                    BarraG = true;
                    GoNext = false;
                }
            }
        }
        else
        {
            if (BarraY)
            {
                if (yellowBar[NY - 1].AuxX > SX / 3)
                {
                        GoNext = true;
                        BarraY = false;
                        BarraG = false;
                }
            }
            else if (BarraG)
            {
                if (greenBar[NG - 1].AuxX > SX / 3)
                {
                    GoNext = true;
                    BarraY = false;
                    BarraG = false;
                }
            }
        }
        if(NextGame == false && score >= 100)
        {
            NextGame = true;
        }
        for (int i = 0; i < NY; i++)
        {

            yellowBar[i].Update(XT, YT, getHeight() / 2 - yellowBar[i].h / 2, getWidth(),velocityX);

        }
        for(int i = 0; i < NG; i++)
        {
            greenBar[i].Update(XT, YT, getHeight() / 2 - greenBar[i].h / 2, getWidth(),velocityX);
        }
        esteira1.Update(velocityX);
        esteira2.Update(velocityX);
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

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        background.Draw(canvas);
        basket.Draw(canvas);
        esteira1.Draw(canvas);
        esteira2.Draw(canvas);
        canvas.drawBitmap(resizedScore, getWidth() / 50, getHeight() / 50, p);
        canvas.drawText(Integer.toString(score), getWidth() / 4.5f, getHeight() / 8.5f, Letter);
        if(NextGame)
        {
            canvas.drawText("Ir para o jogo: J", canvas.getWidth()/50, getHeight() - canvas.getHeight()/50, Letter);
        }
        if(Life == 3)
        {
            hearts[0].Draw(canvas, hearts[0].w*3 + 10 + getWidth() / 50);
            hearts[1].Draw(canvas,hearts[0].w*2 + 5 + getWidth() / 50);
            hearts[2].Draw(canvas,hearts[0].w + getWidth() / 50);
        }
        else if(Life == 2)
        {
            hearts[1].Draw(canvas,hearts[0].w*2 + 5 + getWidth() / 50);
            hearts[2].Draw(canvas,hearts[0].w + getWidth() / 50);
        }
        else if(Life == 1)
        {
            hearts[2].Draw(canvas,hearts[0].w + getWidth() / 50);
        }
        else
        {
            System.exit(0);
        }
        for (int i = 0; i < NY; i++)
        {
            yellowBar[i].Draw(canvas);
        }
        for(int i = 0; i < NG; i++)
        {
            greenBar[i].Draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        XT = (int) motionEvent.getX();
        YT = (int) motionEvent.getY();
        for (int i = 0; i < NY; i++)
        {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:

                    if (XT > yellowBar[i].x && XT < yellowBar[i].x + yellowBar[i].w && YT > yellowBar[i].y && YT < yellowBar[i].y + yellowBar[i].h) {
                        yellowBar[i].Catch = true;
                    } else {
                        yellowBar[i].Catch = false;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (yellowBar[i].Catch && XT >= basket.x && XT <= basket.x + basket.w && YT >= basket.y && YT <= basket.y + basket.h) {
                        CountFault = true;
                        yellowBar[i].Destroy();
                    } else {
                        if (yellowBar[i].AuxX < getWidth() && yellowBar[i].Catch) {
                            yellowBar[i].Catch = false;
                            yellowBar[i].x = yellowBar[i].AuxX;
                        }
                        else if(yellowBar[i].AuxX > SX && yellowBar[i].Catch)
                        {
                            yellowBar[i].Destroy();
                        }
                    }
                    break;
            }

        }
        for(int i = 0; i < NG; i++)
        {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
            {
                case MotionEvent.ACTION_DOWN:

                    if (XT > greenBar[i].x && XT < greenBar[i].x + greenBar[i].w && YT > greenBar[i].y && YT < greenBar[i].y + greenBar[i].h)
                    {
                        greenBar[i].Catch = true;
                    }
                    else
                    {
                        greenBar[i].Catch = false;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (greenBar[i].Catch && XT >= basket.x && XT <= basket.x + basket.w && YT >= basket.y && YT <= basket.y + basket.h)
                    {
                        CountPoint = true;
                        greenBar[i].Destroy();
                    }
                    else
                    {
                        if (greenBar[i].AuxX < getWidth() && greenBar[i].Catch)
                        {
                            greenBar[i].Catch = false;
                            greenBar[i].x = greenBar[i].AuxX;
                        }
                        else if(greenBar[i].AuxX > SX && greenBar[i].Catch)
                        {
                            greenBar[i].Destroy();
                        }
                    }
                    break;
            }
        }
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                if(NextGame)
                {
                    if(XT < SX / 2.25f && YT > SY - SY / 9f)
                    {
                        ctx.startActivity(newGame);
                    }
                }
                break;
        }
        return true;
    }
}

