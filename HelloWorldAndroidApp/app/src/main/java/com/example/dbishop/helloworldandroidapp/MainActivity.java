package com.example.dbishop.helloworldandroidapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
// import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.lang.*;


public class MainActivity extends ActionBarActivity
{
    MyView view;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        view = new MyView(this);
        setContentView(view);
        tv = (TextView)findViewById(R.id.scoreCounter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                view.cursor.setPosX((int)event.getX());
                view.cursor.setPosY((int)event.getY()-220);
                break;
        }
        view.invalidate();
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

        //noinspection SimplifiableIfStatementcmd
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public class MyView extends View
    {
        Point pos = new Point(0, 0);
        Point cursor = new Point(0,0);
        int score = 0;
        Paint paint = new Paint();

        public MyView(Context context)
        {
            super(context);
            randomizeCoords();
        }

        public void randomizeCoords()
        {
            int randX = 40 + (int)(Math.random() * 1000);
            int randY = 40 + (int)(Math.random() * 1840);
            pos.setPosX(randX);
            pos.setPosY(randY);
        }

        public void updateScore(String score)
        {
            tv.setText(score);
            System.out.println(score);
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawCircle(cursor.getPosX(), cursor.getPosY(), cursor.getDistance(pos), paint);

            if (cursor.getDistance(pos) < 30)
            {
                score++;
                updateScore(""+score);
                randomizeCoords();
            }
        }
    }


    public class Point
    {
        private int posX;
        private int posY;

        public Point(int x, int y)
        {
            posX = x;
            posY = y;
        }

        public int getPosX()
        {
            return posX;
        }

        public int getPosY()
        {
            return posY;
        }

        public void setPosX(int x)
        {
            posX = x;
        }

        public void setPosY(int y)
        {
            posY = y;
        }

        public int getDistance(Point other)
        {
            int dx = Math.abs(posX - other.posX);
            int dy = Math.abs(posY - other.posY);
            return (int)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        }
    }
}
