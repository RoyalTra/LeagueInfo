package com.csc4210.royal.leagueinfo.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Royal on 4/13/2018.
 */

public class ImageAdapter extends BaseAdapter {
    public static String[] img_array = new String[Champions_Enum.getSize()];
    private Context context;
    int viewCount = 0;
    ImageView[] img_view = new ImageView[Champions_Enum.getSize()];
    public ImageAdapter(Context c ){

        context = c;
        int count = 0;

        for(Champions_Enum.Champ champ : Champions_Enum.Champ.values()){
            img_array[count++] = champ.name();

        }

    }

    public int getCount(){return Champions_Enum.getSize(); }

    public Object getItem(int position){ return null; }

    public long getItemId(int position){ return position; }

    public boolean hasStableIds(){
        return true;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        viewCount += 1;
        if (convertView == null) {

            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(315, 315));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else {
            imageView = (ImageView) convertView;
        }

        int id = context.getResources().getIdentifier(img_array[position].toLowerCase(),"drawable",context.getPackageName());

        imageView.setImageResource(id);
        return imageView;
    }

    //Used to download an image
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{

        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage){
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bMap = null;
            try{
                InputStream in = new java.net.URL(urldisplay).openStream();
                bMap = BitmapFactory.decodeStream(in);
                Log.println(Log.ERROR, "Bitmap Count",urls[0] );
            }catch(Exception e){
                Log.println(Log.ERROR, "Bitmap Exception", e.toString());
            }
            return bMap;
        }

        @Override
        protected void onPostExecute(Bitmap result){
            bmImage.setImageBitmap(result);
        }

        public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // CREATE A MATRIX FOR THE MANIPULATION
            Matrix matrix = new Matrix();
            // RESIZE THE BIT MAP
            matrix.postScale(scaleWidth, scaleHeight);

            // "RECREATE" THE NEW BITMAP
            Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                    matrix, false);

            return resizedBitmap;
        }
    }

}
