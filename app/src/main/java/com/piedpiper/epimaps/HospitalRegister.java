package com.piedpiper.epimaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HospitalRegister extends AppCompatActivity {
    private FirebaseAuth regAuth;
    TextView hospitalLocation, documentNameEditText;
    EditText emailEditText, nameEditText, passwordEditText;
    Button requestVerificationButton, uploadCertificatesButton, button;
    String name, email, password;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage firebaseStorage;
    FirebaseFirestore fsClient;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);

        regAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        button = (Button) findViewById(R.id.setlocation_regiser_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalRegister.this, HospitalLocation.class);
                startActivity(intent);
            }
        });

        hospitalLocation = (TextView) findViewById(R.id.setlocation_regiser_button);
        emailEditText = (EditText) findViewById(R.id.email_register_edittext);
        nameEditText = (EditText) findViewById(R.id.name_register_edittext);
        passwordEditText = (EditText) findViewById(R.id.password_register_edittext);
        requestVerificationButton = (Button) findViewById(R.id.requestverification_button);
        uploadCertificatesButton = (Button) findViewById(R.id.uploaddocuments_register_button);
        documentNameEditText = (TextView) findViewById(R.id.documentname_TextView);

        hospitalLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        uploadCertificatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                documentSelect();
            }
        });

        emailAuthentication();

    }

    private void documentSelect() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void emailAuthentication() {
        requestVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                name = nameEditText.getText().toString();
                if (!email.isEmpty() && !name.isEmpty() && !password.isEmpty() && filePath != null) {
                    regAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(HospitalRegister.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                regAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            uploadDocument();
                                            Toast.makeText(HospitalRegister.this, "Click on the verification link and sign in", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(HospitalRegister.this, HospitalLogin.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(HospitalRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(HospitalRegister.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                } else {
                    Toast.makeText(HospitalRegister.this, "Fill all the information", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploadDataOnFirestore(String certificateURL) {
        String userId = regAuth.getUid();
        String name = this.name;
        String email = this.email;

        String location = "some LatLng object";
        String pinCode = "some pincode";

        Map<String, Object> update = new HashMap<>();
        update.put("userId", userId);
        update.put("name", name);
        update.put("email", email);
        update.put("certificate", certificateURL);
        update.put("location", location);
        update.put("pinCode", pinCode);
        update.put("verified", false);

        fsClient = FirebaseFirestore.getInstance();
        fsClient.collection("Hospitals")
                .document(userId)
                .set(update);

    }

    private void uploadDocument() {
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final StorageReference reference = storageReference.child("Images/" + UUID.randomUUID().toString());
            reference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(HospitalRegister.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    documentNameEditText.setVisibility(View.GONE);
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.d("path", uri.toString());
                            uploadDataOnFirestore(uri.toString());

                        }
                    });
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(HospitalRegister.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100 * taskSnapshot.getBytesTransferred() / (double) taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploading " + (int) progress + "%");

                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                String filename=filePath.substring(filePath.lastIndexOf("/")+1);
                File file = new File("" + filePath);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imageinByte = stream.toByteArray();
                long sizeOfImage = imageinByte.length / 1024;
                if (sizeOfImage < 1024) {
                    documentNameEditText.setText(file.getName().substring(file.getName().lastIndexOf("%") + 1));
                    documentNameEditText.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(HospitalRegister.this, "Size should be less than 1MB", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
