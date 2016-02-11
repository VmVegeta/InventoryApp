package com.example.vemund.inventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vemund on 09.02.16.
 */
public class MyAdapter extends ArrayAdapter<Equipment>{
    private final Context context;
    private final ArrayList<Equipment> equipmentList;

    public MyAdapter(Context context, ArrayList<Equipment> equipmentList) {
        super(context,R.layout.list_item, equipmentList);
        this.equipmentList = equipmentList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textIt_no = (TextView) rowView.findViewById(R.id.it_no);
        TextView textType = (TextView) rowView.findViewById(R.id.type);
        TextView textBrand = (TextView) rowView.findViewById(R.id.brand);
        TextView textModel = (TextView) rowView.findViewById(R.id.model);

        textIt_no.setText(equipmentList.get(position).getIt_no());
        textType.setText(equipmentList.get(position).getType());
        textBrand.setText(equipmentList.get(position).getBrand());
        textModel.setText(equipmentList.get(position).getModel());

        return rowView;
    }

}
