package com.vinit.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends Activity {

    EditText et;
    TextView tvtip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et =(EditText) findViewById(R.id.editText);
        tvtip = (TextView) findViewById(R.id.tiplabel);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Btn clicked
    //Get value from EditText
    //if it's not null
    //call method calculate tip and set value of TextView with tip value


        public void onClickBtnTen(View v){

            if(et.getText().toString().isEmpty() || et.getText().toString() == null){
                //editText is empty, don't do anything
            }else{
                int amtAdded = Integer.parseInt(et.getText().toString());
                this.calculateTip(amtAdded, 10);

            }
        }


        public void onClickBtnTwenty(View v){

            if(et.getText().toString().isEmpty() || et.getText().toString() == null){
                //editText is empty, don't do anything
            }else{
                int amtAdded = Integer.parseInt(et.getText().toString());
                this.calculateTip(amtAdded, 20);

            }
        }


    public void onClickBtnThirty(View v){

        if(et.getText().toString().isEmpty()|| et.getText().toString() == null){
            //editText is empty, don't do anything
        }else{
            int amtAdded = Integer.parseInt(et.getText().toString());
            this.calculateTip(amtAdded, 30);

        }
    }

    public void calculateTip(int amtAdded, int tipvalue){


        int totalTip =  amtAdded * tipvalue/100;
        tvtip.setText("Tip is:   ".concat(Integer.toString(totalTip)));

    }



}
