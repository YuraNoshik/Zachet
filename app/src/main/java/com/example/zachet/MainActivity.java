package com.example.zachet;

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
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add;
    Button delete;
    Button edit;
    ListView spisok;
    TextView selection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);
        edit = (Button) findViewById(R.id.edit);
        spisok = (ListView) findViewById(R.id.spisok);

        String[] positionName = new String[1];
        int[] positionGl = new int[1];
        positionGl[0] = -1;

        ArrayList<String> listok = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listok);
        spisok.setAdapter(adapter);

        spisok.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View
                    view, int position, long id) {
                positionName[0] = adapter.getItem(position).toString();
                positionGl[0] = adapter.getPosition(positionName[0]);
            }
        });

         add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(positionGl[0] != 1) {
                AlertDialog.Builder bul = new AlertDialog.Builder(MainActivity.this);
                EditText input = new EditText(getApplicationContext());
                bul.setCancelable(false)
                        .setIcon(R.drawable.green)
                        .setTitle("Вы хотите добавить?")
                        .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(!input.getText().toString().isEmpty()) {
                                    listok.add(0, input.getText().toString());
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                bul.setView(input);
                bul.show();
            }
        }
    });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bul = new AlertDialog.Builder(MainActivity.this);
                bul.setCancelable(false)
                        .setTitle("Вы хотите удалить?")
                        .setIcon(R.drawable.red)
                        .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                adapter.remove(positionName[0]);
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });

                bul.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bul= new AlertDialog.Builder(MainActivity.this);
                EditText input = new EditText(getApplicationContext());
                bul.setView(input);
                bul.setCancelable(false)
                        .setTitle("Вы хотите изменить?")
                        .setIcon(R.drawable.pencil)
                        .setPositiveButton("Изменить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(!input.getText().toString().isEmpty()) {
                                    listok.set(positionGl[0], input.getText().toString());
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
                bul.show();

            }
        });
    }
}