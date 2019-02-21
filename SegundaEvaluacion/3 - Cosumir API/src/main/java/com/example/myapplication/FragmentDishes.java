package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class FragmentDishes extends Fragment {
    private List<Dish> dishes;
    private RecyclerView rvListado;
    private IDishListener listener;

    public FragmentDishes() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dishes = MainActivity.platos;
        return inflater.inflate(R.layout.fragment_dishes_listado, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvListado = (RecyclerView)getView().findViewById(R.id.rcvListadoDishes);
        DishAdapter adapter = new DishAdapter(getActivity()  , dishes);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    System.out.println(dishes.get(rvListado.getChildAdapterPosition(view)).getName());
                    Toast.makeText(getActivity(), dishes.get(rvListado.getChildAdapterPosition(view)).getName(),Toast.LENGTH_SHORT);
                    listener.onDishSeleccionado((Dish)dishes.get(rvListado.getChildAdapterPosition(view)));
                }
            }
        });
        rvListado.setAdapter(adapter);
        rvListado.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    public void setDishesListener(IDishListener listener){
        this.listener = listener;
    }
}
