package com.example.antoniok.CalcAI.CalcInput;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.antoniok.CalcAI.R;
import com.example.antoniok.CalcAI.databinding.KeypadBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Math_Evaluation_Library.Engine.Engine;

/**
 * Created by Antonio on 2017-08-21.
 */

public class InputFragment extends Fragment {
    public static String ARG_OBJECT = "KeypadFragment";

    private KeypadBinding keypad;

    protected EditText inputText;
    protected TextView outputText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        keypad = DataBindingUtil.inflate(inflater, R.layout.keypad, container, false);
        return keypad.getRoot();
    }

    private boolean initialized = false;
    public void initializeButtons(){
        initializeButtons(inputText, outputText);
    }
        public void initializeButtons(EditText input, TextView output){
        if (!initialized){
            initializeButtons(getButtons(), input, output);
            initialized = true;
        }
    }
    public void initializeButtons(Button[] buttons, EditText input, TextView output){
        if (!initialized){
            for (Button button : buttons){
                button.setOnClickListener(v -> buttonPressed(button.getText().toString(), input, output));
                button.setOnLongClickListener(v -> buttonLongPressed(button.getText().toString(), input, output));
            }
            initialized = true;
        }
    }

    protected Button[] getButtons(){
        return new Button[]{};
    }
    protected int[][] getButtonCoordinates(){
        return new int[][]{};
    }

    protected void buttonPressed(String text, EditText input, TextView output){
        keypadInput = true;
        switch(text){
            case "←":      backSpace(input, output);                           break;
            case "/":      addToInput(text, input, output);                    break;
            case "×":      addToInput(text, input, output);                    break;
            case "+":      addToInput(text, input, output);                    break;
            case "-":      addToInput(text, input, output);                    break;
            default:       additionalButtonPressedCases(text, input, output);  break;
        }
        keypadInput = false;
    }
    protected void additionalButtonPressedCases(String text, EditText input, TextView output){
        addToInput(text, input, output);
    }
    protected boolean buttonLongPressed(String text, EditText input, TextView output){
        keypadInput = true;
        switch(text){
            case "←":
                input.setText("");
                output.setText("");
                break;
            default:
                additionalButtonLongPressedCases(text, input, output);
                break;
        }
        keypadInput = false;
        return true;
    }
    protected void additionalButtonLongPressedCases(String text, EditText input, TextView output){
        addToInput(text, input, output);
    }

    protected static final String equalsNaN = "= NaN";
    protected static final int equalsNaNColor = Color.LTGRAY;
    protected static final int validColor = Color.BLACK;

    protected static final List<String> replaceable = new ArrayList<>(Arrays.asList("*", "××"));
    protected static final List<String> replaceWith = new ArrayList<>(Arrays.asList("×", "^"));

    protected static boolean keypadInput = false;

    protected void addToInput(String text, EditText input, TextView output){
        int caret = input.getSelectionEnd();
        String oldInputText = input.getText().toString();
        String newInputText = oldInputText.substring(0, caret)+text+oldInputText.substring(caret);

        if (newInputText.length() > 0) {
            input.setText(newInputText);
            input.setSelection(caret + text.length());
//
            for (int a = 0; a<replaceable.size();){
                int index = newInputText.indexOf(replaceable.get(a));
                if (index != -1){
                    newInputText = newInputText.substring(0, index)+replaceWith.get(a)+newInputText.substring(index+replaceable.get(a).length());
                    caret = index+replaceWith.get(a).length();
                }
                else{
                    a++;
                }
            }

            if (!newInputText.equals(input.getText().toString().trim())){
                input.setText(newInputText);
                input.setSelection(caret);
            }

            String evaluated = Engine.evaluateDF(newInputText);
            if (evaluated.equals(equalsNaN)) {
                output.setTextColor(equalsNaNColor);
            } else {
                output.setText(evaluated);
                output.setTextColor(validColor);
            }
        }
    }

    protected void backSpace(EditText input, TextView output){
        String oldInputText = input.getText().toString();
        if (oldInputText.length() > 0) {
            int caret = input.getSelectionEnd();
            String newInputText = oldInputText.substring(0, caret - 1) + oldInputText.substring(caret);
            input.setText(newInputText);
            input.setSelection(caret - 1);

            if (newInputText.length() > 0) {
                String evaluated = Engine.evaluateDF(newInputText);
                if (output.equals(equalsNaN)) {
                    output.setTextColor(equalsNaNColor);
                } else {
                    output.setText(evaluated);
                    output.setTextColor(validColor);
                }
            }
            else{
                output.setText("");
            }
        }
    }

    public EditText getInputText() {
        return inputText;
    }

    public void setInputText(EditText inputText) {
        this.inputText = inputText;
    }

    public TextView getOutputText() {
        return outputText;
    }

    public void setOutputText(TextView outputText) {
        this.outputText = outputText;
    }
}
