package com.example.alzbot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MedsAlarmAdapter extends RecyclerView.Adapter<MedsAlarmAdapter.MedsAlarmHolder> {
    List<Medicines> list=new ArrayList<>();
    OnItemAdded onItemAdded;

    public MedsAlarmAdapter(List<Medicines> list) {
        this.list = list;
    }
    public void setList(List<Medicines> list) {
        this.list = list;
        Log.e( "setList: ", String.valueOf(list.size()));
        notifyDataSetChanged();
        }
    @NonNull
    @Override
    public MedsAlarmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.meds_item,viewGroup,false);
        return new MedsAlarmHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedsAlarmHolder medsAlarmHolder, int i) {
        medsAlarmHolder.name.setText(list.get(i).getName());
        medsAlarmHolder.qty.setText(list.get(i).getQty());
//        onItemAdded.itemAdded(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<Medicines> getList() {
        return list;
    }

    public class MedsAlarmHolder extends RecyclerView.ViewHolder {
        EditText name,qty;
        public MedsAlarmHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            qty=itemView.findViewById(R.id.number);
        }
    }
    public interface OnItemAdded{
        public void itemAdded(Medicines medicines);
    }
}
