package com.example.vemund.inventory;

import android.app.ListFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vemund on 09.02.16.
 */
public class FragmentList extends ListFragment implements AdapterView.OnItemClickListener{
    ArrayList<Equipment> equipmentList = new ArrayList<Equipment>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.equipment_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startDownload();
        MyAdapter myAdapter = new MyAdapter(getActivity(), equipmentList);
        setListAdapter(myAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            showDetailsLandscape(position);
        }else {
            showDetailsPortrait(position);
        }

    }

    private void showDetailsPortrait(int position) {
        FragmentDetails details= new FragmentDetails();
        this.getFragmentManager().beginTransaction().replace(R.id.main_frame, details).commit();
        getFragmentManager().executePendingTransactions();
        details.showDetails(equipmentList.get(position));

    }

    private void showDetailsLandscape(int position) {
        FragmentDetails details = (FragmentDetails) getFragmentManager().findFragmentById(R.id.detail_frag);
        details.showDetails(equipmentList.get(position));
    }


    private void startDownload() {
        Thread thread = new Thread(null, runInBackground, "Background");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private final Runnable runInBackground = new Runnable() {
        public void run() {
            connectToServer();
        }
    };

    private void connectToServer(){
        String myURL = "http://kark.hin.no:8088/d3330log_backend/getTestEquipment";
        HttpURLConnection conn = null;

        try{
            URL url = new URL(myURL);
            conn = (HttpURLConnection) url.openConnection();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                readData(conn.getInputStream());
            }else{
                Log.d(this.getClass().toString(), "Fail to connect to server");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        Gson gson = new Gson();
        equipmentList = gson.fromJson(br, new TypeToken<List<Equipment>>(){}.getType());
    }

}

