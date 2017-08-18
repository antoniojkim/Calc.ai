package com.example.antoniok.CalcAI;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antoniok.CalcAI.databinding.ActivityMainBinding;

import Math_Evaluation_Library.Engine.Engine;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Button[] buttons;

    private int activityNum;

    private GestureDetectorCompat detector;
    private MyGestureHandler handler;
    private LayoutInflater inflater;

    public static final String PUBLIC_KEY = "com.example.prathamdesai.calc.AI.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.activityNum = 0;

        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        handler = new MyGestureHandler();

        detector = new GestureDetectorCompat(this, handler);

        buttons = new Button[]{

                binding.calculator.keypad.topLeftButton,
                binding.calculator.keypad.topLeftDownOneButton,
                binding.calculator.keypad.topLeftDownTwoButton,
                binding.calculator.keypad.topLeftDownThreeButton,
//                binding.calculator.keypad.topLeftDownFourButton,
                binding.calculator.keypad.bottomLeftButton,

                binding.calculator.keypad.topLeftRightOneButton,
                binding.calculator.keypad.topLeftDownOneRightOneButton,
                binding.calculator.keypad.topLeftDownTwoRightOneButton,
                binding.calculator.keypad.topLeftDownThreeRightOneButton,
//                binding.calculator.keypad.topLeftDownFourRightOneButton,
                binding.calculator.keypad.bottomLeftRightOneButton,

                binding.calculator.keypad.topLeftRightTwoButton,
                binding.calculator.keypad.topLeftDownOneRightTwoButton,
                binding.calculator.keypad.topLeftDownTwoRightTwoButton,
                binding.calculator.keypad.topLeftDownThreeRightTwoButton,
//                binding.calculator.keypad.topLeftDownFourRightTwoButton,
                binding.calculator.keypad.bottomLeftRightTwoButton,

                binding.calculator.keypad.topLeftRightThreeButton,
                binding.calculator.keypad.topLeftDownOneRightThreeButton,
                binding.calculator.keypad.topLeftDownTwoRightThreeButton,
                binding.calculator.keypad.topLeftDownThreeRightThreeButton,
//                binding.calculator.keypad.topLeftDownFourRightThreeButton,
                binding.calculator.keypad.bottomLeftRightThreeButton,

                binding.calculator.keypad.topRightButton,
                binding.calculator.keypad.topRightDownOneButton,
                binding.calculator.keypad.topRightDownTwoButton,
                binding.calculator.keypad.topRightDownThreeButton,
//                binding.calculator.keypad.topRightDownFourButton,
                binding.calculator.keypad.bottomRightButton
        };

        binding.calculator.inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = binding.calculator.inputText.getText().toString();
                if (inputText.length() > 0) {
                    int caret = binding.calculator.inputText.getSelectionEnd();
                    for (int a = 0; a<replaceable.length;){
                        int index = inputText.indexOf(replaceable[a]);
                        if (index != -1){
                            inputText = inputText.substring(0, index)+replaceWith[a]+inputText.substring(index+replaceable[a].length());
                            caret = index+replaceWith[a].length();
                        }
                        else{
                            a++;
                        }
                    }
                    if (!inputText.equals(binding.calculator.inputText.getText().toString().trim())){
                        binding.calculator.inputText.setText(inputText);
                        binding.calculator.inputText.setSelection(caret);
                    }

                    String output = Engine.evaluateDF(inputText);
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

            @Override
            public void afterTextChanged(Editable s) {}
        });

        initiateButtonPressed(buttons);

    }

    public void startAnActivity(){

        int num = getActivityNum();
        Intent intent;
        if (num == 1) {
            intent = new Intent(this, TrigPage.class);
            /*EditText output = (EditText) binding.calculator.outputText;
            String output_text = output.getText().toString();
            TextView input = (TextView) binding.calculator.inputText;
            String input_text = input.getText().toString();
            intent.putExtra(PUBLIC_KEY, new String[]{input_text, output_text});*/
        } else{
            intent = null;
        }

        startActivity(intent);

    }

    protected void initiateButtonPressed(Button[] buttons){

        for (Button button : buttons){
            button.setOnClickListener(v -> buttonPressed(button.getText().toString()));
            button.setOnLongClickListener(v -> buttonLongPressed(button.getText().toString()));
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
    protected boolean buttonLongPressed(String text){
        switch(text){
            case "←":
                binding.calculator.inputText.setText("");
                binding.calculator.outputText.setText("");
                break;
            default:       addToInput(text);  break;
        }
        return true;
    }

    public static final String equalsNaN = "= NaN";
    public static final int equalsNaNColor = Color.LTGRAY;
    public static final int validColor = Color.BLACK;

    public static final String[] replaceable = {"*", "××"};
    public static final String[] replaceWith = {"×", "^"};

    protected void addToInput(String text){
        int caret = binding.calculator.inputText.getSelectionEnd();
        String oldInputText = binding.calculator.inputText.getText().toString();
        String newInputText = oldInputText.substring(0, caret)+text+oldInputText.substring(caret);

        if (newInputText.length() > 0) {
            binding.calculator.inputText.setText(newInputText);
            caret += text.length();
            binding.calculator.inputText.setSelection(caret);

            for (int a = 0; a<replaceable.length;){
                int index = newInputText.indexOf(replaceable[a]);
                if (index != -1){
                    newInputText = newInputText.substring(0, index)+replaceWith[a]+newInputText.substring(index+replaceable[a].length());
                    caret = index+replaceWith[a].length();
                }
                else{
                    a++;
                }
            }
            if (!newInputText.equals(binding.calculator.inputText.getText().toString().trim())){
                binding.calculator.inputText.setText(newInputText);
                binding.calculator.inputText.setSelection(caret);
            }

            String output = Engine.evaluateDF(newInputText);
            if (output.equals(equalsNaN)) {
                binding.calculator.outputText.setTextColor(equalsNaNColor);
            } else {
                binding.calculator.outputText.setText(output);
                binding.calculator.outputText.setTextColor(validColor);
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
                if (output.equals(equalsNaN)) {
                    binding.calculator.outputText.setTextColor(equalsNaNColor);
                } else {
                    binding.calculator.outputText.setText(output);
                    binding.calculator.outputText.setTextColor(validColor);
                }
            }
            else{
                binding.calculator.outputText.setText("");
            }
        }
    }

    private String previous = "";
    protected void updatedOutput(EditText input){
        String inputText = input.getText().toString();
        if (inputText.length() > 0) {
            if (!inputText.equals("previous")) {
                int caret = input.getSelectionEnd();

                String output = Engine.evaluateDF(inputText);
                if (output.equals("= NaN")) {
                    binding.calculator.outputText.setTextColor(Color.LTGRAY);
                } else {
                    binding.calculator.outputText.setText(output);
                    binding.calculator.outputText.setTextColor(Color.BLACK);
                }
            }
        }
        else{
            binding.calculator.outputText.setText("");
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

            String swipe_message = "";

            if (e1.getY() > 605 && e2.getY() <= 825) {

                swipe_message = "Trigonometric";

                setActivityNum(1);

                startAnActivity();

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

                //setActivityNum(1);

                //startAnActivity();
                //View page = inflater.inflate(R.layout.another_another_page, null);

                //setContentView(page);

            }else if (e1.getY() > 1265 && e2.getY() <= 1485){

                swipe_message = "Numbers";

            }else if (e1.getY() > 1485 && e2.getY() <= 1705){

                swipe_message = "Integration";

            }else if (e1.getY() > 1705 && e2.getY() <= 1920){

                swipe_message = "Main";

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


