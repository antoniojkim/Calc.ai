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
import com.example.antoniok.CalcAI.databinding.TrigKeypadBinding;

/**
 * Created by Antonio on 2017-08-21.
 */

public class TrigFragment extends InputFragment {

    private TrigKeypadBinding trigKeypad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ARG_OBJECT = "TrigFragment";

        trigKeypad = DataBindingUtil.inflate(inflater, R.layout.trig_keypad, container, false);

        initializeButtons(inputText, outputText);

        return trigKeypad.getRoot();
    }


    @Override
    public Button[] getButtons(){
        return new Button[]{
                trigKeypad.zeroZeroTrig,
                trigKeypad.zeroOneTrig,
                trigKeypad.zeroTwoTrig,
                trigKeypad.zeroThreeTrig,

                trigKeypad.oneZeroTrig,
                trigKeypad.oneOneTrig,
                trigKeypad.oneTwoTrig,
                trigKeypad.oneThreeTrig,

                trigKeypad.twoZeroTrig,
                trigKeypad.twoOneTrig,
                trigKeypad.twoTwoTrig,
                trigKeypad.twoThreeTrig,

                trigKeypad.threeZeroTrig,
                trigKeypad.threeOneTrig,
                trigKeypad.threeTwoTrig,
                trigKeypad.threeThreeTrig,

                trigKeypad.fourZeroTrig,
                trigKeypad.fourOneTrig,
                trigKeypad.fourTwoTrig,
                trigKeypad.fourThreeTrig,
        };
    }

    @Override
    protected void additionalButtonPressedCases(String text, EditText input, TextView output){
        switch (text){
            case "π":       addToInput("π", input, output);    return;
            case "Deg":     addToInput("°", input, output);    return;
            case "Rad":     addToInput("ʳ", input, output);    return;
            default:        break;
        }
        if (text.endsWith("⁻¹")){
            text = "arc"+text.substring(0, text.length()-2);
        }
        addToInput(text+"(", input, output);
    }

}
