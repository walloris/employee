package ru.dubki.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    LayoutInflater lInflater;
    ArrayList <Employee> sotrs;

    public Adapter(Context context, ArrayList<Employee> sotrs) {
        this.sotrs = sotrs;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sotrs.size();
    }

    @Override
    public Employee getItem(int position) {
        return sotrs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = lInflater.inflate(R.layout.adapter_item, parent, false);
        Employee employee = getItem(position);
        TextView tvFam = view.findViewById(R.id.fam1);
        TextView tvName = view.findViewById(R.id.name1);
        TextView tvOt = view.findViewById(R.id.ot1);
        TextView tvDate = view.findViewById(R.id.date1);
        tvFam.setText(employee.fam);
        tvName.setText(employee.name);
        tvOt.setText(employee.ot);
        tvDate.setText(employee.date);
        return view;
    }

}
