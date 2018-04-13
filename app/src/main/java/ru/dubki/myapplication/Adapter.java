package ru.dubki.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter{
    Context context;
    LayoutInflater lInflater;
    ArrayList <Employee> sotrs;
    OnItemListener listener;
    Employee selectEmployee;
    OnLongClickListener longClickListener;


    public Adapter(Activity activity, ArrayList<Employee> sotrs) {
        context = activity;
        this.sotrs = sotrs;
        listener = (OnItemListener) activity;
        longClickListener = (OnLongClickListener) activity;
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

        final Employee employee = getItem(position);
        ConstraintLayout cont = view.findViewById(R.id.container);
        TextView tvFam = view.findViewById(R.id.fam1);
        TextView tvName = view.findViewById(R.id.name1);
        TextView tvOt = view.findViewById(R.id.ot1);
        TextView tvDate = view.findViewById(R.id.date1);
        tvFam.setText(employee.fam);
        tvName.setText(employee.name);
        tvOt.setText(employee.ot);
        tvDate.setText(employee.date);
        cont.setBackgroundColor(context.getResources().getColor(position % 2 == 0 ? R.color.blue : R.color.yellow));
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEmployee = employee;
                listener.setItem(employee);
                notifyDataSetChanged();
            }
        });
        if (employee.equals(selectEmployee)) {
            cont.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
        cont.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.setItem(employee);
                longClickListener.onLongClick(v);
                return true;
            }
        });
        return view;
    }




}
