package com.cemas.s4c.hwb;

import java.util.List;

import com.cemas.s4c.hwb.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnswerSheetActivity extends Activity implements OnClickListener {

	private TextView wellDone;
	private ImageButton vocabicon;
	private ImageButton homeicon;
	private TextView correctOutOf20;
	private View sep2;
	private RelativeLayout shareBar;
	private ImageButton facebookButton;
	private ImageButton twitterButton;
	private String correctoutof20String;

	private void findViews() {
		wellDone = (TextView) findViewById(R.id.well_done);
		vocabicon = (ImageButton) findViewById(R.id.vocabicon);
		homeicon = (ImageButton) findViewById(R.id.homeicon);
		correctOutOf20 = (TextView) findViewById(R.id.correct_out_of_20);
		sep2 = (View) findViewById(R.id.sep2);
		shareBar = (RelativeLayout) findViewById(R.id.shareBar);
		facebookButton = (ImageButton) findViewById(R.id.facebook_button);
		twitterButton = (ImageButton) findViewById(R.id.twitter_button);

		vocabicon.setOnClickListener(this);
		homeicon.setOnClickListener(this);
		facebookButton.setOnClickListener(this);
		twitterButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == vocabicon) {
			Intent i = new Intent(this, VocabActivity.class);
			startActivity(i);
		} else if (v == homeicon) {
			Globals.setQuestionNo(0);
			super.onBackPressed();
		} else if (v == facebookButton) {
			shareViaFacebook();
		} else if (v == twitterButton) {
			Intent i = new Intent(this, TwitterActivity.class);
			startActivity(i);
		}
	}

	private void shareViaFacebook() {
		Intent i = new Intent(this, FacebookLoginFragmentActivity.class);
		startActivity(i);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_sheet);
		findViews();
		populateViews();
	}

	private void populateViews() {
		correctoutof20String = Globals.getNumberOfCorrectAnswers() + "/"
				+ Globals.getNumberOfQuestions();
		correctOutOf20.setText(correctoutof20String);

	}

	@Override
	public void onBackPressed() {
		Globals.setQuestionNo(0);
		super.onBackPressed();
	}

}
