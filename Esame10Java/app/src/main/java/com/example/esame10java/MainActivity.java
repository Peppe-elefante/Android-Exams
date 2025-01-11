package com.example.esame10java;

import static java.lang.Math.log;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MainActivity extends Activity implements Tris.OnButtonClickListener {

    FragmentManager fm;
    FragmentTransaction ft;
    Tris player1, player2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        player1 = new Tris();
        player2 = new Tris();
        player1.setPlayer(true);
        player2.setPlayer(false);


        ft.add(R.id.tris1, player1 , null);
        ft.add(R.id.tris2, player2 , null);

        ft.commit();
    }

    @Override
    public void onButtonClick(boolean isX) {
        if(isX){
            player2.updateScreen(player1.getGame());
        } else{
            player1.updateScreen(player2.getGame());
        }
    }
    public void reset(View v){

        player1.resetGame();
        player2.resetGame();
    }
}
