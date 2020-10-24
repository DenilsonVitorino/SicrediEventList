package br.com.sicredieventlist.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import br.com.sicredieventlist.R;

public class ImageManager {
    private static void loadImage(final Context context, final ImageView imageView, final String url, final int idDrawable) {
        new Thread(new Runnable() {
            public void run(){
                try {
                    Drawable drawable = Drawable.createFromStream((InputStream) new URL(url).getContent(), "src");
                    if (drawable == null) {
                        drawable = context.getResources().getDrawable(idDrawable);
                    }
                    final Drawable finalDrawable = drawable;
                    imageView.post(new Runnable() {
                        public void run() {
                            imageView.setImageDrawable(finalDrawable);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void loadEventImage(final Context context, final ImageView imageView, final String url) {
        loadImage(context,imageView,url,R.drawable.event);
    }

    public static void loadPeopleImage(final Context context, final ImageView imageView, final String url) {
        loadImage(context,imageView,url,R.drawable.people);
    }
}
