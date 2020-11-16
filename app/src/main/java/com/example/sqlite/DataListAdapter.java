package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataListAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public DataListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler{
        TextView name;
        TextView mobile;
        TextView email;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        DataHandler dataHandler;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            dataHandler = new DataHandler();
            dataHandler.name = (TextView) row.findViewById(R.id.name);
            dataHandler.mobile = (TextView) row.findViewById(R.id.mobile);
            dataHandler.email = (TextView) row.findViewById(R.id.email);
            row.setTag(dataHandler);
        }else{
            dataHandler = (DataHandler)row.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        dataHandler.name.setText(dataProvider.getName());
        dataHandler.mobile.setText(dataProvider.getMobile());
        dataHandler.email.setText(dataProvider.getEmail());
        return row;
    }
}
