package com.example.vemund.inventory;

import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            inPortrait();
        }
        setContentView(R.layout.activity_main);
    }

    private void inPortrait() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        FragmentList fragmentList = new FragmentList();
        transaction.replace(R.id.main_frame,fragmentList);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }

    public void closeInfo(View view) {
        inPortrait();
    }
}
