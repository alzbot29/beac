package com.example.alzbot;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
            peopleHolder.address.setText(list.get(i).getPlacee());
            String url = list.get(i).getDownloadUrl();
            try {
                Picasso.get()
                        .load(url)
                        .placeholder(R.drawable.ic_blur_on_black_24dp)
                        .error(R.drawable.ic_blur_off_black_24dp)
                        .into(peopleHolder.imageView);
            } catch (Exception e) {
            }
            peopleHolder.people=list.get(i);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PeopleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,rel,address;
        ImageView imageView;
        People people;
        public PeopleHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            rel=itemView.findViewById(R.id.relationship);
            address=itemView.findViewById(R.id.address);
            imageView=itemView.findViewById(R.id.imageView);

            name.setOnClickListener(this);
            rel.setOnClickListener(this);
            address.setOnClickListener(this);
            imageView.setOnClickListener(this);
            itemView.setOnClickListener(this);


        }

        private void showI(View view)
        {
            Dialog dialog=new Dialog(view.getContext());
            dialog.setContentView(R.layout.item_detail_person);

            TextView name1,rel1,address1,age1,notes1,livesin1;
            ImageView imageView;
            imageView=dialog.findViewById(R.id.imageView);
            name1=dialog.findViewById(R.id.name);
            rel1=dialog.findViewById(R.id.relationship);
            address1=dialog.findViewById(R.id.address);
            age1=dialog.findViewById(R.id.age);
            notes1=dialog.findViewById(R.id.notes);
            livesin1=dialog.findViewById(R.id.livesin);


            name1.setText(people.getNaamm());
            rel1.setText(people.getRelationn());
            address1.setText(people.getPlacee());
            age1.setText(people.getAgee());
            notes1.setText(people.getNotess());
            livesin1.setText(people.getLivee());
            try {
                Picasso.get().load(people.getDownloadUrl()).into(imageView);
            } catch (Exception e) {
            }
            dialog.show();
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        }

        @Override
        public void onClick(View v) {
            showI(v);
        }
    }
}
