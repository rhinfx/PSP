package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class FragmentDetalleDishes extends Fragment {

    private TextView tvDishId;
    private TextView tvDishName;
    private TextView tvDishDescription;
    private List<Dish> dishes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_dishes_detalle, container, false);

        dishes = MainActivity.platos;

        tvDishId = (TextView)layout.findViewById(R.id.tvDishId);
        tvDishName = (TextView)layout.findViewById(R.id.tvDishName);
        tvDishDescription = (TextView)layout.findViewById(R.id.tvDishDescription);

        return layout;
    }

    public void mostrarDetalle(Dish dish){

        tvDishId.setText(String.valueOf(dish.getId()));
        tvDishName.setText(dish.getName());
        tvDishDescription.setText(dish.getDescription());
    }

}
