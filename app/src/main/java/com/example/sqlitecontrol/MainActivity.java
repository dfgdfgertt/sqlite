package com.example.sqlitecontrol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    Button add,remove,edit;
    EditText editTextname;
    ListView lvname;
    ArrayList namelist;
    ArrayList idlist;
    ArrayAdapter adapter;
    int index;
    DBManager data = new DBManager(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        data.addStudent(new Entities("long"));
//        data.addStudent(new Entities("long2"));
//        data.addStudent(new Entities("long3"));
//        data.addStudent(new Entities("long4"));


        anhxa();
        getAll();
        addNew();

        lvname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                editTextname.setText(namelist.get(position).toString());
            }
        });

        removeName();
        editName();


    }

    private void editName() {
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = data.Update(new Entities((Integer)idlist.get(index),editTextname.getText().toString()));
                if(i>0){
                    getNameList();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void removeName() {
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.removeOne((Integer) idlist.get(index));
                getNameList();
                adapter.notifyDataSetChanged();

            }
        });



    }

    private void addNew() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.addOne(new Entities(editTextname.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private ArrayList getNameList(){

        namelist.clear();
        idlist.clear();
        for (Iterator iterator =data.getAll().iterator(); iterator.hasNext();){
            Entities e = (Entities) iterator.next();
            namelist.add(e.getName());
            idlist.add(e.getId());
        }
        return namelist;
    }

    private void getAll() {


        getNameList();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, namelist);
        lvname.setAdapter(adapter);
    }

    private void anhxa() {
        idlist = new ArrayList();
        namelist = new ArrayList();
        add = findViewById(R.id.btnAdd);
        remove = findViewById(R.id.btnRemove);
        edit = findViewById(R.id.btnEdit);
        lvname = findViewById(R.id.lv);
        editTextname = findViewById(R.id.edtInput);

    }
}