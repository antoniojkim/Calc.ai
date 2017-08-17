package com.example.antoniok.CalcAI;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.antoniok.CalcAI.databinding.ActivityDerivLimPageBinding;


public class DerivLimPage extends AppCompatActivity {

    private ActivityDerivLimPageBinding binding;

    private Intent intent;

    private int activityNum;

    private GestureDetectorCompat detector;
    private DerivLimPage.MyGestureHandler handler;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.activityNum = 1;

        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_deriv_lim_page);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_deriv_lim_page);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        handler = new MyGestureHandler();

        detector = new GestureDetectorCompat(this, handler);


    }
    public void startAnActivity(){

        int num = getActivityNum();
        Intent intent;

        if (num == 0)
            intent = new Intent(this, MainActivity.class);
        else
            intent = null;

        startActivity(intent);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        detector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    public class MyGestureHandler extends GestureDetector.SimpleOnGestureListener {

        private static final String tag = "GESTURES";

        private Context context = getApplicationContext();

        public boolean onSingleTapConfirmed(MotionEvent e) {

            Log.d(tag, "Single tap" + "[" + e.getX() + "," + e.getY() + "]");

            Toast.makeText(context, "SINGLE TAP", Toast.LENGTH_SHORT).show();

            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {

            Log.d(tag, "Double tap" + "[" + e.getX() + "," + e.getY() + "]");

            Toast.makeText(context, "DOUBLE TAP", Toast.LENGTH_SHORT).show();

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.d(tag, "Fling" + "[" + e1.getX() + "," + e1.getY() + "]" + "[" + e2.getX() + "," + e2.getY() + "]");

            String swipe_message = "";

            if (e1.getY() > 605 && e2.getY() <= 825) {

                swipe_message = "Trigonometric";

                //setActivityNum(1);

                //startAnActivity();

                //View page = inflater.inflate(R.layout.page, null);

                //setContentView(page);

            } else if (e1.getY() > 825 && e2.getY() <= 1045) {

                swipe_message = "Logarithmic";

                //setActivityNum(2);

                //startAnActivity();
                //View page = inflater.inflate(R.layout.another_page, null);

                //setContentView(page);

            } else if (e1.getY() > 1045 && e2.getY() <= 1265) {

                swipe_message = "Differential";

                setActivityNum(1);

                startAnActivity();
                //View page = inflater.inflate(R.layout.another_another_page, null);

                //setContentView(page);

            }else if (e1.getY() > 1265 && e2.getY() <= 1485){

                swipe_message = "Numbers";

            }else if (e1.getY() > 1485 && e2.getY() <= 1705){

                swipe_message = "Integration";

            }else if (e1.getY() > 1705 && e2.getY() <= 1920){

                swipe_message = "Main";

                setActivityNum(0);

                startAnActivity();

            }


            Toast.makeText(context, "FLING" + swipe_message, Toast.LENGTH_SHORT).show();

            return true;
        }
    }

    public void setActivityNum(int num){

        this.activityNum = num;
    }

    public int getActivityNum(){

        return this.activityNum;
    }

}
