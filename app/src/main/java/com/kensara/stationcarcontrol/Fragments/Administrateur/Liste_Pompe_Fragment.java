package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kensara.stationcarcontrol.Model.Pompe;
import com.kensara.stationcarcontrol.R;

import org.w3c.dom.Text;

public class Liste_Pompe_Fragment extends Fragment {

    public class PompeViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_nom_pompe;
        public TextView tv_superviseur;

        public PompeViewHolder(View itemView) {
            super(itemView);

            tv_nom_pompe = itemView.findViewById(R.id.item_pompe_nom);
            tv_superviseur = itemView.findViewById(R.id.item_pompe_details);
        }

        public void bindPomp(Pompe pompe, View.OnClickListener startClickListener){
            tv_nom_pompe.setText(pompe.getNom());
            tv_superviseur.setText(pompe.getAdresse());
        }
    }

    RecyclerView mRecycler;

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Pompe, PompeViewHolder> mAdapter;
    private LinearLayoutManager mManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_pompe_fragment, container, false);

        getActivity().setTitle("Pompes");

        mRecycler = view.findViewById(R.id.list_pompe_recyclerview);
        mRecycler.setHasFixedSize(true);

        mDatabase = FirebaseDatabase.getInstance().getReference("/Pompes/");

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        mAdapter = new FirebaseRecyclerAdapter<Pompe, PompeViewHolder>(
                Pompe.class,
        R.layout.item_pompe,
        PompeViewHolder.class,
        mDatabase
                ){

            @Override
            protected void populateViewHolder(PompeViewHolder viewHolder, Pompe pompe, int position) {
                viewHolder.tv_nom_pompe.setText(pompe.getNom());
                viewHolder.tv_superviseur.setText(pompe.getAdresse());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        };

        mRecycler.setLayoutManager(mManager);
        mRecycler.setAdapter(mAdapter);

        setHasOptionsMenu(true);

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                ListPompeListener listPompeListener = (ListPompeListener) getActivity();
                listPompeListener.addPompe();
                break;
        }
        return true;
    }


    public interface ListPompeListener{
        public void addPompe();
    }



}
