package com.android.carrental;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CarModelsAdapter extends RecyclerView.Adapter<CarModelsAdapter.CarModelViewHolder> {

    private Context context;
    private List<CarModel> carmodels;

    public CarModelsAdapter(Context context, List<CarModel> carmodels) {
        this.context = context;
        this.carmodels = carmodels;
    }

    @Override
    public CarModelsAdapter.CarModelViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_model_item, viewGroup, false);
        return new CarModelsAdapter.CarModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CarModelsAdapter.CarModelViewHolder carModelViewHolder, int i) {
        CarModel carModel = carmodels.get(i);
        carModelViewHolder.car_model_name.setText(carModel.getName());
    }

    @Override
    public int getItemCount() {
        return carmodels.size();
    }

    public class CarModelViewHolder extends RecyclerView.ViewHolder {

        public TextView car_model_name;

        public CarModelViewHolder(View itemView) {
            super(itemView);
            car_model_name = itemView.findViewById(R.id.car_model_name);
        }
    }

}
