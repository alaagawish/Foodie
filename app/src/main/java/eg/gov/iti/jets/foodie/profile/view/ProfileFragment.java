package eg.gov.iti.jets.foodie.profile.view;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eg.gov.iti.jets.foodie.MainActivity;
import eg.gov.iti.jets.foodie.R;
import eg.gov.iti.jets.foodie.db.LocalSource;
import eg.gov.iti.jets.foodie.favourites.view.FavouriteAdapter;
import eg.gov.iti.jets.foodie.favourites.view.FavouritesFragment;
import eg.gov.iti.jets.foodie.login.view.LoginActivity;
import eg.gov.iti.jets.foodie.model.Meal;
import eg.gov.iti.jets.foodie.model.Repository;
import eg.gov.iti.jets.foodie.network.API_Client;
import eg.gov.iti.jets.foodie.plan.presenter.PlanPresenter;
import eg.gov.iti.jets.foodie.profile.presenter.ProfilePresenter;
import eg.gov.iti.jets.foodie.profile.presenter.ProfilePresenterInterface;
import eg.gov.iti.jets.foodie.utils.UploadUtils;
import okhttp3.internal.cache.DiskLruCache;

public class ProfileFragment extends Fragment implements ProfileClickListener, ProfileViewInterface {
    private static final String TAG = "ProfileFragment";

    private TextView usernameTextView, emailTextView;
    private Button logoutButton, backupButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient gsc;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private GoogleSignInOptions gso;
    private Meal personalMeal;
    private CircleImageView editProfileCircleImageView, profileCircleImageView;
    private static final int PHOTO_SELECTED = 1;
    private static final int PICK_FROM_GALLERY = 0;
    private ProfilePresenterInterface profilePresenterInterface;
//    private List<Meal> favorMeals, planMeals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);

        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
        profilePresenterInterface.getAllFavMeal().observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                personalMeal.setFavList(meals);
            }
        });

        profilePresenterInterface.getAllPlannedMeal().observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                personalMeal.setPlannedList(meals);
            }
        });
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        if (account != null) {
            String userName = account.getDisplayName();
            usernameTextView.setText(userName);
            emailTextView.setText(account.getEmail());
        }
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userName = user.getDisplayName();
            usernameTextView.setText(userName);
            emailTextView.setText(user.getEmail());
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(LoginActivity.PREF, 0);
                        sharedPreferences.edit().clear().commit();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
            }
        });
        backupButton.setOnClickListener(e -> {
            databaseReference = firebaseDatabase.getReference().child("meal").child(usernameTextView.getText().toString());
            personalMeal = new Meal();
            profilePresenterInterface.getAllFavMeal().observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
                @Override
                public void onChanged(List<Meal> meals) {
                    System.out.println("kkkkkkk " + meals.get(0));
                    personalMeal.setFavList(meals);
                    System.out.println("klllll " + personalMeal.getFavList().get(0));

                }
            });

            profilePresenterInterface.getAllPlannedMeal().observe((LifecycleOwner) getContext(), new Observer<List<Meal>>() {
                @Override
                public void onChanged(List<Meal> meals) {
                    personalMeal.setPlannedList(meals);
                    System.out.println("kkkkkoshari "+personalMeal.getPlannedList().get(0).getStrMeal());
                }
            });

            personalMeal.setUserName(usernameTextView.getText().toString());
            personalMeal.setEmail(emailTextView.getText().toString());
//            Log.d(TAG, "onViewCreated: "+personalMeal.getFavList().get(0));
//            System.out.println(personalMeal.getFavList().get(0));
            Log.d(TAG, "onViewCreated: ");
            databaseReference.setValue(personalMeal);

//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren())
//                        Log.d(TAG, "onDataChange: " + dataSnapshot.getValue().toString());
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });

        });
        editProfileCircleImageView.setOnClickListener(e -> {
//            selectPhoto();
        });
    }

    public void init(View view) {
        personalMeal = new Meal();
        usernameTextView = view.findViewById(R.id.usernameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        logoutButton = view.findViewById(R.id.logoutButton);
        backupButton = view.findViewById(R.id.backupButton);
        editProfileCircleImageView = view.findViewById(R.id.editProfileCircleImageView);
        profileCircleImageView = view.findViewById(R.id.profileCircleImageView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        profilePresenterInterface = new ProfilePresenter(this, Repository.getInstance(API_Client.getInstance(), LocalSource.getInstance(getContext()), getContext()));

    }

//    private void selectPhoto() {
//        final CharSequence[] options = {"Choose from Gallery", "Cancel"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Upload Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (options[item].equals("Choose from Gallery")) {
//
//
//                    if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
//                    } else {
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(intent, PHOTO_SELECTED);
//                    }
//
//                } else if (options[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == PHOTO_SELECTED && data != null) {
//            Uri selectedImage = data.getData();
//            String filePath = UploadUtils.getPath(selectedImage, requireContext());
//            String mimeType = requireActivity().getContentResolver().getType(selectedImage);
//            File userPhotoFile = new File(selectedImage.getPath());
//            Log.d("upload: picUri", selectedImage.toString());
//            Log.d("upload: filePath", filePath);
//            Log.d("upload: contetType", mimeType);
//            uploadUserProfilePhoto(userPhotoFile, mimeType);
//        }
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PICK_FROM_GALLERY) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, PHOTO_SELECTED);
//            } else {
//                Toast.makeText(requireContext(), "You have to grant permissions to open the gallery", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    public void uploadUserProfilePhoto(File userPhoto, String contentType) {
//        Uri uri = Uri.fromFile(userPhoto);
//
//        profileCircleImageView.setImageURI(uri);
//    }

    @Override
    public void favMeals(List<Meal> meals) {

    }

    @Override
    public void plannedMeals(List<Meal> meals) {

    }
}