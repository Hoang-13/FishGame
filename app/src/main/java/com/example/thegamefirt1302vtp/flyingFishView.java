package com.example.thegamefirt1302vtp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Toast;

public class flyingFishView extends View
{
    private Bitmap fish[] = new Bitmap[2];
    private  int fishX = 10;
    private  int fishY=10;
    private  int fishSpeed;
    private int canvasWidth, canvasHeight;
    private  int yellowX, yellowY, yellowSpeed = 16;
    private  Paint yellowPain = new Paint();
    private  int greenX, greenY, greenSpeed = 20;
    private  Paint greenPain = new Paint();
    private  int redX, redY, redSpeed = 16;
    private  Paint redPain = new Paint();
    private  int score , lifeCouterOffish;
    private boolean touch = false;






    private Bitmap backgroundImage;
    private Paint ScorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];
    public flyingFishView(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowPain.setColor(Color.YELLOW);
        yellowPain.setAntiAlias(false);
        greenPain.setColor(Color.GREEN);
        greenPain.setAntiAlias(false);
        redPain.setColor(Color.RED);
        redPain.setAntiAlias(false);



        ScorePaint.setColor(Color.WHITE);
        ScorePaint.setTextSize(70);
        ScorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        ScorePaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
        fishY = 550;
        score = 0;
        lifeCouterOffish = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        canvas.drawBitmap(backgroundImage, 0,0, null);
        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() + 3;
        fishY = fishY + fishSpeed;


        if(fishX < minFishY)
        {

            fishY = minFishY;
        }
        if(fishX > maxFishY)
        {

            fishY = minFishY;
        }
        fishSpeed = fishSpeed + 2;
        if(touch)
        {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;

        }
        else
        {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }





        yellowX = yellowX - yellowSpeed;
        if(hitBallChecker(yellowX , yellowY))
        {
            score =  score + 10;
            yellowX =  - 100;
        }

        if(yellowX < 0)
        {

            yellowX = canvasWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;

        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowPain);
        greenX = greenX - greenSpeed;
        if(hitBallChecker(greenX , greenY))
        {
            score =  score + 17;
            greenX =  - 100;
        }

        if(greenX < 0)
        {

            greenX = canvasWidth + 25;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;

        }
        canvas.drawCircle(greenX, greenY, 25, greenPain);
        redX = redX - redSpeed;
        if(hitBallChecker(redX , redY))
        {


            redX =  - 100;
            lifeCouterOffish--;
            if(lifeCouterOffish==0)
            {
                Toast.makeText(getContext(),"Game Over", Toast.LENGTH_SHORT).show();
                Intent gameoverIntent = new Intent(getContext(), GameOverActivity.class);
                gameoverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverIntent.putExtra("score", score);
                gameoverIntent.putExtra("namPlayer", MainActivity.lblName);
                getContext().startActivity(gameoverIntent);

            }
        }

        if(redX < 0)
        {

            redX = canvasWidth + 25;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;

        }
        canvas.drawCircle(redX, redY, 25, redPain);
        canvas.drawText("Score : " + score, 20, 60, ScorePaint);
        for(int i = 0; i < 3; i++)
        {
            int x = (int) (580 + life[0].getWidth() * 1.5 + i);
            int y = 30;
            if(i < lifeCouterOffish)
            {
                canvas.drawBitmap(life[0], x, y ,null);
            }
            else
            {
                canvas.drawBitmap(life[1], x, y ,null);
            }
        }



    }
    public boolean hitBallChecker(int x, int y)
    {
        if(fishX < x && x < (fishX + fish[0].getWidth()) && (fishY < y && y < (fishY + fish[0].getHeight())))
        {
            return  true;

        }
        return false;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            fishSpeed = -22;
        }
        return  true;
    }
}
