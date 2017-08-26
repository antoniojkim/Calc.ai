package com.example.antoniok.CalcAI;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.antoniok.CalcAI.CalcInput.InputFragment;
import com.example.antoniok.CalcAI.CalcInput.KeypadFragment;
import com.example.antoniok.CalcAI.CalcInput.LogFragment;
import com.example.antoniok.CalcAI.CalcInput.NumTheoryFragment;
import com.example.antoniok.CalcAI.CalcInput.TrigFragment;
import com.example.antoniok.CalcAI.databinding.CalculatorBinding;

/**
 * Created by Antonio on 2017-08-21.
 */

public class Calculator extends FragmentActivity {

    private CalculatorBinding calculator;

    private InputPagerAdapter adapter;

    private ViewPager iPager;

//    private GestureDetectorCompat detector;
//
//    private TouchEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        calculator = DataBindingUtil.setContentView(this, R.layout.calculator);

        adapter = new InputPagerAdapter(getSupportFragmentManager());

        iPager = (ViewPager)findViewById(R.id.CalcInputPager);
        iPager.setAdapter(adapter);

//        listener = new TouchEventListener();
//
//        detector = new GestureDetectorCompat(this, listener);

        //iPager.setCurrentItem(0);
        // Use Touch tracking to set the current item.

    }

    public class InputPagerAdapter extends FragmentPagerAdapter {

//        private static final int KEYPAD_ID = 0;
//        private static final int TRIGPAD_ID = 1;
//        private static final int LOGPAD_ID = 2;
//        private static final int NUM_THEORY_ID = 3;
        private int NUM_PAGES = 4;
//
//        private int id = -1;
//        private int nextPage = -1;
//        private KeypadFragment keypadFragment;
//        private TrigFragment trigFragment;
//        private LogFragment logFragment;
//        private NumTheoryFragment numTheoryFragment;

        private InputFragment[] fragments;

        public InputPagerAdapter(FragmentManager fm) {
            super(fm);
//            keypadFragment = new KeypadFragment();
//            trigFragment = new TrigFragment();
//            logFragment = new LogFragment();
//            numTheoryFragment = new NumTheoryFragment();
            fragments = new InputFragment[]{
//                    keypadFragment,
//                    trigFragment,
//                    logFragment,
//                    numTheoryFragment

                    new KeypadFragment(),
                    new TrigFragment(),
                    new LogFragment(),
                    new NumTheoryFragment()
            };
            for (InputFragment fragment : fragments){
                fragment.setInputText(calculator.inputText);
                fragment.setOutputText(calculator.outputText);
            }
        }

        @Override
        public Fragment getItem(int i) {
            return fragments[i];
//            System.out.println("ID - position:  "+i);
//            System.out.println("ID - current:  "+id);
//            System.out.println("ID - next:  "+nextPage);
//            if (nextPage != -1){
//                id = nextPage;
//                switch(id){
//                    case KEYPAD_ID:     return keypadFragment;
//                    case TRIGPAD_ID:    return trigFragment;
//                    case LOGPAD_ID:     return logFragment;
//                    case NUM_THEORY_ID: return numTheoryFragment;
//                    default:            break;
//                }
//                nextPage = -1;
//            }
//            if (id == -1){
//                nextPage = KEYPAD_ID;
//                return inputFragment;
//            }
////            if (id == KEYPAD_ID){
////                id = TRIGPAD_ID;
////                return trigFragment;
////            }
//            id = KEYPAD_ID;
//            return keypadFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

//    private class TouchEventListener extends GestureDetector.SimpleOnGestureListener{
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2,
//                                float distanceX, float distanceY) {
//            float x1 = e1.getX(), x2 = e2.getX(), y1 = e1.getY(), y2 = e2.getY();
//            if (x1 < x2){
//                adapter.nextPage = adapter.KEYPAD_ID;
//            }
//            else{
//                int[][] coordinates = null;
//                if (adapter.id == adapter.KEYPAD_ID){
//                    coordinates = adapter.keypadFragment.getButtonCoordinates();
//                    if (y1 > coordinates[0][1] && y1 < coordinates[1][1]){
//                        adapter.nextPage = adapter.TRIGPAD_ID;
//                    }
//                    else if (y1 > coordinates[1][1] && y1 < coordinates[2][1]){
//                        adapter.nextPage = adapter.LOGPAD_ID;
//                    }
//                    else if (y1 > coordinates[2][1] && y1 < coordinates[3][1]){
//                        adapter.nextPage = adapter.NUM_THEORY_ID;
//                    }
//                }
//            }
//            return true;
//        }
//
//    }

}
