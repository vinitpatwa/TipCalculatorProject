package com.vinit.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends Activity {

    EditText et;
    TextView tvtip;
    TextView tvGrandTotal;
    Double totalTip = 0.0 ;
    Double grandTotal = 0.0;
    TextView tvAfterSplitPerPerson;
    EditText etTipOther;
    EditText etSplitOther;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et =(EditText) findViewById(R.id.editText);
        tvtip = (TextView) findViewById(R.id.tiplabel);
        tvGrandTotal = (TextView) findViewById(R.id.tvGrandTotal);
        tvAfterSplitPerPerson = (TextView) findViewById(R.id.tvAfterSplitPerPerson);
        etTipOther = (EditText) findViewById(R.id.etTipOther);
        etTipOther.setOnEditorActionListener(new DoneOnEditorActionListener());

        etSplitOther = (EditText) findViewById(R.id.etSplitOther);
        etSplitOther.setOnEditorActionListener(new DoneOnEditorActionListener());

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

    public void onClickBtn(View v){

        if(et.getText().toString().isEmpty()|| et.getText().toString() == null){
            //editText is empty, don't do anything
        }else{
            Double amtAdded = getDouble(et.getText().toString()) ;
//                    Integer.parseInt(et.getText().toString());
            switch (v.getId()){
                case R.id.btnthirty:
                    this.calculateTip(amtAdded, 30.0);
                    break;
                case R.id.btntwenty:
                    this.calculateTip(amtAdded, 20.0);
                    break;
                case R.id.btnten:
                    this.calculateTip(amtAdded, 10.0);
                    break;
                default:
                    this.calculateTip(amtAdded, 10.0);
                    break;
            }
        }
    }

    public void onClickSplit(View v){

        int noOfPeople = 1;
        switch (v.getId()){
            case R.id.btSplitFour:
                this.calculateSplit(Long.valueOf(4));
 //               perPersonTotal = grandTotal/4;
                break;
            case R.id.btSplitThree:
                this.calculateSplit(Long.valueOf(3));
   //             perPersonTotal = grandTotal/3;
                break;
            case R.id.btSplitTwo:
                this.calculateSplit(Long.valueOf(2));
                //perPersonTotal = grandTotal/2;
                break;
            default:
                this.calculateSplit((Long.valueOf(noOfPeople)));
                //perPersonTotal = grandTotal/1;
                break;
        }

//        String curBtText = getResources().getString(R.string.tvAfterSplitPerPerson);
//        tvAfterSplitPerPerson.setText(curBtText.concat(Integer.toString(perPersonTotal)));
    }

    public void calculateSplit( Long noOfPeople){
        Double perPersonTotal = 0.0;
        if(noOfPeople <=0){
            noOfPeople = (Long.valueOf(1));
        }
        perPersonTotal = grandTotal/noOfPeople;
        String curBtText = getResources().getString(R.string.tvAfterSplitPerPerson);
        tvAfterSplitPerPerson.setText(curBtText.concat(Double.toString(Math.round(perPersonTotal*100)/100)));
    }


    public void calculateTip(Double amtAdded, Double tipvalue){
        totalTip =  amtAdded * tipvalue/100;
        grandTotal = amtAdded + totalTip;
        tvtip.setText("Tip is:   ".concat(Double.toString(Math.round(totalTip*100)/100)));
        tvGrandTotal.setText("Grand Total is:  ".concat(Double.toString(Math.round(grandTotal*100)/100)));
    }

    private static Double getDouble(String str) {
        if (str == null || str.equals("")) {
            return new Double(0.0);
        } else {
            return Double.parseDouble(str);
        }
    }

    class DoneOnEditorActionListener implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView v, int i, KeyEvent keyEvent) {
            if(i == EditorInfo.IME_ACTION_DONE){
                if(v.getId() == R.id.etTipOther){

                    String etOtherValue = v.getText().toString();
                    Log.w("etOtherValue", etOtherValue);
                    Log.w("\netgetText", "*".concat(et.getText().toString()).concat("*\n"));
                    Double amtAdded = getDouble(et.getText().toString());
                            //Integer.parseInt(et.getText().toString());
//                    if(amtAdded < 0){
//                        amtAdded = 0;
//                    }
//                    if(amtAdded < 0){
//                        amtAdded = 0;
//                    }
                    calculateTip(amtAdded, getDouble(etOtherValue));
                }else if(v.getId() == R.id.etSplitOther){
                    String etSplitOther = v.getText().toString();
                    Log.w("etSplitOther", etSplitOther);
                    calculateSplit(Long.parseLong(etSplitOther));
                }else{
                    //Do Nothing
                    Log.w("DoNothing", "DoNothing");
                }
            }
            return false;
        }
    }
}
