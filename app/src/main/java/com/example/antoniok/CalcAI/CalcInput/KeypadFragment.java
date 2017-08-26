package com.example.antoniok.CalcAI.CalcInput;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.antoniok.CalcAI.R;
import com.example.antoniok.CalcAI.databinding.KeypadBinding;

/**
 * Created by Antonio on 2017-08-21.
 */

public class KeypadFragment extends InputFragment {

    private KeypadBinding keypad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ARG_OBJECT = "KeypadFragment";

        keypad = DataBindingUtil.inflate(inflater, R.layout.keypad, container, false);

        initializeButtons(inputText, outputText);

        return keypad.getRoot();
    }


    @Override
    public Button[] getButtons(){
        return new Button[]{
                keypad.topLeftButton,
                keypad.topLeftDownOneButton,
                keypad.topLeftDownTwoButton,
                keypad.topLeftDownThreeButton,
                keypad.bottomLeftButton,

                keypad.topLeftRightOneButton,
                keypad.topLeftDownOneRightOneButton,
                keypad.topLeftDownTwoRightOneButton,
                keypad.topLeftDownThreeRightOneButton,
                keypad.bottomLeftRightOneButton,

                keypad.topLeftRightTwoButton,
                keypad.topLeftDownOneRightTwoButton,
                keypad.topLeftDownTwoRightTwoButton,
                keypad.topLeftDownThreeRightTwoButton,
                keypad.bottomLeftRightTwoButton,

                keypad.topLeftRightThreeButton,
                keypad.topLeftDownOneRightThreeButton,
                keypad.topLeftDownTwoRightThreeButton,
                keypad.topLeftDownThreeRightThreeButton,
                keypad.bottomLeftRightThreeButton,

                keypad.topRightButton,
                keypad.topRightDownOneButton,
                keypad.topRightDownTwoButton,
                keypad.topRightDownThreeButton,
                keypad.bottomRightButton
        };
    }
    @Override
    public int[][] getButtonCoordinates(){
        Button[] relativeButtons = new Button[]{
                keypad.topRightButton,
                keypad.topRightDownOneButton,
                keypad.topRightDownTwoButton,
                keypad.topRightDownThreeButton,
                keypad.bottomRightButton
        };
        int[][] coordinates = new int[relativeButtons.length][2];
        for (int i = 0; i<coordinates.length; i++){
            relativeButtons[i].getLocationInWindow(coordinates[i]);
        }
        return coordinates;
    }

    @Override
    protected void additionalButtonPressedCases(String text, EditText input, TextView output){
        switch(text){
            case "nCr":    addToInput("C", input, output);   break;
            case "nPr":    addToInput("P", input, output);   break;
            default:       addToInput(text, input, output);  break;
        }
    }

}
