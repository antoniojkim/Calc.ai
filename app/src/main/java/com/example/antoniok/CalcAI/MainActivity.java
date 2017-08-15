package com.example.antoniok.CalcAI;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import com.example.antoniok.CalcAI.databinding.ActivityMainBinding;

import Math_Evaluation_Library.Engine.Engine;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int activityNum;

    private GestureDetectorCompat detector;
    private MyGestureHandler handler;
    private LayoutInflater inflater;

    public static final String PUBLIC_KEY = "com.example.prathamdesai.calculatortest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.activityNum = 0;

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        handler = new MyGestureHandler();

        detector = new GestureDetectorCompat(this, handler);

        initiateButtonPressed();

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
    protected void initiateButtonPressed(){

        binding.calculator.keypad.topLeftButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftButton.getText().toString()));
        binding.calculator.keypad.topLeftDownOneButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownOneButton.getText().toString()));
        binding.calculator.keypad.topLeftDownTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownTwoButton.getText().toString()));
        binding.calculator.keypad.topLeftDownThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownThreeButton.getText().toString()));
        binding.calculator.keypad.topLeftDownFourButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownFourButton.getText().toString()));
        binding.calculator.keypad.bottomLeftButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.bottomLeftButton.getText().toString()));

        binding.calculator.keypad.topLeftRightOneButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftRightOneButton.getText().toString()));
        binding.calculator.keypad.topLeftDownOneRightOneButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownOneRightOneButton.getText().toString()));
        binding.calculator.keypad.topLeftDownTwoRightOneButton.setOnClickListener(v ->buttonPressed(binding.calculator.keypad.topLeftDownTwoRightOneButton.getText().toString()));
        binding.calculator.keypad.topLeftDownThreeRightOneButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownThreeRightOneButton.getText().toString()));
        binding.calculator.keypad.topLeftDownFourRightOneButton.setOnClickListener(v ->buttonPressed(binding.calculator.keypad.topLeftDownFourRightOneButton.getText().toString()));
        binding.calculator.keypad.bottomLeftRightOneButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.bottomLeftRightOneButton.getText().toString()));

        binding.calculator.keypad.topLeftRightTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftRightTwoButton.getText().toString()));
        binding.calculator.keypad.topLeftDownOneRightTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownOneRightTwoButton.getText().toString()));
        binding.calculator.keypad.topLeftDownTwoRightTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownTwoRightTwoButton.getText().toString()));
        binding.calculator.keypad.topLeftDownThreeRightTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownThreeRightTwoButton.getText().toString()));
        binding.calculator.keypad.topLeftDownFourRightTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownFourRightTwoButton.getText().toString()));
        binding.calculator.keypad.bottomLeftRightTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.bottomLeftRightTwoButton.getText().toString()));

        binding.calculator.keypad.topLeftRightThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftRightThreeButton.getText().toString()));
        binding.calculator.keypad.topLeftDownOneRightThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownOneRightThreeButton.getText().toString()));
        binding.calculator.keypad.topLeftDownTwoRightThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownTwoRightThreeButton.getText().toString()));
        binding.calculator.keypad.topLeftDownThreeRightThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownThreeRightThreeButton.getText().toString()));
        binding.calculator.keypad.topLeftDownFourRightThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topLeftDownFourRightThreeButton.getText().toString()));
        binding.calculator.keypad.bottomLeftRightThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.bottomLeftRightThreeButton.getText().toString()));
        binding.calculator.keypad.topRightButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topRightButton.getText().toString()));
        binding.calculator.keypad.topRightDownOneButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topRightDownOneButton.getText().toString()));
        binding.calculator.keypad.topRightDownTwoButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topRightDownTwoButton.getText().toString()));
        binding.calculator.keypad.topRightDownThreeButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topRightDownThreeButton.getText().toString()));
        binding.calculator.keypad.topRightDownFourButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.topRightDownFourButton.getText().toString()));
        binding.calculator.keypad.bottomRightButton.setOnClickListener(v -> buttonPressed(binding.calculator.keypad.bottomRightButton.getText().toString()));
    }

    protected void buttonPressed(String text){
        switch(text){
            case "←":     backSpace();   break;
            case "nCr":    addToInput("C");   break;
            case "nPr":    addToInput("P");   break;
            default:       addToInput(text);  break;
        }
    }

    protected void addToInput(String text){
        int caret = binding.calculator.inputText.getSelectionEnd();
        String oldInputText = binding.calculator.inputText.getText().toString();
        String newInputText = oldInputText.substring(0, caret)+text+oldInputText.substring(caret);
        if (newInputText.length() > 0) {
            binding.calculator.inputText.setText(newInputText);
            binding.calculator.inputText.setSelection(caret + text.length());

            String output = Engine.evaluateDF(newInputText);
            if (output.equals("= NaN")) {
                binding.calculator.outputText.setTextColor(Color.LTGRAY);
            } else {
                binding.calculator.outputText.setText(output);
                binding.calculator.outputText.setTextColor(Color.BLACK);
            }
        }
    }

    protected void backSpace(){
        String oldInputText = binding.calculator.inputText.getText().toString();
        if (oldInputText.length() > 0) {
            int caret = binding.calculator.inputText.getSelectionEnd();
            String newInputText = oldInputText.substring(0, caret - 1) + oldInputText.substring(caret);
            binding.calculator.inputText.setText(newInputText);
            binding.calculator.inputText.setSelection(caret - 1);

            if (newInputText.length() > 0) {
                String output = Engine.evaluateDF(newInputText);
                if (output.equals("= NaN")) {
                    binding.calculator.outputText.setTextColor(Color.LTGRAY);
                } else {
                    binding.calculator.outputText.setText(output);
                    binding.calculator.outputText.setTextColor(Color.BLACK);
                }
            }
            else{
                binding.calculator.outputText.setText("");
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


