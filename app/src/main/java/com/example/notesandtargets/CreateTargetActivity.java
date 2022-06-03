package com.example.notesandtargets;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesandtargets.models.Target;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class CreateTargetActivity extends AppCompatActivity {
    private EditText DescriptionEditText;
    private Button createTargetButton;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_target);

        initViews();

        createTargetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String desription = DescriptionEditText.getText().toString();
                Date creationDate = new Date();
                Boolean isChecked = false;

                if (desription.isEmpty()){
                    showMessage("Desription is empty!");
                    desription = "desription";
                    return;
                }

                database = FirebaseDatabase.getInstance("https://notesandtargets-default-rtdb.europe-west1.firebasedatabase.app/")
                        .getReference().child("targets");

                DatabaseReference pushKey = database.push();
                Target target = new Target(desription,creationDate,isChecked,pushKey.getKey());
                pushKey.setValue(target);

                finish();
            }
        });


    }

    void initViews(){
        DescriptionEditText = findViewById(R.id.DescriptionEditText);
        createTargetButton = findViewById(R.id.createTargetButton);

    }

    void showMessage(String msg){
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
