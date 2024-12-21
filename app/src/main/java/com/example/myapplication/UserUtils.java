package com.example.myapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class UserUtils {

    public interface UsernameCallback {
        void onUsernameRetrieved(String username);
        void onError(String errorMessage);
    }

    public static void fetchLoggedInUsername(final UsernameCallback callback) {
        // Get the current logged-in user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid(); // Get the user's UID
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Reference to the user document
            db.collection("Users").document(userId).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            // Retrieve the username field from Firestore
                            String username = documentSnapshot.getString("username");
                            if (username != null) {
                                callback.onUsernameRetrieved(username); // Pass username back
                            } else {
                                callback.onError("Username field is missing");
                            }
                        } else {
                            callback.onError("User document does not exist");
                        }
                    })
                    .addOnFailureListener(e -> {
                        callback.onError("Error fetching user data: " + e.getMessage());
                    });
        } else {
            callback.onError("No user is currently logged in");
        }
    }
}
