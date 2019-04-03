package com.example.alzbot;

import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

public class AddAlarmAdapter extends RecyclerView.Adapter<AddAlarmAdapter.AddAlarmViewHolder> {
    List<Medicines> medicinesList;

    public AddAlarmAdapter(List<Medicines> medicinesList) {
        this.medicinesList = medicinesList;
    }

    public List<Medicines> getMedicinesList() {
        return medicinesList;
    }

    @NonNull
    @Override
    public AddAlarmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.alarm_items,viewGroup,false);
        return new AddAlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddAlarmViewHolder addAlarmViewHolder, int i) {
        addAlarmViewHolder.name.setText("Med :: "+medicinesList.get(i).getName());
        addAlarmViewHolder.qty.setText("Qty :: "+medicinesList.get(i).getQty());
        addAlarmViewHolder.position=i;
        if(!medicinesList.get(i).getTime().equals("_"))
        addAlarmViewHolder.time.setText(medicinesList.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return medicinesList.size();
    }

    public class AddAlarmViewHolder extends RecyclerView.ViewHolder {
        TextView name,qty;
        Button time;
        int position;
        public AddAlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.med);
            qty=itemView.findViewById(R.id.qty);
            time=itemView.findViewById(R.id.time);
            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            time.setText( selectedHour + ":" + selectedMinute);
                            medicinesList.get(position).setTime(selectedHour+":"+selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();

                }
            });
        }
    }
}
