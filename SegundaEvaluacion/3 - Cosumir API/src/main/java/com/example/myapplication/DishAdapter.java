package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>
        implements View.OnClickListener {

    private List<Dish> dishes;
    private Context context;
    private View.OnClickListener listener;

    public DishAdapter(Context context, List<Dish> dishes) {
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Inflamos el layout preparado para la visualización en formato lista */
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dish, parent, false);
        /** Ampliación: Inflamos el layout preparado para la visualización en formato Grid */
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.griditem_country, parent, false);

        /** Asignamos el listener */
        itemView.setOnClickListener(this);

        /** Creamos el ViewHolder personalizado y lo devolvemos */
        DishViewHolder viewHolder = new DishViewHolder(itemView,context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        /** Obtenemos el país de la posición solicitada */
        Dish dish = dishes.get(position);
        /** Llamamos a nuestro método personalizado que asigna los valores a los componentes del layout */
        holder.bindDish(dish);
    }

    @Override
    public int getItemCount() {
        /** Devolvemos el número de elementos del array de países */
        return dishes.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null) {
            listener.onClick(view);
        }
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDishId;
        private TextView tvDishName;
        private TextView tvDishCategory;
        private TextView tvDishCuisine;
        private Context context;

        public DishViewHolder(View itemView, Context context) {
            super(itemView);
            /** Guardamos el contexto para poder acceder a los recursos de la aplicación */
            this.context = context;
            /** Obtenemos la referencia a los componentes del layout */
            tvDishId = (TextView) itemView.findViewById(R.id.tvDishid);
            tvDishName = (TextView) itemView.findViewById(R.id.tvName);
            tvDishCategory = (TextView) itemView.findViewById(R.id.tvCategory);
            tvDishCuisine = (TextView) itemView.findViewById(R.id.tvCuisine);
        }

        public void bindDish(Dish dish) {
            /**
             * Intentamos obtener el ID del drawable asociado a la imagen a partir del códgio ISO
             * del país de 2 caracteres. En caso de que no exista una imagen para la bandera de dicho
             * país, se dejará el valor por defecto que tiene en el Layout.
             */

            /** Asignamos el nombre del país */
            tvDishId.setText(String.valueOf(dish.getId()));
            tvDishName.setText(dish.getName());
            tvDishCategory.setText(dish.getCategory());
            tvDishCuisine.setText(dish.getCuisine());

        }
    }
}
