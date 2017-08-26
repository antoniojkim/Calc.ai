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
import com.example.antoniok.CalcAI.databinding.LogKeypadBinding;

/**
 * Created by Antonio on 2017-08-21.
 */

public class LogFragment extends InputFragment {

    private LogKeypadBinding logKeypad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ARG_OBJECT = "LogFragment";

        logKeypad = DataBindingUtil.inflate(inflater, R.layout.log_keypad, container, false);

        initializeButtons(inputText, outputText);

        return logKeypad.getRoot();
    }


    @Override
    public Button[] getButtons(){
        return new Button[]{
                logKeypad.zeroZeroLog,
                logKeypad.zeroOneLog,
                logKeypad.zeroTwoLog,
                logKeypad.zeroThreeLog,

                logKeypad.oneZeroLog,
                logKeypad.oneOneLog,
                logKeypad.oneTwoLog,
                logKeypad.oneThreeLog,

                logKeypad.twoZeroLog,
                logKeypad.twoOneLog,
                logKeypad.twoTwoLog,
                logKeypad.twoThreeLog,

                logKeypad.threeZeroLog,
                logKeypad.threeOneLog,
                logKeypad.threeTwoLog,
                logKeypad.threeThreeLog,

                logKeypad.fourZeroLog,
                logKeypad.fourOneLog,
                logKeypad.fourTwoLog,
                logKeypad.fourThreeLog,
        };
    }

    @Override
    protected void additionalButtonPressedCases(String text, EditText input, TextView output){
        if (text.equals("e")){
            addToInput(text, input, output);    return;
        }
        if (text.endsWith("⁻¹")){
            text = "arc"+text.substring(0, text.length()-2);
        }
        else if (text.endsWith("₁₀")){
            text = text.substring(0, text.length()-2);
        }
        addToInput(text+"(", input, output);
    }

}
