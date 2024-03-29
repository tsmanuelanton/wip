package com.example.wip.ui.layouts.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wip.ui.layouts.activities.DetailsActivity;
import com.example.wip.ui.layouts.activities.MainActivity;
import com.example.wip.ui.layouts.activities.NavigationActivity;
import com.example.wip.R;
import com.example.wip.modelo.Fiesta;
import com.example.wip.utils.adapters.ListaFiestasAdapter;

import java.util.ArrayList;

public class ListaFragments extends Fragment {

    public static final String ARG_FIESTAS = "arg_fiestas";
    public static final String FIESTA_SELECCIONADA = "fiesta_seleccionada";
    private RecyclerView recyclerView;

    private ArrayList<Fiesta> fiestas;

    public ListaFragments() {
    }

    /**
     * @return A new instance of fragment ListaFragments.
     */
    public static ListaFragments newInstance(ArrayList<Fiesta> fiestas) {
        ListaFragments fragment = new ListaFragments();
        Bundle args = new Bundle();
        if (fiestas == null) fiestas = new ArrayList<>();
        args.putParcelableArrayList(ARG_FIESTAS, fiestas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            fiestas = getArguments().getParcelableArrayList(ARG_FIESTAS);
//        }
        fiestas = getArguments().getParcelableArrayList(ARG_FIESTAS);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addToLayout();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    //Recorre un array de fiestas y las va añadiendo al layout
    private void addToLayout() {
        //Este codigo será igual en las diferentes pantallas, recogemos el array de fiestas
        recyclerView=(RecyclerView) getActivity().findViewById(R.id.recycler_view_list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        addAdapter();
    }
    //Añade el adapter del recycledView
    private void addAdapter() {
        ListaFiestasAdapter lpAdapter = new ListaFiestasAdapter(fiestas, new ListaFiestasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fiesta fiesta) {
                Intent itent = new Intent(getContext(), NavigationActivity.class);
                itent.putExtra(MainActivity.COMUNIDAD, fiesta.getTownURL());
                startActivity(itent);
            }
        }, new ListaFiestasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fiesta fiesta) {
                Intent itent = new Intent(getContext(), DetailsActivity.class);
                itent.putExtra(ARG_FIESTAS, fiesta.getDetails());
                itent.putExtra(FIESTA_SELECCIONADA,fiesta);
                startActivity(itent);
            }
        }, getContext());
        recyclerView.setAdapter(lpAdapter);
    }
}