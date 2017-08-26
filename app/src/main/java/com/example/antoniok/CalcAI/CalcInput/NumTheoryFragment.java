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
import com.example.antoniok.CalcAI.databinding.NumTheoryKeypadBinding;

/**
 * Created by Antonio on 2017-08-21.
 */

public class NumTheoryFragment extends InputFragment {

    private NumTheoryKeypadBinding numTheoryKeypad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ARG_OBJECT = "NumTheoryFragment";

        numTheoryKeypad = DataBindingUtil.inflate(inflater, R.layout.num_theory_keypad, container, false);

        initializeButtons(inputText, outputText);

        return numTheoryKeypad.getRoot();
    }


    @Override
    public Button[] getButtons(){
        return new Button[]{
                numTheoryKeypad.zeroZeroNumTheory,
                numTheoryKeypad.zeroOneNumTheory,
                numTheoryKeypad.zeroTwoNumTheory,
                numTheoryKeypad.zeroThreeNumTheory,

                numTheoryKeypad.oneZeroNumTheory,
                numTheoryKeypad.oneOneNumTheory,
                numTheoryKeypad.oneTwoNumTheory,
                numTheoryKeypad.oneThreeNumTheory,

                numTheoryKeypad.twoZeroNumTheory,
                numTheoryKeypad.twoOneNumTheory,
                numTheoryKeypad.twoTwoNumTheory,
                numTheoryKeypad.twoThreeNumTheory,

                numTheoryKeypad.threeZeroNumTheory,
                numTheoryKeypad.threeOneNumTheory,
                numTheoryKeypad.threeTwoNumTheory,
                numTheoryKeypad.threeThreeNumTheory,

                numTheoryKeypad.fourZeroNumTheory,
                numTheoryKeypad.fourOneNumTheory,
                numTheoryKeypad.fourTwoNumTheory,
                numTheoryKeypad.fourThreeNumTheory,
        };
    }

    @Override
    protected void additionalButtonPressedCases(String text, EditText input, TextView output){
        addToInput(text+"(", input, output);
    }

}
