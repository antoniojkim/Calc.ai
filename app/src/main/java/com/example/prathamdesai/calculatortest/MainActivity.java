package com.example.prathamdesai.calculatortest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.text.DecimalFormat;
import com.example.prathamdesai.calculatortest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private double num1 = Double.NaN;
    private double num2;

    private static final char add = '+';
    private static final char subtract = '-';
    private static final char multiply = '*';
    private static final char divide = '/';

    private char operation;
    DecimalFormat format = new DecimalFormat("0.000");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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
            num2 = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            if(operation == add){
                num1 = this.num1 + num2;
                //operation = null;
            }
            else if(operation == subtract){
                num1 = this.num1 - num2;
                //operation = null;
            }
            else if(operation == multiply){
                num1 = this.num1 * num2;
                //operation = null;
            }
            else if(operation == divide){
                num1 = this.num1 / num2;
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


