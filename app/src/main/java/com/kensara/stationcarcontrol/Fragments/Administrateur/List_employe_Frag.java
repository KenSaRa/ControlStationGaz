package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.kensara.stationcarcontrol.Model.Employe;
import com.kensara.stationcarcontrol.Model.Pompe;
import com.kensara.stationcarcontrol.R;

public class List_employe_Frag extends Fragment{

    public static class EmployeViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_nom_pompe;
        public TextView tv_nom_employe;

        public EmployeViewHolder(View itemView) {
            super(itemView);

            tv_nom_employe = itemView.findViewById(R.id.item_nom);
            tv_nom_pompe = itemView.findViewById(R.id.item_details);
        }
    }

    RecyclerView mRecycler;

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Employe, EmployeViewHolder> mAdapter;
    private LinearLayoutManager mManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_employe_frag, container, false);

        getActivity().setTitle("Employes");

        mRecycler = view.findViewById(R.id.list_employe_recyclerview);
        mRecycler.setHasFixedSize(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        /*mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);*/
        mRecycler.setLayoutManager(mManager);

        mAdapter = new FirebaseRecyclerAdapter<Employe, EmployeViewHolder>(
                Employe.class,
                R.layout.item_pompe,
                EmployeViewHolder.class,
                mDatabase.child("Employes").orderByChild("nom")
        ) {

            @Override
            protected void populateViewHolder(EmployeViewHolder viewHolder, Employe employe, int position) {

                viewHolder.tv_nom_employe.setText(employe.toString());

                try {
                    viewHolder.tv_nom_pompe.setText(employe.getPompe().getNom());
                } catch (Exception e) {
                    e.printStackTrace();
                    viewHolder.tv_nom_pompe.setText("Pompe non disponible...");
                }


                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        };

        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mManager.scrollToPositionWithOffset(0, 0);
            }
        });

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
        switch (item.getItemId()) {
            case R.id.action_add:
                ListEmployeListener listener = (ListEmployeListener) getActivity();
                listener.addEmploye();
                break;
        }
        return true;
    }


    public interface ListEmployeListener {
        public void addEmploye();
    }
}
