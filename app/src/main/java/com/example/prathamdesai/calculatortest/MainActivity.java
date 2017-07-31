package com.example.prathamdesai.calculatortest;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DecimalFormat;

import com.example.prathamdesai.calculatortest.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private double num1 = Double.NaN;
    private double num2;

    private int activityNum;
    private static final char add = '+';
    private static final char subtract = '-';
    private static final char multiply = '*';
    private static final char divide = '/';

    private char operation;
    private Calc calculatorObject;
    private GestureDetectorCompat detector;
    private MyGestureHandler handler;
    private LayoutInflater inflater;

    public static final String PUBLIC_KEY = "com.example.prathamdesai.calculatortest.MESSAGE";

    DecimalFormat format = new DecimalFormat("0.000000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.activityNum = 0;

        super.onCreate(savedInstanceState);

        calculatorObject = new Calc();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        handler = new MyGestureHandler();

        detector = new GestureDetectorCompat(this, handler);

        calculatorObject.clickClick();

    }
    /*
    public void startAnActivity(){

        int num = getActivityNum();
        Intent intent;

        if (num == 1)
            intent = new Intent(this, INSERTCLASSHERE);
        else if(num == 2)
            intent = new Intent(this, INSERTCLASSHERE);
        else if(num == 3)
            intent = new Intent(this, INSERTCLASSHERE);
        else
            intent = null;

        startActivity(intent);

    }
*/
    public class Calc{

        private void clickClick() {

            binding.buttonZero.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    // binding.editText.getText() gets any text already present on view
                    binding.editText.setText(binding.editText.getText() + "0");
                }
            });

            binding.buttonOne.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "1");
                }
            });

            binding.buttonTwo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "2");
                }
            });

            binding.buttonThree.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "3");
                }
            });

            binding.buttonFour.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "4");
                }
            });

            binding.buttonFive.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "5");
                }
            });

            binding.buttonSix.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "6");
                }
            });

            binding.buttonSeven.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "7");
                }
            });

            binding.buttonEight.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "8");
                }
            });

            binding.buttonNine.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.editText.setText(binding.editText.getText() + "9");
                }
            });

            binding.buttonCE.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    binding.textView.setText(null);
                    binding.editText.setText(null);
                    num1 = Double.NaN;
                    operation = 'z';
                }
            });

            binding.buttonPlus.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    computeOperation();
                    operation = add;

                    binding.textView.setText(format.format(num1) + "+");
                    binding.editText.setText("+");

                }
            });

            binding.buttonSubtract.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    computeOperation();
                    operation = subtract;

                    binding.textView.setText(format.format(num1) + "-");
                    binding.editText.setText("-");

                }
            });

            binding.buttonMultiply.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    computeOperation();
                    operation = multiply;

                    binding.textView.setText(format.format(num1) + "*");
                    binding.editText.setText("*");

                }
            });

            binding.buttonDivide.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    computeOperation();
                    operation = divide;

                    binding.textView.setText(format.format(num1) + "/");
                    binding.editText.setText("/");

                }
            });

            binding.buttonEquals.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    computeOperation();
                    binding.textView.setText(binding.textView.getText().toString() + format.format(num2) + " = " + format.format(num1));
                    binding.editText.setText(format.format(num1));
                    operation = 'z';


                }
            });

        }


        private void computeOperation(){

            if(!Double.isNaN(num1)){
                String first = binding.editText.getText().toString().substring(0, 1);
                num2 = Double.parseDouble(binding.editText.getText().toString().replace(first, ""));
                binding.editText.setText(null);

                if(operation == add){
                    num1 = num1 + num2;
                    //operation = null;
                }
                else if(operation == subtract){
                    num1 = num1 - num2;
                    //operation = null;
                }
                else if(operation == multiply){
                    num1 = num1 * num2;
                    //operation = null;
                }
                else if(operation == divide){
                    num1 = num1 / num2;
                    //operation = null;
                }
            }
            else{
                try {
                    num1 = Double.parseDouble(binding.editText.getText().toString());
                }
                catch (Exception e){

                }
            }
        }
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

            Toast.makeText(context, "FLING", Toast.LENGTH_SHORT).show();

            if (e1.getY() > 500 && e2.getY() <= 973) {

                //setActivityNum(1);

                //startAnActivity();

                //View page = inflater.inflate(R.layout.page, null);

                //setContentView(page);

            } else if (e1.getY() > 973 && e2.getY() <= 1446) {

                //setActivityNum(2);

                //startAnActivity();
                //View page = inflater.inflate(R.layout.another_page, null);

                //setContentView(page);

            } else if (e1.getY() > 1446 && e2.getY() <= 1920) {

                //setActivityNum(3);

                //startAnActivity();
                //View page = inflater.inflate(R.layout.another_another_page, null);

                //setContentView(page);

            }

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


