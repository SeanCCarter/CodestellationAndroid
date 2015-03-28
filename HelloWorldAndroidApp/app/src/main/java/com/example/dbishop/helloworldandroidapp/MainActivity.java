package com.example.dbishop.helloworldandroidapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends ActionBarActivity
{
    MyView view;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        view = new MyView(this);
        setContentView(view);

        Runnable updater = new Runnable(){
            @Override
            public void run()
            {
                view.invalidate();
                view.postDelayed(updater, 1000/30);
            }
        };
        view.post(updater);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                view.touch_x = (int)event.getX();
                view.touch_y = (int)event.getY();
                view.radius = 0;
                break;
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
        }
        view.invalidate();  // Force redrawing.
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyView extends View
    {
        int touch_x = 200;
        int touch_y = 200;
        int radius = 0;
        Paint paint = new Paint();

        public MyView(Context context)
        {
            super(context);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.drawCircle(touch_x, touch_y, ++radius, paint);
            postInvalidateDelayed(1000/30);
        }
    }
}
