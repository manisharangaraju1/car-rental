package com.android.carrental.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.carrental.R;
import com.android.carrental.model.CarModel;
import com.squareup.picasso.Picasso;

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
        Picasso.with(context)
                .load(carModel.getUrl())
                .fit()
                .centerCrop()
                .into(carModelViewHolder.car_model_image);
    }

    @Override
    public int getItemCount() {
        return carmodels.size();
    }

    public class CarModelViewHolder extends RecyclerView.ViewHolder {

        public TextView car_model_name;
        public ImageView car_model_image;

        public CarModelViewHolder(View itemView) {
            super(itemView);
            car_model_name = itemView.findViewById(R.id.car_model_name);
            car_model_image = itemView.findViewById(R.id.car_model_image);
        }
    }

}
