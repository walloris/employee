package ru.dubki.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    EditText fam1;
    EditText name1;
    EditText ot1;
    EditText date1;
    Button ok1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        fam1 = findViewById(R.id.fam1);
        name1 = findViewById(R.id.name1);
        ot1 = findViewById(R.id.ot1);
        date1 = findViewById(R.id.date1);
        ok1 =  findViewById(R.id.ok1);
        ok1.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("employee", new Employee(fam1.getText().toString(), name1.getText().toString(), ot1.getText().toString(), date1.getText().toString()));
        setResult(RESULT_OK, intent);
        finish();
    }
}
