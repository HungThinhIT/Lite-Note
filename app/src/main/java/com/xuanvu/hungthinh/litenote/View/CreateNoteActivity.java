package com.xuanvu.hungthinh.litenote.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xuanvu.hungthinh.litenote.Database.MyDatabase;
import com.xuanvu.hungthinh.litenote.MainActivity;
import com.xuanvu.hungthinh.litenote.Model.Note;
import com.xuanvu.hungthinh.litenote.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {
    private Button btnBack;
    private EditText edtTitle, edtContent;
    private TextView txtTimeModify;
    private MyDatabase myDatabase;

    Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        init();

        back();
    }

    private void init() {
        myDatabase = new MyDatabase(this);
        edtTitle = findViewById(R.id.edt_create_note_title);
        edtContent = findViewById(R.id.edt_create_note_content);
        txtTimeModify = findViewById(R.id.txt_create_time_modify);
        btnBack = findViewById(R.id.btn_create_note_back);

        //init TimeModified
        String date_n = new SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault()).format(new Date());
        txtTimeModify.setText(getString(R.string.detail_time_modify,date_n));
    }

    private void autoSave() {
        Intent intent = new Intent();
        if(edtTitle.getText().toString().equals("") && edtContent.getText().toString().equals("")){
            intent.putExtra("MESSAGE",false);
            setResult(200,intent);
            supportFinishAfterTransition();
        }
        else{
//            currentTime = Calendar.getInstance().getTime();
            String date_n = new SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault()).format(new Date());
            Note note = new Note(edtTitle.getText().toString(),edtContent.getText().toString(),"#FFFFF",date_n);
            myDatabase.addNote(note);
            Toast.makeText(this, "Da luu ", Toast.LENGTH_SHORT).show();
            intent.putExtra("MESSAGE",true);
            setResult(200,intent);
            supportFinishAfterTransition();
        }

    }

    private void back() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        autoSave();
        supportFinishAfterTransition();

    }
}
