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
import com.example.notesandtargets.CreateTargetActivity;
import com.example.notesandtargets.R;
import com.example.notesandtargets.adapters.TargetAdapter;
import com.example.notesandtargets.models.Target;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TargetFragment extends Fragment{

    private RecyclerView recyclerView;

    private DatabaseReference database;
    private TargetAdapter targetAdapter;
    private ArrayList<Target> TargetList;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton actionButton;
    private Button clearTaskButton;


    public TargetFragment() {
        super(R.layout.fragment_target);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        actionButton = view.findViewById(R.id.addTargetButton);
        recyclerView = view.findViewById(R.id.targetList);
        clearTaskButton = view.findViewById(R.id.clearTaskButton);

        TargetList = new ArrayList<>();
        targetAdapter = new TargetAdapter(getContext(), TargetList);
        database = FirebaseDatabase.getInstance("https://notesandtargets-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("targets");
        recyclerView.setAdapter(targetAdapter);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateTargetActivity.class);
                getActivity().startActivity(intent);

            }
        });

        clearTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.removeValue();

            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                TargetList.clear();
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Target target = datasnapshot.getValue(Target.class);
                    TargetList.add(target);

                }
                targetAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_target, container, false);
    }
}
