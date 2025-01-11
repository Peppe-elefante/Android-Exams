package com.example.esame10java;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Tris extends Fragment {

    boolean isX;
    TextView view;
    LayoutInflater inf;
    ViewGroup rootView;
    private OnButtonClickListener mListener;
    int[] textViewIds = {
            R.id.textView1, R.id.textView2, R.id.textView3,
            R.id.textView4, R.id.textView5, R.id.textView6,
            R.id.textView7, R.id.textView8, R.id.textView9
    };

    public String[] game= {" "," "," "," "," "," "," "," "," "};

    public Tris(){

    }

    public void resetGame() {
        View v = getView();
        int counter = 0;
        for(int id : textViewIds){
            TextView textView = v.findViewById(id);
            textView.setText(" ");
            game[counter++] = " ";
        }
    }

    public interface OnButtonClickListener {
        void onButtonClick(boolean isX);
    }


    public void setPlayer(boolean isX){
        this.isX = isX;
    }

    public String[] getGame(){
        return game;
    }


    public void updateScreen(String[] newGame){
        game = newGame;
        int counter = 0;
        for (int id : textViewIds) {
            TextView textView = getView().findViewById(id);
            textView.setText(game[counter]);
            counter++;
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Ensure the Activity implements the listener
        if (context instanceof OnButtonClickListener) {
            mListener = (OnButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnButtonClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inf = inflater;
        rootView = container;
        View v = inflater.inflate(R.layout.tris_fragment, container, false);



        int count = 0;
        for (int id : textViewIds) {
            TextView textView = v.findViewById(id);
            int finalCount = count;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playTris(v, finalCount);
                    mListener.onButtonClick(isX);
                }
            });
            count++;
        }
        return v;
    }

    public void playTris(View v, int count){
            TextView tv = (TextView) v;
            if(tv.getText().equals(" ")){
                String change = (isX)? "X": "O";
                tv.setText(change);
                game[count] = change;
            }
    }
}
