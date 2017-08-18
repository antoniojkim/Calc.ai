package com.example.antoniok.CalcAI;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import Math_Evaluation_Library.Engine.Engine;

import com.example.antoniok.CalcAI.databinding.ActivityTrigPageBinding;

public class TrigPage extends AppCompatActivity {

    private ActivityTrigPageBinding binding;

    private Button[] buttons;

    private int activityNum;

    private GestureDetectorCompat detector;
    private TrigPage.MyGestureHandler handler;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.activityNum = 1;

        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_trig_page);

        //Intent intent = getIntent();

        //String[] messages = intent.getStringArrayExtra(MainActivity.PUBLIC_KEY);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_trig_page);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        handler = new MyGestureHandler();

        detector = new GestureDetectorCompat(this, handler);


        buttons = new Button[]{
                binding.trigCalculator.trigKeypad.zeroZeroTrig,
                binding.trigCalculator.trigKeypad.zeroOneTrig,
                binding.trigCalculator.trigKeypad.zeroTwoTrig,
                binding.trigCalculator.trigKeypad.zeroThreeTrig,

                binding.trigCalculator.trigKeypad.oneZeroTrig,
                binding.trigCalculator.trigKeypad.oneOneTrig,
                binding.trigCalculator.trigKeypad.oneTwoTrig,
                binding.trigCalculator.trigKeypad.oneThreeTrig,

                binding.trigCalculator.trigKeypad.twoZeroTrig,
                binding.trigCalculator.trigKeypad.twoOneTrig,
                binding.trigCalculator.trigKeypad.twoTwoTrig,
                binding.trigCalculator.trigKeypad.twoThreeTrig,

                binding.trigCalculator.trigKeypad.threeZeroTrig,
                binding.trigCalculator.trigKeypad.threeOneTrig,
                binding.trigCalculator.trigKeypad.threeTwoTrig,
                binding.trigCalculator.trigKeypad.threeThreeTrig,

                binding.trigCalculator.trigKeypad.fourZeroTrig,
                binding.trigCalculator.trigKeypad.fourOneTrig,
                binding.trigCalculator.trigKeypad.fourTwoTrig,
                binding.trigCalculator.trigKeypad.fourThreeTrig
        };

        binding.trigCalculator.inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = binding.trigCalculator.inputText.getText().toString();
                if (inputText.length() > 0) {
                    String output = Engine.evaluateDF(inputText);
                    if (output.equals("= NaN")) {
                        binding.trigCalculator.outputText.setTextColor(Color.LTGRAY);
                    } else {
                        binding.trigCalculator.outputText.setText(output);
                        binding.trigCalculator.outputText.setTextColor(Color.BLACK);
                    }
                }
                else{
                    binding.trigCalculator.outputText.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //binding.calculator.inputText.setText(messages[0]);
        //binding.calculator.outputText.setText(messages[1]);

        initiateButtonPressed(buttons);
    }
    public void startAnActivity(){

        int num = getActivityNum();
        Intent intent;
        if (num == 0) {
            intent = new Intent(this, MainActivity.class);
        }
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

                setActivityNum(1);

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

                //setActivityNum(3);

                //startAnActivity();
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
    public static final String equalsNaN = "= NaN";
    public static final int equalsNaNColor = Color.LTGRAY;
    public static final int validColor = Color.BLACK;

    protected void addToInput(String text){
        int caret = binding.trigCalculator.inputText.getSelectionEnd();
        String oldInputText = binding.trigCalculator.inputText.getText().toString();
        String newInputText = oldInputText.substring(0, caret)+text+oldInputText.substring(caret);

        if (newInputText.length() > 0) {
            binding.trigCalculator.inputText.setText(newInputText);
            binding.trigCalculator.inputText.setSelection(caret + text.length());

            String output = Engine.evaluateDF(newInputText);
            if (output.equals(equalsNaN)) {
                binding.trigCalculator.outputText.setTextColor(equalsNaNColor);
            } else {
                binding.trigCalculator.outputText.setText(output);
                binding.trigCalculator.outputText.setTextColor(validColor);
            }
        }
    }

    protected void backSpace(){
        String oldInputText = binding.trigCalculator.inputText.getText().toString();
        if (oldInputText.length() > 0) {
            int caret = binding.trigCalculator.inputText.getSelectionEnd();
            String newInputText = oldInputText.substring(0, caret - 1) + oldInputText.substring(caret);
            binding.trigCalculator.inputText.setText(newInputText);
            binding.trigCalculator.inputText.setSelection(caret - 1);

            if (newInputText.length() > 0) {
                String output = Engine.evaluateDF(newInputText);
                if (output.equals(equalsNaN)) {
                    binding.trigCalculator.outputText.setTextColor(equalsNaNColor);
                } else {
                    binding.trigCalculator.outputText.setText(output);
                    binding.trigCalculator.outputText.setTextColor(validColor);
                }
            }
            else{
                binding.trigCalculator.outputText.setText("");
            }
        }
    }

    protected void initiateButtonPressed(Button[] buttons){

        for (Button button : buttons){
            button.setOnClickListener(v -> buttonPressed(button.getText().toString()));
        }

    }

    protected void buttonPressed(String text){
        switch(text){
            case "←":     backSpace();   break;
            case "nCr":    addToInput("C");   break;
            case "nPr":    addToInput("P");   break;
            default:       addToInput(text);  break;
        }
    }

    public void setActivityNum(int num){

        this.activityNum = num;
    }

    public int getActivityNum(){

        return this.activityNum;
    }

}
