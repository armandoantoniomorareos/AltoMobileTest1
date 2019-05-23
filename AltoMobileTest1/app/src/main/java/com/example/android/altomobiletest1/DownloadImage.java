package com.example.android.altomobiletest1;
/*
 * class not needed
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Class to download Image from an URL, this class is used to populate ListView
 * with the pictures of the animals
 */


public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public DownloadImage(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        String imageURL = "";
        Bitmap bimage = null;

        if (urls.length > 0) {
            imageURL = urls[0];

            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
        }
        return bimage;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}