package com.example.notesandtargets.services;

import com.example.notesandtargets.models.Note;
import com.google.firebase.database.FirebaseDatabase;

public class NoteService {

    public static void storeNote(Note note){
        FirebaseDatabase.getInstance("https://notesandtargets-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("notes")
                .push()
                .setValue(note);
    }
}
