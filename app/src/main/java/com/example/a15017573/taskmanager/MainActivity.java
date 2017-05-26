package com.example.a15017573.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    ListView lvTask;
    ArrayList<String> task;
    ArrayAdapter aa;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTask = (ListView) findViewById(R.id.lvTask);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        task = new ArrayList<String>();

        DBHelper dbh = new DBHelper(MainActivity.this);
        task.clear();
        task.addAll(dbh.getAllTasks());

        dbh.close();
        String txt = "";
        for (int i = 0; i < task.size(); i++) {
            String tmp = task.get(i);
            txt += tmp + "\n";
        }



        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, task);
        lvTask.setAdapter(aa);

        dbh.close();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, 9);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            btnAdd.performClick();
        }
    }
}
