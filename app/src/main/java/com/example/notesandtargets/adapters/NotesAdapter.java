package com.example.notesandtargets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesandtargets.R;
import com.example.notesandtargets.models.Note;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

//    DatabaseReference database;
    Context context;
    ArrayList<Note> notesList;

    public NotesAdapter(Context context, ArrayList<Note> noteList) {
        this.context = context;
        this.notesList = noteList;
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {

        DatabaseReference database = FirebaseDatabase.getInstance("https://notesandtargets-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("notes");


        Note note = notesList.get(position);


        holder.TitleTextView.setText(note.getTitle());
        holder.DescriptionTextView.setText(note.getDescription());


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.child(note.getUid()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }


    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView TitleTextView;
        TextView DescriptionTextView;
        Button deleteButton;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleTextView = itemView.findViewById(R.id.TitleTextView);
            DescriptionTextView = itemView.findViewById(R.id.DescriptionTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }

    }



}
