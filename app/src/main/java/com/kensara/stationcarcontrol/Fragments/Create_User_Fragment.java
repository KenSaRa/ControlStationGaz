package com.kensara.stationcarcontrol.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kensara.stationcarcontrol.UtilisateurActivity;

public class Create_User_Fragment extends Fragment
        implements View.OnClickListener {

    public String email;
    public String password;
    public String username;
    private static final String TAG = "Create_User_fragment";

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

   // public void RegisterAcount() {
//            @Override
//                    auth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with
//                                //
//                                // the signed-in user's information
//                                Log.d(TAG, "createUserWithEmail:success");
//                                FirebaseUser user = auth.getCurrentUser();
//                                // updateUI(user);
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                             //   Toast.makeText(Create_User_Fragment.this, "Authentication failed.",
//                                  //      Toast.LENGTH_SHORT).show();
//                                ////updateUI(null);
//                            // ...
//                        }
//                    });
//    }

    @Override
    public void onClick(View view) {
        
    }
}
