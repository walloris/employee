package ru.dubki.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    EditText fam1;
    EditText name1;
    EditText ot1;
    EditText date1;
    Button ok1;
    Employee employee;
    DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        employee = getIntent().getParcelableExtra("employee");


        fam1 = findViewById(R.id.fam1);
        name1 = findViewById(R.id.name1);
        ot1 = findViewById(R.id.ot1);
        date1 = findViewById(R.id.date1);
        date1.setOnClickListener(this);
        ok1 =  findViewById(R.id.ok1);
        ok1.setOnClickListener(this);
        dbHelper = new DBHelper(this);

        if (employee != null){
            fam1.setText(employee.fam);
            fam1.setEnabled(false);
            fam1.setCursorVisible(false);
            fam1.setKeyListener(null);
            name1.setText(employee.name);
            name1.setEnabled(false);
            name1.setCursorVisible(false);
            name1.setKeyListener(null);
            ot1.setText(employee.ot);
            ot1.setEnabled(false);
            ot1.setCursorVisible(false);
            ot1.setKeyListener(null);
            date1.setText(employee.date);
            date1.setEnabled(false);
            date1.setCursorVisible(false);
            date1.setKeyListener(null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date1:
                DatePicker datePicker = new DatePicker();
                datePicker.show(getSupportFragmentManager(), "date");
                break;
            case R.id.ok1:
                if (this.employee != null) {
                    setResult(RESULT_OK, null);
                    finish();
                }
                if (fam1.length() > 0  && name1.length() > 0 && ot1.length() > 0 && date1.length() > 0){
                    Intent intent = new Intent();
                    Employee employee = new Employee();
                    saveDB(dbHelper.getWritableDatabase(), employee);
                    intent.putExtra("employee", employee);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(this, "Заполнены не все поля. Продолжите заполнение!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void saveDB(SQLiteDatabase sql, Employee employee) {
        ContentValues cv = new ContentValues();
        cv.put("fname", employee.fam);
        cv.put("sname", employee.name);
        cv.put("otch", employee.ot);
        cv.put("date", employee.date);
        long rowID = sql.insert("DB", null, cv);
    }
}
