package com.android.carrental.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.carrental.R;
import com.android.carrental.model.Car;

import java.util.List;

public class AvailableCarsAdapter extends RecyclerView.Adapter<AvailableCarsAdapter.AvailableCarViewHolder> {

    private Context context;
    private List<Car> availableCars;

    public AvailableCarsAdapter(Context context, List<Car> availableCars) {
        this.availableCars = availableCars;
        this.context = context;
    }

    @Override
    public AvailableCarViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, viewGroup, false);
        return new AvailableCarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AvailableCarViewHolder availableCarViewHolder, int i) {
        final Car availableCar = availableCars.get(i);
        availableCarViewHolder.car_name.setText(availableCar.getName());
        availableCarViewHolder.car_color.setText(availableCar.getColor());
        availableCarViewHolder.cost_per_hour.setText("$"+availableCar.getRate()+"/hr");
        availableCarViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBookingDetails(availableCar);
            }
        });
    }

    private void showBookingDetails(Car availableCar) {
    }

    @Override
    public int getItemCount() {
        return availableCars.size();
    }

    public class AvailableCarViewHolder extends RecyclerView.ViewHolder {

        public TextView car_name;
        public TextView car_color;
        public TextView cost_per_hour;

        public AvailableCarViewHolder(View itemView) {
            super(itemView);
            car_name = itemView.findViewById(R.id.car_name);
            car_color = itemView.findViewById(R.id.car_color);
            cost_per_hour = itemView.findViewById(R.id.car_cost_per_hour);
        }
    }

}
