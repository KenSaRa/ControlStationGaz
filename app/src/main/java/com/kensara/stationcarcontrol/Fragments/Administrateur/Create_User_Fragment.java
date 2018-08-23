package com.kensara.stationcarcontrol.Fragments.Administrateur;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kensara.stationcarcontrol.Model.Employe;
import com.kensara.stationcarcontrol.Model.Pompe;
import com.kensara.stationcarcontrol.Model.ProfilUser;
import com.kensara.stationcarcontrol.Model.User;
import com.kensara.stationcarcontrol.R;
import com.kensara.stationcarcontrol.UtilisateurActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class Create_User_Fragment extends Fragment
        implements View.OnClickListener {

    public String email;
    public String password;
    public String username;
    public String image;
    Employe employe;
    private static final String TAG = "Create_User_fragment";
    private Button btnChoose, btnUpload, btnRegister;
    private ImageView imageView;

    private Uri filePath;
    private Uri imageURL;

    private final int PICK_IMAGE_REQUEST = 71;

    EditText et_name, et_password, et_email, et_tel;

    AutoCompleteTextView act_pompe;


    Spinner sp_emplole;

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    StorageReference storageReference;

    public interface CreateUserListenner {
        public void userCreated();
    }

    ProfilUser profil;
    String key = null;

    int success_count = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_user_fragment, container, false);

        getActivity().setTitle("Utilisateur");

        profil = new ProfilUser();

        //instance of firebase
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        auth  =FirebaseAuth.getInstance();


        et_name = (EditText) view.findViewById(R.id.frag_user_et_nom);
        et_password = (EditText) view.findViewById(R.id.frag_user_et_password);
        et_email = (EditText) view.findViewById(R.id.frag_user_et_email);
        et_tel = (EditText) view.findViewById(R.id.frag_user_et_tel);

        sp_emplole = (Spinner) view.findViewById(R.id.frag_user_spi_role);

        //initialize the item to upload image

        btnChoose = (Button) view.findViewById(R.id.btnChoose);
        btnUpload = (Button) view.findViewById(R.id.btnUpload);
        imageView = (ImageView) view.findViewById(R.id.frag_user_profile);

        act_pompe = (AutoCompleteTextView) view.findViewById(R.id.frag_user_act_pompe);
        act_pompe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                profil.getPompes().clear();
                profil.getPompes().add((Pompe)adapterView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        setHasOptionsMenu(true);

        return view;


    }

    //methode to choose image
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    // end methode to choose image


    //on result choose
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //end

    public void RegisterAcount() {

        email = et_email.getText().toString();
        password=et_password.getText().toString();

        if (auth.getCurrentUser() == null){
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                updateProfile();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }else
            updateProfile();



    }

    public void updateProfile(){

        success_count = 0;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(et_name.getText().toString())
                .setPhotoUri(imageURL)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                            ifAllRegistered();
                        }
                    }
                });

        key = user.getUid();

        profil.setEmail(et_email.getText().toString());
        profil.setName(et_name.getText().toString());
        profil.setRole(sp_emplole.getSelectedItem().toString());

        Employe employe = new Employe();
        employe.setTypeEmploi(profil.getRole());
        employe.setNom(profil.getName());
        employe.setTelephone(et_tel.getText().toString());

        if (profil.getPompes().size() > 0)
            employe.setPompe(profil.getPompes().get(0));

        Map<String, Object> child_updates = new HashMap<>();

        child_updates.put("/Profiles/" + key, profil);
        child_updates.put("/Employes/" +key, employe);

        database.getReference().updateChildren(child_updates)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                ifAllRegistered();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

    }


    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                            imageURL = taskSnapshot.getDownloadUrl();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                          //  Toast.makeText(.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_cancel_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_action:
                RegisterAcount();
                break;
            case R.id.cancel_action:
                RegisterSuccess();
                break;
        }
        return true;
    }


    @Override
    public void onClick(View view) {

    }

    private void ifAllRegistered(){
        success_count += 1;
        if (success_count == 2)
            RegisterSuccess();
    }

    private void RegisterSuccess(){
        CreateUserListenner listenner = (CreateUserListenner) getActivity();
        listenner.userCreated();
    }

}
