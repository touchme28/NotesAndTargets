package com.example.notesandtargets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesandtargets.R;
import com.example.notesandtargets.models.Target;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TargetAdapter extends RecyclerView.Adapter<TargetAdapter.TargetViewHolder>{

    Context context;
    ArrayList<Target> targetsList;

    public TargetAdapter(Context context, ArrayList<Target> targetsList) {
        this.context = context;
        this.targetsList = targetsList;
    }

    @NonNull
    @Override
    public TargetAdapter.TargetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_target, parent, false);
        return new TargetAdapter.TargetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TargetAdapter.TargetViewHolder holder, int position) {
        DatabaseReference database = FirebaseDatabase.getInstance("https://notesandtargets-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("targets");

        Target target = targetsList.get(position);


        DateFormat df = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String data = df.format(target.getCreationDate());


        holder.DescriptionTextView.setText(target.getDescription());
        holder.dateTextView.setText(data);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder.checkBox.isChecked()){
                    database.child(target.getUid()).child("checked").setValue(true);

                }
                else {
                    database.child(target.getUid()).child("checked").setValue(false);

                }
            }
        });

        database.child(target.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        database.child(target.getUid()).removeValue();
                    }
                });

                if(snapshot.child("checked").getValue(Boolean.class) != null){
                    if(snapshot.child("checked").getValue(Boolean.class).equals(true)) {
                        holder.checkBox.setChecked(true);
                    }
                    else {
                        holder.checkBox.setChecked(false);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return targetsList.size();
    }

    public class TargetViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView DescriptionTextView;
        Button deleteButton;
        CheckBox checkBox;

        public TargetViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            DescriptionTextView = itemView.findViewById(R.id.DTargetTextView);
            deleteButton = itemView.findViewById(R.id.DeleteButton);
            checkBox = itemView.findViewById(R.id.checkBox);

        }

    }
}
