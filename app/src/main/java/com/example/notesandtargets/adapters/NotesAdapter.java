package com.example.notesandtargets.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesandtargets.R;
import com.example.notesandtargets.models.Note;
import com.example.notesandtargets.services.NoteService;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<Note> NoteList = new ArrayList<>();

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        holder.bind(NoteList.get(position));
    }

    @Override
    public int getItemCount() {
        return NoteList.size();
    }

    public void setItem(Note note) {
        NoteList.add(note);
        NoteService.storeNote(note);
        notifyDataSetChanged();
    }

    public void clearItems() {
        NoteList.clear();
        notifyDataSetChanged();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView TitleTextView;
        TextView DescriptionTextView;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleTextView = itemView.findViewById(R.id.TitleTextView);
            DescriptionTextView = itemView.findViewById(R.id.DescriptionTextView);
        }

        public void bind(Note note) {
            TitleTextView.setText(note.getTitle());
            DescriptionTextView.setText(note.getDescription());
        }
    }



}
