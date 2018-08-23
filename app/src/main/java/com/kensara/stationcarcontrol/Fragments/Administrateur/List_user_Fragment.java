package com.kensara.stationcarcontrol.Fragments.Administrateur;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kensara.stationcarcontrol.Model.User;
import com.kensara.stationcarcontrol.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class List_user_Fragment extends Fragment  {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dref;
    ListView rlistuser;
    ArrayList<String>list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_user_fragment, container, false);

        getActivity().setTitle("Liste User");

        firebaseDatabase = FirebaseDatabase.getInstance();
        dref =firebaseDatabase.getReference("User");
        rlistuser = (ListView) view.findViewById(R.id.frag_user_listview);

        adapter= new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, list);
        rlistuser.setAdapter(adapter);


      /*  private void collectListUser(Map<String, Object> listuser)
        {

        }*/
      dref.addChildEventListener(new ChildEventListener() {

          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              String value =dataSnapshot.getValue(String.class);
              list.add(value);
              adapter.notifyDataSetChanged();

          }

          @Override
          public void onChildChanged(DataSnapshot dataSnapshot, String s) {
              String value =dataSnapshot.getValue(String.class);
              list.remove(value);
              adapter.notifyDataSetChanged();


          }

          @Override
          public void onChildRemoved(DataSnapshot dataSnapshot) {

          }

          @Override
          public void onChildMoved(DataSnapshot dataSnapshot, String s) {

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                // A new comment has been added, add it to the displayed list
                User user = dataSnapshot.getValue(User.class);

                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                User newUser = dataSnapshot.getValue(User.class);
                String userKey = dataSnapshot.getKey();


                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                User user = dataSnapshot.getValue(User.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postUser:onCancelled", databaseError.toException());
               // Toast.makeText(mContext, "Failed to load comments.",
                   //     Toast.LENGTH_SHORT).show();
            }
        };
       // ref.addChildEventListener(childEventListener);

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
