package com.example.esame8java;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainActivity  extends Activity {

    String[] picList = {"moomin1", "moomin2", "moomin3", "moomin4", "moomin5"};
    TextView tvProgress;
    ImageView imMoomin;
    ListView lvPics;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvProgress = findViewById(R.id.tvProgress);
        imMoomin = findViewById(R.id.imMoomin);
        lvPics = findViewById(R.id.lvPics);
        progressBar = findViewById(R.id.progressBar);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.list_element,  R.id.textViewList, picList);
        lvPics.setAdapter(arrayAdapter);


        lvPics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String str = parent.getItemAtPosition(position).toString();
                int index = Integer.valueOf(str.charAt(str.length() - 1) - '0');
                Integer img_id = 0;
                switch(index) {
                    case 1:
                        img_id = R.drawable.moomin1;
                        break;
                    case 2:
                        img_id = R.drawable.moomin2;
                        break;
                    case 3:
                        img_id = R.drawable.moomin3;
                        break;
                    case 4:
                        img_id = R.drawable.moomin4;
                        break;
                    case 5:
                        img_id = R.drawable.moomin5;
                        break;

                }

                new LoadImageTask().execute(img_id);
            }
        });
    }

    class LoadImageTask extends AsyncTask<Integer, Integer, Bitmap> {

        @Override
        protected void onPreExecute() {

            progressBar.setVisibility(ProgressBar.VISIBLE);
            tvProgress.setText("The image is currently loading");
        }

        @Override
        protected Bitmap doInBackground(Integer... img_ids) {
            // Load bitmap
            Bitmap tmp = BitmapFactory.decodeResource(getResources(), img_ids[0]);

            /* Simuliamo il ritardo */
            for (int i = 1; i < 11; i++) {
                sleep();
                publishProgress(i * 10);
            }

            return tmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            if (values[0]>75) {
                tvProgress.setText("The image has almost completed loading");
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            progressBar.setProgress(0);
            tvProgress.setText("The image has been loaded");
            imMoomin.setImageBitmap(result);
        }

        private void sleep() {
            /* Ritardo di 0,5 secondi */
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
