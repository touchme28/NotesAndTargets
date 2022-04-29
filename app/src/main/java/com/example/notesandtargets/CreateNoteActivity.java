package com.example.notesandtargets;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesandtargets.models.Note;
import com.example.notesandtargets.models.NotesList;

public class CreateNoteActivity extends AppCompatActivity {
    EditText DescriptionEditText;
    EditText TitleEditText;
    Button createNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        initViews();

        createNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = TitleEditText.getText().toString();
                String desription = DescriptionEditText.getText().toString();

                if (title.isEmpty()){
                    showMessage("Title is empty!");
                    title = "Title";
                    return;
                }

                if (desription.isEmpty()){
                    showMessage("Desription is empty!");
                    desription = "desription";
                    return;
                }
                Note note = new Note(title,desription);

                NotesList.setN_adapter(note);

                finish();
            }
        });


    };



    void initViews(){
        DescriptionEditText = findViewById(R.id.descriptionEditText);
        TitleEditText = findViewById(R.id.titleEditText);
        createNoteButton = findViewById(R.id.createNoteButton);
    }

    void showMessage(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
