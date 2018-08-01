package com.yixingjjinrong.yixinjinrongapp.application;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageResizerUtils {
    public static Bitmap ImageResizerNotWork(URL url, InputStream is, int reqWidth, int reqHeight) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, options);

            int width = options.outWidth;
            int height = options.outHeight;
            int SampleSize = 1;

            if (width > reqWidth || height > reqHeight) {
                int halfwidth = width / 2;
                int halfheight = height / 2;

                while ((halfwidth / SampleSize) >= reqWidth && (halfheight / SampleSize) >= reqHeight) {
                    SampleSize *= 2;
                }
            }
            options.inSampleSize =SampleSize;
            options.inJustDecodeBounds=false;
            is.close();

            InputStream inputStream = url.openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, options);
            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
