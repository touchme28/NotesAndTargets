package com.example.notesandtargets.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesandtargets.CreateNoteActivity;
import com.example.notesandtargets.R;
import com.example.notesandtargets.adapters.NotesAdapter;
import com.example.notesandtargets.models.NotesList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class NoteFragment extends Fragment {

//    FloatingActionButton actionButton;
//    RecyclerView recyclerView;

    public NoteFragment(){
        super(R.layout.fragment_note);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton actionButton = view.findViewById(R.id.addNoteButton);
        RecyclerView recyclerView =  view.findViewById(R.id.notesList);
        Button clearButton = view.findViewById(R.id.clearButton);


        NotesAdapter notesAdapter = NotesList.getN_adapter();
        recyclerView.setAdapter(notesAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
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
                notesAdapter.clearItems();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_note, container,false);
    }



}
