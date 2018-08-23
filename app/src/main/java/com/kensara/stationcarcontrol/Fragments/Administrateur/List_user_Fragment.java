package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kensara.stationcarcontrol.Model.Employe;
import com.kensara.stationcarcontrol.Model.ProfilUser;
import com.kensara.stationcarcontrol.Model.User;
import com.kensara.stationcarcontrol.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class List_user_Fragment extends Fragment  {

        public static class UserViewHolder extends RecyclerView.ViewHolder {

            public TextView tv_detail;
            public TextView tv_nom_employe;

            public UserViewHolder(View itemView) {
                super(itemView);

                tv_nom_employe = itemView.findViewById(R.id.item_nom);
                tv_detail = itemView.findViewById(R.id.item_details);
            }
        }

        RecyclerView mRecycler;

        private DatabaseReference mDatabase;

        private FirebaseRecyclerAdapter<ProfilUser, UserViewHolder> mAdapter;
        private LinearLayoutManager mManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_user_fragment, container, false);

        getActivity().setTitle("Utilisateurs");

        mRecycler = view.findViewById(R.id.list_user_recyclerview);
        mRecycler.setHasFixedSize(true);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        /*mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);*/
        mRecycler.setLayoutManager(mManager);

        mAdapter = new FirebaseRecyclerAdapter<ProfilUser, UserViewHolder>(
                ProfilUser.class,
                R.layout.item_pompe,
                UserViewHolder.class,
                mDatabase.child("Profiles").orderByChild("nom")
        ) {

            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, ProfilUser profilUser, int position) {

                viewHolder.tv_nom_employe.setText(profilUser.toString());

                viewHolder.tv_detail.setText(profilUser.getRole());


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
                ListUserListener listener = (ListUserListener) getActivity();
                listener.addUser();
                break;
        }
        return true;
    }


    public interface ListUserListener{
        public void addUser();
    }


}
