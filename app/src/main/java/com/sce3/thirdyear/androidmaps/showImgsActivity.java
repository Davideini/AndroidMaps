package com.sce3.thirdyear.androidmaps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.internal.ge;
import com.sce3.thirdyear.androidmaps.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class showImgsActivity extends Activity {
    ImageView img,fullImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_imgs);
        Bundle extras= getIntent().getExtras();
        //img=(ImageButton) findViewById(R.id.imgResButton);
        fullImg=(ImageView) findViewById(R.id.fullScreenImg);
        Bitmap a=(Bitmap)extras.getParcelable("Img");
        fullImg.setImageBitmap(a);

        /*
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) img.getDrawable());
        Bitmap bitmap = bitmapDrawable .getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] imageInByte = stream.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
*/

/*

        fullImg=(ImageView) findViewById(R.id.fullScreenImg);
        Intent intent = getIntent();
        Bitmap b = (Bitmap) intent.getParcelableExtra("Img");
        fullImg.setImageBitmap(b);
/*
        Bitmap bmp = (Bitmap) extras.getParcelable("Img");

        // fullImg.setImageBitmap(bmp);
        fullImg.setImageDrawable(img.getDrawable());
        fullImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        fullImg.setAdjustViewBounds(false);
*/




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_imgs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
