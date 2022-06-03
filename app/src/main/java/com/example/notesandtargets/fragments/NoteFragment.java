package com.example.notesandtargets.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesandtargets.CreateNoteActivity;
import com.example.notesandtargets.R;
import com.example.notesandtargets.adapters.NotesAdapter;
import com.example.notesandtargets.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoteFragment extends Fragment {

    private RecyclerView recyclerView;

    private DatabaseReference database;
    private NotesAdapter notesAdapter;
    private ArrayList<Note> NotesList;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton actionButton;
    private Button clearButton;


    public NoteFragment(){
        super(R.layout.fragment_note);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        actionButton = view.findViewById(R.id.addNoteButton);
        recyclerView = view.findViewById(R.id.notesList);
        clearButton = view.findViewById(R.id.clearButton);

        NotesList = new ArrayList<>();
        notesAdapter = new NotesAdapter(getContext(), NotesList);
        database = FirebaseDatabase.getInstance("https://notesandtargets-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("notes");
        recyclerView.setAdapter(notesAdapter);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateNoteActivity.class);
                getActivity().startActivity(intent);

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.removeValue();

            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                NotesList.clear();
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Note note = datasnapshot.getValue(Note.class);
                    NotesList.add(note);

                }
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container,false);

    }



}
