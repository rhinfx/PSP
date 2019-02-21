package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetalleActivity extends AppCompatActivity {

    public static final String EXTRA_TEXTO = "com.example.myapplication.EXTRA_TEXTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        FragmentDetalleDishes detalle = (FragmentDetalleDishes) getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        detalle.mostrarDetalle((Dish) getIntent().getSerializableExtra(EXTRA_TEXTO));
    }
}
