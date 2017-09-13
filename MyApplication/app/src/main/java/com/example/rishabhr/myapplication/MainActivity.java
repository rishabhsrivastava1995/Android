package com.example.rishabhr.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public DBManager dbManager;
    public ListView lvList;
    EditText etID, etName, etCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);

        lvList = findViewById(R.id.lvList);
        etID =  findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etCost = findViewById(R.id.etCost);
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }

    public void onAdd(View v) {
        dbManager.add(etName.getText().toString(), Double.parseDouble(etCost.getText().toString()));
    }

    public void onDelete(View v) {
        dbManager.delete(Long.parseLong(etID.getText().toString()));
    }

    public void onList(View v) {
        dbManager.setList(this, lvList);
    }
}
