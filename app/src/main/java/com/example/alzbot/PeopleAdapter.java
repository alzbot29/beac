package com.example.alzbot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleHolder> {
    List<People> list;

    public PeopleAdapter(List<People> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PeopleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_item,viewGroup,false);
        return new PeopleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleHolder peopleHolder, int i) {
        if(list!=null){
            peopleHolder.name.setText(list.get(i).getNaamm());
            peopleHolder.rel.setText(list.get(i).getRelationn());
            peopleHolder.address.setText(list.get(i).getPlacee());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PeopleHolder extends RecyclerView.ViewHolder {
        TextView name,rel,address;
        public PeopleHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            rel=itemView.findViewById(R.id.relationship);
            address=itemView.findViewById(R.id.address);
        }
    }
}
