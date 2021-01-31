package com.xar.naulo;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {

	@VisibleForTesting
	private ProgressBar mProgressBar;

	public void setProgressBar(ProgressBar resId) {

		mProgressBar = resId;
	}

	public void showProgressBar() {
		if (mProgressBar != null) {
			mProgressBar.setVisibility(View.VISIBLE);
		}
	}

	public void hideProgressBar() {
		if (mProgressBar != null) {
			mProgressBar.setVisibility(View.INVISIBLE);
		}
	}

	public String getUid() {
		return FirebaseAuth.getInstance().getCurrentUser().getUid();
	}
}