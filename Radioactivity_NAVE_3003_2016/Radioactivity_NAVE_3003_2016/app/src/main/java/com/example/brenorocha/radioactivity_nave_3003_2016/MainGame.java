package com.example.brenorocha.radioactivity_nave_3003_2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainGame extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Desligar Titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Tela
        setContentView(new MainGameView(this));

    }
}
