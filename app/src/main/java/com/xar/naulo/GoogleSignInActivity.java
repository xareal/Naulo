package com.xar.naulo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.xar.naulo.databinding.ActivityGoogleBinding;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.xar.naulo.models.User;

/**
* Determine Firebase Authentication using a Google ID token.
*/
public class GoogleSignInActivity extends BaseActivity implements View.OnClickListener {

	private static final String TAG = "GoogleActivity";
	private static final int RC_SIGN_IN = 9001;

	// Start declare auth
	private FirebaseAuth mAuth;

	private DatabaseReference mDatabase;


	private GoogleSignInClient mGoogleSignInClient;
	private ActivityGoogleBinding mBinding;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = ActivityGoogleBinding.inflate(getLayoutInflater());
		setContentView(mBinding.getRoot());
		setProgressBar(mBinding.progressBar);


		// Button listeners
		mBinding.signInButton.setOnClickListener(this);

		// Start config signin
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestIdToken(getString(R.string.default_web_client_id))
				.requestEmail()
				.build();

		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
		
		// Initialize Firebase Auth
		mAuth = FirebaseAuth.getInstance();		

		// Initialize Database
		mDatabase = FirebaseDatabase.getInstance().getReference();
	}

	@Override
	public void onStart() {
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly
		FirebaseUser currentUser = mAuth.getCurrentUser();
		
		if (currentUser !=  null) {
			onAuthSuccess(currentUser);

		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Return returned from launching the Intent from googleSignInApi
		if (requestCode == RC_SIGN_IN) {
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
			try {
				// Google Sign in was successful, authenticate with Firebase
				GoogleSignInAccount account = task.getResult(ApiException.class);
				Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());
				firebaseAuthWithGoogle(account.getIdToken());
			} catch (ApiException e) {
				Log.w(TAG, "Google sign in failed", e);
				updateUI(null);
			}
		}
	}

	private void firebaseAuthWithGoogle(String idToken) {

		AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
		mAuth.signInWithCredential(credential)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							// Sign in success, update UI with the signed-in user's information
							Log.d(TAG, "signInWithCredential: success");
							FirebaseUser user = mAuth.getCurrentUser();
							onAuthSuccess(user);
						} else {
							// If sign in fails, display a message to the user
							Log.w(TAG, "signInWithCredential: failure", task.getException());
							Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
							updateUI(null);
						}

						hideProgressBar();
					}
				});
	}
	// End auth_with_google

	// TODO: change email sign in to google signin
	// On Success to signin go to MainActivity
	private void onAuthSuccess(FirebaseUser user) {
		user = FirebaseAuth.getInstance().getCurrentUser();

		// Name email address, and profile photo Url
		String uid = user.getUid();
		String name = user.getDisplayName();
		String email = user.getEmail();
		// Uri photoUrl = user.getPhotoUrl();
	
		 	

	 	// Write new user
		writeNewUser(uid, name, email);

		// Go to MainActivity
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		finish();
		
	}




	// TODO: change email sign in to google sign in
	// [Start basic_write]
	private void writeNewUser(String userId, String name, String email) {
		User user = new User(name, email);
		
		mDatabase.child("users").child(userId).setValue(user);
	}
	// [End basic write]

	// Start signin
	private void signIn() {
		Intent signInIntent = mGoogleSignInClient.getSignInIntent();
		startActivityForResult(signInIntent, RC_SIGN_IN);
	}
	// End signin

	private void signOut() {
		// Firebase sign out
		mAuth.signOut();

		// Google sign out
		mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
			@Override
			public void onComplete(@NonNull Task<Void> task) {
				updateUI(null);
			}
		});
	}

	private void revokeAccess() {
		// Firebase sign out
		mAuth.signOut();

		// Google revoke access
		mGoogleSignInClient.revokeAccess().addOnCompleteListener(this, new OnCompleteListener<Void>() {
			@Override
			public void onComplete(@NonNull Task<Void> task) {
				updateUI(null);
			}
		});
	}

	private void updateUI(FirebaseUser user) {
		if (user != null) {
			mBinding.status.setText(getString(R.string.google_status_fmt, user.getEmail()));
			mBinding.status.setText(getString(R.string.firebase_status_fmt, user.getUid()));

			mBinding.signInButton.setVisibility(View.GONE);
			mBinding.signOutAndDisconnect.setVisibility(View.VISIBLE);
		} else {
			mBinding.status.setText(R.string.signed_out);
			mBinding.detail.setText(null);

			mBinding.signInButton.setVisibility(View.VISIBLE);
			mBinding.signOutAndDisconnect.setVisibility(View.GONE);
		}
	}

	 @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signInButton) {
            signIn();
        }
    }
}