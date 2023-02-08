package com.example.sqlliteinsertdata;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button buttonAdd,buttonView,buttonUpdate,buttonDelete;
    EditText  editTextId,editTextName,editTextSurName,editTextMarks,editTextDept,editTextCity;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd=findViewById(R.id.btnIns);
        buttonView=findViewById(R.id.btnView);
        buttonUpdate=findViewById(R.id.btnUpdate);
        buttonDelete=findViewById(R.id.btnDel);
        editTextId =findViewById(R.id.editTextId);
        editTextName =findViewById(R.id.editText1);
        editTextSurName=findViewById(R.id.editText2);
        editTextMarks=findViewById(R.id.editText3);
        editTextDept=findViewById(R.id.editText4);
        editTextCity=findViewById(R.id.editText5);

        myDb = new DatabaseHelper(this);

        myDb.getWritableDatabase();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean IsInserted= myDb.insertData(editTextId.getText().toString(),editTextName.getText().toString(),editTextSurName.getText().toString(),
                        editTextMarks.getText().toString(),editTextDept.getText().toString(),editTextCity.getText().toString());



                if(IsInserted==true)
                {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else {


                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDb.getAllData();



                if(cursor.getCount() == 0 )
                {
                    showMessage("Error","No Data Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();

                while (cursor.moveToNext())
                {

                    buffer.append("ID: "+ cursor.getString(0)+"\n");
                    buffer.append("Name: "+ cursor.getString(1)+"\n");
                    buffer.append("SurName: "+ cursor.getString(2)+"\n");
                    buffer.append("Marks: "+ cursor.getString(3)+"\n \n");
                    buffer.append("Dept: "+ cursor.getString(4)+"\n");
                    buffer.append("City: "+ cursor.getString(5)+"\n");

                }

                showMessage("Data",buffer.toString());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isUpdate = myDb.UpdateData(editTextId.getText().toString(),editTextName.getText().toString()
                        , editTextSurName.getText().toString(),editTextMarks.getText().toString(),editTextDept.getText().toString(),editTextCity.getText().toString());


                if(isUpdate==true)
                {
                    Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_SHORT).show();


                }

                else {

                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Integer deletedRow =  myDb.deleteData(editTextId.getText().toString());

                if(deletedRow>0)
                {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }


            }
        });





    }


    public void showMessage(String title, String Message)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();



    }
}