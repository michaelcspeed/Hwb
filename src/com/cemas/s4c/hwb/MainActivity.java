package com.cemas.s4c.hwb;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends Activity implements OnClickListener {

	Dialog mSplashDialog;
	private Button playbutton;
	private ImageButton vocabicon;
	private ImageButton infoicon;
	private boolean quizIsValid = true;

	private void findViews() {
		playbutton = (Button) findViewById(R.id.playbutton);
		vocabicon = (ImageButton) findViewById(R.id.vocabicon);
		infoicon = (ImageButton) findViewById(R.id.infoicon);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences prefs = this.getSharedPreferences("com.example.app",
				Context.MODE_PRIVATE);

		setContentView(R.layout.activity_main);
		showSplashScreen();
		findViews();
		addClickListeneters();

		updateQuestions();

	}

	private void updateQuestions() {

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					WebServiceAdapter ws = new WebServiceAdapter();
					String json = ws.downloadQuestions();
					Gson gson = new Gson();
					JSONObject jsonObj = new JSONObject(json);
					JSONObject quizObject = jsonObj.getJSONObject("quiz");
					Quiz currentQuiz = gson.fromJson(quizObject.toString(),
							Quiz.class);
					saveDate(currentQuiz);
					Globals.setCurrentQuiz(currentQuiz);
					Log.d("hwb", currentQuiz.toString());
				} catch (Exception e) {
					// Toast.makeText(getApplicationContext(),
					// "Could not get questions!", Toast.LENGTH_LONG).show();
					quizIsValid = false;
					e.printStackTrace();
				}
			}

			private void getSavedData() {
			}

			private void saveDate(Quiz currentQuiz) {
				SharedPreferences pref = getSharedPreferences(
						Globals.getHwbPackageName(), Context.MODE_PRIVATE);
				pref.edit()
						.putString(Globals.getUpdateDateKey(),
								currentQuiz.getBroadcast_dt()).commit();
			}
		});

		thread.start();

	}

	private void addClickListeneters() {
		playbutton.setOnClickListener(this);
		vocabicon.setOnClickListener(this);
		infoicon.setOnClickListener(this);

	}

	/**
	 * Removes the Dialog that displays the splash screen
	 */
	protected void removeSplashScreen() {
		if (mSplashDialog != null) {
			mSplashDialog.dismiss();
			mSplashDialog = null;
		}
	}

	/**
	 * Shows the splash screen over the full Activity
	 */
	protected void showSplashScreen() {
		mSplashDialog = new Dialog(this, R.style.SplashScreen);
		mSplashDialog.setContentView(R.layout.splashscreen);
		mSplashDialog.setCancelable(false);
		mSplashDialog.show();

		// Set Runnable to remove splash screen just in case
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				updateQuestions();
				removeSplashScreen();
			}
		}, 4000);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.vocabicon:
			Intent i = new Intent(this, VocabActivity.class);
			startActivity(i);
			break;
		case R.id.infoicon:
			Intent i2 = new Intent(this, InfoActivity.class);
			startActivity(i2);
			break;
		case R.id.playbutton:
			if (quizIsValid) {
				Intent i3 = new Intent(this, ChooseTimeFormatActivity.class);
				startActivity(i3);
			} else {
				Toast.makeText(this, "Invalid quiz", Toast.LENGTH_LONG).show();
			}

			break;
		default:
			break;
		}
	}
}
