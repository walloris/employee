package ru.dubki.myapplication;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemListener, View.OnClickListener, View.OnLongClickListener  {
    ArrayList<Employee> sotrs;
    Adapter adapter;
    Employee selectEmployee;
    DBHelper dbHelper;
    final String LOG_TAG = "LOG_TAG";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        if (savedInstanceState == null) {
            sotrs = new ArrayList<>();
        } else {
            sotrs = savedInstanceState.getParcelableArrayList("arraylist");
        }

        final ListView list = findViewById(R.id.list_view);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter = new Adapter(this, sotrs);
        list.setAdapter(adapter);
        Button add = findViewById(R.id.btn1);
        add.setOnClickListener(this);
        Button del = findViewById(R.id.btn2);
        del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn2:
                sotrs.remove(selectEmployee);
                adapter.notifyDataSetChanged();
        }}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("arraylist", sotrs);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1 || data == null) {
            return;
        }
        Employee employee = data.getParcelableExtra("employee");
        sotrs.add(employee);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void setItem(Employee employee) {
        this.selectEmployee = employee;
    }

    @Override
    public Employee getItem() {
        return selectEmployee;
    }

    @Override
    public boolean onLongClick(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("employee", selectEmployee);
        startActivity(intent);

        return false;
    }

    private ArrayList<Employee> loadDb(SQLiteDatabase sql) {
        Cursor c = sql.query("DB", null, null, null, null, null, null);
        int IdColIndex = c.getColumnIndex("id");
        int fnameColIndex = c.getColumnIndex("fname");
        int snameColindex = c.getColumnIndex("sname");
        int otchColindex = c.getColumnIndex("otch");
        int dateColIndex = c.getColumnIndex("date");
        fam1.setText(c.getString(fnameColIndex));
        name1.setText(c.getString(snameColindex));
        ot1.setText(c.getString(otchColindex));
        date1.setText(c.getString(dateColIndex));
        return null;
    }
}