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
import android.view.View;
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
            int[] rowOne = new int[2];
            int[] rowTwo = new int[2];
            int[] rowThree = new int[2];
            int[] rowFour = new int[2];
            int[] rowFive = new int[2];

            View layout = findViewById(R.id.trig_calculator);
            View keypad = layout.findViewById(R.id.trig_keypad);

            View rowOneButton = keypad.findViewById(R.id.zero_zero_trig);
            View rowTwoButton = keypad.findViewById(R.id.one_zero_trig);
            View rowThreeButton = keypad.findViewById(R.id.two_zero_trig);
            View rowFourButton = keypad.findViewById(R.id.three_zero_trig);
            View rowFiveButton = keypad.findViewById(R.id.four_zero_trig);

            // row one button position
            rowOneButton.getLocationInWindow(rowOne);
            int y1 = rowOne[1];

            // row two button position
            rowTwoButton.getLocationInWindow(rowTwo);
            int y2 = rowTwo[1];

            // row three button position
            rowThreeButton.getLocationInWindow(rowThree);
            int y3 = rowThree[1];

            // row four button positiin
            rowFourButton.getLocationInWindow(rowFour);
            int y4 = rowFour[1];

            // row five button position
            rowFiveButton.getLocationInWindow(rowFive);
            int y5 = rowFive[1];

            if (e1.getY() >= y1 && e2.getY() < y2) {

                swipe_message = "Trigonometric";

                setActivityNum(1);

                //View page = inflater.inflate(R.layout.page, null);

                //setContentView(page);

            } else if (e1.getY() >= y2 && e2.getY() < y3) {

                swipe_message = "Logarithmic";

                //setActivityNum(2);

                //startAnActivity();
                //View page = inflater.inflate(R.layout.another_page, null);

                //setContentView(page);

            } else if (e1.getY() >= y3 && e2.getY() < y4) {

                swipe_message = "Misc";

                //setActivityNum(1);

                //startAnActivity();
                //View page = inflater.inflate(R.layout.another_another_page, null);

                //setContentView(page);

            }else if (e1.getY() >= y4 && e2.getY() < y5){

                swipe_message = "Number theory";
            }else{
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
